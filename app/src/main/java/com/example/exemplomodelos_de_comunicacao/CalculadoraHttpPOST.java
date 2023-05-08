package com.example.exemplomodelos_de_comunicacao;

import android.widget.TextView;

public class CalculadoraHttpPOST implements Calculadora {

    TextView tv;
    String oper1,oper2;
    PrecisaCalcular pc;
    Callback onResultado;
    public CalculadoraHttpPOST() {

    }

    public void setOnResultado(Callback onResultado) {
        this.onResultado = onResultado;
    }

    @Override
    public double somar(String oper1, String oper2) {
        ComunicadorHTTP comunicadorHTTP = new ComunicadorHTTP("" + oper1, "" + oper2, ComunicadorHTTP.OPERACAO_SOMAR);
        comunicadorHTTP.setOnResultado(this.onResultado);
        comunicadorHTTP.execute();
        return 0;
    }

    @Override
    public double subtrair(String oper1, String oper2) {
        ComunicadorHTTP comunicadorHTTP = new ComunicadorHTTP("" + oper1, "" + oper2, ComunicadorHTTP.OPERACAO_SUBTRAIR);
        comunicadorHTTP.setOnResultado(this.onResultado);
        comunicadorHTTP.execute();
        return 0;
    }

    @Override
    public double multiplicar(String oper1, String oper2) {
        ComunicadorHTTP comunicadorHTTP = new ComunicadorHTTP("" + oper1, "" + oper2, ComunicadorHTTP.OPERACAO_MULTIPLICAR);
        comunicadorHTTP.setOnResultado(this.onResultado);
        comunicadorHTTP.execute();
        return 0;
    }

    @Override
    public double dividir(String oper1, String oper2) {
        ComunicadorHTTP comunicadorHTTP = new ComunicadorHTTP("" + oper1, "" + oper2, ComunicadorHTTP.OPERACAO_DIVIDIR);
        comunicadorHTTP.setOnResultado(this.onResultado);
        comunicadorHTTP.execute();
        return 0;
    }
}

