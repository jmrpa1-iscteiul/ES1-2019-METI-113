package projeto;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.text.NumberFormatter;

public class GUI_regras {
	
	private JFrame frame;
	private JPanel metricsPanel;
	private JComboBox<String> box_defeito1, box_Metric_1, box_Threshold_1, box_Operator, box_Metric_2, box_Threshold_2;
	private JFormattedTextField metric_1_value, metric_2_value;
	private int loc, cyclo, atfd, laa;
	private HashMap<String,Integer> hashValues = new HashMap<String,Integer>();
	private HashMap<String,String> hashThreshold = new HashMap<String,String>();
	public GUI_regras() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		  
	    addContent();
	    
	    frame.pack();
		frame.setSize(1000, 300); 
		frame.setVisible(true);	
	    
	}

	
	private void addContent() {
		metricsPanel = new JPanel();
		metricsPanel.setLayout(new GridLayout(1,8));
		frame.add(metricsPanel, BorderLayout.NORTH);
		
		addIsLongMethodFields();
		
		JButton criarRegra = new JButton("Criar Regra");
		frame.add(criarRegra, BorderLayout.SOUTH);
		
		criarRegra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRegra();								
			}
		});	

	}
	
	private void addIsLongMethodFields() {
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMaximum(100);

		box_defeito1 = new JComboBox<String>();
		box_defeito1.addItem("is_long_method");
		box_defeito1.addItem("is_envy_method");
		box_defeito1.setSelectedItem(null);
		metricsPanel.add(box_defeito1);

		box_Metric_1 = new JComboBox<String>();
		box_Metric_1.addItem("LOC");
		box_Metric_1.addItem("CYCLO");
		box_Metric_1.addItem("ATFD");
		box_Metric_1.addItem("LAA");
		box_Metric_1.setSelectedItem(null);
		metricsPanel.add(box_Metric_1);
		
		box_Threshold_1 = new JComboBox<String>();
		box_Threshold_1.addItem("<");
		box_Threshold_1.addItem(">");
		box_Threshold_1.setSelectedItem(null);
		metricsPanel.add(box_Threshold_1);
		
		metric_1_value = new JFormattedTextField(numberFormatter); metricsPanel.add(metric_1_value);
		
		box_Operator = new JComboBox<String>();
		box_Operator.addItem("&&");
		box_Operator.setSelectedItem(null);
		metricsPanel.add(box_Operator);
		
		box_Metric_2 = new JComboBox<String>();
		box_Metric_2.addItem("LOC");
		box_Metric_2.addItem("CYCLO");
		box_Metric_2.addItem("ATFD");
		box_Metric_2.addItem("LAA");
		box_Metric_2.setSelectedItem(null);		
		metricsPanel.add(box_Metric_2);
		
		box_Threshold_2 = new JComboBox<String>();
		box_Threshold_2.addItem("<");
		box_Threshold_2.addItem(">");
		box_Threshold_2.setSelectedItem(null);
		metricsPanel.add(box_Threshold_2);
		
		metric_2_value = new JFormattedTextField(numberFormatter); metricsPanel.add(metric_2_value);

	}
	
	private void getRegra() {
		
		switch(box_Metric_1.getSelectedIndex()) {
			case 0://LOC
				loc = Integer.parseInt(metric_1_value.getText());
				hashValues.put("LOC", loc);		
				System.out.println("GUI_Regras "+ String.valueOf(box_Threshold_1.getSelectedItem()));
				hashThreshold.put("LOC", String.valueOf(box_Threshold_1.getSelectedItem()));
				break;
			case 1://CYLO
				cyclo = Integer.parseInt(metric_1_value.getText());
				hashValues.put("CYCLO", cyclo);
				hashThreshold.put("CYCLO", String.valueOf(box_Threshold_1.getSelectedItem()));
				break;
			case 2://ATFD
				atfd = Integer.parseInt(metric_1_value.getText());
				hashValues.put("ATFD", atfd);	
				hashThreshold.put("ATFD", String.valueOf(box_Threshold_1.getSelectedItem()));
				break;
			case 3://LAA
				laa = Integer.parseInt(metric_1_value.getText());
				hashValues.put("LAA", laa);
				hashThreshold.put("LAA", String.valueOf(box_Threshold_1.getSelectedItem()));
				break;	
		}
	
		switch(box_Metric_2.getSelectedIndex()) {
			case 0://LOC
				loc = Integer.parseInt(metric_2_value.getText());
				hashValues.put("LOC", loc);
				hashThreshold.put("LOC", String.valueOf(box_Threshold_2.getSelectedItem()));
				break;
			case 1://CYLO
				cyclo = Integer.parseInt(metric_2_value.getText());
				hashValues.put("CYCLO", cyclo);
				hashThreshold.put("CYCLO", String.valueOf(box_Threshold_2.getSelectedItem()));
				break;
			case 2://ATFD
				atfd = Integer.parseInt(metric_2_value.getText());
				hashValues.put("ATFD", atfd);	
				hashThreshold.put("ATFD", String.valueOf(box_Threshold_2.getSelectedItem()));
				break;
			case 3://LAA
				laa = Integer.parseInt(metric_2_value.getText());
				hashValues.put("LAA", laa);
				hashThreshold.put("LAA", String.valueOf(box_Threshold_2.getSelectedItem()));
				break;	
		}	
		
	
	}
	
	
	
	public HashMap<String, Integer> getHashValues() {
		return hashValues;
	}

	public HashMap<String, String> getHashThreshold() {
		return hashThreshold;
	}

	public static void main(String[] args) throws IOException {
		GUI_regras gui = new GUI_regras();
	}

	
}
