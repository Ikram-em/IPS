package giis.demo.tkrun.partidos.entradas;

public class FilaDisponibleDTO {
	private int fila;
	private int asientos_ocupados;
	
	public FilaDisponibleDTO() {}
	
	public FilaDisponibleDTO(int fila, int asientosOcupados) {
		this.fila = fila;
		this.asientos_ocupados = asientosOcupados;
	}

	public int getAsientos_ocupados() {
		return asientos_ocupados;
	}

	public void setAsientos_ocupados(int asientos_ocupados) {
		this.asientos_ocupados = asientos_ocupados;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}
}
