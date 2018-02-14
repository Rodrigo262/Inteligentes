package modelo;

import java.util.Comparator;

public class Nodo implements Comparable<Nodo>{
	private Nodo parent;
	private int valor;
	private Character accion;
	private Estado estado;
	private int profundidad;
	private int costo;
	private int limite = 10000;
	
	public Nodo(Estado Estado_inicial){
		this.profundidad = 0;
		this.parent = null;
		this.accion = 'a';
		this.estado = Estado_inicial;
		this.valor = 0;
		this.costo = 0;
	}
	
	public Nodo(Nodo parent, Character accion, Estado sucesor, String estrategia, int prof_max){
		this.parent = parent;
		this.profundidad = this.parent.getProfundidad()+1;
		this.costo = this.parent.getCosto()+1;
		this.accion = accion;
		this.estado = sucesor;
		if(estrategia == "Anchura")
			this.valor = this.parent.getProfundidad()+1;
		else if(estrategia == "Costo Uniforme")
			this.valor =  this.parent.getCosto()+1;
		else if(estrategia == "Profundidad" || estrategia == "Profundidad Acotada" || estrategia == "Profundidad Iterativa")
			this.valor = (-1)*(this.parent.getProfundidad()+1);
		else if(estrategia == "A estrella")
			this.valor = (this.parent.getProfundidad()+1) + this.estado.heuristica();
		if(this.profundidad > prof_max)
			this.parent = null;
	}

	public Nodo getParent() {
		return parent;
	}

	public void setParent(Nodo parent) {
		this.parent = parent;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Character getAccion() {
		return accion;
	}

	public void setAccion(Character accion) {
		this.accion = accion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int compareTo(Nodo n){
		if(getValor() < n.getValor()) return -1;
	    else if(getValor() > n.getValor()) return 1;
		return 0;
	}
	
	public String toString(){
		return "{"+getAccion()+",\n"+getEstado()+",\n"+getCosto()+"}";
	}
	
	public static class Comparators{
		public static Comparator<Nodo> comparateNodo = new Comparator<Nodo>() {
			public int compare(Nodo n1, Nodo n2) {
				return n1.compareTo(n2);
			}
		};
	}
		
	
		
	
}
