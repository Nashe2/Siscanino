package com.nashe.siscanino.actividad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.ActividadDao;
import com.nashe.siscanino.data.dao.CaninoActividadDao;
import com.nashe.siscanino.data.entity.Actividad;
import com.nashe.siscanino.data.entity.CaninoActividad;
import com.nashe.siscanino.utils.CustomSpinnerAdapter;
import com.nashe.siscanino.utils.DateOperation;
import com.nashe.siscanino.utils.SharedPreferencesPersonalizados;
import com.nashe.siscanino.utils.ViewHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

@SuppressLint({"SimpleDateFormat", "BinaryOperationInTimber", "SetTextI18n"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal", "NullPointerException", "ConstantConditions"})
public class ActividadFormFragment extends BaseFragment
        implements BaseFragment.Formulario {

    // Por parametro
    private static final String ARG_CANINO_ACTIVIDAD = "caninoActividad_id";
    private int param;

    // Base de datos
    private CaninoActividadDao dao;
    private ActividadDao daoDerecha;

    // Views
    private Spinner spinner;
    private TextInputEditText txtNombre;
    private MaterialButton btnFecha;
    private MaterialButton btnHora;
    private MaterialButton btnAccion;
    private MaterialToolbar toolbar;

    // Auxiliares
    private int canino_id;
    private CustomSpinnerAdapter customSpinnerAdapter;
    private List<Actividad> listaDerecha;
    private int[] ids;
    private String[] nombres;
    private CaninoActividad actualizar;

    public ActividadFormFragment() { /* Requiere un constructor vacio */ }

    public static ActividadFormFragment newInstance(int actividad) {
        ActividadFormFragment fragment = new ActividadFormFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CANINO_ACTIVIDAD, actividad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param = getArguments().getInt(ARG_CANINO_ACTIVIDAD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actividad_form, container, false);

        canino_id = SharedPreferencesPersonalizados.obtenerCaninoSeleccionado(activity.getBaseContext());

        // Configuracion de la BD y auxiliares
        daoDerecha = database.actividadDao();
        dao = database.caninoActividadDao();
        listaDerecha = daoDerecha.get();

        if (listaDerecha.size() != 0) {
            ids = new int[listaDerecha.size()];
            nombres = new String[listaDerecha.size()];
            for (int iterador = 0; iterador < listaDerecha.size(); iterador++) {
                ids[iterador] = listaDerecha.get(iterador).getId();
                nombres[iterador] = listaDerecha.get(iterador).getTipo();
            }
        } else {
            Toast.makeText(activity.getBaseContext(), "Es necesario contar con listaDerecha activos, comuniquese con el servicio técnico", Toast.LENGTH_LONG).show();
            activity.onBackPressed();
        }

        // Configuracion extra
        toolbar = ViewHandler.configToolbarBotonAtras(activity, view);
        ViewHandler.ocultarBottomNavigation(activity);

        // Views
        spinner = view.findViewById(R.id.spinner_actividad);
        txtNombre = view.findViewById(R.id.txtActividades_nombre);
        btnHora = view.findViewById(R.id.btnActividades_hora);
        btnFecha = view.findViewById(R.id.btnActividades_fecha);
        btnAccion = view.findViewById(R.id.btnActivades_accion);

        // Comportamientos
        customSpinnerAdapter = new CustomSpinnerAdapter(activity.getBaseContext(), ids, nombres);
        spinner.setAdapter(customSpinnerAdapter);

        cargarCampos(param);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePickerDialog(activity, btnFecha, "Fecha: ");
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePickerDialog(activity, btnHora, "Hora: ");
            }
        });


        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checarCampos()) {
                    toast(getString(R.string.checar_formulario));
                    return;
                }

                imprimirResultado();
                String[] fecha = ViewHandler.obtenerFecha(btnFecha, 7);
                String[] hora = ViewHandler.obtenerHora(btnHora, 6);
                Calendar calendario = Calendar.getInstance();
                calendario.set(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]) - 1, Integer.valueOf(fecha[0]),
                        Integer.valueOf(hora[0]), Integer.valueOf(hora[1]), Integer.valueOf(hora[2]));

                if (param != -1) {
                    actualizar = dao.getById(param);
                    actualizar.setNombre(txtNombre.getText().toString());
                    actualizar.setFechaHora(new Date(calendario.getTime().getTime()));
                    actualizar.setActividadId((int) spinner.getSelectedItemId());
                    long actualizado = dao.update(actualizar);
                    Timber.i("ID Actualizado: " + actualizado);
                } else {
                    long registro = dao.insert(
                            new CaninoActividad(canino_id,
                                    (int) spinner.getSelectedItemId(),
                                    txtNombre.getText().toString(),
                                    new Date(calendario.getTime().getTime()))
                    );
                    Timber.i("ID Nuevo: " + registro);
                }

                ((ActividadFragment) activity.getSupportFragmentManager()
                        .findFragmentByTag(Constantes.ACTIVIDAD_FRAGMENT))
                        .actualizarLista(canino_id);

                activity.onBackPressed();
            }
        });

        return view;
    }

    @Override
    public boolean checarCampos() {
        return txtNombre.getText().toString().trim().equals("")
                || btnFecha.getText().toString().trim().equals("Fecha:")
                || btnHora.getText().toString().trim().equals("Hora:");
    }

    @Override
    public void imprimirResultado() {
        Timber.d("Registro: "
                + "\nNombre: " + txtNombre.getText()
                + "\nFecha: " + btnFecha.getText().toString()
                + "\nHora: " + btnHora.getText().toString()
                + "\nCanino: " + canino_id
                + "\nActividad: " + spinner.getSelectedItemId());
    }

    @Override
    public void cargarCampos(int idRegistro) {
        if (idRegistro == -1) return;
        btnAccion.setText("Actualizar");
        actualizar = dao.getById(param);
        txtNombre.setText(actualizar.getNombre());
        btnFecha.setText("Fecha: " + DateOperation.formatDate(actualizar.getFechaHora().getTime()));
        btnHora.setText("Hora: " + DateOperation.formatTime(actualizar.getFechaHora().getTime()));
        int auxCiclo = 0;
        for (int index = 0; index < listaDerecha.size(); index++) {
            if (listaDerecha.get(index).getId() == actualizar.getActividadId()) {
                auxCiclo = index;
                break;
            }
        }
        spinner.setSelection(auxCiclo);
    }
}
