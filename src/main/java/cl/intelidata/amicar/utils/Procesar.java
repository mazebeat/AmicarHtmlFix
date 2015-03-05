package cl.intelidata.amicar.utils;

public class Procesar {
	/************************************************************************************************************/

	public static String linea(String strLinea) {
		String strLineaProcesada = "";
		if (strLinea.toLowerCase().startsWith("movil")) {
			strLineaProcesada = "01;" + procesarMovil(strLinea);
		} else if (strLinea.toLowerCase().startsWith("fija")) {
			strLineaProcesada = "01;" + procesarFija(strLinea);
		}
		return strLineaProcesada;
	}

	/************************************************************************************************************/
	private static String procesoLineaMovil(String strLinea) {
		String strLineaProcesada = "";
		String[] array = strLinea.split(";");
		strLineaProcesada = array[0] + ";";
		for (int i = 1; i < array.length; i++) {
			if ((i == 7) || (i == 13)) {
				array[i] = procesarFecha(array[i]);
			}
			strLineaProcesada = strLineaProcesada + array[i] + ";";
		}
		return strLineaProcesada;
	}

	/************************************************************************************************************/

	private static String procesarFija(String strLinea) {
		String strLineaProcesada = "";
		String[] array = strLinea.split(";");
		strLineaProcesada = array[0] + ";" + procesoRut(array[1]) + ";" + procesoLineaFija(strLinea);
		return strLineaProcesada;
	}

	/************************************************************************************************************/
	private static String procesoRut(String strLinea) {
		String strLineaProcesada = "";
		int rut = Integer.parseInt(strLinea.substring(0, strLinea.length() - 1));
		strLineaProcesada = String.valueOf(rut) + "-" + strLinea.substring(strLinea.length() - 1);
		return strLineaProcesada;
	}

	/************************************************************************************************************/
	private static String procesoLineaFija(String strLinea) {
		String strLineaProcesada = "";
		String[] array = strLinea.split(";");
		strLineaProcesada = array[2] + ";";
		for (int i = 3; i < array.length; i++) {
			if (i == 3) {
				array[i] = procesarFecha(array[i]);
			}
			strLineaProcesada = strLineaProcesada + array[i] + ";";
		}
		return strLineaProcesada;
	}

	/************************************************************************************************************/
	private static String procesarFecha(String strLinea) {
		String strLineaProcesada = "";
		strLineaProcesada = strLinea.substring(6, 8) + "-" + strLinea.substring(4, 6) + "-" + strLinea.substring(0, 4);
		return strLineaProcesada;
	}

	/************************************************************************************************************/

	private static String procesarMovil(String strLinea) {
		String strLineaProcesada = "";
		strLineaProcesada = procesoLineaMovil(strLinea);
		return strLineaProcesada;
	}
	/************************************************************************************************************/

}
