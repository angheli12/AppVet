package com.stm.appvet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionCitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_cita);

        TextView txtResumenCita = findViewById(R.id.txtResumenCita);
        Button btnConfirmar = findViewById(R.id.btnConfirmarCita);
        Button btnCancelar = findViewById(R.id.btnCancelarCita);


        SharedPreferences preferencias = getSharedPreferences("datos_mascota", MODE_PRIVATE);
        String dueno = preferencias.getString("dueno", "");
        String mascota = preferencias.getString("mascota", "");


        if (!dueno.isEmpty() && !mascota.isEmpty()) {
            String detalleCompleto = "Mascota: " + mascota +
                    "\nDueño: " + dueno +
                    "\nMotivo: Control veterinario" +
                    "\nEstado: Pendiente de confirmación";
            txtResumenCita.setText(detalleCompleto);
        } else {
            txtResumenCita.setText("No hay pacientes registrados todavía. Por favor, registra una cita primero.");
        }


        btnConfirmar.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("MENSAJE_RESULTADO", "¡Cita agendada y confirmada exitosamente en la veterinaria!");
            setResult(RESULT_OK, returnIntent);
            finish();
        });


        btnCancelar.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("MENSAJE_RESULTADO", "La cita fue cancelada por el usuario.");
            setResult(RESULT_CANCELED, returnIntent);
            finish();
        });
    }
}