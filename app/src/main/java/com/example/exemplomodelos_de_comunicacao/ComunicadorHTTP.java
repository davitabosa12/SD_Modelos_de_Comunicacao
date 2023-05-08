package com.example.exemplomodelos_de_comunicacao;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ComunicadorHTTP extends AsyncTask<String, String, String> {

    public static final String OPERACAO_SOMAR = "1";
    public static final String OPERACAO_SUBTRAIR = "2";
    public static final String OPERACAO_MULTIPLICAR = "3";
    public static final String OPERACAO_DIVIDIR = "4";
    private String oper1;
    private String oper2;
    private String operacao;

    private Callback onResultado;

    public ComunicadorHTTP(String oper1, String oper2, String operacao) {
        this.oper1 = oper1;
        this.oper2 = oper2;
        this.operacao = operacao;
    }
    public void setOnResultado(Callback onResultado) {
        this.onResultado = onResultado;
    }
    @Override
    protected String doInBackground(String... strings) {
        String result="";
        try {

            URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true) ;

            //ENVIO DOS PARAMETROS
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write("oper1="+oper1+"&oper2="+oper2+"&operacao=" + operacao);
            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {

                //RECBIMENTO DOS PARAMETROS


                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                result = response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
    @Override
    protected void onPostExecute(String resultado){
        if (this.onResultado != null) {
            this.onResultado.onResultado(resultado);
        }
    }
}
