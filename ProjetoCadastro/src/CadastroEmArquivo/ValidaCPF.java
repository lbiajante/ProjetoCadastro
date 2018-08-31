package CadastroEmArquivo;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

public class ValidaCPF {
	private Scanner entrada = new Scanner(System.in);

	public static boolean isCPF(String CPF) {

		if (CPF.equals("00000000000") || CPF.equals("11111111111")
				|| CPF.equals("22222222222") || CPF.equals("33333333333")
				|| CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777")
				|| CPF.equals("88888888888") || CPF.equals("99999999999")
				|| (CPF.length() != 11)) {
			return (false);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;

			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}
			sm = 0;
			peso = 11;

			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';

			} else {
				dig11 = (char) (r + 48);
			}
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {

				return (true);
			} else {
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
				+ CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
	}

	public String validarCPF() {
		String CPF = null;
		CadastroEmArquivo cad = new CadastroEmArquivo();
		boolean confere = true;
		while (confere) {
			try {
				System.out.println("Digite o CPF (somente numeros)");
				CPF = entrada.nextLine().trim();
				if (ValidaCPF.isCPF(CPF)) {
					CPF = ValidaCPF.imprimeCPF(CPF);
					cad.setCpf(CPF);
					confere = false;
				} else {
					System.out.println("Número de CPF invalido");
				}
			} catch (Exception e) {
				System.out.println("Erro, CPF invalido!");
				confere = true;
			}
		}
		return CPF;
	}

	public String formatString(String campo, String mascara) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter(mascara);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(campo);
		} catch (ParseException ex) {
			return campo;
		}
	}
}
