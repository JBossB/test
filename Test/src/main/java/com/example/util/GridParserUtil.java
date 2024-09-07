package com.example.util;

import org.json.JSONArray;
import org.json.JSONObject;

import com.exaple.model.Celda;
import com.exaple.model.Mazmorra;

public class GridParserUtil {


    public static Mazmorra parseJsonToGrid(String input) {
        // Reemplazar comillas incorrectas por comillas dobles
        input = input.replaceAll("(?<!\\w)(g|m)(?!\\w)", "\"$1\""); // Reemplaza las claves g y m por "g" y "m"
        input = input.replaceAll("\\{\\s*(\\d+)\\s*\\}", "\\{\"g\": $1, \"m\": 0\\}"); // Reemplaza { valor } por {"g": valor, "m": 0}
        
        // Asegurarse de que el JSON esté bien formado
        if (!input.endsWith("]")) {
            input += "]";
        }
        
        try {
        	input = input.replaceAll(" ", "");
        	
        	System.out.println("Grid: " + input);
            // Parsear el string a un JSONArray
            JSONArray jsonArray = new JSONArray(input);

            // Dimensiones de la matriz
            int rows = jsonArray.length();
            int cols = jsonArray.getJSONArray(0).length();

            // Crear la matriz de celdas
            Celda[][] grid = new Celda[rows][cols];

            // Llenar la matriz con los valores del JSON
            for (int i = 0; i < rows; i++) {
                JSONArray rowArray = jsonArray.getJSONArray(i);
                for (int j = 0; j < cols; j++) {
                    JSONObject cellObject = rowArray.getJSONObject(j);
                    int oro = cellObject.getInt("g");
                    int monstruo = cellObject.optInt("m", 0); // Valor por defecto si no se especifica "m"
                    grid[i][j] = new Celda(oro, monstruo, i, j);
                }
            }
            
            Mazmorra mazmorra = new Mazmorra(grid, rows, cols);
            
//            int rows2 = mazmorra.grid.length;
//            int cols2 = mazmorra.grid[0].length;
//            System.out.println("Mazmorra rows:: "+rows+"|"+rows2);
//            System.out.println("Mazmorra cols:: "+cols+"|"+cols2);
            

            return mazmorra;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error parsing JSON string.");
        }
    }

    public static void main(String[] args) {
        // Ejemplo de string JSON
        String input = "[[{ g: 2 }, { g: 4, m: 3 }, { g: 7, m: 3 }], [{ g: 2 }, { g: 1, m: 1 }, { g: 1, m: 4 }], [{ g: 8 }, { g: 9, m: 4 }, { g: 5 }], [{ g: 1 }, { g: 3, m: 1 }, { g: 1 }]]";

        // Convertir el JSON a una matriz de Celdas
        Mazmorra grid = parseJsonToGrid(input);

        // Imprimir el resultado
        System.out.println("Matriz de Celdas:");
        grid.print();
    }
}
