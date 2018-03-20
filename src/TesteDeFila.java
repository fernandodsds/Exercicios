import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fila.Fila;

/*
Convencoes de nomenclatura:
==========================
1) Palavras reservadas da linguagem:
   totalmente em minusculo
   ex: class, static, for, if, int

2) Nomes de classes e projetos
   as letras iniciais de cada palavra que forma
   o nome devem ser maiusculas, as demais,
   minusculas e as palavras justapostas
   ex: Fila, Pilha, InputStreamReader

3) Nomes de variaveis (atributos ou nao),
   parametros e metodos
   as letras iniciais de cada palavra que forma
   o nome devem ser maiusculas, menos a inicial
   da primeira palavra, as demais,
   minusculas e as palavras justapostas
   ex: guardeUmItem, getUmItem, jogueUmItemFora,
       getUmItem, item

4) Nomes de constantes (atributos ou nao)
   as letras devem ser TODAS maiusculas e as
   palavras separadas por underline ( _ )
   ex: a constante MAX_VALUE da classe Integer,
       a constante PI da classe Math

5) Nomes de bibliotecas de classes
   as letras devem ser TODAS minusculas e as
   palavras separadas por ponto ( . )
   ex: java.io, java.sql, java.lang.reflect

*/
public class TesteDeFila {
	public static void main(String[] args) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		Fila<String> f;

		for (;;) {
			System.out.print("Tamanho desejado? ");

			try {
				f = new Fila<String>(Integer.parseInt(teclado.readLine()));
				break;
			} catch (IOException erro) {
			} // DANGER: NUNCA FAÇA ISSO SE NAO TEM CERTEZA DE QUE NAO VAI OCORRER A EXCEÇÃO
			catch (NumberFormatException erro) {
				System.err.println("Nao pode digitar letras e outros caracteres especiais!");
				System.err.println("Nao pode tampouco digitar numeros fracionarios!");
				System.err.println("Tente novamente...");
			} catch (Exception erro) {
				System.err.println("Tamanho invalido!");
				System.err.println("O tamanho tem que ser positivo!");
				System.err.println("Tente novamente...");
			}
		}

		char opcao;
		do {
			System.out.print("Opcao (G:guardar; R:recuperar; J:jogar fora; S:sair)? ");
			String digitacao = null;
			try {
				digitacao = teclado.readLine().toUpperCase();
			} catch (IOException erro) {
			} // DANGER: NUNCA FAÇA ISSO SE NAO TEM CERTEZA DE QUE NAO VAI OCORRER A EXCEÇÃO
			if (digitacao.length() != 1)
				opcao = ' ';
			else
				opcao = digitacao.charAt(0);

			switch (opcao) {
			case 'G':
				System.out.print("Guardar o que? ");
				String oque = null;
				try {
					oque = teclado.readLine();
				} catch (IOException erro) {
				} // DANGER: NUNCA FAÇA ISSO SE NAO TEM CERTEZA DE QUE NAO VAI OCORRER A EXCEÇÃO

				try {
					f.guardeUmItem(oque);
					System.out.println("Operacao realizada com sucesso");
				} catch (Exception erro) {
					System.err.println(erro.getMessage());
				}
				break;

			case 'R':
				try {
					System.out.println(f.getUmItem());
				} catch (Exception erro) {
					System.err.println(erro.getMessage());
				}
				break;

			case 'J':
				try {
					f.jogueUmItemFora();
					System.out.println("Operacao realizada com sucesso");
				} catch (Exception erro) {
					System.err.println(erro.getMessage());
				}
				break;

			case 'S':
				System.out.println("Obrigado por usar este programa!");
				break;

			default:
				System.err.println("Opcao invalida!");
			}
			System.out.println(f);
		} while (opcao != 'S');
	}
}