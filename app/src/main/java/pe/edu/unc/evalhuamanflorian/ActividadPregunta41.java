package pe.edu.unc.evalhuamanflorian;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActividadPregunta41 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_pregunta41);


        ListView lvResultados = findViewById(R.id.lvResultado);


        HashMap<String, Integer> votos = (HashMap<String, Integer>) getIntent().getSerializableExtra("votos");

        if (votos != null) {
            List<String> resultados = new ArrayList<>();
            for (Map.Entry<String, Integer> voto : votos.entrySet()) {
                resultados.add(voto.getKey() + ": " + voto.getValue() + " votos");
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultados);
            lvResultados.setAdapter(adapter);
        }
    }
}
