
public class Nodo implements Comparable<Nodo>{
	private Nodo parent;
	private double valor;
	private Character accion;
	private Puzzle estado;
	private int profundidad;
	private int costo;
	
	public Nodo(Puzzle puzzle_inicial){
		this.profundidad = 0;
		this.parent = null;
		this.accion = null;
		this.estado = puzzle_inicial;
//		this.valor = Math.random();  //recorrido aleatorio
		this.valor = 0; //para recorrido en anchura o en profundidad
		this.costo = 0;
	}
	
	public Nodo(Nodo parent, Character accion, Puzzle sucesor, int costo, int profundidad){
		this.profundidad = profundidad;
		this.parent = parent;
		this.accion = accion;
		this.estado = sucesor;
//		this.valor = Math.random(); //recorrido aleatorio
		this.valor = profundidad; //recorrido en anchura
//		this.valor = (1/profundidad); //recorrido en profndidad
		this.costo = costo;
	}

	public Nodo getParent() {
		return parent;
	}

	public void setParent(Nodo parent) {
		this.parent = parent;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Character getAccion() {
		return accion;
	}

	public void setAccion(Character accion) {
		this.accion = accion;
	}

	public Puzzle getEstado() {
		return estado;
	}

	public void setEstado(Puzzle estado) {
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
}
