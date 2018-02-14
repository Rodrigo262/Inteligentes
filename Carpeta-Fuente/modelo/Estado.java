package modelo;
import java.io.*;


public class Estado extends Puzzle{
	
	public Estado(){}
	
	public Estado(int rows, int cols, String path, Pieza[][] original) throws IOException{
		super(rows, cols, path, original);
	}
	public int heuristica(){
		int count=0;
		for(int i=0; i<EspacioDeEstados.eobjetivo.getPiezas().length; i++)
			for(int j=0; j<EspacioDeEstados.eobjetivo.getPiezas()[0].length; j++)
				if(EspacioDeEstados.eobjetivo.getPiezas()[i][j].getId()!=this.getPiezas()[i][j].getId()&&this.getPiezas()[i][j].getId()!=0)
					count++;
		return count;
	}
}

