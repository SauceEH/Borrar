package com.example.borrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView tvHello;
    public TextView input;
    public Button butCambiar;
    public Button butSiguiente;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // "enganchamos" el textView y el Button
        tvHello = findViewById(R.id.tvHello);
        butCambiar = findViewById(R.id.butCambiar);
        butSiguiente = findViewById(R.id.butSiguiente);
        input = findViewById(R.id.input);


        // le añadimos un Listener al boton
        butCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // le cambiamos el texto dependiendo de lo que haya puesto

                // comparando el texto que haya en el String helloWorld
                if (tvHello.getText().equals(getText(R.string.helloWorld)))
                {
                    tvHello.setText(R.string.agur);
                }

                else
                {
                    tvHello.setText(R.string.helloWorld);
                }
            }
        });

        // para pasar a otra pantalla
        // le añadimos un Listener al boton
        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {

                // creamos un intent nuevo indicando el origen y el destino
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // añadimos un valor extra al intent, en este caso el texto que haya en el text view
                intent.putExtra(Values.CLAVE_INTENT, input.getText().toString());

                // intent.putExtra(Values.CLAVE_INTENT, getText(R.string.helloWorld));

                // cambiamos de activity esperando un resultado
                // deja la activity en pausa hasta que devuelva un resultCode esperado (un numero definido en la clase values)
                startActivityForResult(intent, Values.REQ_ACT_2);

            }
        });

        // CARGAR DATOS
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String valor = sharedPref.getString(getString(R.string.clave), getString(R.string.porDefecto));
        tvHello.setText(valor);

    }



    // Sobrecargamos el metodo para recibir los codigos de resultados
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case Values.REQ_ACT_2:
            {
                // si el resultado que viene del activity2 es OK
                if (resultCode == RESULT_OK)
                {
                    // ponemos el texto que viene en el intent del activity2 en el edit text del activity1
                    tvHello.setText(data.getStringExtra(Values.CLAVE_INTENT));

                    // GUARDAR DATOS
                    editor = sharedPref.edit();
                    String valor = data.getStringExtra(Values.CLAVE_INTENT);
                    editor.putString(getString(R.string.clave), valor);
                    editor.apply();
                }
                break;
            }

        }


    }
}