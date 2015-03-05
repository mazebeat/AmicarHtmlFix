package cl.intelidata.amicar.main;

import cl.intelidata.amicar.utils.Archivo;
import cl.intelidata.amicar.utils.Variables;


public class AmicarHtmlFix {

	private static Variables variables;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mensajeInicial();
		try {
			//Lee la ubicacion del archivo
			variables = new Variables();
			Archivo.iniciarLectura(variables.getsPathEntrada(), variables.getsPathSalida());
		} catch (Exception e) {
			System.exit(1);
		}
	}

	private static void mensajeInicial() {
		System.out.println("*********************************************************************************************");
		System.out.println("***********\t\t\t\t\t\t\t\t\t  ***********");
		System.out.println("**********\t\t    Iniciando el fix de los archivos html             \t   **********");
		System.out.println("***********\t\t\t\t\t\t\t\t\t  ***********");
		System.out.println("*********************************************************************************************");

	}

}
