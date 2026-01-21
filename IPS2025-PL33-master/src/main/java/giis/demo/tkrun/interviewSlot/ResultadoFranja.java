package giis.demo.tkrun.interviewSlot;

import giis.demo.ui.interviewSlot.InterviewSlotView.ConflictoFranja;

public class ResultadoFranja {

	private ConflictoFranja conflicto;
	private Integer idFranja;

	public ResultadoFranja(ConflictoFranja conflicto, Integer idFranja) {
		this.conflicto = conflicto;
		this.idFranja = idFranja;
	}

	public ConflictoFranja getConflicto() {
		return conflicto;
	}

	public Integer getIdFranja() {
		return idFranja;
	}

}
