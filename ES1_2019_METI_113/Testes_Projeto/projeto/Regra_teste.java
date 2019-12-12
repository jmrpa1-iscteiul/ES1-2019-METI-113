package projeto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Regra_teste { 

			
	Regra regra_long_method1 = new Regra("is_long_method", "LOC", 3, "&&", "CYCLO", 6);	
	Regra regra_is_feature_envy_1 = new Regra("is_feature_envy", "ATFD", 10, "||", "LAA", (int) 0.7);
	
	
	@Test 
	void getName_metrica1Test() { 
		assertEquals(regra_long_method1.getName_metrica1(), "LOC");
		assertEquals(regra_is_feature_envy_1.getName_metrica1(), "ATFD");
	
	} 
	@Test 
	void getOperator() {
		assertEquals(regra_long_method1.getOperator(), "&&");
		assertEquals(regra_is_feature_envy_1.getOperator(), "||");
		
	}
	
	@Test
	void getName_metrica2() {
		assertEquals(regra_long_method1.getName_metrica2(),"CYCLO");
		assertEquals(regra_is_feature_envy_1.getName_metrica2(),"LAA");
	}
	
	@Test
	void getValue_metrica1() {
		assertEquals(regra_long_method1.getValue_metrica1(),3);
		assertEquals(regra_is_feature_envy_1.getValue_metrica1(),10);
	}
	
	@Test
	void getValue_metrica2() {
		assertEquals(regra_long_method1.getValue_metrica2(),6);
		assertEquals(regra_is_feature_envy_1.getValue_metrica2(), (int) (0.7));
	}
	
	
	

}
