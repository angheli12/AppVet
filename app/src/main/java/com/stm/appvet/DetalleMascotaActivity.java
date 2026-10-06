package com.stm.appvet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleMascotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        TextView txtNombre = findViewById(R.id.txtNombreMascota);
        TextView txtEspecie = findViewById(R.id.txtEspecieMascota);
        TextView txtDiagnostico = findViewById(R.id.txtDiagnosticoMascota);


        String nombre = getIntent().getStringExtra("NOMBRE_MASCOTA");
        String especie = getIntent().getStringExtra("ESPECIE");
        String diagnostico = getIntent().getStringExtra("DIAGNOSTICO");


        if (nombre == null || nombre.isEmpty()) {
            SharedPreferences preferencias = getSharedPreferences("datos_mascota", MODE_PRIVATE);
            nombre = preferencias.getString("mascota", "No registrada");
            String dueno = preferencias.getString("dueno", "Desconocido");
            especie = "Canino / Felino (Dueño: " + dueno + ")";
            diagnostico = "Control preventivo general";
        }


        if (especie == null || especie.isEmpty()) especie = "No especificada";
        if (diagnostico == null || diagnostico.isEmpty()) diagnostico = "Sin diagnóstico previo";


        txtNombre.setText("Mascota: " + nombre);
        txtEspecie.setText("Especie: " + especie);
        txtDiagnostico.setText("Diagnóstico: " + diagnostico);
    }
}