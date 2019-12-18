package objetos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Administrador"/*, uniqueConstraints = {
 @UniqueConstraint(columnNames = "idAdm"),
 @UniqueConstraint(columnNames = "username"),
 @UniqueConstraint(columnNames = "password")}*/)

public class Administrador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue @Id 
	@Column(name="idAdm", unique=true, nullable=false) private int idAdm;
	//@Column(name="username", unique=true, nullable=false, length=50) private String username;
	//@Column(name="password", unique=false, nullable=false, length=50) private String password;

	public Administrador() {
	}
	
	public Administrador(int idAdm/*, String username, String password*/) {
		this.idAdm = idAdm;
		//this.username = username;
		//this.password = password;
	}
	
	/*public String getUsername() {
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
	}*/


	public int getIdAdm() {
		return this.idAdm;
	}

	public void setIdAdm(int idAdm) {
		this.idAdm = idAdm;
	}

}
