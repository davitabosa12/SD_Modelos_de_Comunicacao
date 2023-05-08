package com.example.exemplomodelos_de_comunicacao;

public interface Calculadora {
    public default String sayHello(String nome, String sobrenome) {
        return "Fala "+ nome + " " + sobrenome;
    }
    double somar(String oper1, String oper2);
    double subtrair(String oper1, String oper2);
    double multiplicar(String oper1, String oper2);
    double dividir(String oper1, String oper2);
}
