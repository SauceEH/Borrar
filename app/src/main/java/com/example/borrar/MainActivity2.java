package com.example.borrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity2 extends AppCompatActivity {

    public TextView tvHello2;
    public TextView output;
    public Button butAnterior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvHello2 = findViewById(R.id.tvHello2);
        output = findViewById(R.id.output);
        butAnterior = findViewById(R.id.butAnterior);


        tvHello2.setText(getIntent().getStringExtra(Values.CLAVE_INTENT));
        output.setText(getIntent().getStringExtra(Values.CLAVE_INTENT));


        // para volver al activity1 deolviendo datos
        butAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                // añadimos un valor extra al intent, en este caso el texto que haya en el text view
                intent.putExtra(Values.CLAVE_INTENT, output.getText().toString());

                // le damos un valor al resultado y le pasamos el intent con los datos
                setResult(RESULT_OK, intent);

                // finalizamos este activity, resumiendo el activity 1
                finish();
            }
        });



    }
}