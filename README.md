/**
	 * La función dfs (Depth-First Search, o Búsqueda en Profundidad) que se ha
	 * implementado en el código está diseñada para explorar todas las posibles
	 * rutas desde una celda inicial en la mazmorra, recolectando oro y manteniendo
	 * un registro de la salud restante mientras se mueve a través de la grilla.
	 *  
	 * @param mazmorra
	 * @param vida
	 */

  Ejecutar: la clase

  ## public class QuestMain {

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

Algunos ejemplos de input:
grid: [[{g:2},{g:4,m:3},{g:7,m:3}],[{g:2},{g:1,m:1},{g:1,m:4}],[{g:8},{g:9,m:4},{g:5}],[{g:1},{g:3,m:1},{g:1}]]
grid: [[{g:5,m:6},{g:2,m:1}],[{g:4,m:10},{g:1,m:1}]]
grid: [[{g:6,m:2}]], vida: 5 => 6
grid: [[{g:0,m:3}]], vida 6 => 0
grid: [[{g:5},{g:0},{g:8}],[{g:4,m:5},{g:7},{g:7,m:4}],[{g:1},{g:5,m:4},{g:0}],[{g:1,m:4},{g:0},{g:0}]], vida: 10 => 33
