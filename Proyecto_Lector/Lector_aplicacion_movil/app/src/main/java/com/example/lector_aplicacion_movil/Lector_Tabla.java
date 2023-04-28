package com.example.lector_aplicacion_movil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class Lector_Tabla extends AppCompatActivity {
    ImageButton btnreturn;
    Button btnEscaner;
    EditText txtResutado;

    ListView listaLotes;

    EditText socket;


    ArrayList<String> listalotes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_tabla);

        btnreturn = (ImageButton)findViewById(R.id.btnreturn);
        btnEscaner = (Button) findViewById(R.id.btnEscaner);
        listaLotes = (ListView) findViewById(R.id.ListaLotes);
        socket=(EditText) findViewById(R.id.socket);





        //Boton de Regresar
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarAdvertencia();
            }
        });





        //Boton de Escanear SOLO ABRE EL METODO DE ESCANEO
        btnEscaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrador = new IntentIntegrator(Lector_Tabla.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDB");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }
        });
    }

    public void enviar(View v){

        Enviar_A_Servidor ens = new Enviar_A_Servidor();
        ens.execute(socket.getText().toString());
    }

    private void mostrarAdvertencia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Lector_Tabla.this);
        builder.setTitle("ADVERTENCIA");
        builder.setMessage("¿Seguro quieres regresar? SE PERDERAN TODOS LOS DATOS")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent salidas = new Intent(Lector_Tabla.this,MainActivity.class);
                        startActivity(salidas);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Cancelado",Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false)
                .show();
    }
    private void EliminarArray() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Lector_Tabla.this);
        builder.setTitle("ADVERTENCIA");
        builder.setMessage("¿Quieres eliminar este registro?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent salidas = new Intent(Lector_Tabla.this,MainActivity.class);
                        startActivity(salidas);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Cancelado",Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false)
                .show();
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);



        if(result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                listalotes.add(result.getContents());

                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listalotes);

                listaLotes.setAdapter(adapter);
            }
        }else{

            super.onActivityResult(requestCode, resultCode, data);

        }

    }
}