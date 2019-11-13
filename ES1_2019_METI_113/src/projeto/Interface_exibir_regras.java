package projeto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Interface_exibir_regras {
	
	private JFrame frame;
	private JLabel texto;
	private JTextArea field;
	private JFrame firstFrame;
	private ArrayList<String>array = new ArrayList<String>();
	private DefaultListModel<Object> listaDefault = new DefaultListModel<Object>();
	private JList<Object>lista = new JList<Object>(listaDefault);

	public Interface_exibir_regras(JFrame firstFrame, ArrayList<String>array) {
		frame = new JFrame("Regras");
		this.firstFrame=firstFrame;
				this.array=array;
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
		
		JPanel sul = new JPanel();
		
		//JLabel caixa = new JLabel();
		
		//caixa.setEditable(false);
		
	//	System.out.println(array);
		
		for(String m : array)
			listaDefault.addElement(m);
		//	caixa.setText(caixa.getText() + "\n" + m);
		
		frame.add(lista, BorderLayout.CENTER);
		
		JButton apagarRegra = new JButton("Apagar regra");
		sul.add(apagarRegra);
		
		apagarRegra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				array.remove((String)listaDefault.getElementAt(lista.getSelectedIndex()));
				
				listaDefault.removeAllElements();
				
				for(String m : array)
					listaDefault.addElement(m);
			}
			
		});	
		
		JButton sair = new JButton("Sair");
		sul.add(sair);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
			
		});	
		
		frame.add(sul, BorderLayout.SOUTH);
		
	}
	
	public JLabel getJFrame() {
		return texto;
	}
	
	public JTextArea getJTextArea() {
		return field;
	}

}
