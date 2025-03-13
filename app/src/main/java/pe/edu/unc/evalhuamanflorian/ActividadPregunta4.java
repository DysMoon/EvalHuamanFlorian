package pe.edu.unc.evalhuamanflorian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ActividadPregunta4 extends AppCompatActivity {

    private int totalElectores = 160;
    private String[] candidatos = {"Candidato 1", "Candidato 2", "Candidato 3"};
    private String[] grados = {"Primero", "Segundo", "Tercero", "Cuarto", "Quinto"};
    private Map<String, Integer> votos;
    private Map<String, Map<String, Integer>> votosPorGrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_pregunta4);

        votos = new HashMap<>();
        votosPorGrado = new HashMap<>();

        for (String candidato : candidatos) {
            votos.put(candidato, 0);
        }
        votos.put("Blanco", 0);
        votos.put("Viciado", 0);

        for (String grado : grados) {
            Map<String, Integer> votosGrado = new HashMap<>();
            for (String candidato : candidatos) {
                votosGrado.put(candidato, 0);
            }
            votosGrado.put("Blanco", 0);
            votosGrado.put("Viciado", 0);
            votosPorGrado.put(grado, votosGrado);
        }

        simularVotacion();

        Button btnResultados = findViewById(R.id.btnIniciarVotacion);
        btnResultados.setEnabled(true);
        btnResultados.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActividadPregunta41.class);

            intent.putExtra("votos", (Serializable) new HashMap<>(votos));
            intent.putExtra("votosPorGrado", (Serializable) new HashMap<>(votosPorGrado));

            startActivity(intent);
        });
    }

    private void simularVotacion() {
        Random random = new Random();

        for (int i = 1; i <= totalElectores; i++) {
            String grado = grados[random.nextInt(grados.length)];
            int voto = random.nextInt(100);
            String opcion;

            if (voto < 70) {
                opcion = candidatos[random.nextInt(candidatos.length)];
            } else if (voto < 90) {
                opcion = "Blanco";
            } else {
                opcion = "Viciado";
            }

            votos.put(opcion, votos.get(opcion) + 1);
            votosPorGrado.get(grado).put(opcion, votosPorGrado.get(grado).get(opcion) + 1);
        }
    }
}
