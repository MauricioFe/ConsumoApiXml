package com.example.consumoapixml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Usuario> usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarios = new ArrayList<>();
        buscarDados("https://gophonebrasil.com/api/clientes");
    }

    private void buscarDados(String url) {
        MyTask task = new MyTask();
        task.execute(url);
    }

    private class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            String content = HttpConnection.getDados(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String conteudoRecebido) {
            usuarios = ParseUsuarioXml.parseDados(conteudoRecebido);
            for (int i = 0; i< usuarios.size(); i++) {
                Toast.makeText(MainActivity.this,
                        usuarios.get(i).getId() + ", \n" + usuarios.get(i).getNome()+", \n"+ usuarios.get(i).getIdade()
                        , Toast.LENGTH_SHORT).show();
            }
        }
    }
}