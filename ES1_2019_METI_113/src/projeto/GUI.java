package projeto;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.NumberFormatter;

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
	private int LOCvalue, CYCLOvalue, ATFDvalue, LAAvalue;
	private JFormattedTextField  fieldLOC, fieldCYCLO, fieldATFD, fieldLAA;
	private ArrayList<String>array = new ArrayList<String>();
	
	public GUI() {
		
		firstFrame = new JFrame();
		firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		firstFrame.setLayout(new BorderLayout());
		firstFrame.pack();
		firstFrame.setSize(400, 300); 
	    firstFrame.setVisible(true); 
		//contentFirstFrame();
	}
	
	public void open() {
		contentFirstFrame();
	}
	
	//sfse
	//ola sdsdf asdasds
	
	private void contentFirstFrame() {
		firstFrame = new JFrame();
		firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		firstFrame.setLayout(new BorderLayout());
		firstFrame.pack();
		
		addContentMetricsPanel();
		
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
						LAAvalue = Integer.parseInt(fieldLAA.getText());
						ATFDvalue = Integer.parseInt(fieldATFD.getText());
						CYCLOvalue = Integer.parseInt(fieldCYCLO.getText());
						LOCvalue = Integer.parseInt(fieldLOC.getText());
						System.out.println(LOCvalue + ", "+ ", " + CYCLOvalue + ", " + ATFDvalue + ", " + LAAvalue);

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
		
		JButton chooseCodeFileButton = new JButton("Choose code file");
		buttonsPanel.add(chooseCodeFileButton);
		chooseCodeFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				
				int returnValue = jfc.showOpenDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					try {
						readCode(selectedFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton fazerRegra = new JButton("Fazer regra");
		
		buttonsPanel.add(fazerRegra);
		fazerRegra.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			//firstFrame.setVisible(false);
			//Interface_Regras inter = new Interface_Regras(firstFrame, LOCvalue, CYCLOvalue, ATFDvalue, LAAvalue, array);
			Interface_Regras inter = new Interface_Regras(firstFrame, LOCvalue, CYCLOvalue, ATFDvalue, LAAvalue, array);
			//Interface_Regras inter = new Interface_Regras(firstFrame, LOCvalue, CYCLOvalue, ATFDvalue, LAAvalue, this);
			inter.open();
		}
		
	});	
	
	}

	private void addContentMetricsPanel() {
		JPanel metricsPanel = new JPanel();
		metricsPanel.setLayout(new GridLayout(4,4));
		firstFrame.add(metricsPanel, BorderLayout.NORTH);
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMaximum(100);
		
		JLabel labelLOC = new JLabel ("LOC: "); metricsPanel.add(labelLOC);
		fieldLOC = new JFormattedTextField(numberFormatter); metricsPanel.add(fieldLOC);
		JLabel labelCYCLO = new JLabel ("CYCLO: "); metricsPanel.add(labelCYCLO);
		fieldCYCLO = new JFormattedTextField(numberFormatter); metricsPanel.add(fieldCYCLO);
		JLabel labelATFD = new JLabel ("ATFD: "); metricsPanel.add(labelATFD);
		fieldATFD = new JFormattedTextField(numberFormatter); metricsPanel.add(fieldATFD);
		JLabel labelLAA = new JLabel ("LAA: "); metricsPanel.add(labelLAA);
		fieldLAA = new JFormattedTextField(numberFormatter);metricsPanel.add(fieldLAA);
		
	}

	private void contentSecondFrame() {
		secondFrame = new JFrame();
		secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		secondFrame.setLayout(new BorderLayout());
		secondFrame.pack();
		
		JTable table = new JTable(data, columnNames);
		secondFrame.add(table);
		secondFrame.setSize(1100, 400); 
		secondFrame.setVisible(true); 		
		
	}
	
	public String[][] readExcel(String excelFilePath) throws IOException {
    	String[][] matrix = new String[50][12];
    	int iMatrix=0; 
    	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        
        while (iterator.hasNext()) {
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
	
	public ArrayList <String> getArray(){
		return array;
	}
	
	public void readCode(File selectedFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(selectedFile);
		
		//Number of atributes in the given class
        System.out.println("Number of atributes: " +selectedFile.getClass());
        Field[] atributos = selectedFile.getClass().getDeclaredFields();
        for(Field f: atributos) {
        	System.out.println(f.getName());
        }
	   
        int lineNum = 0;
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        //Number of lines per method
	        if(line.contains("public") || line.contains("private") && line.contains("{")) {
	        	lineNum++;	        	
	        }
	    }
	    System.out.println("Number of methods: " + lineNum);
	}
	


	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
		gui.open();
	}
}
