package modelo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Frontera1 {
	private PriorityQueue<Nodo> frontera;
	public static ArrayList<Double> tinsert = new ArrayList<Double>();
	private double tinsert1=0;
	private double tinsert2=0;


	private Frontera1(){
		this.frontera = new PriorityQueue<Nodo>();
	}
	
	public static Frontera1 crearFrontera() {
		return new Frontera1();
	}

	public PriorityQueue<Nodo> getFrontera() {
		return this.frontera;
	}

	public Nodo eliminar() {
		return this.frontera.remove();
	}

	public void insertar(Nodo n) {
		tinsert1=Stat.obtenerTiempo('N');
		this.frontera.add(n);
		tinsert2=Stat.obtenerTiempo('N');
		tinsert.add((tinsert2-tinsert1));
	}

	public boolean esVacia() {
		if (this.frontera.size() > 0)
			return false;
		else
			return true;
	}

	public void printFrontera() {
		Iterator<Nodo> through = frontera.iterator();
		while (through.hasNext()) {
			System.out.print(through.next().getValor() + "\n");
		}
		System.out.println();
	}

}
