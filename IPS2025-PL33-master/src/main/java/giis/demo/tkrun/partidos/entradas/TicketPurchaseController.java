package giis.demo.tkrun.partidos.entradas;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.factura.FacturaDatos;
import giis.demo.tkrun.factura.FacturaItem;
import giis.demo.tkrun.factura.FacturaPDFGenerator;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.Session;
import giis.demo.ui.partidos.entradas.TicketPurchaseView;

public class TicketPurchaseController extends AbstractController {
	private TicketPurchaseView ticketPurchaseView;
	private AsientosReservadosDTO asientosReservados;
	
	private String nombre;
	private String telefono;
	private String direccion;
	private String email;

	public TicketPurchaseController(TicketPurchaseView ticketPurchaseView, AsientosReservadosDTO asientosReservados,
			String nombre, String telefono, String direccion, String email, AuditService audit) {
		super(audit);
		this.ticketPurchaseView = ticketPurchaseView;
		this.asientosReservados = asientosReservados;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		ticketPurchaseView.getSPnDetailedPurchase().add(ticketPurchaseView.getDetails(asientosReservados));
		initView();
	}
	
	private void initView() {
		loadDetails();
		addActionListeners();
		this.ticketPurchaseView.setVisible(true);
	}
	
	private void loadDetails() {
		this.ticketPurchaseView.add(ticketPurchaseView.getDetails(asientosReservados));
	}
	
	private void addActionListeners() {
		ticketPurchaseView.getBtnVerFactura().addActionListener(ev -> {
			try {
				// Datos cliente
				FacturaDatos datosCliente = new FacturaDatos(nombre, // nombre
						direccion, // dirección
						telefono, // teléfono
						email // email
				);

				// Item de factura: 1 entrada con el total
				FacturaItem item = new FacturaItem("Entrada - " + asientosReservados.getMatchName() + " (Fila "
						+ asientosReservados.getRow() + ")", asientosReservados.getTotalPrice(), 1);

				// Generar PDF de factura
				FacturaPDFGenerator pdfGen = new FacturaPDFGenerator();
				String facturaPath = pdfGen.generarFacturaPDF(datosCliente,
						java.util.Collections.singletonList(item));

				// Abrir factura automáticamente
				java.awt.Desktop.getDesktop().open(new java.io.File(facturaPath));

				// --- LOG ---
				String detalleLog = String.format("Factura generada, usuarioId=%d, partido=%s, fila=%d, total=%.2f",
						Session.get().getUserId(), asientosReservados.getMatchName(), asientosReservados.getRow(),
						asientosReservados.getTotalPrice());
				audit.log("FACTURA_GENERADA", detalleLog);

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(ticketPurchaseView,
						"No se pudo generar la factura PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});
	}
	
}
