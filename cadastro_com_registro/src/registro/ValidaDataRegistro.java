package registro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidaDataRegistro {
	private Scanner entrada = new Scanner(System.in);

	public String hora() {
		boolean confere = true;
		Date tmns = null;

		while (confere) {

			try {
				SimpleDateFormat timeFormatIn = new SimpleDateFormat("HHmm");
				System.out.println("Digite a hora da visita no formato: hhmm");
				timeFormatIn.setLenient(false);
				Date time = timeFormatIn.parse(entrada.nextLine().trim());
				tmns = time;
				confere = false;
			} catch (Exception e) {
				System.out.println("Hora invalida");
			}
		}
		String horaVisita = null;
		try {
			SimpleDateFormat timeFormatOut = new SimpleDateFormat("HH:mm");
			String timeToString = timeFormatOut.format(tmns);
			horaVisita = timeToString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horaVisita;
	}

	public String data() {
		boolean confere = true;
		Date dtns = null;

		while (confere) {

			try {
				SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
				System.out
				.println("Digite a data da visita no formato: 'ddmmaaaa'");
				dataFormatIn.setLenient(false);
				Date date = dataFormatIn.parse(entrada.nextLine().trim());
				dtns = date;
				confere = false;
			} catch (Exception e) {
				System.out.println("Data invalida");
			}
		}
		String dataVisita = null;
		try {
			SimpleDateFormat dataFormatOut = new SimpleDateFormat("dd-MM-yyyy");
			String dataToString = dataFormatOut.format(dtns);
			dataVisita = dataToString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataVisita;
	}

}
