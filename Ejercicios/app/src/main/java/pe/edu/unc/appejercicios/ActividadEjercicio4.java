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

public class ActividadEjercicio4 extends AppCompatActivity {

    EditText edtX;
    Button btnCalcular;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_ejercicio4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtX = findViewById(R.id.edtX);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular.setOnClickListener(v -> calcularAreaMaxima());
    }

    private void calcularAreaMaxima() {
        String valorX = edtX.getText().toString();

        if (valorX.isEmpty()) {
            edtX.setError("Ingrese un valor para X");
            return;
        }

        double x = Double.parseDouble(valorX);
        double area = x * (20 - x);

        txtResultado.setText("Área máxima: " + area);
    }
}