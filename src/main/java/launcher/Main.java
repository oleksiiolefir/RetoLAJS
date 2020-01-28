package launcher;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import objetos.Alojamiento;
import util.hibernate.SessionFactoryUtil;
import util.logger.LogLevel;
import util.logger.Logger;

public class Main {

	public static void main(String[] args) {
		Logger.getInstance().log("Inicio sesion", LogLevel.DEBUG, null, null);
		Logger.getInstance().setMinLevel(LogLevel.INFO);

		System.out.println("Creando sesion....");
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		System.out.println("Sesion creada.");
		if (session != null) {
			ArrayList<Alojamiento> list = new DataGetter().getData();
			new JSONGenerator().generateJsonFile(list, "alojamientos.json");
			new DataSetter(list);
		} else
			System.out.println("Error conexion BD");
	}

}
