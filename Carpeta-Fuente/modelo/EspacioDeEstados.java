package modelo;
import java.io.IOException;
import java.util.ArrayList;


public class EspacioDeEstados {
	public static Estado eobjetivo;

		
	  public EspacioDeEstados(String path, int rows, int cols) throws IOException{
		  this.eobjetivo = new Estado(rows, cols, path, null);
		  System.out.println(this.eobjetivo);
	  }

	  public boolean esValido(Estado Estado_modificado){
		  boolean valido = true;
		  if(Estado_modificado.getPiezas()[0][0].getId()==-1){
			  valido=false;
			  System.out.println("Estado inicial no válido");
		  }
		  return valido;
	  }
	  
	  public boolean esObjetivo(Estado Estado_modificado){
		  int id = 0;
		  boolean esObjetivo = true;
		  for(int i = 0; i< Estado_modificado.getPiezas().length && esObjetivo; i++)
			  for(int j=0; j<Estado_modificado.getPiezas()[i].length && esObjetivo; j++)
				  if(Estado_modificado.getPiezas()[i][j].getId()!=eobjetivo.getPiezas()[i][j].getId()) esObjetivo=false;
		  return esObjetivo;
	  }
	  
	  //Por cada movimiento valido generamos un sucesor con la siguiente estructura {Acción, estado_siguiente, coste_accion}. Consideramos que todas las acciones tienen un coste unitario
	  public ArrayList<Sucesor> funcionSucesor(Estado Estado_modificado){
		  ArrayList<Sucesor> sucesores = new ArrayList<Sucesor>();
		  ArrayList<Character> movimientos = Estado_modificado.movimientosValidos();
		  for(int i = 0; i < movimientos.size(); i++){
			  Estado aux = new Estado();
			  Pieza[][] piezas_sucesor = new Pieza[Estado_modificado.getPiezas().length][Estado_modificado.getPiezas()[0].length];
			  for(int j=0; j<Estado_modificado.getPiezas().length;j++)
				  for(int k=0; k<Estado_modificado.getPiezas()[0].length;k++)
					  piezas_sucesor[j][k]=Estado_modificado.getPiezas()[j][k];
			  aux.setPiezas(piezas_sucesor);
			  aux.initPosition_black(Estado_modificado.getPosition_black()[0], Estado_modificado.getPosition_black()[1]);
			  aux.mover(movimientos.get(i));
			  Sucesor sucesor = new Sucesor(movimientos.get(i), aux);
			  sucesores.add(sucesor);
		  }
		  return sucesores;
	  }
}
