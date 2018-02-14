package modelo;

public class Sucesor {
	private Character accion;
	private Estado estado;
	private int coste = 0;
	
	public Sucesor(Character accion, Estado estado){
		this.accion = accion;
		this.estado = estado;
		this.coste += 1;
	}
	
	public Sucesor(Character accion, Estado estado, int coste){
		this.accion = accion;
		this.estado = estado;
		this.coste += coste;
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

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
	
}
