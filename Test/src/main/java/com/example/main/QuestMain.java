package com.example.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.util.GridParserUtil;
import com.example.util.ObtenerPosicionesUtil;
import com.exaple.model.Celda;
import com.exaple.model.Mazmorra;
import com.exaple.model.Resultado;

public class QuestMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduce la cadena JSON de la mazmorra: ");
		String grid = scanner.nextLine();

		// Pedir al usuario que ingrese el valor de vida
		System.out.print("Introduce el valor de vida: ");
		int vida;
		while (true) {
			try {
				vida = Integer.parseInt(scanner.nextLine());
				break; // Salir del bucle si la conversión es exitosa
			} catch (NumberFormatException e) {
				System.out.println("Error: El valor de vida debe ser un número entero. Inténtalo de nuevo.");
			}
		}
		// Parsear el JSON a una matriz de celdas
		Mazmorra mazmorra = GridParserUtil.parseJsonToGrid(grid);

		System.out.println("Vida: " + vida);
		System.out.println("Matriz de Celdas:");
		mazmorra.print();

		// Ejecutar la función dungeonCorreos
		dungeonCorreos(mazmorra, vida);

	}

	/**
	 * La función dfs (Depth-First Search, o Búsqueda en Profundidad) que has
	 * implementado en tu código está diseñada para explorar todas las posibles
	 * rutas desde una celda inicial en la mazmorra, recolectando oro y manteniendo
	 * un registro de la salud restante mientras se mueve a través de la grilla.
	 *  
	 * @param mazmorra
	 * @param vida
	 */
	public static void dungeonCorreos(Mazmorra mazmorra, int vida) {
		int rows = mazmorra.rows;
		int cols = mazmorra.cols;

		// Movimientos posibles: norte, este, sur, oeste
		int[][] movimientos = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Resultado mejorResultado = new Resultado(0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

		List<Celda> posicionesIniciales = ObtenerPosicionesUtil.obtenerPosicionesIniciales(mazmorra.grid);
		for (Celda posicion : posicionesIniciales) {
			boolean[][] visitado = new boolean[rows][cols];
			List<Celda> caminoActual = new ArrayList<>();
			List<Integer> oroAcumuladoActual = new ArrayList<>();
			List<Integer> saludAcumuladaActual = new ArrayList<>();
			dfs(mazmorra, posicion, vida, 0, caminoActual, oroAcumuladoActual, saludAcumuladaActual, movimientos,
					visitado, mejorResultado);
		}

		// Imprimir el máximo oro recolectado y el camino recorrido
		System.out.println("Oro máximo recolectado: " + mejorResultado.getMaxOro());
		System.out.println("Camino recorrido: " + mejorResultado.getCamino());
		System.out.println("Oro acumulado en el camino: " + mejorResultado.getOroAcumulado());
		System.out.println("Salud acumulada en el camino: " + mejorResultado.getSaludAcumulada());
	}

	/***
	 * Parámetros 
	 * mazmorra: La mazmorra en la que se realiza la búsqueda. Contiene la grilla con todas las celdas. 
	 * celdaActual: La celda desde la cual se está realizando la búsqueda en el momento actual. 
	 * saludRestante: La cantidad de vida que queda al llegar a la celda actual. 
	 * oroAcumulado: La cantidad total de oro recolectado hasta el momento. 
	 * caminoActual: La lista de celdas que forman el camino actual desde el inicio hasta la celda actual.
	 * oroAcumuladoActual: La lista de acumulados de oro en cada paso del camino.
	 * saludAcumuladaActual: La lista de acumulados de salud en cada paso del camino. 
	 * movimientos: Las posibles direcciones de movimiento (norte, este, sur, oeste). 
	 * visitado: Una matriz que indica si una celda ha sido visitada para evitar ciclos. 
	 * mejorResultado: Un objeto que almacena el mejor resultado encontrado hasta ahora (máximo oro, camino, oro acumulado, salud acumulada).
	 */
	private static void dfs(Mazmorra mazmorra, Celda celdaActual, int saludRestante, int oroAcumulado,
			List<Celda> caminoActual, List<Integer> oroAcumuladoActual, List<Integer> saludAcumuladaActual,
			int[][] movimientos, boolean[][] visitado, Resultado mejorResultado) {
		int x = celdaActual.getFila();
		int y = celdaActual.getColumna();

		// Verificar si la celda está dentro del rango y si no ha sido visitada
		/**
		 * Verifica si la celda actual está dentro de los límites de la grilla y si ya
		 * ha sido visitada. Si alguna de estas condiciones es verdadera, termina la
		 * búsqueda en esta dirección.
		 */
		if (x < 0 || x >= mazmorra.rows || y < 0 || y >= mazmorra.cols || visitado[x][y]) {
			return;
		}

		// Actualizar el oro acumulado y la salud restante en la celda actual

		saludRestante -= celdaActual.getMonstruo();

		// Si la salud restante es menor o igual a 0, terminamos el camino
		/**
		 * Resta la cantidad de vida perdida por el monstruo en la celda actual. Si la
		 * salud restante es menor o igual a cero, significa que el camino actual ha
		 * fallado y se debe comparar el oro acumulado con el mejor resultado. Si el oro
		 * acumulado en este camino es mayor que el máximo registrado, actualiza el
		 * mejor resultado. Si la salud restante es positiva, suma el oro encontrado en
		 * la celda actual al total acumulado.
		 */

		if (saludRestante <= 0) {
			// Solo actualizar si se ha encontrado una mejor solución
			if (oroAcumulado > mejorResultado.getMaxOro()) {
				mejorResultado.setMaxOro(oroAcumulado);
				mejorResultado.setCamino(new ArrayList<>(caminoActual));
				mejorResultado.setOroAcumulado(new ArrayList<>(oroAcumuladoActual));
				mejorResultado.setSaludAcumulada(new ArrayList<>(saludAcumuladaActual));
			}
			return;
		} else {
			oroAcumulado += celdaActual.getOro();
		}

		// Marcar la celda actual como visitada
		/**
		 * Marca la celda actual como visitada para evitar volver a ella en el mismo
		 * camino. Añade la celda actual al camino y actualiza las listas de oro y salud
		 * acumulada.
		 */

		visitado[x][y] = true;
		caminoActual.add(celdaActual);
		oroAcumuladoActual.add(oroAcumulado);
		saludAcumuladaActual.add(saludRestante);

		// Explorar las celdas adyacentes
		/**
		 * Para cada movimiento posible (norte, este, sur, oeste), calcula la nueva
		 * posición y verifica si está dentro de los límites de la grilla y si no ha
		 * sido visitada. Si es una celda válida, realiza una llamada recursiva a dfs
		 * desde la nueva celda.
		 */
		for (int[] movimiento : movimientos) {
			int nuevoX = x + movimiento[0];
			int nuevoY = y + movimiento[1];

			if (nuevoX >= 0 && nuevoX < mazmorra.rows && nuevoY >= 0 && nuevoY < mazmorra.cols
					&& !visitado[nuevoX][nuevoY]) {
				Celda nuevaCelda = mazmorra.grid[nuevoX][nuevoY];
				dfs(mazmorra, nuevaCelda, saludRestante, oroAcumulado, caminoActual, oroAcumuladoActual,
						saludAcumuladaActual, movimientos, visitado, mejorResultado);
			}
		}

		// Desmarcar la celda actual como visitada para otras posibles rutas
		/**
		 * Una vez que se han explorado todas las rutas posibles desde la celda actual,
		 * desmarca la celda como visitada para permitir su exploración en otras rutas.
		 * Elimina la celda actual del camino y las listas de oro y salud acumulada para
		 * revertir el estado al de antes de explorar esta celda.
		 */

		visitado[x][y] = false;
		caminoActual.remove(caminoActual.size() - 1);
		oroAcumuladoActual.remove(oroAcumuladoActual.size() - 1);
		saludAcumuladaActual.remove(saludAcumuladaActual.size() - 1);
	}

}
