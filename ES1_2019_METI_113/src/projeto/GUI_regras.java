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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.NumberFormatter;

public class GUI_regras {
	
	private JFrame frame;
	private JPanel metricsPanel, metricsPanel2;
	private JComboBox<String> box_operador_1, box_operador_2;
	private JTextField caixa1, caixa2, caixa3, caixa4, caixa5, caixa6, caixa7, caixa8, caixa9, caixa10;
	private JFormattedTextField metric_1_value, metric_2_value, metric_3_value, metric_4_value;
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
		
		
		addIsLongMethodFields();
		
		JButton criarRegra = new JButton("Criar Regra");
		frame.add(criarRegra, BorderLayout.SOUTH);
		
		criarRegra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	getRegra();	
			}
		});	

	}
	
	private void addIsLongMethodFields() {
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMaximum(100);

		metricsPanel = new JPanel();
		metricsPanel.setLayout(new GridLayout(2,8));
		
		//metricsPanel2 = new JPanel();
		//metricsPanel2.setLayout(new GridLayout(1,8));
		frame.add(metricsPanel, BorderLayout.NORTH);
		//frame.add(label2, BorderLayout.CENTER);
		
		caixa9 = new JTextField("is long method");
		caixa9.setEditable(false);
		metricsPanel.add(caixa9);
		
		caixa1 = new JTextField("LOC");
		caixa1.setEditable(false);
		metricsPanel.add(caixa1);
		
		caixa2 = new JTextField(">");
		caixa2.setEditable(false);
		metricsPanel.add(caixa2);
		
		JFormattedTextField box_loc_value = new JFormattedTextField(numberFormatter); 
		metricsPanel.add(box_loc_value);
		//loc=Integer.parseInt(metric_1_value.getText());
				
		box_operador_1 = new JComboBox<String>();
		box_operador_1.addItem("&&");
		box_operador_1.addItem("||");
		box_operador_1.setSelectedItem(null);
		metricsPanel.add(box_operador_1);

		caixa3 = new JTextField("CYCLO");
		caixa3.setEditable(false);
		metricsPanel.add(caixa3);
		
		caixa4 = new JTextField(">");
		caixa4.setEditable(false);
		metricsPanel.add(caixa4);
		
		metric_2_value = new JFormattedTextField(numberFormatter); 
		metricsPanel.add(metric_2_value);
		
		caixa10 = new JTextField("is feature envy");
		caixa10.setEditable(false);
		metricsPanel.add(caixa10);
		
		caixa5 = new JTextField("ATFD");
		caixa5.setEditable(false);
		metricsPanel.add(caixa5);
		
		caixa6 = new JTextField(">");
		caixa6.setEditable(false);
		metricsPanel.add(caixa6);
		
		
		metric_3_value = new JFormattedTextField(numberFormatter); 
		metricsPanel.add(metric_3_value);
		
		box_operador_2 = new JComboBox<String>();
		box_operador_2.addItem("&&");
		box_operador_2.addItem("||");
		box_operador_2.setSelectedItem(null);
		metricsPanel.add(box_operador_2);

		caixa7 = new JTextField("LAA");
		caixa7.setEditable(false);
		metricsPanel.add(caixa7);
		
		caixa8 = new JTextField("<");
		caixa8.setEditable(false);
		metricsPanel.add(caixa8);
		
		metric_4_value = new JFormattedTextField(numberFormatter); 
		metricsPanel.add(metric_4_value);

	}
	
	/*private void getRegra() {
		
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
		
	
	}*/
	
	
	
	public HashMap<String, Integer> getHashValues() {
		return hashValues;
	}

	public HashMap<String, String> getHashThreshold() {
		return hashThreshold;
	}

//	public static void main(String[] args) throws IOException {
	//	GUI_regras gui = new GUI_regras();
	//}

	
}
