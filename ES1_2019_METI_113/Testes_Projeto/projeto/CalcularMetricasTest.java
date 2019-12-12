package projeto;

import javax.swing.JTable;

/** Classe para calcular a quantidade dos metodos is_long_method e is_feature_method ao longo do ficheiro Excel e das métricas
 * 
 * @author Andreb , jmrpa1 
 * @Date   12/Dez/2019 
 * version 1.0
 */  

public class CalcularMetricas {
	     
	private JTable table;
	private int rows=1;
	private int tempLOC=0;
	private int tempCYCLO=0;
	private int tempATFD=0;
	private int tempLAA=0;
	private boolean is_long_method=false;
	private boolean is_feature_envy=false;
	private boolean tempiPlasma=false, tempPMD=false;
	private int DCIplasma; int DIIplasma=0; int ADCIplasma=0; int ADIIplasma=0;
	private int DCIpmd=0; int DIIpmd=0; int ADCIpmd=0; int ADIIpmd=0;
	
	
	private Regra x1;
	private Regra b1;
	
	/**
	 * @param x1 - Regra relativa ao defeito is_long_method
	 * @param b1 - Regra relativa ao defeito is_feature_method
	 * @param table - Tabela relativa ao ficheiro Excel
	 */
	public CalcularMetricas(Regra x1, Regra b1, JTable table) {
		this.x1 = x1;
		this.b1 = b1;
		this.table = table;
		
	}

	
	
	/**
	 * Método para calcular as métricas perante a regra definida pelo utilizador e também calcula o numero total de cada indicador
	 */
	
	public void CalcularMetricas1() {
		
		
	while(rows<table.getRowCount()) { 
		String s = table.getValueAt(rows, 4).toString();
		
		String [] s1 =s.split("\\.");
		tempLOC=Integer.parseInt(s1[0]);
		
		s = table.getValueAt(rows, 5).toString();
		s1 =s.split("\\.");			
		tempCYCLO=Integer.parseInt(s1[0]);
		
		s = table.getValueAt(rows, 6).toString();
		String [] s2 = s.split("\\.");
		tempATFD=Integer.parseInt(s2[0]);
		
		s = table.getValueAt(rows, 7).toString();
		String [] s3 = s.split("\\.");
		tempLAA=Integer.parseInt(s3[0]);
		
		//System.out.println(tempLOC + " ," + tempCYCLO);
		
		if(b1.getOperator().equals("&&")) {
			if(tempATFD>b1.getValue_metrica1() && tempLAA>b1.getValue_metrica2()) {
				is_feature_envy=true;
			}
		}
		
		else {
			if(tempATFD>b1.getValue_metrica1() || tempLAA>b1.getValue_metrica2()) {
			//	System.out.println("Linha " + rows + " is_long_method");
				is_feature_envy=true;	
				
			}
		}
	
		if(x1.getOperator().equals("&&")) {
			if(tempLOC>x1.getValue_metrica1() && tempCYCLO>x1.getValue_metrica2()) {
		//		System.out.println("Linha " + rows + " is_long_method");
				is_long_method=true;
				
			}
		} else {
			if(tempLOC>x1.getValue_metrica1() || tempCYCLO>x1.getValue_metrica2()) {
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
		
		System.out.println("Plasma=" + Boolean.valueOf(tempiPlasma) + ", PMD=" + Boolean.valueOf(tempPMD) + ", long_method=" + Boolean.valueOf(is_long_method) + ", is_feature_envy=" + Boolean.valueOf(is_feature_envy));
		
		rows++;
	}
	
	
	}
	
	/**
	 * @return o valor do indicador ADCI em relaçao à ferramenta iPlasma
	 */
	public int getADCIplasma() {
		return ADCIplasma;
	}
	
	
	/**
	 * @return o valor do indicador ADCI em relaçao à ferramenta PMD
	 */
	public int getADCIpmd() {
		return ADCIpmd;
	}
	
	
	/**
	 * @return o valor do indicador ADII em relaçao à ferramenta iPlasma
	 */
	public int getADIIplasma() {
		return ADIIplasma;
	}
	
	
	/**
	 * @return o valor do indicador ADII em relaçao à ferramenta PMD
	 */
	public int getADIIpmd() {
		return ADIIpmd;
	}
	
	
	/**
	 * @return o valor do indicador DCI em relaçao à ferramenta iPlasma
	 */
	public int getDCIplasma() {
		return DCIplasma;
	}
	
	
	/**
	 * @return o valor do indicador DCI em relaçao à ferramenta PMD
	 */
	public int getDCIpmd() {
		return DCIpmd;
	}
	
	
	
	/**
	 * @return o valor do indicador DII em relaçao à ferramenta iPlasma
	 */
	public int getDIIplasma() {
		return DIIplasma;
	}
	 
	
	/**
	 * @return o valor do indicador DII em relaçao à ferramenta iPlasma
	 */
	
	public int getDIIpmd() {
		return DIIpmd;
	}



	public Regra getRegra1() {
		return x1;
	}
	
	public Regra getRegra2() {
		return b1;
	}
	
}
	
	
	
	
