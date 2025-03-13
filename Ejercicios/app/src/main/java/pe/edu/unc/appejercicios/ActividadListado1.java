package pe.edu.unc.appejercicios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActividadListado1 extends AppCompatActivity {


    private ListView lvMatriculados;
    private TextView tvTotalRecaudado;
    private ArrayList<String> listaMatriculados;
    private double totalRecaudado = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_listado1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        lvMatriculados = findViewById(R.id.lvMatriculados);
        tvTotalRecaudado = findViewById(R.id.tvTotalRecaudado);

        // Obtener los datos de la actividad anterior
        Intent intent = getIntent();
        listaMatriculados = intent.getStringArrayListExtra("listaMatriculados");
        totalRecaudado = intent.getIntExtra("totalRecaudado", 0); // Recibir como int

        // Mostrar la lista de matriculados
        if (listaMatriculados != null && !listaMatriculados.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMatriculados);
            lvMatriculados.setAdapter(adapter);
        }

        // Mostrar el total recaudado en el TextView
        tvTotalRecaudado.setText("Total recaudado: S/. " + totalRecaudado);




    }
}
