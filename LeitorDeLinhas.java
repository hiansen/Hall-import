import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeitorDeLinhas {
	public static void main(String[] args) {

		String linha = " 6 Fonte:     40  ASPS - Acoes de Servicos Publ          220.881,00                16,46          153.275,39          -67.605,61 ";

		System.out.println(linha);
		String fonte = "6 Fonte:     40  ASPS - Acoes de Servicos Publ          220.881,00                16,46          153.275,39          -67.605,61";

		String queremosIsso = "([A-Z])\\w+";

		imprimeValorDoRegex(fonte, queremosIsso);

	}

	private static void imprimeValorDoRegex(String fonte, String queremosIsso) {
		/*
		 * Nesse momento obteremos uma instância de Pattern, através do * método static
		 * compile(String regex), o qual recebe uma String que é a expressção regular
		 */
		Pattern p = Pattern.compile(queremosIsso);

		/*
		 * Aqui, através da instancia de Pattern, chamando o método * matcher() e
		 * passando a fonte de busca
		 */

		Matcher m = p.matcher(fonte);

		// Ligando o motor
		while (m.find()) {

			// Obtendo o inicio do que foi encontrado
			System.out.println(m.group());

		}
	}

}
