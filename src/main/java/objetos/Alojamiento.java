package objetos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Alojamiento")
public class Alojamiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="idAloj", unique=true, nullable=false) private int idAloj;
	@Column(name="tipo", length=50) private String tipo;
	@Column(name="nombre", length=50) private String nombre;
	@Column(name="descripcion", length=2000) private String descripcion;
	@Column(name="direccion", length=50) private String direccion;
	@Column(name="localidad", length=50) private String localidad;
	@Column(name="telefono", length=50) private String telefono;
	@Column(name="email", length=50) private String email;
	@Column(name="web", length=50) private String web;
	@Column(name="capacidad") private int capacidad;
	@Column(name="latitud") private float latitud;
	@Column(name="longitud") private float longitud;
	
	

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

}
