package com.example.exemplomodelos_de_comunicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Callback, View.OnClickListener {

    TextView tv;
    CalculadoraHttpPOST calculadoraHttpPOST;
    EditText oper1;
    EditText oper2;
    Button btnSoma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSoma = findViewById(R.id.btnSomar);
        oper1 = findViewById(R.id.edtOp1);
        oper2 = findViewById(R.id.edtOp2);
        tv= findViewById(R.id.textView);
        calculadoraHttpPOST = new CalculadoraHttpPOST();
        calculadoraHttpPOST.setOnResultado(this);
        btnSoma.setOnClickListener(this);
        }

    @Override
    public void onResultado(String resultado) {
        tv.setText(resultado);
    };

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnSomar) {
            calculadoraHttpPOST.somar(oper1.getText().toString(), oper2.getText().toString());
        } else if (id == R.id.btnSubtrair) {
            calculadoraHttpPOST.subtrair(oper1.getText().toString(), oper2.getText().toString());
        } else if (id == R.id.btnMultiplicar) {
            calculadoraHttpPOST.multiplicar(oper1.getText().toString(), oper2.getText().toString());
        } else if (id == R.id.btnDividir) {
            calculadoraHttpPOST.dividir(oper1.getText().toString(), oper2.getText().toString());
        }
    }
}
