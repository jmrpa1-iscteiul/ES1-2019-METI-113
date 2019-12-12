package projeto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GUI_test {     

	GUI gui = new GUI();
	
	@Test
	public void GUItest() {
		gui.open();
		assertEquals(gui, gui); 

	}
	
	@Test
	public void contentSecondFrameTest() {
		gui.contentSecondFrame();
	}
	

}
