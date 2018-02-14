package modelo;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import java.util.ArrayList;

public class Puzzle {
	protected Pieza piezas[][];
	protected int[] position_black;


	public Puzzle(){
		this.piezas=null;
		this.position_black=new int[2];
	}
	/**
	 * Devuelve un elemento Puzzle de dimensiones (cols x rows)-1 piezas, cada una de ellas con una porción de la imagen a partir del fichero con extensión .png pasada por parámetro. Si el parámetro “original” de tipo Puzzle en el momento de la invocación en nulo, el puzzle generado corresponde a la fase de creación y en caso contrario a la fase de reconstrucción.
	 * @param rows
	 * @param cols
	 * @param path
	 * @param original
	 * @throws IOException
	 */

	protected Puzzle(int rows, int cols, String path, Pieza[][] original) throws IOException{
		this.piezas = new Pieza[rows][cols];
		this.position_black=new int[2];
		this.position_black[0]=-1;
		this.position_black[1]=-1;
		splitImage(path, original);
	}
	/**
	 * Crea un elemento puzzle “original” de dimensiones (cols x rows)-1 piezas a partir de un fichero path con extensión .png.
	 * @param rows
	 * @param cols
	 * @param path
	 * @return Puzzle
	 * @throws IOException
	 */
	public Puzzle creacion(int rows, int cols, String path) throws IOException{
		long ini = Stat.obtenerTiempo('M');
		Puzzle original = new Puzzle(rows, cols, path, null);
		long fin = Stat.obtenerTiempo('M');
		System.out.println("Tiempo total en la creación del puzzle: "+(fin-ini)+"ms");
		return original;
	}

	/**
	 * Reconstruye un elemento Puzzle de dimensiones (cols x rows)-1 piezas a partir de un fichero con extensión .png correspondiente a la imagen generada de un artefacto Puzzle modificado
	 * @param rows
	 * @param cols
	 * @param path
	 * @param original
	 * @return Puzzle
	 * @throws IOException
	 */
	public Puzzle reconstruccion(int rows, int cols, String path, Pieza[][] piezas_original) throws IOException{
		long ini = Stat.obtenerTiempo('M');
		Puzzle modificado = new Puzzle(rows, cols, path, piezas_original);
		long fin = Stat.obtenerTiempo('M');
		System.out.println("Tiempo total en la reconstruccion del puzzle: "+(fin-ini)+"ms");
		return modificado;
	}

	/**
	 * Genera una lista de movimientos válidos según {“U”, “D”, “L”, “R”} (Up, Down, Left, Right) en función de la posición que ocupe la pieza vacía del artefacto puzzle.
	 * @return ArrayList<Character>
	 */
	public ArrayList<Character> movimientosValidos(){
		int rows=getPiezas().length;
		int cols=getPiezas()[0].length;
		ArrayList<Character> list=new ArrayList<Character>();
		if(getPosition_black()[0]!=0) list.add('U');
		if(getPosition_black()[0]!=rows-1) list.add('D');
		if(getPosition_black()[1]!=0) list.add('L');
		if(getPosition_black()[1]!=cols-1) list.add('R');
		return list;
	}

	/**
	 * Compara si la imagen correspondiente a la pieza[ pos_i ][ pos_j ] del Puzzle coincide con alguna de las imágenes de las piezas existentes en el puzzle. Devuelve el id de la pieza a insertar en caso de coincidencia y -1 en caso contrario.
	 * @param pos_i
	 * @param pos_j
	 * @return int
	 */
	public int checkImagenesInPuzzle(int pos_i, int pos_j){
		int id=-1;
		int rows=getPiezas().length;
		int cols=getPiezas()[0].length;
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				if(i==pos_i && j==pos_j) return id;
				if(getPiezas()[i][j].equals(getPiezas()[pos_i][pos_j])){
					id=getPiezas()[i][j].getId();
					return id;
				}
			}
		}
		return id;
	}
	/**
	 * Comparar las imágenes entre artefactos Puzzle original y modificado y garantiza la correspondencia entre estos. Devuelve el id de la pieza del puzzle original en caso de coincidencia y -1 en caso contrario.
	 * @param pieza
	 * @return int
	 */
	public int checkImagesBetweenPuzzle(Pieza pieza, Pieza[][] piezas_original){
		int id = -1;
		int rows = piezas_original.length;
		int cols = piezas_original[0].length;
		for(int i=0; i<rows && id==-1; i++){
			for(int j=0; j<cols && id==-1; j++){
				if(pieza.equals(piezas_original[i][j])){
					id = piezas_original[i][j].getId();
				}
			}
		}
		return id;
	}

	/**
	 * Devuelve el id de la pieza anterior a la posición pos_i, pos_j en el caso en el que no exista ninguna pieza con la misma imagen en el puzzle. Se utiliza en la construcción del Puzzle original.
	 * @param pos_i
	 * @param pos_j
	 * @return
	 */
	public int getIdAnterior(int pos_i, int pos_j){
		int id;
		int cols=getPiezas()[0].length;
		if(pos_j==0) id=getPiezas()[pos_i-1][cols-1].getId();
		else id=getPiezas()[pos_i][pos_j-1].getId();
		return id;
	}

	/**
	 * Metodo que divide una imagen almacenada en path en n rows y m cols. El metodo acepta como parámetro un objeto puzzle que será utilizado en el caso en el que se nos proporcione una puzzle modificado
	 * @param rows
	 * @param cols
	 * @param path
	 * @param puzzle
	 * @throws IOException
	 */
	private void splitImage(String path, Pieza[][] piezas_original) throws IOException{
		boolean resoluble=true;
		File file = new File(path); 
		FileInputStream fis = new FileInputStream(file);
		long t1 = Stat.obtenerTiempo('M');
		BufferedImage image = ImageIO.read(fis);
		long t2 = Stat.obtenerTiempo('M');
		System.out.println("Tiempo ImageIO: "+(t2-t1)+"ms");
		int rows=getPiezas().length;
		int cols=getPiezas()[0].length;
		int cellWidth = image.getWidth() / cols; 
		int cellHeight = image.getHeight() / rows;
		int id;

		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				getPiezas()[i][j]=new Pieza(new BufferedImage(cellWidth, cellHeight, image.getType()));
				Graphics2D gr = getPiezas()[i][j].getImage().createGraphics();
				if(piezas_original==null){
					if(i==0 && j==0){
						gr.setColor(Color.BLACK);
						getPiezas()[i][j].setId(0);
						initPosition_black(0, 0);
					}
					else{
						gr.drawImage(image, 0, 0, cellWidth, cellHeight, cellWidth * j, cellHeight * i, cellWidth * j + cellWidth, cellHeight * i + cellHeight, null);
						id=checkImagenesInPuzzle(i, j);
						if(id==-1) getPiezas()[i][j].setId(getIdAnterior(i, j)+1);
						else getPiezas()[i][j].setId(id);
					}
				}
				else{
					gr.drawImage(image, 0, 0, cellWidth, cellHeight, cellWidth * j, cellHeight * i, cellWidth * j + cellWidth, cellHeight * i + cellHeight, null);
					id=checkImagesBetweenPuzzle(getPiezas()[i][j], piezas_original);
					if(id!=-1){
						getPiezas()[i][j].setId(id);
						if(id==0) initPosition_black(i, j);
					}
					else{
						resoluble=false;
						getPiezas()[0][0].setId(-1);
					}
				}
				gr.dispose();
			}
		}

		if(getPosition_black()[0]==-1 || getPosition_black()[1]==-1) getPiezas()[0][0].setId(-1);
		if(piezas_original==null)
			System.out.println("Creación terminada");
		else 
			System.out.println("Reconstrucción terminada");
	}



	/**
	 * Método que genera un fichero con extensión .png a partir de las imágenes de cada una de las piezas que conforman el artefacto Puzzle. Toma como parámetro el nombre de fichero de salida
	 * @param OutputFileName
	 * @throws IOException
	 */
	public void generarImagen(String OutputFileName) throws IOException{
		int cellWidth = this.getPiezas()[0][0].getImage().getWidth();
		int cellHeight = this.getPiezas()[0][0].getImage().getHeight();
		int type = this.getPiezas()[0][0].getImage().getType();

		BufferedImage resultImg = new BufferedImage(cellWidth*this.getPiezas()[0].length, cellHeight*this.getPiezas().length, type);

		for (int i = 0; i < this.getPiezas().length; i++) {
			for (int j = 0; j < this.getPiezas()[i].length; j++) {
				resultImg.createGraphics().drawImage(this.getPiezas()[i][j].getImage(), cellWidth * j, cellHeight * i, null);

			}
		}
		System.out.println("Image "+OutputFileName+".png concatenated.....");
		ImageIO.write(resultImg, "png", new File(OutputFileName+".png"));
	}

	public void generarImagen(String OutputFileName, int width, int height) throws IOException{
		int type = this.getPiezas()[0][0].getImage().getType();

		BufferedImage resultImg = new BufferedImage(width, height, type);

		for (int i = 0; i < this.getPiezas().length; i++) {
			for (int j = 0; j < this.getPiezas()[i].length; j++) {
				resultImg.createGraphics().drawImage(this.getPiezas()[i][j].getImage(), width, height, null);
			}
		}
		ImageIO.write(resultImg, "png", new File("images/"+OutputFileName+".png"));
	}

	/**
	 * Método que ejecuta un movimiento en la pieza del artefacto Puzzle según un movimiento válido pasado como parámetro entre {“U”, “D”, “L”, “R”} (Up, Down, Left, Right).
	 */
	public void mover(char c){
		Pieza aux;
		switch (c){
		case 'U':
			aux = this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]];
			this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]]=this.getPiezas()[getPosition_black()[0]-1][getPosition_black()[1]];
			this.getPiezas()[getPosition_black()[0]-1][getPosition_black()[1]]=aux;
			this.setPosition_black(-1,0);
			break;
		case 'D':
			aux = this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]];
			this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]]=this.getPiezas()[getPosition_black()[0]+1][getPosition_black()[1]];
			this.getPiezas()[getPosition_black()[0]+1][getPosition_black()[1]]=aux;
			this.setPosition_black(1,0);
			break;
		case 'L':
			aux = this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]];
			this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]]=this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]-1];
			this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]-1]=aux;
			this.setPosition_black(0,-1);
			break;
		case 'R':
			aux = this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]];
			this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]]=this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]+1];
			this.getPiezas()[getPosition_black()[0]][getPosition_black()[1]+1]=aux;
			this.setPosition_black(0,1);
			break;
		}
	}

	/**
	 * método que imprime por consola el id de cada una de las piezas que conforman el artefacto Puzzle.
	 */
	public String toString(){
		String s ="";
		for(int i = 0; i < getPiezas().length; i++){
			for(int j = 0; j < getPiezas()[i].length; j++){
				s=s+getPiezas()[i][j].getId()+" ";
			}
			s=s+"\n";
		}
		return s;
	}

	public int[] findBlack(){
		int[] black = new int[2];
		for(int i=0; i < getPiezas().length; i++)
			for(int j=0; j < getPiezas()[0].length; j++)
				if(this.getPiezas()[i][j].getId()==0){
					black[0]=i;
					black[1]=j;
				}
		return black;
	}

	public Pieza[][] getPiezas() {
		return piezas;
	}

	public void setPiezas(Pieza[][] piezas){
		this.piezas = piezas;
	}

	public int[] getPosition_black() {
		return position_black;
	}

	public void setPosition_black(int x, int y) {
		this.position_black[0]+=x;
		this.position_black[1]+=y;
	}
	public void initPosition_black(int x, int y) {
		this.position_black[0] = x;
		this.position_black[1] = y;
	}
}

