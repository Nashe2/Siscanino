package com.nashe.siscanino.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class SharedPreferenceHandler {

    public enum Type {STRING, INT, LONG, FLOAT, BOOLEAN}

    public static void set(Context context, String variable, Object value) throws IllegalArgumentException {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        if (value.getClass().getSimpleName().equals(String.class.getSimpleName())) {
            editor.putString(variable, value.toString()).apply();
        } else if (value.getClass().getSimpleName().equals(Integer.class.getSimpleName())) {
            editor.putInt(variable, (int) value).apply();
        } else if (value.getClass().getSimpleName().equals(Long.class.getSimpleName())) {
            editor.putLong(variable, (long) value).apply();
        } else if (value.getClass().getSimpleName().equals(Float.class.getSimpleName())) {
            editor.putFloat(variable, (float) value).apply();
        } else if (value.getClass().getSimpleName().equals(Boolean.class.getSimpleName())) {
            editor.putBoolean(variable, (boolean) value).apply();
        } else {
            throw new IllegalArgumentException("The type " + value.getClass().getSimpleName() + " isn't support it to SharedPreferences");
        }
    }

    @Nullable
    public static Object get(Context context, String variable, Type type) throws IllegalArgumentException {
        Object value;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!sharedPreferences.getAll().containsKey(variable)) return null;

        switch (type) {
            case STRING:
                value = sharedPreferences.getString(variable, "");
                break;
            case INT:
                value = sharedPreferences.getInt(variable, -1);
                break;
            case LONG:
                value = sharedPreferences.getLong(variable, -1L);
                break;
            case FLOAT:
                value = sharedPreferences.getFloat(variable, -1F);
                break;
            case BOOLEAN:
                value = sharedPreferences.getBoolean(variable, false);
                break;

            default:
                throw new IllegalArgumentException("The type " + type.name() + " isn't support it to SharedPreferences");
        }

        return value;
    }

    public static boolean delete(Context context, String variable) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!sharedPreferences.getAll().containsKey(variable)) return false;
        return sharedPreferences.edit().remove(variable).commit();
    }
}