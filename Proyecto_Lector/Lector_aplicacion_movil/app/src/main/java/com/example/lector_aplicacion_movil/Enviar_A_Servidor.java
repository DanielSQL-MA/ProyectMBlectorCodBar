package com.example.lector_aplicacion_movil;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class Enviar_A_Servidor extends AsyncTask<String, Void, Void> {
    static Socket s ;
    static DataOutputStream dt;
    static PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids){
        String mensaje = voids[0];

        try{

            s = new Socket("192.168.1.8",7283);
            s.setSoTimeout(10000);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(mensaje);
            pw.flush();
            pw.close();
            s.close();
        }catch(IOException e){
            e.printStackTrace();

        }
        return null;
    }
}
