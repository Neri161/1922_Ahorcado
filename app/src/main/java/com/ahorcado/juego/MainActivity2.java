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
import android.view.KeyEvent;
import android.view.View.OnKeyListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    public palabras palabra;
    public TextView txtPalabra;
    public Button btnComprobar;
    public EditText edtCaracter;
    public ImageView image; // imagen para mostrar ahorcado
    public int Intentos = 0; // contador de intentos para validar palabras
    public String imagen ="",dato="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // inicialización de variables para interfaz
        txtPalabra = (TextView) findViewById(R.id.tvPalabra);
        palabra = (palabras) getIntent().getSerializableExtra("dato");
        imagen = (String) getIntent().getSerializableExtra("imagen");
        btnComprobar = (Button) findViewById(R.id.btComprobar);
        edtCaracter = (EditText) findViewById(R.id.etCaracter);
        image = (ImageView) findViewById(R.id.tvImagen);

        String palabras = "";
        // se convierte la cadena de texto en un arrayChar
        char[] palabraMostrar = palabra.getCadena().toCharArray();
        // variable palabras concatena guiones por cada letra de la palabra
        for (int i = 0; i < palabraMostrar.length; i++) {
            palabras += " _";
        }
        // objeto palabras realiza proceso llenarList
        palabra.llenarList();
        // Se coloca en txt la concatenación de guiones para palabra
        txtPalabra.setText(palabras);
        // proceso onClick para botón comprobar
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesoComprobar();
            } // llama función local procesoComprobar
        });
        // proceso onKey para aceptar tecla enter como submit
        edtCaracter.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    procesoComprobar(); // llama a función local procesoComprobar
                    return true;
                }
                return false;
            }
        });
    }
// función para comprobar aciertos y fallas
    public void procesoComprobar() {
        if (edtCaracter.getText().toString().length() < 2) { // valida que se ingrese solo 1 caracter
            String aux = palabra.validar(edtCaracter.getText().toString().toUpperCase()); // convierte caracter a mayuscula
            if (palabra.isExiste()) { // valida que exista caracter
                txtPalabra.setText(aux);
                edtCaracter.setText("");
                if (palabra.isPalabraValida()) { // valida que esté completa la palabra ingresada
                    Toast toast = Toast.makeText(getApplicationContext(), "Ganaste", Toast.LENGTH_LONG); // retorna aviso
                    toast.show();
                    Intent i = new Intent(MainActivity2.this, finalPoke.class); // devuelve a MainActivity para reiniciar juego
                    i.putExtra("dato", dato);
                    i.putExtra("imagen", imagen);
                    startActivity(i);
                    return;
                }
            } else {
                Intentos++; // aumenta el numero de intentos
                cambiarImg(); // cambia a la siguiente imagen de error
                edtCaracter.setText("");
                if (Intentos > 6) {// valida sí ahorcado aún no termina
                    // retorna aviso de perdia
                    Toast toast = Toast.makeText(getApplicationContext(), "Game Over!! \n Correcto: " + palabra.getCadena(), Toast.LENGTH_LONG);
                    toast.show();
                    Intent i = new Intent(MainActivity2.this, MainActivity.class);
                    i.putExtra("dato", dato);
                    i.putExtra("imagen", imagen);
                    startActivity(i); // devuelve a MainActivity para reiniciar
                    return;
                }
            }
        } else {
            // mensaje de aviso sí no se introdujo ningun caracter
            Toast toast = Toast.makeText(getApplicationContext(), "introduzca una letra con mas de 1 caracter!!!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
    }
// función para cambiar imagen dependiendo del numero de intentos realizados
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