package giis.demo.ui.partidos.entradas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import giis.demo.tkrun.partidos.entradas.AsientosReservadosDTO;
import giis.demo.ui.PurchaseView;

public class TicketPurchaseView extends PurchaseView {
    private static final long serialVersionUID = 1L;

    private JPanel sPnDetailedPurchase;
    private JButton btnVerFactura;

    public TicketPurchaseView() {
        super();
        addVerFacturaButton();
    }

    private void addVerFacturaButton() {
        btnVerFactura = new JButton("Ver Factura");
        btnVerFactura.setPreferredSize(new Dimension(150, 30));
        btnVerFactura.setEnabled(true); 
        buttonPanel.add(btnVerFactura, 0); 
    }

    public JButton getBtnVerFactura() {
        return btnVerFactura;
    }

    @Override
    public JPanel getSPnDetailedPurchase() {
        if (sPnDetailedPurchase == null) {
            sPnDetailedPurchase = new JPanel();
            sPnDetailedPurchase.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
            sPnDetailedPurchase.setBackground(Color.WHITE);

            Border line = BorderFactory.createLineBorder(Color.GRAY, 1, true);
            Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            sPnDetailedPurchase.setBorder(BorderFactory.createCompoundBorder(padding, line));
        }
        return sPnDetailedPurchase;
    }

    public JPanel getDetails(AsientosReservadosDTO asientosReservados) {
        JPanel purchaseDetails = new JPanel();
        purchaseDetails.setBackground(Color.WHITE);
        purchaseDetails.setLayout(new BoxLayout(purchaseDetails, BoxLayout.Y_AXIS));
        purchaseDetails.setPreferredSize(new Dimension(350, 250));
        purchaseDetails.add(getKeyValuePn("Partido:", asientosReservados.getMatchName()));
        purchaseDetails.add(getKeyValuePn("Tribuna:", asientosReservados.getTribuneName()));
        purchaseDetails.add(getKeyValuePn("Sección:", asientosReservados.getSectionName()));
        purchaseDetails.add(getKeyValuePn("Fila:", String.valueOf(asientosReservados.getRow())));
        purchaseDetails.add(getKeyValuePn("Asientos:", asientosReservados.getInitialSeat() + " - " +
                (asientosReservados.getInitialSeat() + asientosReservados.getSeatsBought() - 1)));
        purchaseDetails.add(getKeyValuePn("Valor total:", String.format("%.2f €", asientosReservados.getTotalPrice())));

        return purchaseDetails;
    }

    public JPanel getKeyValuePn(String key, String value) {
        JPanel keyValuePanel = new JPanel();
        keyValuePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
        keyValuePanel.add(createLabel(key, Font.BOLD, 14));
        keyValuePanel.add(createLabel(value, Font.PLAIN, 14));
        return keyValuePanel;
    }
}

