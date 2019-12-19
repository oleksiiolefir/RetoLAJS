package objetos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRes", unique=true, nullable=false) private int idRes;
	@Column(name="idAloj") private int idAloj;
	@Column(name="idCli") private int idCli;
	
	/*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Alojamiento alojamiento;
	
	 @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(unique = true)
	 private Cliente cliente;*/

	public Reserva() {
	}

	public Reserva(int idRes, Alojamiento alojamiento, Cliente cliente) {
		this.idRes = idRes;
		/*this.alojamiento = alojamiento;
		this.cliente = cliente;*/
	}

	public int getIdRes() {
		return this.idRes;
	}

	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}
	/*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAloj")
	public Alojamiento getAlojamiento() {
		return this.alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCli")
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}*/

}
