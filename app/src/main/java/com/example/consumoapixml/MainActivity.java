package com.example.consumoapixml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {

    List<Usuario> usuarios;
    List<Categoria> categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarios = new ArrayList<>();
        categorias = new ArrayList<>();
        trustEveryone();
        // buscarDados("https://192.168.0.8:44366/api/Usuarios");
        buscarDados("https://192.168.0.8:44387/api/Categoria");
    }

    private void buscarDados(String url) {
        MyTask task = new MyTask();
        task.execute(url);
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String content = HttpConnection.getDados(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String conteudoRecebido) {
//            usuarios = ParseUsuarioJson.parseDados(conteudoRecebido);
            categorias = ParseCategoriaXml.parseDados(conteudoRecebido);
//                Toast.makeText(MainActivity.this,
//                        usuarios.get(0).getNome()
//                        , Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this,
                    categorias.get(0).getDescricao()
                    , Toast.LENGTH_SHORT).show();

        }
    }

    /*Código para fazer conexão com urls e ssls não seguros*/
    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }
}