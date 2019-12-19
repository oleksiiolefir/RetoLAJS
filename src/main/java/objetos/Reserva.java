package objetos;

import java.io.Serializable;

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

	@ManyToOne @JoinColumn(name="idCli",nullable=false) 
	private Cliente cliente;
	
	public Reserva() {
	}

	public Reserva(int idRes, Alojamiento alojamiento, Cliente cliente) {
		super();
		this.idRes = idRes;
		this.alojamiento = alojamiento;
		this.cliente = cliente;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
