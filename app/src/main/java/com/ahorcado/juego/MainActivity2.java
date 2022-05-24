package com.ahorcado.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    public palabras palabra;
    public TextView txtPalabra;
    public Button btnComprobar;
    public EditText edtCaracter;
    public ImageView image;
    public int Intentos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtPalabra = (TextView) findViewById(R.id.tvPalabra);
        palabra = (palabras) getIntent().getSerializableExtra("dato");
        btnComprobar = (Button) findViewById(R.id.btComprobar);
        edtCaracter = (EditText) findViewById(R.id.etCaracter);
        image = (ImageView) findViewById(R.id.tvImagen);
        String palabras = "";
        char[] palabraMostrar = palabra.getCadena().toCharArray();
        for (int i = 0; i < palabraMostrar.length; i++) {
            palabras += " _";
        }
        palabra.llenarList();
        txtPalabra.setText(palabras);
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtCaracter.getText().toString().length() < 2) {
                    String aux = palabra.validar(edtCaracter.getText().toString().toUpperCase());
                    if (palabra.isExiste()) {
                        txtPalabra.setText(aux);
                        edtCaracter.setText("");
                        if(palabra.isPalabraValida())
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Ganaste", Toast.LENGTH_LONG);
                            toast.show();
                            Intent i = new Intent(MainActivity2.this, MainActivity.class);
                            startActivity(i);
                            return;
                        }
                    } else {
                        Intentos++;
                        cambiarImg();
                        edtCaracter.setText("");
                        if (Intentos > 6) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Game Over!!", Toast.LENGTH_LONG);
                            toast.show();
                            Intent i = new Intent(MainActivity2.this, MainActivity.class);
                            startActivity(i);
                            return;
                        }
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "introduzca una letra con mas de 1 caracter!!!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });
    }

    public void cambiarImg() {
        if (Intentos == 0)
            image.setImageResource(R.drawable.img0);
        if (Intentos == 1)
            image.setImageResource(R.drawable.img1);
        if (Intentos == 2)
            image.setImageResource(R.drawable.img2);
        if (Intentos == 3)
            image.setImageResource(R.drawable.img3);
        if (Intentos == 4)
            image.setImageResource(R.drawable.img4);
        if (Intentos == 5)
            image.setImageResource(R.drawable.img5);
        if (Intentos == 6)
            image.setImageResource(R.drawable.img6);
        if (Intentos == 7)
            image.setImageResource(R.drawable.img7);

    }
}