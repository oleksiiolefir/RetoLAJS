package launcher;

import java.util.ArrayList;

import objetos.Alojamiento;
import util.logger.LogLevel;
import util.logger.Logger;

public class Main {
	
	public static void main(String[] args) {	
		Logger.getInstance().log("Inicio sesion", LogLevel.DEBUG, null, null);
		Logger.getInstance().setMinLevel(LogLevel.INFO);
		
		
		ArrayList<Alojamiento> list = new DataGetter().getData();
		new JSONGenerator().generateJsonFile(list, "alojamientos.json");
		new DataSetter(list);
		
	}

}
