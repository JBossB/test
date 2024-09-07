package com.exaple.model;

import java.util.List;

public class Resultado {
	 private int maxOro;
     private List<Celda> camino;
     private List<Integer> oroAcumulado;
     private List<Integer> saludAcumulada;

     public Resultado(int maxOro, List<Celda> camino, List<Integer> oroAcumulado, List<Integer> saludAcumulada) {
         this.maxOro = maxOro;
         this.camino = camino;
         this.oroAcumulado = oroAcumulado;
         this.saludAcumulada = saludAcumulada;
     }

     public int getMaxOro() {
         return maxOro;
     }

     public void setMaxOro(int maxOro) {
         this.maxOro = maxOro;
     }

     public List<Celda> getCamino() {
         return camino;
     }

     public void setCamino(List<Celda> camino) {
         this.camino = camino;
     }

     public List<Integer> getOroAcumulado() {
         return oroAcumulado;
     }

     public void setOroAcumulado(List<Integer> oroAcumulado) {
         this.oroAcumulado = oroAcumulado;
     }

     public List<Integer> getSaludAcumulada() {
         return saludAcumulada;
     }

     public void setSaludAcumulada(List<Integer> saludAcumulada) {
         this.saludAcumulada = saludAcumulada;
     }

	@Override
    public String toString() {
        return "Resultado{" +
               "maxOro=" + maxOro +
               ", camino=" + camino +
               ", oroAcumulado=" + oroAcumulado +
               ", saludAcumulada=" + saludAcumulada +
               '}';
    }
}
