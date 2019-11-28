package com.nashe.siscanino.utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import timber.log.Timber;

@SuppressLint({"SimpleDateFormat", "SetTextI18n", "BinaryOperationInTimber"})
@SuppressWarnings("unused")
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static final String TAG = "DatePickerDialogFragment";

    private View v;
    private String prefix;

    public TimePickerFragment(View v, String prefix) {
        this.v = v;
        this.prefix = prefix;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        return new TimePickerDialog(v.getContext(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), i, i1, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = sdf.format(c.getTime());
        Timber.d(formattedDate);

        if (v instanceof MaterialButton) ((MaterialButton) v).setText(prefix + formattedDate);
    }
}
