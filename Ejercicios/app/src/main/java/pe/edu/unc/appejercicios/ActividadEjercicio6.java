package pe.edu.unc.appejercicios;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;

public class ActividadEjercicio6 extends AppCompatActivity {

    Button btnCandidato1, btnCandidato2, btnCandidato3, btnResultado;
    TextView txtGanador, txtResultados;

    int votosCandidato1 = 0;
    int votosCandidato2 = 0;
    int votosCandidato3 = 0;
    boolean votoRealizado = false;
    boolean resultadosMostrados = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_ejercicio6);

        btnCandidato1 = findViewById(R.id.btnCandidato1);
        btnCandidato2 = findViewById(R.id.btnCandidato2);
        btnCandidato3 = findViewById(R.id.btnCandidato3);
        btnResultado = findViewById(R.id.btnResultado);
        txtGanador = findViewById(R.id.txtGanador);
        txtResultados = findViewById(R.id.txtResultados);

        generarVotosAleatorios();

        btnCandidato1.setOnClickListener(v -> votar(1));
        btnCandidato2.setOnClickListener(v -> votar(2));
        btnCandidato3.setOnClickListener(v -> votar(3));
        btnResultado.setOnClickListener(v -> mostrarResultados());
    }

    private void generarVotosAleatorios() {
        Random random = new Random();
        for (int i = 0; i < 159; i++) {
            int voto = random.nextInt(3);
            if (voto == 0) votosCandidato1++;
            else if (voto == 1) votosCandidato2++;
            else votosCandidato3++;
        }
    }

    private void votar(int candidato) {
        if (votoRealizado) {
            txtResultados.setText("¡Ya emitiste tu voto!");
            return;
        }

        if (candidato == 1) votosCandidato1++;
        else if (candidato == 2) votosCandidato2++;
        else votosCandidato3++;

        votoRealizado = true;
        txtResultados.setText("Voto registrado. Ahora presiona 'Mostrar Resultados'.");
    }

    private void mostrarResultados() {
        if (!votoRealizado) {
            txtResultados.setText("Debes votar antes de ver los resultados.");
            return;
        }

        if (resultadosMostrados) {
            txtResultados.setText("Los resultados ya fueron mostrados.");
            return;
        }

        String resultados = "Resultados:\n" +
                "Candidato 1: " + votosCandidato1 + " votos\n" +
                "Candidato 2: " + votosCandidato2 + " votos\n" +
                "Candidato 3: " + votosCandidato3 + " votos\n";

        String ganador = determinarGanador();

        txtResultados.setText(resultados);
        txtGanador.setText(ganador);
        resultadosMostrados = true;
    }

    private String determinarGanador() {
        if (votosCandidato1 > votosCandidato2 && votosCandidato1 > votosCandidato3) {
            return "Ganador: Candidato 1 🎉";
        } else if (votosCandidato2 > votosCandidato1 && votosCandidato2 > votosCandidato3) {
            return "Ganador: Candidato 2 🎉";
        } else if (votosCandidato3 > votosCandidato1 && votosCandidato3 > votosCandidato2) {
            return "Ganador: Candidato 3 🎉";
        } else {
            return "¡Empate! Se requiere segunda vuelta.";
        }
    }
}
