package projeto;

/** Classe Regra, indica o construtor com duass metricas e metodos para os getters.
 * A classe Regra contem no construtor o nome da regra, nome da metrica1, o valor, o operador (|| ou &&)
 * e o mesmo para a metrica2
 *@author joset, andreb
 *@Date 12/Dez/2019
 *@version 1.0
 */ 
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

	
	/**metodo que retorna a metrica1 ( por exemplo long_method)
	 * @return
	 */
	public String getName_metrica1() {
		return name_metrica1;
	}

	/**metodo que retorna a o operador da metrica1 ( por exemplo &&)
	 * @return
	 */
	public String getOperator() {
		return operator;
	}

	/**metodo que retorna a metrica2 ( por exemplo feature_envy)
	 * @return
	 */
	public String getName_metrica2() {
		return name_metrica2;
	}

	/**metodo que retorna o valor da metrica1 ( por exemplo 30)
	 * @return
	 */
	public int getValue_metrica1() {
		return value_metrica1;
	}

	/**metodo que retorna o valor da  metrica2 ( por exemplo 3)
	 * @return
	 */
	public int getValue_metrica2() {
		return value_metrica2;
	}
	

	
	
}
