package com.nashe.siscanino;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseActivity extends AppCompatActivity {

    protected InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View viewActual = getCurrentFocus();
            if (viewActual instanceof TextInputEditText) {
                Rect bordeViewActual = new Rect();
                viewActual.getGlobalVisibleRect(bordeViewActual);
                if (!bordeViewActual.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    inputMethodManager.hideSoftInputFromWindow(viewActual.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
