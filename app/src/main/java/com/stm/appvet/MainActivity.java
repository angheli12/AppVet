package com.stm.appvet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private final ActivityResultLauncher<Intent> launcherFormulario =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            String mensaje =
                                    result.getData().getStringExtra("RESPUESTA");

                            Toast.makeText(
                                    this,
                                    mensaje,
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
            );


    private final ActivityResultLauncher<Intent> launcherConfirmacion =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getData() != null) {
                            String mensaje =
                                    result.getData()
                                            .getStringExtra("MENSAJE_RESULTADO");

                            if (mensaje != null) {
                                Toast.makeText(
                                        this,
                                        mensaje,
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDetalle = findViewById(R.id.btnDetalle);
        Button btnForm = findViewById(R.id.btnForm);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        Button btnMapa = findViewById(R.id.btnMapa);
        Button btnLlamar = findViewById(R.id.btnLlamar);
        Button btnCorreo = findViewById(R.id.btnCorreo);
        Button btnFoto = findViewById(R.id.btnFoto);
        Button btnCalendario = findViewById(R.id.btnCalendario);



        btnDetalle.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetalleMascotaActivity.class);
            intent.putExtra("NOMBRE_MASCOTA", "Firulais");
            intent.putExtra("ESPECIE", "Canino - Ovejero");
            intent.putExtra("DIAGNOSTICO", "Control preventivo y vacuna antirrábica.");
            startActivity(intent);
        });



        btnForm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            launcherFormulario.launch(intent);
        });



        btnConfirmar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConfirmacionCitaActivity.class);
            launcherConfirmacion.launch(intent);
        });



        btnMapa.setOnClickListener(v -> {
            Uri ubicacion = Uri.parse("geo:-33.4489,-70.6693?q=Clinica+Veterinaria+Santiago");
            Intent intent = new Intent(Intent.ACTION_VIEW, ubicacion);
            startActivity(intent);
        });



        btnLlamar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+56972386910"));
            startActivity(intent);
        });


        btnCorreo.setOnClickListener(v -> {

            android.view.View dialogView = getLayoutInflater().inflate(R.layout.dialog_correo, null);
            final EditText inputCorreo = dialogView.findViewById(R.id.inputCorreoDestino);
            final EditText inputMensaje = dialogView.findViewById(R.id.inputMensajeCorreo);

            new AlertDialog.Builder(this)
                    .setTitle("Enviar Correo de Consulta")
                    .setView(dialogView)
                    .setPositiveButton("Enviar", (dialog, which) -> {
                        String para = inputCorreo.getText().toString().trim();
                        String mensaje = inputMensaje.getText().toString().trim();

                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:" + (para.isEmpty() ? "" : para)));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta Veterinaria AppVet");
                        intent.putExtra(Intent.EXTRA_TEXT, mensaje.isEmpty() ? "Hola, necesito consultar sobre mi mascota..." : mensaje);

                        try {
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(this, "No hay aplicaciones de correo instaladas.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });



        btnFoto.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });



        btnCalendario.setOnClickListener(v -> {
            Calendar startTime = Calendar.getInstance();
            startTime.add(Calendar.DAY_OF_MONTH, 1);
            startTime.set(Calendar.HOUR_OF_DAY, 10);
            startTime.set(Calendar.MINUTE, 0);

            Calendar endTime = Calendar.getInstance();
            endTime.add(Calendar.DAY_OF_MONTH, 1);
            endTime.set(Calendar.HOUR_OF_DAY, 11);
            endTime.set(Calendar.MINUTE, 0);

            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, "Control / Vacuna Mascota - AppVet")
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, "Clínica Veterinaria Central")
                    .putExtra(CalendarContract.Events.DESCRIPTION, "Cita agendada desde AppVet.")
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());

            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "No se pudo abrir el calendario.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}