package pe.edu.unc.appejercicios;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadEjercicio1 extends AppCompatActivity {
    //Un vehículo recorre n distancias en n tiempos diferentes ¿Cuál será su velocidad
    //promedio para toda su trayectoria?
    EditText edtDistancias, edtTiempos;
    Button btnCalcular;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_ejercicio1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtDistancias = findViewById(R.id.edtDistancias);
        edtTiempos = findViewById(R.id.edtTiempos);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular.setOnClickListener(v -> calcularYMostrarVelocidad());

    }
    public boolean validarCampos(){
        if(edtDistancias.getText().toString().isEmpty()){
            edtDistancias.setError("Ingrese las distancias");
            return true;
        }
        if(edtTiempos.getText().toString().isEmpty()){
            edtTiempos.setError("Ingrese los tiempos");
            return true;
        }
        return false;

    }


    private void calcularYMostrarVelocidad() {

        if(validarCampos())
            return;

        String[] distanciasStr = edtDistancias.getText().toString().split(",");
        String[] tiemposStr = edtTiempos.getText().toString().split(",");

        if (distanciasStr.length != tiemposStr.length) {
            txtResultado.setText("Las listas deben tener la misma cantidad de elementos.");
            return;
        }

        double[] distancias = new double[distanciasStr.length];
        double[] tiempos = new double[tiemposStr.length];

        try {
            for (int i = 0; i < distanciasStr.length; i++) {
                distancias[i] = Double.parseDouble(distanciasStr[i].trim());
                tiempos[i] = Double.parseDouble(tiemposStr[i].trim());
            }

            double velocidadPromedio = calcularVelocidadPromedio(distancias, tiempos);
            txtResultado.setText("Velocidad promedio: " + velocidadPromedio + " km/h");

        } catch (NumberFormatException e) {
            txtResultado.setText("Error: Ingrese solo números válidos.");
        }
    }

    @NonNull
    private double calcularVelocidadPromedio(@NonNull double[] distancias, double[] tiempos) {
        double sumaDistancias = 0;
        double sumaTiempos = 0;

        for (int i = 0; i < distancias.length; i++) {
            sumaDistancias += distancias[i];
            sumaTiempos += tiempos[i];
        }

        if (sumaTiempos == 0) {
            Toast.makeText(this, "No se puede calcular la velocidad con tiempo total igual a cero.", Toast.LENGTH_SHORT).show();
        }

        return sumaDistancias / sumaTiempos;
    }


}

