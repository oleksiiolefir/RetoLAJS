package objetos;

import java.io.Serializable;

public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRes;
	private Alojamiento alojamiento;
	private Cliente cliente;

	public Reserva() {
	}

	public Reserva(int idRes, Alojamiento alojamiento, Cliente cliente) {
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
		return this.alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
