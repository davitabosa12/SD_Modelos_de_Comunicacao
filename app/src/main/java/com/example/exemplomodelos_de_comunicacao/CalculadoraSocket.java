package com.example.exemplomodelos_de_comunicacao;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraSocket implements Calculadora {

    Callback onResultado;
    String host;
    int port;
    public CalculadoraSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void setOnResultado(Callback onResultado) {
        this.onResultado = onResultado;
    }
    @Override
    public double somar(String oper1, String oper2) {
        ComunicadorSocket comunicadorSocket = new ComunicadorSocket("" + oper1, "" + oper2, ComunicadorSocket.OPERACAO_SOMAR, "192.168.18.188", 9090);
        comunicadorSocket.setOnResultado(this.onResultado);
        comunicadorSocket.execute();
        return 0;
    }

    @Override
    public double subtrair(String oper1, String oper2) {
        ComunicadorSocket comunicadorSocket = new ComunicadorSocket("" + oper1, "" + oper2, ComunicadorSocket.OPERACAO_SUBTRAIR, "192.168.18.188", 9090);
        comunicadorSocket.setOnResultado(this.onResultado);
        comunicadorSocket.execute();
        return 0;
    }

    @Override
    public double multiplicar(String oper1, String oper2) {
        ComunicadorSocket comunicadorSocket = new ComunicadorSocket("" + oper1, "" + oper2, ComunicadorSocket.OPERACAO_MULTIPLICAR, "192.168.18.188", 9090);
        comunicadorSocket.setOnResultado(this.onResultado);
        comunicadorSocket.execute();
        return 0;
    }

    @Override
    public double dividir(String oper1, String oper2) {
        ComunicadorSocket comunicadorSocket = new ComunicadorSocket("" + oper1, "" + oper2, ComunicadorSocket.OPERACAO_DIVIDIR, "192.168.18.188", 9090);
        comunicadorSocket.setOnResultado(this.onResultado);
        comunicadorSocket.execute();
        return 0;
    }
}