package pe.edu.unc.appejercicios;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActividadEjercicio2 extends AppCompatActivity {

    //Cuatro piezas de un cargador frontal de mineral cuestan 300, 700, 1900 y 2300
    //dólares respectivamente, ¿Cuáles son las posibles cantidades que podemos adquirir
    //de cada pieza, si disponemos de 11300 dólares y deseamos gastartodo?

    EditText edtPresupuesto;
    Button btnCalcular;
    TextView txtResultado;

    int[] precios = {300, 700, 1900, 2300};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_ejercicio2);

        edtPresupuesto = findViewById(R.id.edtPresupuesto);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular.setOnClickListener(v -> calcularCombinaciones());
    }

    private void calcularCombinaciones() {
        String presupuestoStr = edtPresupuesto.getText().toString();
        if (presupuestoStr.isEmpty()) {
            edtPresupuesto.setError("Ingrese un presupuesto válido");
            return;
        }

        int presupuesto = Integer.parseInt(presupuestoStr);
        List<String> soluciones = new ArrayList<>();

        for (int x = 0; x <= presupuesto / precios[0]; x++) {
            for (int y = 0; y <= presupuesto / precios[1]; y++) {
                for (int z = 0; z <= presupuesto / precios[2]; z++) {
                    for (int w = 0; w <= presupuesto / precios[3]; w++) {
                        if (precios[0] * x + precios[1] * y + precios[2] * z + precios[3] * w == presupuesto) {
                            soluciones.add("(" + x + ", " + y + ", " + z + ", " + w + ")");
                        }
                    }
                }
            }
        }

        if (soluciones.isEmpty()) {
            txtResultado.setText("No hay combinaciones posibles.");
        } else {
            txtResultado.setText("Combinaciones:\n" + String.join("\n", soluciones));
        }
    }


}