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

public class ActividadEjercicio5 extends AppCompatActivity {

    EditText edtSaldo, edtRetiro;
    Button btnRetirar;
    TextView txtSaldoRestante;
    double saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_ejercicio5);

        edtSaldo = findViewById(R.id.edtSaldo);
        edtRetiro = findViewById(R.id.edtRetiro);
        btnRetirar = findViewById(R.id.btnRetirar);
        txtSaldoRestante = findViewById(R.id.txtSaldoRestante);

        btnRetirar.setOnClickListener(v -> realizarRetiro());
    }

    private void realizarRetiro() {
        if (edtSaldo.getText().toString().isEmpty()) {
            edtSaldo.setError("Ingrese el saldo inicial");
            return;
        }
        if (edtRetiro.getText().toString().isEmpty()) {
            edtRetiro.setError("Ingrese el monto a retirar");
            return;
        }

        if (saldo == 0) {
            saldo = Double.parseDouble(edtSaldo.getText().toString());
        }

        double montoRetiro = Double.parseDouble(edtRetiro.getText().toString());

        if (montoRetiro > saldo) {
            txtSaldoRestante.setText("Saldo insuficiente.");
        } else {
            saldo -= montoRetiro;
            txtSaldoRestante.setText("Saldo restante: " + saldo);

            if (saldo == 0) {
                txtSaldoRestante.setText("Saldo agotado.");
                btnRetirar.setEnabled(false);
            }
        }
    }
}
