import java.util.StringTokenizer;

import fila.Fila;
import pilha.Pilha;

public class algebraDoMaligno {
	public static double resolveExpressao(String str) throws Exception{
		return resolvePolonesa(montaPolonesa(str));
		
	}

	public static Fila montaPolonesa(String str) throws Exception {
		StringTokenizer exp = new StringTokenizer(str, "+-*/^()", true);
		Fila<String> fila = new Fila<String>(20);
		Pilha<String> pilha = new Pilha<String>(20);

		while (exp.hasMoreTokens()) {
			boolean ativo = false;
			String next = (String) exp.nextElement();
			System.out.println(next);
			try {
				ativo = false;
				double tente = Double.parseDouble(next);
				fila.guardeUmItem(next);
			} catch (NumberFormatException e) {
				if (next.charAt(0) == '(' || pilha.vazia())
					pilha.guardeUmItem(next);
				else {
					for (;;) {

						if (next.equals(")")) {
							boolean encontrou = false;
							while (!encontrou) {
								if (pilha.getUmItem().equals("(")) {
									pilha.jogueUmItemFora();
									encontrou = true;
									break;
								} else {
									fila.guardeUmItem(pilha.getUmItem());
									pilha.jogueUmItemFora();
								}

							}
							break;

						}

						if (verificaTable(pilha.getUmItem().charAt(0), next.charAt(0))) {
							fila.guardeUmItem(pilha.getUmItem());
							pilha.jogueUmItemFora();
						} else {
							pilha.guardeUmItem(next);
							break;

						}
						if (pilha.vazia()) {
							pilha.guardeUmItem(next);
							break;
						}

					}

				}
				System.out.println(fila);
				System.out.println(pilha);

			}

		}

		while (!pilha.vazia()) {
			fila.guardeUmItem(pilha.getUmItem());
			pilha.jogueUmItemFora();
		}
		return fila;
	}

	public static double resolvePolonesa(Fila f) throws Exception {
		Pilha<Double> pilha = new Pilha<Double>(20);
		while (!f.vazia()) {
			try {
				pilha.guardeUmItem(Double.parseDouble((String) f.getUmItem()));
				f.jogueUmItemFora();
			} catch (Exception e) {
				double num2 = pilha.getUmItem();
				pilha.jogueUmItemFora();
				double num1 = pilha.getUmItem();
				pilha.jogueUmItemFora();
				char op = ((String) f.getUmItem()).charAt(0);
				f.jogueUmItemFora();

				pilha.guardeUmItem(fazAsContas(op, num1, num2));

			}
		}
		return pilha.getUmItem();
	}

	public static boolean verificaTable(Character a, Character b) {
		boolean[][] verify = { { false, false, false, false, false, false, true },
				{ false, false, true, true, true, true, true }, { false, false, true, true, true, true, true },
				{ false, false, true, true, true, true, true }, { false, false, false, false, true, true, true },
				{ false, false, false, false, true, true, true }, { false, false, false, false, false, false, false } };

		return verify[retornaValorChar(a)][retornaValorChar(b)];
	}

	public static int retornaValorChar(Character c) {
		switch (c) {
		case '(':
			return 0;
		case '^':
			return 1;
		case '*':
			return 2;
		case '/':
			return 3;
		case '+':
			return 4;
		case '-':
			return 5;
		case ')':
			return 6;
		default:
			break;
		}
		return -1;
	}

	public static double fazAsContas(Character c, double valor1, double valor2) throws Exception {
		switch (c) {
		case '^':
			return Math.pow(valor1, valor2);
		case '*':
			return valor1 * valor2;
		case '/':
			return valor1 / valor2;
		case '+':
			return valor1 + valor2;
		case '-':
			return valor1 - valor2;
		default:
			throw new Exception("Operador invalido");
		}
	}
}
