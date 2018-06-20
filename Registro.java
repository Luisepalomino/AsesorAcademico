package com.example.luispalomino.asesoracademico;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.os.LocaleListCompat.create;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText etnombre, etapellido, etregistro,etnummero, etcorreo, etcontraseña,etsexo;

    Button btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = findViewById(R.id.Et_nombre);
        etapellido = findViewById(R.id.Et_apellido) ;
        etregistro = findViewById(R.id.Et_registro);
        etnummero = findViewById(R.id.Et_numero);
        etcorreo = findViewById(R.id.Et_correo);
        etcontraseña = findViewById(R.id.Et_Contraseña);
        etsexo = findViewById(R.id.Et_sexo);

        btn_registrar = findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final String nombre = etnombre.getText().toString();
        final String apellido = etapellido.getText().toString();
        final int registro = Integer.parseInt(etregistro.getText().toString());
        final int numero= Integer.parseInt(etnummero.getText().toString());
        final String correo = etcorreo.getText().toString();
        final String Contrasena = etcontraseña.getText().toString();
        final String sexo = etsexo.getText().toString();


        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success){
                        Intent intent = new Intent(Registro.this,MainActivity.class);
                        Registro.this.startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("Error Registro")
                            .setNegativeButton("Retry", null)
                            .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        RegisterRequest registerRequest = new RegisterRequest( nombre, apellido, registro, numero, correo, Contrasena, sexo, respoListener);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registerRequest);

    }
}
