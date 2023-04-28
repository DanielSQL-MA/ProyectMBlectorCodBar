package com.example.lector_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button salidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salidas = (Button) findViewById(R.id.btnmain);

        salidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salidas = new Intent(MainActivity.this,Lector_Tabla.class);
                startActivity(salidas);
            }
        });


    }
}