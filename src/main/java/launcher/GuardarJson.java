package launcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import objetos.Alojamiento;
import objetos.Cliente;

public class GuardarJson {

	public static void main(String[] args) {
		guardaJson();
	}
	public static void guardaJson() {
		ArrayList<Alojamiento> al = new ArrayList<Alojamiento>();
		DescargaWeb dw = new DescargaWeb();
		File archivo = new File("landerArchivo.xml");
		dw.hacerDescarga(archivo);
		dw.leerTag(archivo, al);
		
		//String json = new Gson().toJson(al);
		
		try {
			FileWriter arc = new FileWriter("ArchivoJson.json");
			arc.write(new Gson().toJson(al));
			arc.flush();
			arc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(new Gson().toJson(al));
		
	}
}


