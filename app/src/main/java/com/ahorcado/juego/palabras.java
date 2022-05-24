package com.ahorcado.juego;

import java.util.regex.*;
import java.util.ArrayList;
import java.util.*;

public class palabras {
    private boolean existe = false; //comprobación si palabra tiene caracter introducido
    public boolean palabraValida = false; // validación sí la palabra se ha validado
    public int contador = 0; // contador de letras acertadas

    /* se devuelve lista string, se pide cadena de palabra inicial y letra de intento,
    String cadena se podría cambiar por variable global*/
    public List<String> validar(String cadena, String intento) {
        List<String> posiciones = new ArrayList<String>(); // lista de retorno, donde se almacenan los indices donde se encontraron caracteres iguales
        char[] aux = cadena.toCharArray(); // variable auxiliar se convierte la cadena en un arreglo char
        // inicio expresión regular comparación
        Pattern pat = Pattern.compile(intento);
        String input = cadena;
        Matcher mat = pat.matcher(input);
        if (mat.find()) {
            existe = true;
        } else {
            posiciones.add("NULL"); // se retornará NULL en lista en caso de no coincidir con nada
            existe = false;
        }
        try {
            if (existe == true) { // Sí coincide con caracter se inicia proceso para buscar su posición en la frase
                for (int i = 0; i < aux.length; i++) {
                    char auxiliar = intento.charAt(0);
                    if (aux[i] == auxiliar) {
                        posiciones.add(aux[i] + "");
                        contador++; // contador para letras encontradas
                    }
                }
            }
            if (contador == aux.length) { // cuando se hayan encontrado todas las letras se convierte en true
                palabraValida = true;
            }
        } catch (Exception e) {
            palabraValida = false;
        }
        return posiciones;
    }
}
