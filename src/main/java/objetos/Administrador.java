package objetos;

import java.io.Serializable;

public class Administrador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idAdm;

	public Administrador() {
	}

	public Administrador(int idAdm) {
		this.idAdm = idAdm;
	}

	public int getIdAdm() {
		return this.idAdm;
	}

	public void setIdAdm(int idAdm) {
		this.idAdm = idAdm;
	}

}
