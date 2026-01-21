package giis.demo.tkrun.accion.campana.comprar;

import java.awt.Desktop;
import java.io.File;
import java.util.Collections;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.accion.AccionistaDAO;
import giis.demo.tkrun.accion.AccionistaSimpleDTO;
import giis.demo.tkrun.accion.campana.CampanaDTO;
import giis.demo.tkrun.accion.campana.CampanaModel;
import giis.demo.tkrun.factura.FacturaDatos;
import giis.demo.tkrun.factura.FacturaItem;
import giis.demo.tkrun.factura.FacturaPDFGenerator;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.ShareholderSession;
import giis.demo.ui.acciones.campana.CompraAccionesCampanaView;

public class ComprarAccionesController extends AbstractController {

    private CompraAccionesCampanaView view;
    private CampanaModel cM = new CampanaModel();
    private AccionistaDAO accionistaDAO = new AccionistaDAO();
    private String ultimaFacturaPath;
    private CampanaDTO campana;
    
    int numeroFacturasGeneradas = 0;

	public ComprarAccionesController(CompraAccionesCampanaView view, AuditService audit) {
		super(audit);
        this.view = view;
        initView();
    }

    private void initView() {
    	ShareholderSession shareholder = ShareholderSession.get();
		
    	campana = cM.getActiveCampaign();
    	
    	if (campana == null) {
    		JOptionPane.showMessageDialog(null, "No hay ninguna campaña activa");
    		return;
    	}
    	
    	view.getCampaignValue().setText(campana.toString());
    	view.getEtiquetaNumAcciones().setText("El número de acciones disponibles en la campaña es: " + (campana.getN_accionesDisponibles() - campana.getN_accionesVendidas()));
		int nAccionesPuedeComprar = cM.getNAccionesComprarFase1(shareholder.getId(),
				campana.getFecha_creacion(), campana.getId_campana());
		// Comprobar el n de acciones que ya tenia el accionista
		if (campana.getFase() == 1) {
			view.getEtiquetaNumAccionesAccionista()
					.setText("Puede comprar " + nAccionesPuedeComprar + " acciones");
		}
        this.view.setVisible(true);
        addActionListeners();
    }

    private void addActionListeners() {
        view.getBuyBtn().addActionListener(e -> {
            int accionesAComprar = 0;

            try {
                accionesAComprar = (Integer) view.getCantAcciones().getValue();
                if (accionesAComprar <= 0) {
                    JOptionPane.showMessageDialog(view, "El número de acciones debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Número de acciones no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (campana == null) {
                JOptionPane.showMessageDialog(view, "Selecciona una campaña.");
                return;
            }

            ShareholderSession session = ShareholderSession.get();
            if (session.getId() == null) {
            	return;
            }
            boolean exitoCompra = comprarAcciones(session.getId(), campana.getId_campana(), accionesAComprar);
            if (exitoCompra) {
                view.getVerFacturaBtn().setEnabled(true);
				audit.log("INFO", "El accionista con id=" + session.getId() + " ha comprado " + accionesAComprar
						+ " acción(es) de la campaña con id " + campana.getId_campana());
            }
			view.dispose();
        });

        view.getVerFacturaBtn().addActionListener(e -> {
            ShareholderSession shareholder = ShareholderSession.get();
            int accionesAComprar = (Integer) view.getCantAcciones().getValue();
            generarFactura(shareholder.getId(), campana, accionesAComprar);
            abrirFactura();
        });
    }

    public boolean comprarAcciones(int idAccionista, int idCampaign, int nAcciones) {
        CampanaDTO campana = cM.getCampana();
        System.out.println("Campaña con id: " + campana.getId_campana());
        int fase = campana.getFase();
        AccionistaSimpleDTO accionista = accionistaDAO.getAccionista(idAccionista);

        int accionesDisponibles = campana.getN_accionesDisponibles() - campana.getN_accionesVendidas();
        System.out.println("accionesDisponibles: " + accionesDisponibles);
		if (accionesDisponibles < nAcciones) {
			JOptionPane.showMessageDialog(null, "La campaña no dispone de ese número de acciones");
			return false;
		}

		if (fase == 1) {
			int nAccionesPuedeComprar = cM.getNAccionesComprarFase1(idAccionista, campana.getFecha_creacion(), campana.getId_campana());
			System.out.println("Fase 1: " + nAccionesPuedeComprar);
				// Comprobar el n de acciones que ya tenia el accionista
			System.out.println("Quiero: " + nAcciones + ". Tengo: " + nAccionesPuedeComprar);
				if (nAcciones <= nAccionesPuedeComprar) {
					cM.comprarAcciones(accionista, nAcciones);
					JOptionPane.showMessageDialog(view, "Las acciones han sido compradas correctamente");
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "No puedes comprar ese número de acciones");
					return false;
				}
		}
		System.out.println("Fase 2 o 3: ");
		cM.comprarAcciones(accionista, nAcciones);
		JOptionPane.showMessageDialog(view, "Las acciones han sido compradas correctamente");
		return true;
    }

    private void generarFactura(int shareholderId, CampanaDTO campana, int nAcciones) {
        AccionistaSimpleDTO accionista = accionistaDAO.getAccionista(shareholderId);
        String nombre = accionista.getNombre() + " " + accionista.getApellido();
        String telefono = view.getTxtTelefono().getText().trim();
        String direccion = view.getTxtDireccion().getText().trim();
        String email = view.getTxtEmail().getText().trim();

        if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Completa todos los campos para generar la factura.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FacturaItem item = new FacturaItem(
                "Compra de acciones: Campaña " + campana.toString() + " (" + nAcciones + " acciones)",
                nAcciones * 10,
                1
        );
        FacturaDatos datosCliente = new FacturaDatos(nombre, telefono, direccion, email);

        FacturaPDFGenerator pdfGen = new FacturaPDFGenerator();
        ultimaFacturaPath = pdfGen.generarFacturaPDF( datosCliente, Collections.singletonList(item));
    }

    private void abrirFactura() {
        if (ultimaFacturaPath != null) {
            try {
                Desktop.getDesktop().open(new File(ultimaFacturaPath));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "No se pudo abrir la factura PDF.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
      
    }

}
