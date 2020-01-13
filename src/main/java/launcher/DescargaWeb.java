package launcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

	private BufferedReader bReader;
	private BufferedWriter bWriter;
	
	public void descargarURL(URL url, File archivo) {
		try {
			bReader = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
			bWriter = new BufferedWriter(new FileWriter(archivo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//escribirFichero();
		//NodeList nodeList = leerNodosXML(archivo);
		//leerTags(nodeList);
	}
	
	private void leerTags(NodeList nodeList) {
		for(int i=0;i<nodeList.getLength();i++) {
			Node node = nodeList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				getTagContent((Element) node, "signatura");
			}
		}
	}

	private String getTagContent(Element element, String tag) {
		return element.getElementsByTagName(tag).item(0).getTextContent();		
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
	
	public ArrayList<Alojamiento> leerTag(File archivo, ArrayList<Alojamiento> aloj) {
		try {                  
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            System.out.println(archivo.getAbsolutePath());
            
            document.getDocumentElement().normalize();
            
            NodeList listaUsuarios = document.getElementsByTagName("row");
            System.out.println(listaUsuarios.getLength());
            for(int i = 0 ; i < listaUsuarios.getLength() ; i++) {
            	Alojamiento aloj1 = new Alojamiento();
                Node nodo = listaUsuarios.item(i);
                
                //System.out.println("Elemento: " + nodo.getNodeName());
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    try {
                    	aloj1.setIdAloj(Integer.parseInt(element.getElementsByTagName("signatura").item(0).getTextContent()));
                    }catch(Exception ex) {                    	
                    	aloj1.setIdAloj(i);
                    }
                    try {                 	
                        aloj1.setTipo(element.getElementsByTagName("lodgingtype").item(0).getTextContent());
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
                        aloj1.setLocalidad(element.getElementsByTagName("municipality").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setLocalidad(" ");
                    }
                    try {
                        aloj1.setProvincia(element.getElementsByTagName("territory").item(0).getTextContent());
                    }catch(Exception ex){
                    	aloj1.setProvincia(" ");
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

                }
                aloj.add(aloj1);
            }
            //eliminarDuplicados(aloj);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
		return aloj;
	}

	/*
	private void eliminarDuplicados(ArrayList<Alojamiento> list) {	
		for(int i=0;i<list.size();i++) {
			for(int j=i;j<list.size()-1;j++) {
				if(list.get(i).getIdAloj().equals(list.get(j+1).getIdAloj())) {
					list.remove(j+1);
					System.out.println("Borrado " + (j+1));
					//break;
				}
			}
		}	
	}*/
	
}
