package objetos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCli", unique=true, nullable=false) private int idCli;
	@Column(name="username", unique=true, length=50) private String username;
	@Column(name="password", unique=false, length=50) private String password;
	
	public Cliente() {
	}

	public Cliente(int idCli,String username) {
		this.idCli = idCli;
		this.username = username;
	}

	public int getIdCli() {
		return this.idCli;
	}

	public void setIdCli(int idCli) {
		this.idCli = idCli;
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

}
