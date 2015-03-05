package cl.intelidata.amicar.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jam.superutils.FastFileTextReader;

public class Archivo {
	private static String strPathEntrada;
	private static String strPathSalida;

	/************************************************************************************************************/
	/**
	 * inicia la lectura del archivo, metodo publico que arranca la clase
	 * 
	 * @param strDirectorioEntrada
	 * @param strDirectorioSalida
	 */
	public static void iniciarLectura(String strDirectorioEntrada, String strDirectorioSalida) {
		try {
			setPath(strDirectorioEntrada, strDirectorioSalida);
			System.out.println("Iniciando el procesamiento de los archivos alojados en: " + strDirectorioEntrada);
			leerDirectorio();
			/********************************************************************/
			// Confirma la lectura exitosa
			System.out.println("Procesamiento de los archivos completado con exito...");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/************************************************************************************************************/
	private static void setPath(String strDirectorioEntrada, String strDirectorioSalida) {
		setStrPathEntrada(strDirectorioEntrada);
		setStrPathSalida(strDirectorioSalida);
	}

	/************************************************************************************************************/
	/**
	 * lee el directorio de entrada indicado en el config.ini luego toma uno a
	 * uno los archivos y los pasa al metodo que se encarga de leer cada uno de
	 * ellos
	 */
	private static void leerDirectorio() {

		try {
			File directorio = new File(getStrPathEntrada().trim());
			File listaArchivoControl[] = directorio.listFiles();
			String strArchivo;
			String strNombre;
			if (listaArchivoControl == null) {
				System.out.println("El directorio se encuentra vacio");
			} else {
				for (File path : listaArchivoControl) {
					strArchivo = path.toString();
					strNombre = path.getName().toString();
					// Comienza a leer cada uno de los archivos Html
					if (strNombre.contains(".html")||strNombre.contains(".htm")) {
						System.out.println("Iniciando lectura del archivo  " + strArchivo);
						leer(strArchivo, strNombre);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problemas al leer el directorio");
			System.exit(1);
		}
	}

	/************************************************************************************************************/

	/**
	 * inicia la lectura del archivo que le fue entregado por parametro
	 * 
	 * @param strArchivo
	 *            Ruta del archivo
	 * @param strNombre
	 *            Nombre del archivo a leer
	 */
	private static void leer(String strArchivo, String strNombre) {
		// Atributos y variables locales
		String strLinea = "";
		boolean haylink = false;
		try {
			/********************************************************************/
			System.out.println("leyendo del archivo...");
			// Crea el objeto que leera el archivo
			FastFileTextReader fastFileTextReader = new FastFileTextReader(strArchivo.trim(), FastFileTextReader.ISO_8859_1, 1024 * 10);
			/********************************************************************/
			// Comienza la lectura del archivo linea por linea
			System.out.println("Escribiendo el archivo..."); // Eliminar
			System.out.println("Procesando los datos..."); // Eliminar
			while ((strLinea = fastFileTextReader.readLine()) != null) {
				if (strLinea.equals(Cadena.IMG_ORIGINAL)) {
					System.out.println("Imagen link a cambiar encontrada: " + Cadena.IMG_ORIGINAL);
					haylink = true;
				}
				if (haylink && strLinea.contains(Cadena.LINK_ORIGINAL)) {
					System.out.println("Modificando el enlace");
					String enlace = strLinea.replace(Cadena.CADENA_INICIO, "");
					enlace = enlace.replace(Cadena.CADENA_FINAL, "");
					guardar(Cadena.IMG_NUEVA_INICIO + enlace + Cadena.IMG_NUEVA_FINAL, strNombre);
					guardar(Cadena.LINK_NUEVA, strNombre);
					haylink = false;
				} else if (!haylink) {
					guardar(strLinea, strNombre);
				}

			}
			/********************************************************************/
			// Intenta el cierre del objeto que lee el archivo
			try {
				fastFileTextReader.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problemas al cerrar el archivo");
				System.exit(1);
			}
			// Confirma la lectura exitosa
			System.out.println("Proceso del archivo teminado con exito");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problemas al leer el archivo");
			System.exit(1);
		}
	}

	/************************************************************************************************************/
	/**
	 * guarda la linea indicada en el archivo indicado
	 * 
	 * @param strDatos
	 *            linea a escribir en el archivo
	 * @param strArchivo
	 *            nombre del archivo a guardar
	 */
	private static void guardar(String strDatos, String strArchivo) {
		// Atributos y variables locales
		String strNuevoArchivo;
		BufferedWriter bufferedWriter = null;
		// indica donde saldra el archivo final
		strNuevoArchivo = getStrPathSalida().trim() + strArchivo;

		/********************************************************************/
		// Comienza la escritura del archivo
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(strNuevoArchivo, true));
			bufferedWriter.append(strDatos);
			bufferedWriter.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} finally {
			/********************************************************************/
			// Cierra el buffer de escritura
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			/********************************************************************/
		}
		/********************************************************************/

	}

	public static String getStrPathEntrada() {
		return strPathEntrada;
	}

	public static void setStrPathEntrada(String strPathEntrada) {
		Archivo.strPathEntrada = strPathEntrada;
	}

	public static String getStrPathSalida() {
		return strPathSalida;
	}

	public static void setStrPathSalida(String strPathSalida) {
		Archivo.strPathSalida = strPathSalida;
	}

	/************************************************************************************************************/

}
