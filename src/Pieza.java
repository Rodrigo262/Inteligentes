import java.awt.image.BufferedImage;

public class Pieza {
	private int id;
	private BufferedImage image;
	
	public Pieza(int id, BufferedImage image){
		this.id = id;
		this.image = image;
	}
	public Pieza(){
		this.id = -1;
		this.image = null;
	}
	
	public Pieza(int id){
		this.id = id;
	}
	
	public Pieza(BufferedImage image){
		this.id = -1;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Método para la comparación de imágenes entre piezas. Devuelve un booleano igual a true si existe coincidencia entre las imágenes de los objetos pieza invocante y pasado como parámetro y false en casa contrario
	 * @param p
	 * @return boolean
	 */
	public boolean equals(Pieza p){
		if(getImage().getWidth()!=p.getImage().getWidth() || getImage().getHeight()!=p.getImage().getHeight())	return false;
		int w = getImage().getWidth();
		int h = getImage().getHeight();
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(getImage().getRGB(j,i) != p.getImage().getRGB(j,i))
					return false;
			}
		}
		return true;
	}
	
}
