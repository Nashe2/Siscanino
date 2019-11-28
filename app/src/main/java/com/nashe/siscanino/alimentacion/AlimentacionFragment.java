package com.nashe.siscanino.alimentacion;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.AlimentacionDao;
import com.nashe.siscanino.data.dao.CaninoAlimentacionDao;
import com.nashe.siscanino.data.dao.CaninoDao;

@SuppressLint({"ClickableViewAccessibility", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class AlimentacionFragment extends BaseFragment {

    // Base de datos
    private CaninoDao caninoDao;
    private CaninoAlimentacionDao caninoAlimentacionDao;
    private AlimentacionDao alimentacionDao;

    // Views
    private MaterialButton btnTiempo;

    // Auxiliares
    private int canino_id;

    public AlimentacionFragment() { /* Requiere un constructor vacio */ }

    public static AlimentacionFragment newInstance() {
        AlimentacionFragment fragment = new AlimentacionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alimentacion, container, false);

        btnTiempo = view.findViewById(R.id.btnCanino_fecha);

        btnTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePickerDialog(activity, btnTiempo, "Tiempo: ");
            }
        });

        return view;
    }

}
