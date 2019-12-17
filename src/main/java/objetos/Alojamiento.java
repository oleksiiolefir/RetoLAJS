package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Alojamiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idAloj;
	private String tipo;
	private String nombre;
	private String descripcion;
	private String direccion;
	private String localidad;
	private String telefono;
	private String email;
	private String web;
	private int capacidad;
	private float latitud;
	private float longitud;
	private Set<Reserva> reservas = new HashSet<Reserva>(0);

	public Alojamiento() {
	}

	public Alojamiento(int idAloj, String tipo, String nombre, String descripcion, String direccion, String localidad,
			String telefono, String email, String web, int capacidad, float latitud, float longitud) {
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

	public Alojamiento(int idAloj, String tipo, String nombre, String descripcion, String direccion, String localidad,
			String telefono, String email, String web, int capacidad, float latitud, float longitud, Set<Reserva> reservas) {
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
		this.reservas = reservas;
	}

	public int getIdAloj() {
		return this.idAloj;
	}

	public void setIdAloj(int idAloj) {
		this.idAloj = idAloj;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public float getLatitud() {
		return this.latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return this.longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public Set<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

}
