package pe.edu.unc.evalhuamanflorian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActividadPregunta3 extends AppCompatActivity {


    private EditText edtEntra, edtIntento;
    private Button btnJugar;
    private ArrayList<String> palabras;
    private int maximo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_pregunta3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEntra = findViewById(R.id.edtEntra);
        edtIntento = findViewById(R.id.edtIntento);
        btnJugar = findViewById(R.id.btnJugar);
        palabras = new ArrayList<>();

        btnJugar.setOnClickListener(v -> {
            String[] wordArray = edtEntra.getText().toString().split(",");
            for (String word : wordArray) {
                palabras.add(word.trim());
            }

            try {
                maximo = Integer.parseInt(edtIntento.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ingrese un número válido de intentos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (palabras.isEmpty() || maximo <= 0) {
                Toast.makeText(this, "Ingrese palabras e intentos válidos", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ActividadPregunta31.class);
            intent.putStringArrayListExtra("words", palabras);
            intent.putExtra("maxAttempts", maximo);
            startActivity(intent);
        });
    }

}



