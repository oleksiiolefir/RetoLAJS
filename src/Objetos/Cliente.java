package Objetos;

public class Cliente {

	protected int idCli;
	protected String nombre;
	protected String password;
	
	public Cliente(int idCli, String nombre, String password) {
		super();
		this.idCli = idCli;
		this.nombre = nombre;
		this.password = password;
	}
	
	public int getIdCli() {
		return idCli;
	}
	public void setIdCli(int idCli) {
		this.idCli = idCli;
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
