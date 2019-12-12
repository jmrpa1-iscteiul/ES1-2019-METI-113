package projeto;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTable;

import org.junit.jupiter.api.Test;

class CalcularMetricasTest {
	 
	private Regra x1 = null;
	private Regra b1 = null;
	private String[] columnNames = { "MethodID", "Package", "Class", "Method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "iPlasma", "PMD", "is_feature_envy" }; 
	private JTable table = new JTable(421, 12);
  
	CalcularMetricas calcularmetricas = new CalcularMetricas(x1, b1, table);

	@Test
	public void RegraTest() {
		assertEquals(null, calcularmetricas.getRegra1());
		assertEquals(null, calcularmetricas.getRegra2());
	}
	
	@Test
	public void TableTest() {
		assertEquals(421, table.getRowCount());
	}
	
	@Test
	public void ADCIplasmaTest() {
		assertEquals(calcularmetricas.getADCIplasma(),0);
	}
	@Test
	public void ADCIpmdTest() {
		assertEquals(calcularmetricas.getADCIpmd(),0);
	}
	@Test
	public void ADIIplasmaTest() {
		assertEquals(calcularmetricas.getADIIplasma(),0);
	}
	@Test
	public void ADIIpmdTest() {
		assertEquals(calcularmetricas.getADIIpmd(),0);
	}
	@Test
	public void DCIplasmaTest() {
		assertEquals(calcularmetricas.getDCIplasma(),0);
	}
	@Test
	public void DCIpmdTest() {
		assertEquals(calcularmetricas.getDCIpmd(),0);
	}
	
	@Test
	public void DIIplasmaTest() {
		assertEquals(calcularmetricas.getDIIplasma(),0);
	}
	@Test
	public void DIIpmdTest() {
		assertEquals(calcularmetricas.getDIIpmd(),0);
	}
	
}



