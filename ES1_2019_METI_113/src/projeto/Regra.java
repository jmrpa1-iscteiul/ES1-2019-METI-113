package projeto;

public class Regra {
 
	private String nome, name_metrica1, operator, name_metrica2; 
	private int value_metrica1, value_metrica2;
	
	public Regra(String nome, String name_metrica1, int value_metrica1, String operator, String name_metrica2, int value_metrica2) {
		this.nome=nome;
		this.name_metrica1=name_metrica1;
		this.name_metrica2=name_metrica2;
		this.value_metrica1=value_metrica1;
		this.value_metrica2=value_metrica2;
		this.operator=operator;
	}	

	public String getName_metrica1() {
		return name_metrica1;
	}

	public String getOperator() {
		return operator;
	}

	public String getName_metrica2() {
		return name_metrica2;
	}

	public int getValue_metrica1() {
		return value_metrica1;
	}

	public int getValue_metrica2() {
		return value_metrica2;
	}
	
	public void is(int tempMetrica1, int tempMetrica2) {
		
	}
	
	
}
