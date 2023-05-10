package com.example.exemplomodelos_de_comunicacao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtHost;
    EditText edtPort;
    CheckBox cbUseSockets;
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        edtHost = findViewById(R.id.edt_host);
        edtPort = findViewById(R.id.edt_port);
        cbUseSockets = findViewById(R.id.cb_use_sockets);
        Bundle fromMainMenu = getIntent().getExtras();
        if(fromMainMenu != null) {
            cbUseSockets.setChecked(fromMainMenu.getBoolean("useSockets"));
            // Enable/Disable edit texts
            edtHost.setEnabled(cbUseSockets.isChecked());
            edtPort.setEnabled(cbUseSockets.isChecked());
            edtHost.setText(fromMainMenu.getString("host"));
            edtPort.setText(String.format("%d", fromMainMenu.getInt("port")));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_main_menu) {
            try{

                Bundle extras = checkAndSaveOptions();
                goToMainMenu(extras);
            } catch (Exception e){
                goToMainMenu(null);
            }
        }
    }

    private Bundle checkAndSaveOptions() {
        Bundle extras = new Bundle();
        String host = edtHost.getText().toString();
        if (!checkIp(host)) {
            Toast.makeText(this, "IP inválido", Toast.LENGTH_SHORT).show();
            return null;
        }
        int port;
        try {
            port = Integer.parseInt(edtPort.getText().toString());
            if (port == 0) {
                throw new RuntimeException("Port 0 is reserved.");
            }
        } catch (RuntimeException e) {
            Toast.makeText(this, "Porta inválida", Toast.LENGTH_SHORT).show();
            return null;
        }
        extras.putString("host", host);
        extras.putInt("port", port);
        extras.putBoolean("useSockets", cbUseSockets.isChecked());
        return extras;
    }

    private void goToMainMenu(Bundle bundle) {
        Intent i = new Intent(this, MainActivity.class);
        if(bundle != null) {
            i.putExtras(bundle);
        }
        startActivity(i);
    }

    private boolean checkIp(String ip) {
        try {
            if (ip == null || ip.isEmpty()) {
                return false;
            }
            if(ip.endsWith(".")) return false;
            String[] nets = ip.split("\\.");
            if (nets.length != 4) return false;
            for (String num : nets) {
                Integer i = Integer.parseInt(num);
                if (i <0 || i > 255) return false;
            }
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
