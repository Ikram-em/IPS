package giis.demo.tkrun.partidos.entradas;

public class AsientosReservadosDTO {
	private String matchName;
	private String tribuneName;
	private String sectionName;
	private int row;
	private int seatsBought;
	private int initialSeat;
	private Double totalPrice;
	
	public AsientosReservadosDTO(String matchName, String tribuneName, String sectionName, int row, int seatsBought, int initialSeat, Double totalPrice) {
		this.tribuneName = tribuneName;
		this.sectionName = sectionName;
		this.row = row;
		this.seatsBought = seatsBought;
		this.initialSeat = initialSeat;
		this.matchName = matchName;
		this.totalPrice = totalPrice;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public String getMatchName() {
		return matchName;
	}

	public String getTribuneName() {
		return tribuneName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public int getRow() {
		return row;
	}

	public int getSeatsBought() {
		return seatsBought;
	}

	public int getInitialSeat() {
		return initialSeat;
	}
}
