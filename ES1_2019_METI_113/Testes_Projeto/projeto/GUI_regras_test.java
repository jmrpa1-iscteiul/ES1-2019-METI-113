package projeto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GUI_regras_test {
	
	GUI_regras gui_regras = new GUI_regras();
	Regra is_long_method = new Regra("is_long_method", "LOC", 3, "&&", "CYCLO", 6);
	Regra is_feature_envy = new Regra("is_feature_envy", "ATFD", 5, "||", "LAA",2);
	
	
	@Test
	public void getIs_long_methodTest() {
		assertEquals(gui_regras.getIs_long_method(),is_long_method);
	}
	 
	@Test 
	public void getIs_feature_envyTest() {
		assertEquals(gui_regras.getIs_feature_envy(),is_feature_envy);
	}
	
	
}