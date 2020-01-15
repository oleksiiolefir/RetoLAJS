package objetos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESERVA")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRes", unique=true, nullable=false) 
	private int idRes;
	
	@ManyToOne @JoinColumn(name="idAloj",nullable=false) 
	private Alojamiento alojamiento;

	@ManyToOne @JoinColumn(name="idUsr",nullable=false) 
	private Usuario usuario;
	
	@Column(name="fechaEntrada", nullable=false) private Date fechaEntrada;
	@Column(name="fechaSalida", nullable=false) private Date fechaSalida;
	

	public Reserva() {
	}

	public Reserva(int idRes, Alojamiento alojamiento, Usuario usuario, Date fechaEntrada, Date fechaSalida) {
		this.idRes = idRes;
		this.alojamiento = alojamiento;
		this.usuario = usuario;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	public int getIdRes() {
		return this.idRes;
	}

	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
}
