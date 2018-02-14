import java.util.Iterator;
import java.util.PriorityQueue;


public class Frontera {
	private PriorityQueue<Nodo> frontera;
	
	public Frontera(){
		this.frontera = new PriorityQueue<Nodo>();
	}
	
	public PriorityQueue<Nodo> getFrontera(){
		return this.frontera;
	}
	
	public void printFrontera(){
		 Iterator<Nodo> through = frontera.iterator() ;
	        while(through.hasNext() ) {
	                System.out.print(through.next().getValor() + "\n") ;
	        }
	        System.out.println() ;
	}
	 
	
	//implementar el metodo compareTo para la inserción ordenada en el LinkedList
}
