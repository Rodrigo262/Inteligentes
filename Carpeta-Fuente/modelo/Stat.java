package modelo;

public class Stat {
	/**
	 * método que obtiene la hora del sistema en nanosegundo o milisegundos según el parámetro medida. Se utiliza para el cálculo del tiempo de creación, reconstrucción y generación de imagenes de los artefactos puzzle
	 * @param medida
	 */
	public static long obtenerTiempo(char medida){
		long time = 0;
			switch(medida){
			case 'M':
				time = System.currentTimeMillis();
				break;
			case 'N':
				time = System.nanoTime();
				break;
			}
		return time;
	}
}
