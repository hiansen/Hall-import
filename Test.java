import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Test {
	
	private static String input = "C:/Users/hians/Downloads/outubro.pdf";
	private static String output = "C:/Users/hians/Downloads/teste.csv";

	public static void main(String[] args) throws IOException, DocumentException {
		PdfReader pdfReader = new PdfReader(input);
		List<String> linesWithValue = extractLinesWithValueFrom(pdfReader);
		generateCsv(linesWithValue);
	}

	private static List<String> extractLinesWithValueFrom(PdfReader pdfReader) throws IOException {
		int pages = pdfReader.getNumberOfPages();
		List<String> linhasArquivo = new ArrayList<String>();

		for (int i = 1; i <= pages; i++) {
			String pageContent = PdfTextExtractor.getTextFromPage(pdfReader, i);
			String[] pageLines = pageContent.split("\n");
			for (String line : pageLines) {
				if (verificaLinhaFonte(line)) {
					linhasArquivo.add(line);
				} else {
					System.out.println("não é uma linha");
				}
			}
		}
		return linhasArquivo;
	}

	private static void generateCsv(List<String> linhasArquivo) throws IOException {
		FileWriter csvWriter = new FileWriter(output);

		for (String line : linhasArquivo) {
			imprimeValorDoRegex(line,"(?:[0-9,]+(?:\\.[0-9,.]+)?|\\.[0-9,]\\,)", csvWriter);
			csvWriter.append("\n");

		}
		csvWriter.flush();
		csvWriter.close();
	}
	
	private static void imprimeValorDoRegex(String fonte, String queremosIsso, FileWriter csvWriter) throws IOException {
		Pattern p = Pattern.compile(queremosIsso);

		Matcher m = p.matcher(fonte);

		while (m.find()) {

			csvWriter.append(m.group());
			csvWriter.append(";");
			
		}
	}
	
	// Verificar se o número está na posição 0
	public static boolean verificaLinhaFonte(String line) {
		Pattern p = Pattern.compile("([1-9])\\w+");
		Matcher m = p.matcher(line);
		return m.find();
	}
}
