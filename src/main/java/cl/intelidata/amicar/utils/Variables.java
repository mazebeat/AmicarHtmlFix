package cl.intelidata.amicar.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Variables {

	private String sPathEntrada;
	private String sPathSalida;

	public Variables() {

		this.cargar();
	}

	private void cargar() {

		File archivo;
		FileReader frArchivo;
		BufferedReader brLeerArchivo = null;
		String sContenido = null;
		// comenzamos a manipular el archivo
		try {
			// creamos todos los objetos
			archivo = new File("config.ini");
			frArchivo = new FileReader(archivo);
			brLeerArchivo = new BufferedReader(frArchivo);

			while ((sContenido = brLeerArchivo.readLine()) != null) {
				// Lee el archivo de configuracion y saca el directorio de
				// entrada y el de salida
				System.out.println(sContenido);
				if (sContenido.substring(1, 8).trim().equalsIgnoreCase("entrada")) {
					this.setsPathEntrada(sContenido.substring(10));
				} else if (sContenido.substring(1, 7).trim().equalsIgnoreCase("salida")) {
					this.setsPathSalida(sContenido.substring(9));
				}

			}
		} catch (IOException ex) {
			System.out.println("Problemas al leer el config.ini ");
			System.out.println("Error de lectura: " + ex.getMessage());

		} finally {
			try {
				brLeerArchivo.close();
			} catch (IOException ex) {
				Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public String getsPathEntrada() {
		return sPathEntrada;
	}

	public void setsPathEntrada(String sPathEntrada) {
		this.sPathEntrada = sPathEntrada;
	}

	public String getsPathSalida() {
		return sPathSalida;
	}

	public void setsPathSalida(String sPathSalida) {
		this.sPathSalida = sPathSalida;
	}

}
