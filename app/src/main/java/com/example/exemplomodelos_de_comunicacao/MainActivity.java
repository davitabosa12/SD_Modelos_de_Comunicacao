package com.example.exemplomodelos_de_comunicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Callback, View.OnClickListener {

    TextView tv;
    Calculadora calculadora;
    EditText oper1;
    EditText oper2;
    Button btnSoma;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getBoolean("useSockets")) {
                String host = extras.getString("host");
                int port = extras.getInt("port");
                calculadora = new CalculadoraSocket(host, port);
            } else {
                calculadora = new CalculadoraHttpPOST();
            }
        } else {
            calculadora = new CalculadoraHttpPOST();
        }

        setContentView(R.layout.activity_main);
        btnSoma = findViewById(R.id.btnSomar);
        oper1 = findViewById(R.id.edtOp1);
        oper2 = findViewById(R.id.edtOp2);
        tv= findViewById(R.id.textView);

        calculadora.setOnResultado(this);
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
            calculadora.somar(oper1.getText().toString(), oper2.getText().toString());
        } else if (id == R.id.btnSubtrair) {
            calculadora.subtrair(oper1.getText().toString(), oper2.getText().toString());
        } else if (id == R.id.btnMultiplicar) {
            calculadora.multiplicar(oper1.getText().toString(), oper2.getText().toString());
        } else if (id == R.id.btnDividir) {
            calculadora.dividir(oper1.getText().toString(), oper2.getText().toString());
        } else if (id == R.id.btnConfig) {
            Intent i = new Intent(this, SettingsActivity.class);
            if(this.extras != null) {
                i.putExtras(this.extras);
            }
            startActivity(i);
        }
    }
}
