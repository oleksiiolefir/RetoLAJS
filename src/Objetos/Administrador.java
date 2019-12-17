package Objetos;

import java.io.Serializable;


public class Administrador {

	protected int idAdm;
	protected String nombre;
	protected String password;
	
	
	public Administrador(int idAdm, String nombre, String password) {
		super();
		this.idAdm = idAdm;
		this.nombre = nombre;
		this.password = password;
	}
	
	public int getIdAdm() {
		return idAdm;
	}
	public void setIdAdm(int idAdm) {
		this.idAdm = idAdm;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
