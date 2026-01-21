package giis.demo.tkrun.ingresos;

public enum TipoIngreso {
	
	MERCHANDISING,
    ABONO_TEMPORADA,
    ACCIONISTA,
    ENTRADA_PARTIDO,
    RESERVA_EXTERNA,
    TIENDA,
    CAMPANA_ACCIONISTAS,
    SUPLEMENTO;
    
    @Override
    public String toString() {
        switch (this) {
            case MERCHANDISING : return "Merchandaising";
            case ABONO_TEMPORADA: return "Abono";
            case ACCIONISTA: return "Accionista";
            case ENTRADA_PARTIDO: return "Entrada Partido";
            case RESERVA_EXTERNA: return "Reserva";
            case TIENDA: return "Tienda";
            case SUPLEMENTO: return "Suplemento";
            case CAMPANA_ACCIONISTAS: return "Campaña de Accionistas";
            default: return super.toString();
        }
    }
}
