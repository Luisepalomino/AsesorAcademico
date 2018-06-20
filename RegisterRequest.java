package com.example.luispalomino.asesoracademico;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="http://192.168.0.5:8080/Register.php";
    private Map<String,String>params;
    public RegisterRequest(String nombre, String apellido, int registro, int numero, String correo, String Contrasena, String sexo, Response.Listener<String>listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombre", nombre);
        params.put("apellido", apellido);
        params.put("registro", registro+"");
        params.put("numero", numero+"");
        params.put("correo", correo);
        params.put("Contrasena", Contrasena);
        params.put("sexo", sexo);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
