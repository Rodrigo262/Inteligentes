import java.io.IOException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


public class EspacioDeEstados {
//	  private ArrayList<Puzzle> estados = new ArrayList<Puzzle>();
//	  
//	  public EspacioDeEstados(){
//		  this.estados = null;
//	  }
//	  public EspacioDeEstados(Puzzle puzzle_modificado){
//		  this.estados.add(puzzle_modificado);
//	  }
	  
	
	//Hay que pensar si la implementación de este metodo es el correcto. No tengo muy claro con lo que se refiere a un estado válido
	  public static boolean esValido(Puzzle puzzle_modificado){
		  boolean valido = true;
		  if(puzzle_modificado.getPiezas()[0][0].getId()==-1) valido=false;
		  return valido;
	  }
	  //la implementación de este método no es correcta. Unicamente sirve para aquellos puzzles en el que todas sus piezas sean distintas, es decir, el id de las piezas sea {1,2,3,4,5....n}
	  //Pero si el puzzle el blanco, por ejemplo, los ids de las piezas serían {0,1,1,1,1,1,1....} y por tanto no funcionaría. Habría que comparar directamente con el puzzle original pero no estoy seguro de que pueda hacerse
	  public static boolean esObjetivo(Puzzle puzzle_modificado){
		  int id = 0;
		  boolean esObjetivo = true;
		  for(int i = 0; i< puzzle_modificado.getPiezas().length && esObjetivo; i++)
			  for(int j=0; j<puzzle_modificado.getPiezas()[i].length && esObjetivo; j++)
				  if(id == puzzle_modificado.getPiezas()[i][j].getId()) id++;
				  else esObjetivo=false;
		  return esObjetivo;
	  }
	  
	  //Por cada movimiento valido generamos un sucesor con la siguiente estructura {Acción, estado_siguiente, coste_accion}. Consideramos que todas las acciones tienen un coste unitario
	  public static ArrayList<Sucesor> funcionSucesor(Puzzle puzzle_modificado){
		  ArrayList<Sucesor> sucesores = new ArrayList<Sucesor>();
		  ArrayList<Character> movimientos = puzzle_modificado.movimientosValidos();
		  for(int i = 0; i < movimientos.size(); i++){
			  Puzzle aux = new Puzzle();
			  Pieza[][] piezas_sucesor = new Pieza[puzzle_modificado.getPiezas().length][puzzle_modificado.getPiezas()[0].length];
			  for(int j=0; j<puzzle_modificado.getPiezas().length;j++)
				  for(int k=0; k<puzzle_modificado.getPiezas()[0].length;k++)
					  piezas_sucesor[j][k]=puzzle_modificado.getPiezas()[j][k];
			  aux.setPiezas(piezas_sucesor);
			  int[] black = aux.findBlack();
			  aux.initPosition_black(black[0], black[1]);
			  aux.mover(movimientos.get(i));
			  Sucesor sucesor = new Sucesor(movimientos.get(i), aux);
			  sucesores.add(sucesor);
		  }
		  return sucesores;
	  }
	  
//	  public ArrayList<Puzzle> getEspacioEstados(){
//		  return this.estados;
//	  }
}
