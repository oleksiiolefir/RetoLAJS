package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idCli;
	private Set<Reserva> reservas = new HashSet<Reserva>(0);

	public Cliente() {
	}

	public Cliente(int idCli) {
		this.idCli = idCli;
	}

	public Cliente(int idCli, Set<Reserva> reservas) {
		this.idCli = idCli;
		this.reservas = reservas;
	}

	public int getIdCli() {
		return this.idCli;
	}

	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}

	public Set<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

}
