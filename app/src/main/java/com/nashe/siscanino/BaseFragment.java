package com.nashe.siscanino;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nashe.siscanino.data.DatabaseRoom;
import com.nashe.siscanino.utils.DatePickerFragment;
import com.nashe.siscanino.utils.TimePickerFragment;

import java.util.List;

import timber.log.Timber;

@SuppressLint("BinaryOperationInTimber")
@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseFragment extends Fragment {

    protected OnFragmentInteractionListener mListener;
    protected AppCompatActivity activity;
    protected DatabaseRoom database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        database = activity != null ? DatabaseRoom.getInstance(activity.getBaseContext()) : null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void cambiarFragment(String fragment);

        void cambiarActivity(String activity);
    }

    protected boolean eliminar(FragmentManager manager, String tag) {
        Fragment fragment = manager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (fragment == null) return false;
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
        return true;
    }

    protected void back(FragmentManager manager, String tag) {
        manager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    protected void toast(String mensaje){
        Toast.makeText(activity.getBaseContext(),mensaje,Toast.LENGTH_SHORT).show();
    }

    public static void cargar(FragmentManager manager, Fragment fragment, String tag) {
        List<Fragment> fragments = manager.getFragments();
        FragmentTransaction transaccion = manager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        Timber.d("Pila: " + manager.getBackStackEntryCount());
        Timber.d("Fragments: " + fragments.size());

        for (Fragment item : fragments) {
            if (!item.isHidden()) {
                transaccion.hide(item);
                Timber.d("Escondiendo: " + item.getTag());
            }
        }

        if (manager.findFragmentByTag(tag) == null) {
            transaccion.add(R.id.frame_contenedor, fragment, tag); // Poner el mismo el ID  del FrameLayout para manejar toddos los Fragments
            transaccion.addToBackStack(tag);
            Timber.d("Agregando: " + fragment.getTag());
        } else {
            Fragment actual = manager.findFragmentByTag(tag);
            if (actual != null && actual.isHidden()) {
                transaccion.show(actual);
                Timber.d("Mostrando: " + actual.getTag());
            }
        }
        transaccion.commit();
    }

    protected void mostrarDatePickerDialog(AppCompatActivity activity, View view, String prefix) {
        FragmentManager manager = activity.getSupportFragmentManager();
        DialogFragment dialogFragment = new DatePickerFragment(view, prefix);
        dialogFragment.show(manager, DatePickerFragment.TAG);
    }

    protected void mostrarTimePickerDialog(AppCompatActivity activity, View view, String prefix) {
        FragmentManager manager = activity.getSupportFragmentManager();
        DialogFragment dialogFragment = new TimePickerFragment(view, prefix);
        dialogFragment.show(manager, TimePickerFragment.TAG);
    }

    public interface Formulario {
        boolean checarCampos();

        void imprimirResultado();

        void cargarCampos(int idRegistro);
    }
}