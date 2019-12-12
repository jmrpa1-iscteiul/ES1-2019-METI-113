package projeto;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**Classe para a criação da interface gráfica, que contém os 4 tipos de opções disponibilizadas pelo software, "Choose File", "Generate Table", "Criar Regra", "Calcular Métricas";
 * Apresenta ainda os valores dos defeitos (DCI, DII, ADCI, ADII) das ferramentas iPlasma e PMI.
 * @author Diogo, andreB, candido
 * @Date 12/Dez/2019
 * @version 1.0
 */
public class GUI{

	
	
	private JFrame firstFrame;
	private CalcularMetricas calcularMetricas1;
	private JFrame secondFrame;
	private String[] columnNames = { "MethodID", "Package", "Class", "Method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "iPlasma", "PMD", "is_feature_envy" }; 
	private String[][] data;
	private int LOCvalue, CYCLOvalue, ATFDvalue, LAAvalue, iPlasmaErrors, PMDErrors;
	private JFormattedTextField  fieldLOC, fieldCYCLO, fieldATFD, fieldLAA;
	private ArrayList<String>array = new ArrayList<String>();
	private JTable table;
	private GUI_regras guiRegras;
	private JTextField dciplasma, dcipmi,diiplasma,diipmi,adciplasma,adcipmi,adiiplasma,adiipmi;
	
	public GUI() {
		
		firstFrame = new JFrame();
		firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		firstFrame.setLayout(new BorderLayout());
		
		open();
		
	}
	
	/**Método responsável por criar e definir os limites da interface gráfica (500/300); 
	 */
	public void open() {
		firstFrame = new JFrame();
		firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		firstFrame.setLayout(new BorderLayout());
		firstFrame.pack();
				
		addContentButtonsPanel();
				
		firstFrame.setSize(500, 300); 
	    firstFrame.setVisible(true); 
	}
	
	/** Método principal para a criação da interface gráfica, onde são gerados dois "Jpanel" (ButtonsPanel) e (Indicators),
	 * divindo assim a interface em dois paines, (buttonsPanel) a Norte contendo os "Jbuttons" com as diferentes opções
	 * e o painel de (Indicators) a Sul contendo "JtextFields" e "JLabels" com os respetivos indicadores e valores das ferramentas iPlasma e PMD.
	 *De notar ainda que o JPanel (indicators) foi criado com a topologia de GridLayout (5 por 3) por forma a aperfeiçoar a interface.
	 * 
	 */
	private void addContentButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		firstFrame.add(buttonsPanel, BorderLayout.NORTH);
		JPanel indicators = new JPanel();
		firstFrame.add(indicators,BorderLayout.SOUTH);
		indicators.setLayout(new GridLayout(5,3));
		
		indicators.add(new JLabel ("Indicadores"));
		indicators.add(new JLabel ("iPlasma"));
		indicators.add(new JLabel ("PMI"));

		indicators.add(new JLabel ("DCI"));
		
		dciplasma = new JTextField();
        dciplasma.setEditable(false);
        indicators.add(dciplasma);

        dcipmi = new JTextField();
        dcipmi.setEditable(false);
        indicators.add(dcipmi);

        
		indicators.add(new JLabel("DII"));
		
		diiplasma = new JTextField();
        diiplasma.setEditable(false);
        indicators.add(diiplasma);
        
        diipmi = new JTextField();
        diipmi.setEditable(false);
        indicators.add(diipmi);

		indicators.add(new JLabel("ADCI"));
		
		adciplasma = new JTextField();
        adciplasma.setEditable(false);
        indicators.add(adciplasma);
        
        adcipmi = new JTextField();
        adcipmi.setEditable(false);
        indicators.add(adcipmi);
        
        indicators.add(new JLabel("ADII"));
        
        adiiplasma = new JTextField();
        adiiplasma.setEditable(false);
        indicators.add(adiiplasma);	
        
        adiipmi = new JTextField();
        adiipmi.setEditable(false);
        indicators.add(adiipmi);	
		JButton chooseButton = new JButton("Choose file");
		buttonsPanel.add(chooseButton);
		JTextField fileNameArea = new JTextField();
		
		chooseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					try {
						data = readExcel(selectedFile.getAbsolutePath());
						fileNameArea.setText(selectedFile.getAbsolutePath());
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});	
		
		JButton generateButton = new JButton("Generate Table");
		buttonsPanel.add(generateButton);
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			contentSecondFrame();
				
			}
			
		});	
		
		
		JButton fazerRegraButton = new JButton("Criar regra");
		
		buttonsPanel.add(fazerRegraButton);
		fazerRegraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiRegras = new GUI_regras();
			}
		
		});	
		
		
		JButton calcularMetricasButton = new JButton("Calcular Metricas");

		buttonsPanel.add(calcularMetricasButton);
		calcularMetricasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regra regra_is_long_method = guiRegras.getIs_long_method();
				Regra regra_is_feature_envy = guiRegras.getIs_feature_envy();
				CalcularMetricas calcularMetricas1= new CalcularMetricas(regra_is_long_method,regra_is_feature_envy, table);
				calcularMetricas1.CalcularMetricas1();
				calcularMetricas1.getADCIplasma();
				calcularMetricas1.getADIIplasma();
				calcularMetricas1.getADCIpmd();
				calcularMetricas1.getADIIpmd();
				calcularMetricas1.getDCIplasma();
				calcularMetricas1.getDCIpmd();
				calcularMetricas1.getDIIplasma();
				calcularMetricas1.getDIIpmd();
				

				dciplasma.setText(Integer.toString(calcularMetricas1.getDCIplasma()));
				dcipmi.setText(Integer.toString(calcularMetricas1.getDCIpmd()));
				diiplasma.setText(Integer.toString(calcularMetricas1.getDIIplasma()));
				diipmi.setText(Integer.toString(calcularMetricas1.getDIIpmd()));
				adciplasma.setText(Integer.toString(calcularMetricas1.getADCIplasma()));
				adcipmi.setText(Integer.toString(calcularMetricas1.getADCIpmd()));
				adiiplasma.setText(Integer.toString(calcularMetricas1.getADIIplasma()));
				adiipmi.setText(Integer.toString(calcularMetricas1.getADIIpmd()));
				
				
		}
		
		});
		
		
	
	}


	public void contentSecondFrame() {
		secondFrame = new JFrame();
		secondFrame.setLayout(new BorderLayout());
		secondFrame.pack();
		
		this.table = new JTable(data, columnNames);
		secondFrame.add(table);
		System.out.println(table.getValueAt(1, 1));
		JScrollPane scrollPane = new JScrollPane(table);
		secondFrame.add(scrollPane);
		secondFrame.setSize(1100, 400); 
		secondFrame.setVisible(true);

	}
	
	
		
	/**Esta função tal como o título indica, irá ler o ficheiro excel que for selecionado;
	 * Para essa seleção recorremos a métodos da classe workbook, possibilitanto o uso do método do tipo "FileInputStream";
	 * A função irá então devolver o ficheiro selecionado, em forma de matriz de tamanho [421] [12];
	 * @param excelFilePath
	 * @return matrix
	 * @throws IOException
	 */
	public String[][] readExcel(String excelFilePath) throws IOException {
    	String[][] matrix = new String[421][12];
    	int iMatrix=0; 
    	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        int i=0;
        while (iterator.hasNext() && i<421) {
        	i++;
        	String [] stringArray = new String [12];
            int iArray=0;
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellType = cell.getCellType().toString();
                
                switch (cellType) {
                    case "STRING":
                        stringArray[iArray] = cell.getStringCellValue();
                      //  System.out.print(cell.getStringCellValue());
                        break;
                    case "BOOLEAN":
                    	stringArray[iArray] = String.valueOf(cell.getBooleanCellValue());
                       // System.out.print(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case "NUMERIC":
                    	stringArray[iArray] = Double.toString(cell.getNumericCellValue());
                       // System.out.print(cell.getNumericCellValue());
                        break;
                }
                iArray++;
               //System.out.print(" - ");
            }
            for(int a=0;a<stringArray.length;a++) {
            	matrix[iMatrix][a]=stringArray[a].toString();
            }
            iMatrix++;
        } 
        workbook.close();
        inputStream.close();
    	return matrix;
	}


	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
	}
}
