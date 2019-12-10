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

public class GUI{

	
	private JFrame firstFrame;
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
	
	private void open() {
		firstFrame = new JFrame();
		firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		firstFrame.setLayout(new BorderLayout());
		firstFrame.pack();
				
		addContentButtonsPanel();
				
		firstFrame.setSize(500, 300); 
	    firstFrame.setVisible(true); 
	}
	
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
				calcularMetricas();
		}
		
		});
		
		
	
	}


	private void contentSecondFrame() {
		secondFrame = new JFrame();
		secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
	
	
	private void calcularMetricas() {
		int rows=1;
		int tempLOC=0;
		int tempCYCLO=0;
		boolean is_long_method=false;
		boolean tempiPlasma=false, tempPMD=false;
		iPlasmaErrors=0;PMDErrors=0;
		
		int DCIplasma=0; int DIIplasma=0; int ADCIplasma=0; int ADIIplasma=0;
		int DCIpmd=0; int DIIpmd=0; int ADCIpmd=0; int ADIIpmd=0;

		Regra regra_is_long_method = guiRegras.getIs_long_method();
	//	Regra regra_is_feature_envy = guiRegras.getIs_feature_envy();
		
		while(rows<10) { // trocar por rows<table.getRowCount()!!!!

		HashMap<String,Integer> values=guiRegras.getHashValues();
		HashMap<String,String> thresholds=guiRegras.getHashThreshold();
		System.out.println(thresholds.size());
		String metrica1=values.keySet().stream().findFirst().get();
		System.out.println("Regra introduzida pelo utilizador: " + metrica1 + thresholds.get(metrica1) + values.get(metrica1));
		while(rows<table.getRowCount()) { // trocar por rows<table.getRowCount()!!!!

			String s = table.getValueAt(rows, 4).toString();
			
			String [] s1 =s.split("\\.");
			tempLOC=Integer.parseInt(s1[0]);
			
			s = table.getValueAt(rows, 5).toString();
			s1 =s.split("\\.");			
			tempCYCLO=Integer.parseInt(s1[0]);
			
			//System.out.println(tempLOC + " ," + tempCYCLO);
		
			if(regra_is_long_method.getOperator().equals("&&")) {
				if(tempLOC>regra_is_long_method.getValue_metrica1() && tempCYCLO>regra_is_long_method.getValue_metrica2()) {
			//		System.out.println("Linha " + rows + " is_long_method");
					is_long_method=true;	
				}
			} else {
				if(tempLOC>regra_is_long_method.getValue_metrica1() || tempCYCLO>regra_is_long_method.getValue_metrica2()) {
				//	System.out.println("Linha " + rows + " is_long_method");
					is_long_method=true;	
				}
			}
				
			
			String iPlasmaString =(String) table.getValueAt(rows, 9);

			switch(iPlasmaString) { //iplasma
				case "false":
					tempiPlasma=false;
				break;
				case "true":
					tempiPlasma=true;
				break;
				
			}
			
			String PMDString =(String) table.getValueAt(rows, 10);
			switch(PMDString) { //iplasma
				case "false":
					tempPMD=false;
				break;
				case "true":
					tempPMD=true;
				break;
				
			}
			
			if(Boolean.valueOf(is_long_method).equals(true) && Boolean.valueOf(tempiPlasma).equals(true)) {
				DCIplasma++;
			}
			if(Boolean.valueOf(is_long_method).equals(false) && Boolean.valueOf(tempiPlasma).equals(true)) {
				DIIplasma++;
			}
			if(Boolean.valueOf(is_long_method).equals(false) && Boolean.valueOf(tempiPlasma).equals(false)) {
				ADCIplasma++;
			}
			if(Boolean.valueOf(is_long_method).equals(true) && Boolean.valueOf(tempiPlasma).equals(false)) {
				ADIIplasma++;
			}
			
			if(Boolean.valueOf(is_long_method).equals(true) && Boolean.valueOf(tempPMD).equals(true)) {
				DCIpmd++;
			}
			if(Boolean.valueOf(is_long_method).equals(false) && Boolean.valueOf(tempPMD).equals(true)) {
				DIIpmd++;
			}
			if(Boolean.valueOf(is_long_method).equals(false) && Boolean.valueOf(tempPMD).equals(false)) {
				System.out.println("pmd " + rows);
				ADCIpmd++;
			}
			if(Boolean.valueOf(is_long_method).equals(true) && Boolean.valueOf(tempPMD).equals(false)) {
				ADIIpmd++;
			}
			
			System.out.println("Plasma=" + Boolean.valueOf(tempiPlasma) + ", PMD=" + Boolean.valueOf(tempPMD));
			
			if(!Boolean.valueOf(is_long_method).equals(Boolean.valueOf(tempiPlasma))) {
				iPlasmaErrors++;
			}
			if(!Boolean.valueOf(is_long_method).equals(Boolean.valueOf(tempPMD)))
				PMDErrors++;
			
			
			rows++;
		}
		
		dciplasma.setText(Integer.toString(DCIplasma));
		dcipmi.setText(Integer.toString(DCIpmd));
		diiplasma.setText(Integer.toString(DIIplasma));
		diipmi.setText(Integer.toString(DIIpmd));
		adciplasma.setText(Integer.toString(ADCIplasma));
		adcipmi.setText(Integer.toString(ADCIpmd));
		adiiplasma.setText(Integer.toString(ADIIplasma));
		adiipmi.setText(Integer.toString(ADIIpmd));
	}
	
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
