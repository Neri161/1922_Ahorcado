package com.ahorcado.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button siguiente;
    EditText palabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // variables de interfaz
        siguiente = (Button) findViewById(R.id.btnSiguiente);
        palabra = (EditText) findViewById(R.id.etPalabra);
        siguiente.setOnClickListener(new View.OnClickListener() { // función OnClick para botón siguiente
            @Override
            public void onClick(View view) {
                if (palabra.getText().toString().equals(null) || palabra.getText().toString().equals("")) { // valida sí se introdujo alguna palabra
                    Toast toast = Toast.makeText(getApplicationContext(), "introduzca una palabra!!!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else if(palabra.getText().toString().length()>1){
                    //envía a MainActivity2 y se envía un extra "dato" del valor de palabras2
                    palabras palabras2 = new palabras(palabra.getText().toString());
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    i.putExtra("dato", palabras2);
                    i.putExtra("imagen", "NULO");
                    startActivity(i);
                }else {
                    // valida que la palabra tenga minimo 2 caracteres
                    Toast toast = Toast.makeText(getApplicationContext(), "introduzca una palabra con mas de 1 caracter!!!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

            }
        });
    }
}
