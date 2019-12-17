package Objetos;

public class Alojamiento {

	protected String idAloj;
	protected String tipo;
	protected String nombre;
	protected String descripcion;
	protected String direccion;
	protected String localidad;
	protected String telefono;
	protected String email;
	protected String web;
	protected int capacidad;
	protected float latitud;
	protected float longitud;
		
	public Alojamiento(String idAloj, String tipo, String nombre, String descripcion, String direccion,
			String localidad, String telefono, String email, String web, int capacidad, float latitud, float longitud) {
		super();
		this.idAloj = idAloj;
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.localidad = localidad;
		this.telefono = telefono;
		this.email = email;
		this.web = web;
		this.capacidad = capacidad;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public String getIdAloj() {
		return idAloj;
	}
	public void setIdAloj(String idAloj) {
		this.idAloj = idAloj;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
}
