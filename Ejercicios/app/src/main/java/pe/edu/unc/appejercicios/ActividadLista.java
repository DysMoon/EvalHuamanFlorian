package pe.edu.unc.appejercicios;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ActividadLista extends ListActivity {

    //IMPORTANTE --> Sino no se va a poder ejecutar es el mismo nombre que de layout
    String[] ejer = {"Ejercicio1", "Ejercicio2", "Ejercicio3", "Ejercicio4", "Ejercicio5", "Ejercicio6", "Ejercicio7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ejer));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int index, long id) {
        super.onListItemClick(l, v, index, id);
     //   Toast.makeText(this, "Has pulsado " + index, Toast.LENGTH_LONG).show();

        Intent ointent=null;
        try {
            //Declarar una objeto de una clase generica
            Class<?> ActividadDestino = Class.forName("pe.edu.unc.appejercicios.Actividad" + ejer[index]);
            ointent = new Intent(this, ActividadDestino);
            startActivity(ointent);
        }catch (Exception e){
            Log.d("Error", e.getMessage());

        }


    }
}