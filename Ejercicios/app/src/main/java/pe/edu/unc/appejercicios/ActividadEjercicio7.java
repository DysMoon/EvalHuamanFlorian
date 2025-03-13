package pe.edu.unc.appejercicios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class ActividadEjercicio7 extends AppCompatActivity {

    EditText etNombre, etMonto;
    RadioButton rbManana, rbTarde, rbNoche;
    CheckBox cbPython, cbJava, cbCSharp;
    Button btnRegistrar, btnListado, btnCerrar;
    List<String> estudiantes = new ArrayList<>();
    int totalRecaudado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_ejercicio7);

        etNombre = findViewById(R.id.etNombre);
        etMonto = findViewById(R.id.etMonto);
        rbManana = findViewById(R.id.rbManana);
        rbTarde = findViewById(R.id.rbTarde);
        rbNoche = findViewById(R.id.rbNoche);
        cbPython = findViewById(R.id.cbPython);
        cbJava = findViewById(R.id.cbJava);
        cbCSharp = findViewById(R.id.cbCSharp);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnListado = findViewById(R.id.btnListado);
        btnCerrar = findViewById(R.id.btnCerrar);

        btnRegistrar.setOnClickListener(v -> registrarEstudiante());
        btnListado.setOnClickListener(v -> verListado());
        btnCerrar.setOnClickListener(v -> finish());
    }

    private void registrarEstudiante() {
        String nombre = etNombre.getText().toString().trim();
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingrese el nombre", Toast.LENGTH_SHORT).show();
            return;
        }

        String turno = rbManana.isChecked() ? "Mañana" : rbTarde.isChecked() ? "Tarde" : "Noche";

        int costoTotal = 0;

        StringBuilder cursos = new StringBuilder();
        if (cbPython.isChecked()) {
            costoTotal += (rbManana.isChecked() ? 200 : rbTarde.isChecked() ? 250 : 300);
            cursos.append("Python, ");
        }
        if (cbJava.isChecked()) {
            costoTotal += (rbManana.isChecked() ? 300 : rbTarde.isChecked() ? 350 : 400);
            cursos.append("Java, ");
        }
        if (cbCSharp.isChecked()) {
            costoTotal += (rbManana.isChecked() ? 500 : rbTarde.isChecked() ? 550 : 600);
            cursos.append("C#, ");
        }

        if (costoTotal == 0) {
            Toast.makeText(this, "Seleccione al menos un curso", Toast.LENGTH_SHORT).show();
            return;
        }

        totalRecaudado += costoTotal;
        String detalle = nombre + " matriculado en " + cursos.substring(0, cursos.length() - 2) + " turno " + turno + " (S/. " + costoTotal + ")";
        estudiantes.add(detalle);

        etMonto.setText(String.valueOf(costoTotal));

        etNombre.setText("");
        rbManana.setChecked(true);
        cbPython.setChecked(false);
        cbJava.setChecked(false);
        cbCSharp.setChecked(false);
    }

    private void verListado() {
        Intent intent = new Intent(this, ActividadListado1.class);
        intent.putStringArrayListExtra("listaMatriculados", new ArrayList<>(estudiantes));
        intent.putExtra("totalRecaudado", totalRecaudado);
        startActivity(intent);
    }
}