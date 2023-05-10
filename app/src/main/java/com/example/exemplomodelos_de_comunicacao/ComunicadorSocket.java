package com.example.exemplomodelos_de_comunicacao;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Socket;

public class ComunicadorSocket extends AsyncTask<String, String, String> {
    public static final String OPERACAO_SOMAR = "1";
    public static final String OPERACAO_SUBTRAIR = "2";
    public static final String OPERACAO_MULTIPLICAR = "3";
    public static final String OPERACAO_DIVIDIR = "4";
    private String oper1;
    private String oper2;
    private String operacao;
    private Callback onResultado;
    private String host;
    private int port;
    public ComunicadorSocket(String oper1, String oper2, String operacao, String host, int port) {
        this.oper1 = oper1;
        this.oper2 = oper2;
        this.operacao = operacao;
        this.host = host;
        this.port = port;
    }
    public void setOnResultado(Callback onResultado) {
        this.onResultado = onResultado;
    }
    @Override
    protected String doInBackground(String... voids) {
        String result="";
        try {

            //Conexão com o Servidor
            Socket clientSocket = new Socket(host, port);
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());

            //Enviando os dados
            socketSaidaServer.writeBytes(operacao+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            socketSaidaServer.flush();

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            result=messageFromServer.readLine();

            clientSocket.close();

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
