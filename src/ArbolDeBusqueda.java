import java.util.ArrayList;
import java.util.LinkedList;


public class ArbolDeBusqueda {
	private Frontera frontera; 
	private ArrayList<Double> tinsert =  new ArrayList<>();
	private ArrayList<Double> tremove = new ArrayList<>();
	
	public ArbolDeBusqueda(Puzzle puzzle_inicial){
		this.frontera = new Frontera();
		this.frontera.getFrontera().add(new Nodo(puzzle_inicial));
	}
	
	public Nodo generarArbol(){
		boolean fin = false;
		Nodo nodo = null;
		double ini=0;
		double end = 0;
		double ttoinsert = 0;
		for(;!fin && frontera.getFrontera().size() < 1000;){
			ini = Stat.obtenerTiempo('N');
			nodo = this.frontera.getFrontera().remove();
			end = Stat.obtenerTiempo('N');
			ttoinsert = end-ini;
			tremove.add(ttoinsert);
			if(!EspacioDeEstados.esObjetivo(nodo.getEstado())){
				ArrayList<Sucesor> sucesores = EspacioDeEstados.funcionSucesor(nodo.getEstado());
				for(int i=0; i<sucesores.size(); i++){
					ini = Stat.obtenerTiempo('N');
					this.frontera.getFrontera().add(new Nodo(nodo, sucesores.get(i).getAccion(), sucesores.get(i).getEstado(), sucesores.get(i).getCoste(), nodo.getProfundidad()+1));
					end = Stat.obtenerTiempo('N');
					ttoinsert = end-ini;
					tinsert.add(ttoinsert
							);
				}
			}
			else fin = true;
		}
//		System.out.println("Tiempos de inserción");
//		imprimeTiempos(tinsert);
//		System.out.println("\nTiempos de eliminación");
//		imprimeTiempos(tremove);
		return nodo;
	}
	
	public void imprimeTiempos(ArrayList<Double> t){
		for(int i=0; i<t.size(); i++) System.out.print(t.get(i)+"\n");
		System.out.println();
	}
	
}
