package projeto;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
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
	public GUI() {
		
		firstFrame = new JFrame();
		firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		firstFrame.setLayout(new BorderLayout());
		
		contentFirstFrame();
		
	}
	
	public void open() {
		firstFrame.pack();
		firstFrame.setSize(400, 300); 
	    firstFrame.setVisible(true); 

	}

	private void contentFirstFrame() {
		firstFrame = new JFrame();
		firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		firstFrame.setLayout(new BorderLayout());
		firstFrame.pack();
				
		addContentButtonsPanel();
				
		firstFrame.setSize(400, 300); 
	    firstFrame.setVisible(true); 
	}
	
	private void addContentButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		firstFrame.add(buttonsPanel, BorderLayout.SOUTH);
		firstFrame.add(buttonsPanel);
		
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
		
		HashMap<String,Integer> values=guiRegras.getHashValues();
		HashMap<String,String> thresholds=guiRegras.getHashThreshold();
		System.out.println(thresholds.size());
		String metrica1=values.keySet().stream().findFirst().get();
		System.out.println("Regra introduzida pelo utilizador: " + metrica1 + thresholds.get(metrica1) + values.get(metrica1));
		while(rows<25) { // trocar por rows<table.getRowCount()!!!!
			String s = table.getValueAt(rows, 4).toString();
			
			String [] s1 =s.split("\\.");
			tempLOC=Integer.parseInt(s1[0]);
			
			s = table.getValueAt(rows, 5).toString();
			s1 =s.split("\\.");			
			tempCYCLO=Integer.parseInt(s1[0]);
			
			System.out.println(tempLOC + " ," + tempCYCLO);
			
			if(tempLOC<3 && tempCYCLO<2) { //trocar por regra inserida pelo utilizador! este é so um exemplo
				is_long_method=true;
				System.out.println(table.getValueAt(rows,3));
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
			if(!Boolean.valueOf(is_long_method).equals(Boolean.valueOf(tempiPlasma))) {
			//	System.out.println("Is_long_method: " + Boolean.valueOf(is_long_method) + " ; " + "iPlasma value: "+ Boolean.valueOf(tempiPlasma));
				iPlasmaErrors++;
			}
			if(!Boolean.valueOf(is_long_method).equals(Boolean.valueOf(tempPMD)))
				PMDErrors++;
			
			
			rows++;
		}
		System.out.println("iPlasmaErrors: " +iPlasmaErrors  + " ; " + "PMD errors: "+ PMDErrors);
		
	}
	
	public String[][] readExcel(String excelFilePath) throws IOException {
    	String[][] matrix = new String[50][12];
    	int iMatrix=0; 
    	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        System.out.println(excelFilePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        int i=0;
        while (iterator.hasNext() && i<25) {
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
		gui.open();
	}
}
