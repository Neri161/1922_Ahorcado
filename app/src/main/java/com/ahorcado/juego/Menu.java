package com.ahorcado.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ahorcado.juego.modelo.ModeloRetorno;
import com.ahorcado.juego.modelo.Pokedex;

import java.util.Random;

public class Menu extends AppCompatActivity {
    public String respuesta="",imagen="";
    public ModeloRetorno pokedex = new ModeloRetorno();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnSolitario=(Button) findViewById(R.id.btn_solo);
        Button btnAmigos=(Button) findViewById(R.id.btn_amigos);
        btnSolitario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int idAleatorio = (rand.nextInt(898) + 1);
                ConsultarApi rg = new ConsultarApi();
                try {
                    rg.respuesta(idAleatorio+"");
                    muestraToast("Buscando Palabra aleatoria...");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pokedex = rg.modeloRetorno;
                            respuesta =pokedex.getName();
                            imagen = pokedex.getFront_default();
                            if (!respuesta.equals("")) {
                                Intent intent = new Intent(Menu.this, MainActivity2.class);
                                palabras palabras2 = new palabras(respuesta);
                                intent.putExtra("dato", palabras2);
                                intent.putExtra("respuesta", respuesta);
                                intent.putExtra("imagen", imagen);
                                startActivity(intent);
                                muestraToast(respuesta);
                            }
                        }
                    }, 5000);


                } catch (Exception ex) {
                    muestraToast("Error: " + ex);
                }
            }
        });
        btnAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    public void muestraToast(String mensaje) {
        Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT).show();
    }
}