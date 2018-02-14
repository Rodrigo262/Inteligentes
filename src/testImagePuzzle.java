import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class testImagePuzzle {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Hello");
		int cols = 10;
		int rows = 5;
		String path_original = "images/alhambra.png";
		String path_modificado = "images/alhambra10x5.png";
		Problema problema = new Problema(cols, rows, path_original, path_modificado);
		System.out.println(problema.getEstado_objetivo());
		System.out.println(problema.getEstado_inicial());
		Nodo solucion = problema.buscarSolucion();

		problema.printSolucion(solucion);
		
		

	
		
		

		
		

//		Puzzle puzzle_original = new Puzzle();
//		Puzzle puzzle_original = Puzzle.creacion(rows, cols, path_original);
//		Puzzle puzzle_modificado = Puzzle.reconstruccion(rows, cols, path_modificado, puzzle_original);
//		if(Puzzle.esValido(puzzle_modificado)){
//			System.out.println("Es valido");
//			
//		}
//		else System.out.println("no es valido");
//		System.out.println(puzzle_original);
//		System.out.println(puzzle_modificado);
//		if(puzzle_original.getPiezas()[0][0].getId()!=-1){
//			System.out.println(puzzle_original);
//			Puzzle puzzle_modificado = new Puzzle();
//			puzzle_modificado = puzzle_modificado.reconstruccion(rows, cols, path_modificada, puzzle_original);
//			if(puzzle_modificado.getPiezas()[0][0].getId()!=-1){
//				System.out.println(puzzle_modificado);
//				puzzle_original.generarImagen("puzzle_original");
//				puzzle_modificado.generarImagen("puzzle_modificado");
//				ArrayList<Character> list=puzzle_modificado.movimientosValidos();
//				Iterator<Character> it=list.iterator();
//				while(it.hasNext()){
//					System.out.print(it.next()+" ");
//				}
//				System.out.print("\n");
//				
//			}
//		}
	}
}
