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


public class ActividadEjercicio3 extends AppCompatActivity {

    EditText edt1, edt2, edt3, edt4, edt5, edt6;
    Button btnValidar;
    TextView txtMensaje;
    int[] claveCorrecta = {1, 1, 1, 1, 1, 1}; // Clave predeterminada
    int intentos = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_ejercicio3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edt5 = findViewById(R.id.edt5);
        edt6 = findViewById(R.id.edt6);
        btnValidar = findViewById(R.id.btnValidar);
        txtMensaje = findViewById(R.id.txtMensaje);

        btnValidar.setOnClickListener(v -> validarClave());

    }

    private void validarClave() {
        int[] claveIngresada = {
                Integer.parseInt(edt1.getText().toString()),
                Integer.parseInt(edt2.getText().toString()),
                Integer.parseInt(edt3.getText().toString()),
                Integer.parseInt(edt4.getText().toString()),
                Integer.parseInt(edt5.getText().toString()),
                Integer.parseInt(edt6.getText().toString())
        };

        if (esClaveCorrecta(claveIngresada)) {
            txtMensaje.setText("¡Clave correcta! Bóveda desbloqueada.");
            btnValidar.setEnabled(false);
        } else {
            intentos--;
            if (intentos == 0) {
                txtMensaje.setText("Se agotaron los intentos. Reinicie.");
                btnValidar.setEnabled(false);
            } else {
                txtMensaje.setText("Clave incorrecta. Intentos restantes: " + intentos);
            }
        }
    }

    private boolean esClaveCorrecta(int[] claveIngresada) {
        for (int i = 0; i < claveCorrecta.length; i++) {
            if (claveIngresada[i] != claveCorrecta[i]) {
                return false;
            }
        }
        return true;
    }


}