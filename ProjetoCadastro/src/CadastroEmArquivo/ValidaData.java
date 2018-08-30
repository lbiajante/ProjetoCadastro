package CadastroEmArquivo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidaData {
	private Scanner entrada = new Scanner(System.in);
	public String data() {
		boolean confere = true;
		Date dtns = null;

		while (confere) {
			try {
				SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
				System.out
						.println("Digite a data de nascimento com o formato: ddmmaaaa");
				dataFormatIn.setLenient(false);
				Date date = dataFormatIn.parse(entrada.next());
				dtns = date;
				confere = false;
			} catch (Exception e) {
				System.out.println("Data invalida");
			}
		}
		String dataNasc = null;
		try {
			SimpleDateFormat dataFormatOut = new SimpleDateFormat("dd/MM/yyyy");
			String dataToString = dataFormatOut.format(dtns);
			dataNasc = dataToString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataNasc;
	}

}
