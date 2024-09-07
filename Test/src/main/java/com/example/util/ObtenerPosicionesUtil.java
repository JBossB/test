package com.example.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.exaple.model.Celda;

public class ObtenerPosicionesUtil {
	
	public static List<Celda> obtenerPosicionesIniciales(Celda[][] matriz) {
        List<Celda> posiciones = new ArrayList<Celda>();
        
        int filas = matriz.length;
        int columnas = matriz[0].length;
        HashMap<String, Celda> mapa = new HashMap<>();
        
        // Agregar celdas en la primera fila
        for (int j = 0; j < columnas; j++) {
            Celda celda = matriz[0][j];
            String clave = "0-" + j;
            if (!mapa.containsKey(clave)) {
                mapa.put(clave, celda);
            }
        }
        
        // Agregar celdas en la última fila
        for (int j = 0; j < columnas; j++) {
            Celda celda = matriz[filas - 1][j];
            String clave = (filas - 1) + "-" + j;
            if (!mapa.containsKey(clave)) {
                mapa.put(clave, celda);
            }
        }
        
        // Agregar celdas en la primera columna, omitiendo las ya agregadas en la primera y última fila
        for (int i = 1; i < filas - 1; i++) {
            Celda celda = matriz[i][0];
            String clave = i + "-0";
            if (!mapa.containsKey(clave)) {
                mapa.put(clave, celda);
            }
        }
        
        // Agregar celdas en la última columna, omitiendo las ya agregadas en la primera y última fila
        for (int i = 1; i < filas - 1; i++) {
            Celda celda = matriz[i][columnas - 1];
            String clave = i + "-" + (columnas - 1);
            if (!mapa.containsKey(clave)) {
                mapa.put(clave, celda);
            }
        }
        
        // Convertir el mapa a una lista
        posiciones.addAll(mapa.values());
        
        return posiciones;
    }

}
