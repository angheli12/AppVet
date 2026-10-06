package com.stm.appvet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    private EditText edtDueno, edtMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        edtDueno = findViewById(R.id.edtDueno);
        edtMascota = findViewById(R.id.edtMascota);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> validarYGuardar());
    }

    private void validarYGuardar() {

        String dueno = edtDueno.getText().toString().trim();
        String mascota = edtMascota.getText().toString().trim();


        if (dueno.isEmpty()) {
            edtDueno.setError("El nombre del dueño es obligatorio");
            edtDueno.requestFocus();
            return;
        }

        if (mascota.isEmpty()) {
            edtMascota.setError("El nombre de la mascota es obligatorio");
            edtMascota.requestFocus();
            return;
        }


        SharedPreferences preferencias =
                getSharedPreferences("datos_mascota", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();

        editor.putString("dueno", dueno);
        editor.putString("mascota", mascota);

        editor.apply();

        Toast.makeText(
                this,
                "Datos guardados correctamente",
                Toast.LENGTH_SHORT
        ).show();


        Intent returnIntent = new Intent();

        returnIntent.putExtra(
                "RESPUESTA",
                "¡Registro exitoso para " + mascota +
                        " (Dueño: " + dueno + ")!"
        );

        setResult(RESULT_OK, returnIntent);

        finish();
    }
}
