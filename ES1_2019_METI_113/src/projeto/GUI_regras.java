package projeto;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.NumberFormatter;


/**Classe para a criacao da interface grafica das metricas long_method e feature_envy
 *@author joset, joaos
 *@Date 12/Dez/2019 
 *@version 1.0   
 */
public class GUI_regras {

	private JFrame frame;
	private JPanel metricsPanel, metricsPanel2;
	private JComboBox<String> box_operador_1, box_operador_2;
	private JTextField caixa1, caixa2, caixa3, caixa4, caixa5, caixa6, caixa7, caixa8, caixa9, caixa10;
	private JTextField loc_value, cyclo_value, atfd_value, laa_value;
	private int loc, cyclo, atfd, laa;
	

	private Regra is_long_method, is_feature_envy;


	public GUI_regras() {
		frame = new JFrame();
		//	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		addContent();

		frame.pack();
		frame.setSize(1000, 300); 
		frame.setVisible(true);	

	}


	/**Funcao para adicionar a metrica long_method (addIsLongMethodFields()) e os fields do long_method,
	 * botao para criar a regra e tratamento de excepcoes (inserir espaços em branco)
	 */
	private void addContent() {


		addIsLongMethodFields();

		JButton criarRegra = new JButton("Criar Regra");
		frame.add(criarRegra, BorderLayout.SOUTH);

		criarRegra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!loc_value.getText().equals("") && !cyclo_value.getText().equals("") && !atfd_value.getText().equals("") && !laa_value.getText().equals("") && box_operador_1.getSelectedIndex() != -1 && box_operador_2.getSelectedIndex() != -1) {
					getRegra();
					JOptionPane.showMessageDialog( null,"Regra criada com Sucesso!");
					frame.setVisible(false);
				}else {

					JOptionPane.showMessageDialog( null,"Deve Preencher Todos os Campos e Selecionar os Operadores");
				}
			}
		});	

	}

	/**adicionar os Jtextfields à interface grafica
	 * medoto para inserir os parametros do long method "LOC", Numero de linhas, "CYCLO", complexidade desejada,
	 * adicionar os Jtextfields à interface grafica
	 *
	 */
	private void addIsLongMethodFields() {
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMaximum(100);

		metricsPanel = new JPanel();
		metricsPanel.setLayout(new GridLayout(2,8));

		frame.add(metricsPanel, BorderLayout.NORTH);
		

		caixa9 = new JTextField("is long method");
		caixa9.setEditable(false);
		metricsPanel.add(caixa9);

		caixa1 = new JTextField("LOC");
		caixa1.setEditable(false);
		metricsPanel.add(caixa1);

		caixa2 = new JTextField(">");
		caixa2.setEditable(false);
		metricsPanel.add(caixa2);

		loc_value = new JTextField(); 
		metricsPanel.add(loc_value);
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

		cyclo_value = new JTextField(); 
		metricsPanel.add(cyclo_value);

		caixa10 = new JTextField("is feature envy");
		caixa10.setEditable(false);
		metricsPanel.add(caixa10);

		caixa5 = new JTextField("ATFD");
		caixa5.setEditable(false);
		metricsPanel.add(caixa5);

		caixa6 = new JTextField(">");
		caixa6.setEditable(false);
		metricsPanel.add(caixa6);


		atfd_value = new JTextField(); 
		metricsPanel.add(atfd_value);

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

		laa_value = new JTextField(); 
		metricsPanel.add(laa_value);


	}
	/**funcao para adicionar uma metrica, long_method e feature_envy, com base nos inputs do utilizador
	 *
	 */
	public void getRegra() {
		is_long_method = new Regra("is_long_method", "LOC", Integer.parseInt(loc_value.getText()),
				String.valueOf(box_operador_1.getSelectedItem()), "CYCLO",Integer.parseInt(cyclo_value.getText()));

		is_feature_envy = new Regra("is_feature_envy", "ATFD", Integer.parseInt(atfd_value.getText()),
				String.valueOf(box_operador_2.getSelectedItem()), "LAA",Integer.parseInt(laa_value.getText()));


	}

	/**metodo que retorna a regra criada pela funcao GetRegra().
	 * retorna um long_method
	 *
	 */
	public Regra getIs_long_method() {
		return is_long_method;
	} 

	/**metodo que retorna a regra criada pela funcao GetRegra().
	 * retorna um future_envy
	 *
	 */
	public Regra getIs_feature_envy() {
		return is_feature_envy;
	}





}
