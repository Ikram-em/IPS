package giis.demo.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PurchaseView extends JFrame {
    private static final long serialVersionUID = 7574359456379315341L;

    private JButton closeBtn;
    protected JPanel buttonPanel; 
    public PurchaseView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 814, 540);

        getContentPane().setLayout(new BorderLayout());

        // --- Panel superior ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(createLabel("¡Gracias por tu compra!", Font.BOLD, 18));
        topPanel.add(createLabel("Tu resumen es:", Font.PLAIN, 14));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // --- Panel central ---
        getContentPane().add(getSPnDetailedPurchase(), BorderLayout.CENTER);

        // --- Panel inferior ---
        initializeButtonPanel();
    }

    protected void initializeButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(getCloseButton());
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    public abstract JComponent getSPnDetailedPurchase();

    protected JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        return label;
    }

    private JButton getCloseButton() {
        if (closeBtn == null) {
            closeBtn = new JButton("Aceptar");
            closeBtn.addActionListener(e -> dispose());
            closeBtn.setPreferredSize(new Dimension(150, 30));
        }
        return closeBtn;
    }
}

