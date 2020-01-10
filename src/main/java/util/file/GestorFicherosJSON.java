package util.file;

import java.io.File;

public class GestorFicherosJSON extends GestorFicheros {

	public GestorFicherosJSON(File fichero) {
		super(fichero);
	}
	
	@Override
	public boolean escribirFichero() {
		return false;
		
	}
/*
	public static void guardaJsonCamping() {
		ArrayList<Alojamiento> al = new ArrayList<Alojamiento>();
		DescargaWeb dw = new DescargaWeb();
		File archivo = new File("campings.xml");

		dw.hacerDescarga(archivo);
		dw.leerTag(archivo, al);

		// String json = new Gson().toJson(al);

		try {
			FileWriter arc = new FileWriter("campings.json");

			arc.write(new Gson().toJson(al));
			arc.flush();

			arc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(new Gson().toJson(al));

	}

	public static void guardaJsonTuristicos() {
		ArrayList<Alojamiento> al = new ArrayList<Alojamiento>();
		DescargaWeb dw = new DescargaWeb();

		File archivo = new File("alojTuristicos.xml");

		dw.hacerDescarga(archivo);
		dw.leerTag(archivo, al);

		// String json = new Gson().toJson(al);

		try {

			FileWriter arc = new FileWriter("alojT.json");

			arc.write(new Gson().toJson(al));
			arc.flush();

			arc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(new Gson().toJson(al));

	}

	public static void guardaJsonRural() {
		ArrayList<Alojamiento> al = new ArrayList<Alojamiento>();
		DescargaWeb dw = new DescargaWeb();

		File archivo = new File("alojRural.xml");
		dw.hacerDescarga(archivo);
		dw.leerTag(archivo, al);

		// String json = new Gson().toJson(al);

		try {

			FileWriter arc = new FileWriter("alojR.json");
			arc.write(new Gson().toJson(al));
			arc.flush();

			arc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(new Gson().toJson(al));

	}*/
}
