import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public aspect GuiBase {
	private JFrame Application.window;
	private JPanel Application.panel;
	private JPanel Application.panel_1;
	private JSplitPane Application.splitPane;
	private JToolBar Application.toolBar;
	
	void Application.createGui(){
		JFrame window = new JFrame("File Explorer");
		window.setSize(800, 500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);		

		this.window = window;
		
		splitPane = new JSplitPane();
		window.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
	}
	
	pointcut execute(): execution(void Application.show());
	void around(): execute(){

			Application fileBrowser = new Application();
			fileBrowser.createGui();
			fileBrowser.show();
		
	}
}
