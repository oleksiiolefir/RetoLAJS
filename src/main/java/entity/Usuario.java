package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUsr", unique=true, nullable=false) private int idUsr;
	
	@Column(name="admin") private boolean admin;
	@Column(name="username", unique=true, length=50) private String username;
	@Column(name="password", unique=false, length=50) private String password;
	@Column(name="nombre", length=50) private String nombre;
	@Column(name="apellidos", length=100) private String apellidos;
	@Column(name="dni", length=10) private String dni;
	@Column(name="fechaNac") private Date fechaNac;
	
	@OneToMany(mappedBy="usuario") 
	private Set<Reserva> reservas;
	 
	public Usuario() {
	}

	public Usuario(int idUsr, boolean admin, String username, String password, String nombre, String apellidos, String dni,
			Date fechaNac) {
		this.idUsr = idUsr;
		this.admin = admin;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNac = fechaNac;
	}

	public int getIdUsr() {
		return this.idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Set<Reserva> getReservas(){
		return reservas;
	}
}
