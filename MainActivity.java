package com.example.luispalomino.asesoracademico;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_registrar;
    Button btn_iniciar;
    EditText et_registro, et_contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_registrar = findViewById(R.id.tv_registrar);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        et_registro = findViewById(R.id.et_registro);
        et_contraseña = findViewById(R.id.et_contraseña);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intentReg);
            }
        });

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int registro = Integer.parseInt(et_registro.getText().toString());
                final String contraseña = et_contraseña.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String nombre = jsonResponse.getString("nombre");
                                String apellido = jsonResponse.getString("apellido");
                                int numero = jsonResponse.getInt("numero");
                                String correo = jsonResponse.getString("correo");
                                String sexo = jsonResponse.getString("sexo");

                                Intent intent = new Intent(MainActivity.this, Inicio.class);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("apellido", apellido);
                                intent.putExtra("registro", registro);
                                intent.putExtra("numero", numero);
                                intent.putExtra("correo", correo);
                                intent.putExtra("contraseña", contraseña);
                                intent.putExtra("sexo", sexo);

                                MainActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Error Login")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest( registro, contraseña, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });


    }
}
