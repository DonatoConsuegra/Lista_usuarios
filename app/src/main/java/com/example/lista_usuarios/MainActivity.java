package com.example.lista_usuarios;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.lista_usuarios.WebService.Asynchtask;
import com.example.lista_usuarios.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
                    implements Asynchtask {



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ImageView imagenweb;
        imagenweb = (ImageView) findViewById(R.id.imgUsr);

        View header = getLayoutInflater().inflate(R.layout.layout_item, null);
        imagenweb.addChildrenForAccessibility(header.getTouchables());

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, (Context) MainActivity.this, (Asynchtask) MainActivity.this);
        ws.execute("GET");


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });


    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONObject JSONlista = new JSONObject(result);
        JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("data");

        ArrayList<Usuario> lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);

        AdaptadorUsuarios adapatorUsuario = new AdaptadorUsuarios(this, lstUsuarios);

        AbsListView lstOpciones = null;
        lstOpciones.setAdapter(adapatorUsuario);
    }
}