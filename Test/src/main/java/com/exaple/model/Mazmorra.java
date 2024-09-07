package com.exaple.model;

public class Mazmorra {

	public Celda[][] grid; // Puede ser una matriz de Celdas o de Mazmorras (multidimensional)
	public int rows;
	public int cols;
	
	
	public Mazmorra(Celda[][] grid, int rows, int cols) {
		this.grid = grid;
		this.rows = rows;
		this.cols = cols;
	}

	public void print() {
		printGrid(grid);
	}

	private void printGrid(Object grid) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(((Celda[][]) grid)[i][j].toString());
			}
			System.out.println();
		}
	}

}
