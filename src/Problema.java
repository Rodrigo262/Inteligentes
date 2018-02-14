import java.io.IOException;


public class Problema {
	private Puzzle estado_objetivo;
	private Puzzle estado_inicial;
	//private EspacioDeEstados edp;
	
	public Problema(int cols, int rows, String path_original, String path_modificado) throws IOException{
		this.estado_objetivo = Puzzle.creacion(rows, cols, path_original);
		this.estado_inicial = Puzzle.reconstruccion(rows, cols, path_modificado, this.estado_objetivo);
	}
	
	public Puzzle getEstado_objetivo(){
		return this.estado_objetivo;
	}
	
	public boolean esObjetivo(Puzzle puzzle_modificado){
		return EspacioDeEstados.esObjetivo(puzzle_modificado);
	}

	public Puzzle getEstado_inicial() {
		return this.estado_inicial;
	}
	
	public void printSolucion(Nodo n){
		if(n.getParent()==null) System.out.println(n);
		else{
			System.out.println(n);
			printSolucion(n.getParent());
		}
	}
	
	public Nodo buscarSolucion(){
		Nodo nodo = null;
		if(EspacioDeEstados.esValido(getEstado_inicial())){
			ArbolDeBusqueda adb = new ArbolDeBusqueda(getEstado_inicial());
			System.out.println("Generando arbol");
			nodo = adb.generarArbol();
			System.out.println("Arbol generado - Solución encontrada");
		}
		
		return nodo;
	}
	
}
