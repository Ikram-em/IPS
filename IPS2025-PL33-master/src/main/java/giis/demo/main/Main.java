package giis.demo.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import giis.demo.ui.StartingView;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatIntelliJLaf());
					StartingView frame = new StartingView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
