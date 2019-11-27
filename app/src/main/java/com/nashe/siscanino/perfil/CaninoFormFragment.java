package com.nashe.siscanino.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.CaninoDao;
import com.nashe.siscanino.data.dao.RazaDao;
import com.nashe.siscanino.data.dao.UsuarioCaninoDao;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.Raza;
import com.nashe.siscanino.data.entity.UsuarioCanino;
import com.nashe.siscanino.utils.DateOperation;
import com.nashe.siscanino.utils.ViewHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

@SuppressLint({"SimpleDateFormat", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal", "NullPointerException"})
public class CaninoFormFragment extends BaseFragment {

    // Por parametro
    private static final String ARG_USUARIO = "usuario_id";
    private int paramUsuario;
    private static final String ARG_CANINO = "canino_id";
    private int paramCanino;

    // Base de datos
    private CaninoDao caninoDAO;
    private UsuarioCaninoDao usuarioCaninoDAO;
    private RazaDao razaDAO;

    // Views
    private TextInputEditText txtNombre;
    private MaterialButton btnFecha;
    private TextInputEditText txtColor;
    private Spinner spinnerSexo;
    private TextInputEditText txtSenias;
    private TextInputEditText txtPeso;
    private Spinner spinnerTamanio;
    private MaterialButton btnAccion;
    private Spinner spinnerRaza;
    private MaterialToolbar toolbar;

    // Auxiliares
    private ArrayAdapter arrayAdapter;
    private CustomSpinnerAdapter customSpinnerAdapter;
    private List<Raza> listaRaza;
    private Canino caninoActualizar;

    public CaninoFormFragment() { /* Requiere un constructor vacio */ }

    public static CaninoFormFragment newInstance(int canino, int usuario) {
        CaninoFormFragment fragment = new CaninoFormFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CANINO, canino);
        args.putInt(ARG_USUARIO, usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramUsuario = getArguments().getInt(ARG_USUARIO);
            paramCanino = getArguments().getInt(ARG_CANINO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canino_form, container, false);

        // Configuracion de la BD
        caninoDAO = database.caninoDao();
        usuarioCaninoDAO = database.usuarioCaninoDao();
        razaDAO = database.razaDao();
        listaRaza = razaDAO.get();

        // Configuracion del menu
        toolbar = ViewHandler.configToolbarBotonAtras(activity, view);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

        // Ocultando la navegacion de abajo
        ViewHandler.ocultarBottomNavigation(activity);

        // Configuracion de los otros views
        spinnerRaza = view.findViewById(R.id.spinner_raza);
        spinnerSexo = view.findViewById(R.id.spinner_sexo);
        spinnerTamanio = view.findViewById(R.id.spinner_tamanio);
        txtNombre = view.findViewById(R.id.txtCanino_nombre);
        txtColor = view.findViewById(R.id.txtCanino_color);
        txtSenias = view.findViewById(R.id.txtCanino_senias);
        txtPeso = view.findViewById(R.id.txtCanino_peso);
        btnFecha = view.findViewById(R.id.btnCanino_fecha);
        btnAccion = view.findViewById(R.id.btnCanino_accion);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePickerDialog(activity, btnFecha, "Fecha: ");
            }
        });

        arrayAdapter = new ArrayAdapter<>(activity.getBaseContext(), R.layout.support_simple_spinner_dropdown_item, Constantes.SEXO_LISTA);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<>(activity.getBaseContext(), R.layout.support_simple_spinner_dropdown_item, Constantes.TAMANIO_LISTA);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTamanio.setAdapter(arrayAdapter);

        if (listaRaza.size() != 0) {
            int[] ids = new int[listaRaza.size()];
            String[] nombres = new String[listaRaza.size()];
            for (int iterador = 0; iterador < listaRaza.size(); iterador++) {
                ids[iterador] = listaRaza.get(iterador).getId();
                nombres[iterador] = listaRaza.get(iterador).getNombre();
            }
            customSpinnerAdapter = new CustomSpinnerAdapter(activity.getBaseContext(), ids, nombres);
        } else {
            customSpinnerAdapter = new CustomSpinnerAdapter(activity.getBaseContext(), new int[]{0}, new String[]{"Desconocida"});
        }

        spinnerRaza.setAdapter(customSpinnerAdapter);

        // Si lo envia para editar
        if (paramCanino != -1) {
            btnAccion.setText("Actualizar");

            caninoActualizar = caninoDAO.getById(paramCanino);
            txtNombre.setText(caninoActualizar.getNombre());
            txtColor.setText(caninoActualizar.getColor());
            txtPeso.setText(caninoActualizar.getPeso() + "");
            txtSenias.setText(caninoActualizar.getSenias());
            btnFecha.setText("Fecha: " + DateOperation.formatted(caninoActualizar.getNacimiento().getTime()));
            int auxTamanio = 0;
            for (int index = 0; index < Constantes.TAMANIO_LISTA.length; index++) {
                if (Constantes.TAMANIO_LISTA[index].equals(caninoActualizar.getTamanio())) {
                    auxTamanio = index;
                }
            }
            spinnerTamanio.setSelection(auxTamanio);
            int auxRaza = 0;
            for (int index = 0; index < listaRaza.size(); index++) {
                if (listaRaza.get(index).getId() == caninoActualizar.getRazaId()) {
                    auxRaza = index;
                }
            }
            spinnerRaza.setSelection(auxRaza);
            spinnerSexo.setSelection(caninoActualizar.getSexo());
        }

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNombre.getText().toString().trim().equals("")
                        || btnFecha.getText().toString().trim().equals("Fecha:")
                        || txtColor.getText().toString().trim().equals("")
                        || txtPeso.getText().toString().trim().equals("")
                        || txtSenias.getText().toString().trim().equals("")) {
                    Timber.i("Aun faltan datos");
                    return;
                }

                String fecha = btnFecha.getText().subSequence(7, btnFecha.getText().length()).toString();

                String resultado = "\nNombre: " + txtNombre.getText()
                        + "\nFecha: " + fecha
                        + "\nColor: " + txtColor.getText()
                        + "\nPeso: " + txtPeso.getText()
                        + "\nSeñas: " + txtSenias.getText()
                        + "\nRaza: " + spinnerRaza.getSelectedItem() + " - " + spinnerRaza.getSelectedItemId()
                        + "\nSexo: " + spinnerSexo.getSelectedItem()
                        + "\nTamaño: " + spinnerTamanio.getSelectedItem();

                Timber.i(resultado);

                String[] fechaSplit = fecha.split("\\/");
                Calendar calendario = Calendar.getInstance();
                calendario.set(Integer.valueOf(fechaSplit[2]), Integer.valueOf(fechaSplit[1])-1, Integer.valueOf(fechaSplit[0]));

                if (paramCanino != -1) { // Actualizar
                    caninoActualizar = new Canino(paramCanino,
                            txtNombre.getText().toString(),
                            new Date(calendario.getTime().getTime()),
                            txtColor.getText().toString(),
                            spinnerSexo.getSelectedItemPosition(),
                            txtSenias.getText().toString(),
                            Double.valueOf(txtPeso.getText().toString()),
                            (String) spinnerTamanio.getSelectedItem(),
                            (int) spinnerRaza.getSelectedItemId());
                    long caninoo_id = caninoDAO.update(caninoActualizar);
                    Timber.i("Canino actualizado: " + caninoo_id);
                } else { // Agregar
                    Canino caninoNuevo = new Canino(txtNombre.getText().toString(),
                            new Date(calendario.getTime().getTime()),
                            txtColor.getText().toString(),
                            spinnerSexo.getSelectedItemPosition(),
                            txtSenias.getText().toString(),
                            Double.valueOf(txtPeso.getText().toString()),
                            (String) spinnerTamanio.getSelectedItem(),
                            (int) spinnerRaza.getSelectedItemId());
                    long caninoo_id = caninoDAO.insert(caninoNuevo);
                    usuarioCaninoDAO.insert(new UsuarioCanino(paramUsuario, (int) caninoo_id));
                }

                ((PerfilFragment) getFragmentManager().findFragmentByTag(Constantes.PERFIL_FRAGMENT)).actualizarLista();
                activity.onBackPressed();
            }
        });

        return view;
    }
}

