package com.example.luispalomino.asesoracademico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {

    TextView tvnombre, tvapellido, tvRegister;
    TextView tvnomero, tvcorreo, tvcontraseña, tvsexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        tvnombre = findViewById(R.id.tetv_nombre);
        tvapellido = findViewById(R.id.tetv_apellido);
        tvRegister = findViewById(R.id.tetv_Register);
        tvnomero = findViewById(R.id.tetv_nomero);
        tvcorreo = findViewById(R.id.tetv_correo);
        tvcontraseña = findViewById(R.id.tetv_contraseña);
        tvsexo = findViewById(R.id.tetv_sexo);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        int registro = intent.getIntExtra("registro", -1);
        int numero = intent.getIntExtra("numero", -1);
        String correo = intent.getStringExtra("correo");
        String contraseña = intent.getStringExtra("contraseña");
        String sexo = intent.getStringExtra("sexo");

        tvnombre.setText(nombre);
        tvapellido.setText(apellido);
        tvRegister.setText(registro+"");
        tvnomero.setText(numero+"");
        tvcorreo.setText(correo);
        tvcontraseña.setText(contraseña);
        tvsexo.setText(sexo);


    }
}
