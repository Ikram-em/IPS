package giis.demo.tkrun.teammanagement;

public class EquipoDTO {
	
	private int id;
	private String name;
	private int primerEntrenador;
	
	private int segundoEntrenador;
	private TeamType tipoEquipo;
	private FormationTeamCategory formationTeamCategory;
	
	public EquipoDTO() {
		
	}

	public EquipoDTO(int id, String name, int primerEntrenador, int segundoEntrenador, TeamType tipoEquipo, FormationTeamCategory formationTeamCategory) {
		this.id = id;
		this.name = name;
		this.primerEntrenador = primerEntrenador;
		this.segundoEntrenador = segundoEntrenador;
		this.tipoEquipo = tipoEquipo;
		this.formationTeamCategory = formationTeamCategory;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof PersonDTO)) return false;
	    PersonDTO p = (PersonDTO) o;
	    return id == p.getId();
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(id);
	}

	public int getPrimerEntrenador() {
		return primerEntrenador;
	}

	public int getSegundoEntrenador() {
		return segundoEntrenador;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public TeamType getTipoEquipo() {
		return tipoEquipo;
	}

	public FormationTeamCategory getFormationTeamCategory() {
		return formationTeamCategory;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrimerEntrenador(int primerEntrenador) {
		this.primerEntrenador = primerEntrenador;
	}

	public void setSegundoEntrenador(int segundoEntrenador) {
		this.segundoEntrenador = segundoEntrenador;
	}

	public void setTipoEquipo(TeamType tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}

	public void setFormationTeamCategory(FormationTeamCategory formationTeamCategory) {
		this.formationTeamCategory = formationTeamCategory;
	}

	@Override
	public String toString() {
		return name + ", tipoEquipo=" + tipoEquipo + "]";
	}
	
	
	
	
}
