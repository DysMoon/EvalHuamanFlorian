package pe.edu.unc.evalhuamanflorian;

import android.content.Intent;
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

import android.widget.Toast;

import java.util.Random;


public class ActividadPregunta31 extends AppCompatActivity {

    private TextView edtPalabra, txtIntR;
    private EditText txtPal;
    private Button btnProbar, btnJugarnuevo;
    private String palabraSeleccionada, palabraOculta;
    private int intentosRestantes;
    private ArrayList<String> listaPalabras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_pregunta31);

        edtPalabra = findViewById(R.id.edtPalabra);
        txtIntR = findViewById(R.id.txtIntR);
        txtPal = findViewById(R.id.txtPal);
        btnProbar = findViewById(R.id.btnProbar);
        btnJugarnuevo = findViewById(R.id.btnJugarnuevo);

        Intent intent = getIntent();
        listaPalabras = intent.getStringArrayListExtra("words"); // Lista de palabras
        intentosRestantes = intent.getIntExtra("maxAttempts", 5); // Número máximo de intentos

        reiniciarJuego();

        btnProbar.setOnClickListener(v -> procesarIntento());
        btnJugarnuevo.setOnClickListener(v -> reiniciarJuego());
    }

    private void reiniciarJuego() {
        ArrayList<String> palabras = getIntent().getStringArrayListExtra("words");
        int maximo = getIntent().getIntExtra("maxAttempts", 5);

        if (palabras == null || palabras.isEmpty()) {
            Toast.makeText(this, "No hay palabras disponibles", Toast.LENGTH_SHORT).show();
            return;
        }

        Random random = new Random();
        palabraSeleccionada = palabras.get(random.nextInt(palabras.size()));

        palabraOculta = ocultarLetras(palabraSeleccionada);

        intentosRestantes = maximo;

        txtPal.setText("");
        txtIntR.setText("Intentos restantes: " + intentosRestantes);
        edtPalabra.setText(palabraOculta);
    }


    private String ocultarLetras(String palabra) {
        char[] letrasOcultas = palabra.toCharArray();
        Random random = new Random();
        int ocultarCantidad = (int) (palabra.length() * 0.6);
        for (int i = 0; i < ocultarCantidad; i++) {
            int indice;
            do {
                indice = random.nextInt(palabra.length());
            } while (letrasOcultas[indice] == '_');
            letrasOcultas[indice] = '_';
        }
        return new String(letrasOcultas);
    }
    private void procesarIntento() {
        if (intentosRestantes <= 0) {
            Toast.makeText(this, "¡Juego terminado! Reinicia para jugar de nuevo.", Toast.LENGTH_SHORT).show();
            return;
        }

        String intento = txtPal.getText().toString().trim();

        if (intento.isEmpty()) {
            Toast.makeText(this, "Ingrese una letra o palabra", Toast.LENGTH_SHORT).show();
            return;
        }

        if (intento.length() == 1) {
            if (palabraSeleccionada.contains(intento)) {
                descubrirLetra(intento.charAt(0));
            } else {
                intentosRestantes--;
            }
        } else if (intento.equals(palabraSeleccionada)) {
            Toast.makeText(this, "¡Felicidades! Has adivinaste!!!!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            intentosRestantes--;
        }

        if (intentosRestantes <= 0) {
            Toast.makeText(this, "Perdiste - La palabra era: " + palabraSeleccionada, Toast.LENGTH_LONG).show();
            intentosRestantes = 0;
        }

        txtIntR.setText("Intentos restantes: " + intentosRestantes);
    }


    private void descubrirLetra(char letra) {
        char[] letrasActuales = palabraOculta.toCharArray();
        for (int i = 0; i < palabraSeleccionada.length(); i++) {
            if (palabraSeleccionada.charAt(i) == letra) {
                letrasActuales[i] = letra;
            }
        }
        palabraOculta = new String(letrasActuales);
        edtPalabra.setText(palabraOculta);

        if (!palabraOculta.contains("_")) {
            Toast.makeText(this, "!You win!", Toast.LENGTH_SHORT).show();
        }
    }
}










