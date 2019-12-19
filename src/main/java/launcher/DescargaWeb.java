package launcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import objetos.Alojamiento;

public class DescargaWeb {

	public static void main(String[] args) {
		DescargaWeb descarga = new DescargaWeb();
		File archivo = new File("alojTuristicos.xml");
		File archivo1 = new File("alojRural.xml");
		File archivo2 = new File("campings.xml");
		ArrayList<Alojamiento> aloj = new ArrayList<Alojamiento>();
		descarga.hacerDescarga(archivo);
		descarga.hacerDescarga(archivo1);
		descarga.hacerDescarga(archivo2);
		descarga.leerTag(archivo, aloj);
		descarga.leerTag(archivo1, aloj);
		descarga.leerTag(archivo2, aloj);
		System.out.println(aloj.size());
		
	}
	
	public void hacerDescarga(File archivo) {
		try {
			URL url = null;
			System.out.println(archivo.getName());
			if(archivo.getName()=="alojTuristicos.xml") {
				url = new URL("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/alojamiento_de_euskadi/opendata/alojamientos.xml");
			}else if(archivo.getName()=="alojRural.xml") {
				url = new URL("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/alojamientos_rurales_euskadi/opendata/alojamientos.xml");
			}else if(archivo.getName()=="campings.xml") {
				url = new URL("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/campings_de_euskadi/opendata/alojamientos.xml");
			}
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
			String cadena;		
			while((cadena = bf.readLine())!=null) {
				bw.write(cadena);
			}
			bf.close();
			bw.close();
			System.out.println("archivo creado");
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void leerTag(File archivo, ArrayList<Alojamiento> aloj) {
		try {            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            System.out.println(archivo.getAbsolutePath());
            
            document.getDocumentElement().normalize();
            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            
            NodeList listaUsuarios = document.getElementsByTagName("row");
            System.out.println(listaUsuarios.getLength());
            for(int i = 0 ; i < listaUsuarios.getLength() ; i++) {
            	Alojamiento aloj1 = new Alojamiento();
                Node nodo = listaUsuarios.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    aloj1.setIdAloj(i);
                    try {                 	
                        aloj1.setTipo(element.getElementsByTagName("templatetype").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setTipo(" ");
                    }
                    try {
                    	aloj1.setNombre(element.getElementsByTagName("documentname").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setNombre(" ");
                    }
                    try {
                    	aloj1.setDescripcion(element.getElementsByTagName("turismdescription").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setDescripcion(" ");
                    }
                    try {
                    	aloj1.setDireccion(element.getElementsByTagName("address").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setDireccion(" ");
                    }    
                    try {
                        aloj1.setLocalidad(element.getElementsByTagName("marks").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setLocalidad(" ");
                    }
                    try {
                        aloj1.setTelefono(element.getElementsByTagName("phone").item(0).getTextContent().replace(" ",""));
                    }catch(Exception ex){
                    	aloj1.setTelefono(" ");
                    }
                    try {
                        aloj1.setEmail(element.getElementsByTagName("tourismemail").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setEmail(" ");
                    }
                    try {
                        aloj1.setWeb(element.getElementsByTagName("web").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setWeb(" ");
                    }
                    try {
                        aloj1.setCapacidad(Integer.parseInt(element.getElementsByTagName("capacity").item(0).getTextContent()));
                    }catch(Exception ex){
                    	aloj1.setCapacidad(0);
                    }
                    try {
                        aloj1.setLatitud(Float.valueOf(element.getElementsByTagName("latwgs84").item(0).getTextContent()));
                    }catch(Exception ex){
                    	aloj1.setLatitud(0);
                    }
                    try {
                        aloj1.setLongitud(Float.valueOf(element.getElementsByTagName("lonwgs84").item(0).getTextContent()));
                    }catch(Exception ex){
                    	aloj1.setLongitud(0);
                    }

                    System.out.println(aloj1.getTipo());
                    System.out.println(aloj1.getNombre());
                    System.out.println(aloj1.getDescripcion());
                    System.out.println(aloj1.getDireccion());
                    System.out.println(aloj1.getLocalidad());
                    System.out.println(aloj1.getTelefono());
                    System.out.println(aloj1.getEmail());
                    System.out.println(aloj1.getWeb());
                    System.out.println(aloj1.getCapacidad());
                    System.out.println(aloj1.getLatitud());
                    System.out.println(aloj1.getLongitud());

                    System.out.println("");
                    
                }
                
                aloj.add(aloj1);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	public void llerTags2(File archivo) {
		
	}

}
