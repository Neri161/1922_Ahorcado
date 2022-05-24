package com.ahorcado.juego;

import java.io.Serializable;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.*;

public class palabras implements Serializable {
    private String cadena;
    private boolean existe = false; //comprobación si palabra tiene caracter introducido
    public boolean palabraValida = false; // validación sí la palabra se ha validado
    public int contador = 0; // contador de letras acertadas
    public List<String> posiciones = new ArrayList<>();

    public palabras(String cadena) {
        this.cadena = cadena;
    }

    public void llenarList(){
        char[] aux = cadena.toCharArray();
        for (int x = 0; x<aux.length; x++)
            posiciones.add("_");
    }
    public void validarCadena(String letra){
        char[] aux = cadena.toCharArray();
        existe =  false;
        for (int i = 0; i < aux.length; i++){
            if (letra.equals(String.valueOf(aux[i]))){
                posiciones.set(i,aux[i]+"");
                existe = true;
            }
        }
        String auxCadena = "";
        for (String pos:posiciones){
            auxCadena+= pos;
        }
        if(auxCadena.equals(cadena))
            palabraValida = true;
    }
    /* se devuelve lista string, se pide cadena de palabra inicial y letra de intento,
        String cadena se podría cambiar por variable global*/
    public List<String> validar(String intento) {
         // lista de retorno, donde se almacenan los indices donde se encontraron caracteres iguales
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
    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public boolean isPalabraValida() {
        return palabraValida;
    }

    public void setPalabraValida(boolean palabraValida) {
        this.palabraValida = palabraValida;
    }
}
