package com.ahorcado.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class finalPoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_poke);
        TextView txtDato = (TextView) findViewById(R.id.btComprobar);
        ImageView img1 = (ImageView) findViewById(R.id.tvImagen);
        Button btnVolver = (Button) findViewById(R.id.btComprobar);
        Bundle extras = getIntent().getExtras();
        String dato = extras.getString("dato");
        String imagen = extras.getString("imagen");
        txtDato.setText("Palabra correcta: "+dato);
        if (!imagen.equals("NULO")) {
            Picasso.get().load(imagen).error(R.mipmap.ic_launcher).into(img1);
        }
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(finalPoke.this, Menu.class);
                startActivity(intent);
            }
        });
    }
}