package launcher;

import util.logger.LogLevel;
import util.logger.Logger;

public class Main {

	public static final String CONFIG_FILEPATH = "src\\main\\java\\hibernate.cfg.xml";

	public static void main(String[] args) {
		Logger.getInstance().log("Inicio sesion", LogLevel.DEBUG, null, null);
		Logger.getInstance().setMinLevel(LogLevel.INFO);

		DataSetter ds = new DataSetter();
		ds.connect(CONFIG_FILEPATH);
		
		/*
		ArrayList<Alojamiento> list = new DataGetter().getData();
		new JSONGenerator().generateJsonFile(list, "alojamientos.json");
		new DataSetter(list);
		*/
	}

}
