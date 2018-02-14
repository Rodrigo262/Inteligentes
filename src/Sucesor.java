
public class Sucesor {
	private Character accion;
	private Puzzle estado;
	private int coste = 0;
	
	public Sucesor(Character accion, Puzzle estado){
		this.accion = accion;
		this.estado = estado;
		this.coste += 1;
	}
	
	public Sucesor(Character accion, Puzzle estado, int coste){
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

	public Puzzle getEstado() {
		return estado;
	}

	public void setEstado(Puzzle estado) {
		this.estado = estado;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
	
}
