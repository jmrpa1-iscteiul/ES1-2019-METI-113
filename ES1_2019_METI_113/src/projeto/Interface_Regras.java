package projeto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Interface_Regras {
	
	private JFrame frame;
	private JLabel texto;
	private JTextArea field;
	private int LOCvalue, CYCLOvalue, ATFDvalue, LAAvalue;
	private JFrame firstFrame;
	private ArrayList<String> array;

	public Interface_Regras(JFrame firstFrame, int LOCvalue, int CYCLOvalue, int ATFDvalue, int LAAvalue, ArrayList<String> array) {
		frame = new JFrame("Regras");
		this.array=array;
		this.firstFrame=firstFrame;
		this.LOCvalue=LOCvalue;
		this.CYCLOvalue=CYCLOvalue;
		this.ATFDvalue=ATFDvalue;
		this.LAAvalue=LAAvalue;
				
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());
		
		addFrameContent();
		
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();
		
		frame.setSize(400,400);
	}

	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
		
	}
	
	private void addFrameContent() {
		
		frame.setLayout(new BorderLayout());
		
		JPanel norte = new JPanel();
		JPanel sul = new JPanel();
		
		JTextArea caixa = new JTextArea();
		
		caixa.setEditable(false);
		
		frame.add(caixa, BorderLayout.CENTER);
		
		JComboBox<String> box_1 = new JComboBox<String>();
		box_1.addItem("LOC");
		box_1.addItem("CYCLO");
		box_1.addItem("ATFD");
		box_1.addItem("LAA");
		box_1.setSelectedItem(null);
		
		JComboBox<String> box_2 = new JComboBox<String>();
			box_2.addItem("<");
			box_2.addItem(">");
			box_2.addItem("=");
		box_2.setSelectedItem(null);
		
		JComboBox<String> box_3 = new JComboBox<String>();
			box_3.addItem("&&");
			box_3.addItem("||");
		box_3.setSelectedItem(null);
		
		JButton ok = new JButton("OK");
			int valor;
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(box_1.getSelectedIndex()==0)
					//valor = LOCvalue;
					caixa.setText(caixa.getText() + " " + box_1.getItemAt(box_1.getSelectedIndex()) + " " + box_2.getItemAt(box_2.getSelectedIndex()) + " " + LOCvalue + " " + box_3.getItemAt(box_3.getSelectedIndex()));
				if(box_1.getSelectedIndex()==1)
					//valor = CYCLOvalue;
					caixa.setText(caixa.getText() + " " + box_1.getItemAt(box_1.getSelectedIndex()) + " " + box_2.getItemAt(box_2.getSelectedIndex()) + " " + CYCLOvalue + " " + box_3.getItemAt(box_3.getSelectedIndex()));
				if(box_1.getSelectedIndex()==2)
					//valor = ATFDvalue;
					caixa.setText(caixa.getText() + " " + box_1.getItemAt(box_1.getSelectedIndex()) + " " + box_2.getItemAt(box_2.getSelectedIndex()) + " " + ATFDvalue + " " + box_3.getItemAt(box_3.getSelectedIndex()));
				if(box_1.getSelectedIndex()==3)
					//valor = LAAvalue;
					caixa.setText(caixa.getText() + " " + box_1.getItemAt(box_1.getSelectedIndex()) + " " + box_2.getItemAt(box_2.getSelectedIndex()) + " " + LAAvalue + " " + box_3.getItemAt(box_3.getSelectedIndex()));
				
		//caixa.setText(box_1.getItemAt(box_1.getSelectedIndex()) + " " + box_2.getItemAt(box_2.getSelectedIndex()) + " " + valor + " " + box_3.getItemAt(box_3.getSelectedIndex()));
			}
			
		});	
		
		norte.add(ok);
		
		norte.add(box_1);
		norte.add(box_2);
		norte.add(box_3);
		norte.add(ok);
		frame.add(norte, BorderLayout.NORTH);
		
		JButton exibirRegra = new JButton("Exibir regras");
		sul.add(exibirRegra);
		
		exibirRegra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interface_exibir_regras inter = new Interface_exibir_regras(frame, array);
				inter.open();	
			}
			
		});	
		
		JButton finalizarRegra = new JButton("Filanizar regra");
		sul.add(finalizarRegra);
		
		finalizarRegra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean verifica = true;
				
				if(box_1.getSelectedIndex()==-1 || box_2.getSelectedIndex()==-1)
					JOptionPane.showMessageDialog(null, "não existe nenhuma regra");
				else {
					for(String m : array) {
						if(m.equals(caixa.getText())) {
							verifica = false;
						}
					}
					
					if(verifica==true)
						array.add(caixa.getText());
					
					else
						JOptionPane.showMessageDialog(null, "a regra já se encontra na base de dados");
			
				caixa.setText("");
				
				
				}
			}
			
		});	
		
		
		JButton sair = new JButton("Sair");
		sul.add(sair);
		
		
		 
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);  
				firstFrame.setVisible(true);
			}
			
		});	
		
		frame.add(sul, BorderLayout.SOUTH);
		
		//fhejhff
	}
	
	public JLabel getJFrame() {
		return texto;
	}
	
}
