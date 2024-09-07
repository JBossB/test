package com.exaple.model;

public class Celda {

	public int fila;
	public int columna;
	
	public int oro = 0;
	public int monstruo = 0;
	

    public Celda(int oro, int monstruo, int fila, int columna) {
        this.oro = oro;
        this.monstruo = monstruo;
        this.fila = fila;
        this.columna = columna;
    }

    
	public Celda(int oro, int monstruo) {
		this.oro=oro;
		this.monstruo=monstruo;
	}
	
	public Celda(int oro) {
		this.oro=oro;
	}
	
	public int getOro() {
		return oro;
	}
	public int getFila() {
		return fila;
	}

	public int getMonstruo() {
		return monstruo;
	}

	public int getColumna() {
		return columna;
	}
	
	@Override
    public String toString() {
        return "[("+fila+","+columna+")(g:" + oro + ",m:" + monstruo + ")]  ";
    }
}
