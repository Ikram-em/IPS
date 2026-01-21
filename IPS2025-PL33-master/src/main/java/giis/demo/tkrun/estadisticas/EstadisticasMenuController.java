package giis.demo.tkrun.estadisticas;

import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.estadisticas.EstadisticasMenuView;
import giis.demo.ui.estadisticas.EstadisticasAnualesView;
import giis.demo.ui.estadisticas.EstadisticasMensualesView;


public class EstadisticasMenuController {

    private EstadisticasMenuView view;
    private AuditService audit;

    public EstadisticasMenuController(EstadisticasMenuView view, AuditService audit) {
        this.view = view;
        this.audit = audit;

        addListeners();
        view.setVisible(true);
    }

    private void addListeners() {
        // Botón de estadísticas mensuales
        view.getBtnEstadisticasClub().addActionListener(e -> {
            EstadisticasMensualesView mensualesView = new EstadisticasMensualesView();
            new EstadisticasMensualesController(mensualesView, audit);
            audit.log("INFO", "Abriendo estadísticas mensuales");
        });

        // Botón de estadísticas anuales
        view.getBtnEstadisticasAnuales().addActionListener(e -> {
            EstadisticasAnualesView anualesView = new EstadisticasAnualesView();
            new EstadisticasAnualesController(anualesView, audit); 
            audit.log("INFO", "Abriendo estadísticas anuales");
        });
        


            
    }
}
