package modelo;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Problema {
	private Estado einicial;
	private EspacioDeEstados ede = null;
	private float ttotal = 0;
	private float tinicio = 0;
	private float tfin = 0;


	public Problema(EspacioDeEstados ede, Estado einicial) throws IOException{
		this.ede=ede;
		this.einicial=einicial;
		System.out.println(this.einicial);
	}

	public boolean esObjetivo(Estado Estado_modificado){
		return ede.esObjetivo(Estado_modificado);
	}

	public Estado getEinicial() {
		return this.einicial;
	}

	public boolean Estado_Meta(Estado estado){
		return ede.esObjetivo(estado);
	}

	public EspacioDeEstados getEde() {
		return ede;
	}

	public void setEdp(EspacioDeEstados edp) {
		this.ede = edp;
	}

	public void escribirSolucion(Nodo n, String estrategia, String path_original, String path_modificado, int rows, int cols) throws IOException{
		FileWriter fwi=new FileWriter("solucion_"+estrategia+".txt");
		ArrayList <Nodo> solucion=new ArrayList <Nodo>();
		fwi.write("Ruta del archivo original: "+path_original+"\n");
		fwi.write("Ruta del archivo modificado: "+path_modificado+"\n");
		fwi.write("Número de filas: "+rows+"\n");
		fwi.write("Número de columnas: "+cols+"\n");
		fwi.write("Estrategia: "+estrategia+"\n");
		fwi.write("Tiempo empleado en encontrar la solución: ");
		if(ttotal/Math.pow(10, 9)>1)
			fwi.write("Tiempo empleado en encontrar la solución: "+ttotal/Math.pow(10, 9)+"s\n");
		else{
			if (ttotal/Math.pow(10, 6)>1)
				fwi.write("Tiempo empleado en encontrar la solución: "+ttotal/Math.pow(10, 6)+"ms\n");
			else
				fwi.write("Tiempo empleado en encontrar la solución: "+ttotal+"ns\n");
		}
		fwi.write("Profundidad a la que se alcanza un nodo objetivo:"+n.getProfundidad()+"\n\n");
		fwi.write("Acciones llevadas a cabo para solucionar el puzzle:\n");
		
		while(n.getParent()!=null){
			solucion.add(n);
			n=n.getParent();
		}
		for (int i=solucion.size()-1;i>=0;i--){
			fwi.write("Movimiento: "+solucion.get(i).getAccion()+"\n");
		}
		
		fwi.close();
	}

	//	metodo general para frontera priority queue
	public Nodo busqueda_acotada(String estrategia, int prof_max){
		boolean solucion = false;
		Nodo n_actual = null;
		Frontera1 frontera=Frontera1.crearFrontera();
		Nodo n_inicial=new Nodo(getEinicial());
		if(tinicio == 0) tinicio = Stat.obtenerTiempo('N');
		frontera.insertar(n_inicial);
		System.out.println("Generando arbol...");
		try {
			while(!solucion && !frontera.esVacia()){
				n_actual = frontera.eliminar();
				if(Estado_Meta(n_actual.getEstado()))
					solucion=true;
				else{
					ArrayList<Sucesor> sucesores = getEde().funcionSucesor(n_actual.getEstado());
					for(int i=0; i<sucesores.size(); i++){
						Nodo n = new Nodo(n_actual, sucesores.get(i).getAccion(), sucesores.get(i).getEstado(), estrategia, prof_max);
						if(n.getParent()!=null)
							frontera.insertar(n);
						
					}
				}
			}
			System.out.println("Arbol generado");

		}catch(OutOfMemoryError e){
			System.out.println("ERROR: Limite de memoria alcanzada. Solución no encontrada");
			return null;
		}
		if(solucion){
			System.out.println("¡Solución encontrada!");
			return n_actual;
		}
		else{
			System.out.println("¡No se ha encontrado solución!");
			return null;
		}
	}

	public Nodo busqueda(String estrategia, int prof_max, int inc_cota) throws IOException{
		Nodo n = null;
	
		int cota_act = inc_cota;
		while (n == null && cota_act <= prof_max) {
			
			n = busqueda_acotada(estrategia, cota_act);
			cota_act += inc_cota;
		}
		
		tfin=Stat.obtenerTiempo('N');
		ttotal = tfin-tinicio;
		return n;
	}

}
