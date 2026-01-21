package giis.demo.ui.tienda.stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.tienda.stock.RestockOrderDTO;

public class PnOngoingOrderData extends JPanel {

	private static final long serialVersionUID = 1L;
	private RestockOrderDTO order;
	private JButton finishOrderBtn;
	private JButton detailsBtn;
	
	public PnOngoingOrderData(RestockOrderDTO restockOrderDTO) {
		this.order = restockOrderDTO;
		setBorder(new LineBorder(Color.GRAY, 1, true));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		add(getOrderDetail(), BorderLayout.WEST);
		add(getFinishOrderBtn(), BorderLayout.EAST);
	}

	private JPanel getOrderDetail() {
		JPanel orderDetail = new JPanel();
		orderDetail.setLayout(new BoxLayout(orderDetail, BoxLayout.X_AXIS));
		orderDetail.add(createLabel(order.getFecha(), Font.PLAIN, 13));
		orderDetail.add(createLabel("(" + order.getTotal() + "€)", Font.PLAIN, 13));
		orderDetail.add(getBtnVerDetalle());
		
		return orderDetail;
	}
	
	public JButton getFinishOrderBtn() {
		if (finishOrderBtn == null) {
			finishOrderBtn = new JButton("Marcar como recibida");
		}
		
		return finishOrderBtn;
	}
	
	public JButton getBtnVerDetalle() {
		if (detailsBtn == null) {
			detailsBtn = new JButton("Ver detalles");
		}
		
		return detailsBtn;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,0,0,5));
        return label;
    }
}
