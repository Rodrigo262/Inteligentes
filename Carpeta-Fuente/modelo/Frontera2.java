package modelo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;



public class Frontera2 {
	private PriorityBlockingQueue<Nodo> frontera;
	public static ArrayList<Double> tinsert =  new ArrayList<Double>();
	private double tinsert1=0;
	private double tinsert2=0;

	private Frontera2() {
		this.frontera = new PriorityBlockingQueue<Nodo>(); 
	}

	public static Frontera2 crearFrontera() {
		return new Frontera2();
	}

	public PriorityBlockingQueue<Nodo> getFrontera() {
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
		Iterator<Nodo> iter = frontera.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println();
	}
}
