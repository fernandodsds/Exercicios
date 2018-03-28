import java.util.StringTokenizer;

import fila.Fila;
import pilha.Pilha;

public class algebraDoMaligno {
	
	public static StringTokenizer quebra(String exp)throws Exception {
		if(exp == null || exp.equals(""))
			throw new Exception("Insira uma expressão");
//		if(exp.contains("^[a-zA-Z\\s]+$"))
//			throw new Exception("Somente");
		StringTokenizer ret = new StringTokenizer(exp, "+-*/^()",true);
		
		return ret;
	}
	
	public static void primeiraParte(StringTokenizer exp) throws Exception
	{

			Fila<String> fila =  new Fila<String>(exp.countTokens());
			Pilha<String> pilha =  new Pilha<String>(exp.countTokens());

		while(exp.hasMoreTokens())
		{
			try {
				Double valor = Double.parseDouble(exp.nextToken());
				System.out.println(valor);
				fila.guardeUmItem(exp.nextToken());
			} catch (Exception e){
				String verificable =exp.nextToken();
//				while(true) {
//					if(verificaTable(verificable.charAt(0), pilha.getUmItem().charAt(0)))
//					{
//						fila.guardeUmItem(pilha.getUmItem());
//						pilha.jogueUmItemFora();
//						
//					}
//					else
//					{
//						pilha.guardeUmItem(verificable);
//						break;
//					}
//				}
				System.out.println(verificable);
			}
			
			
			
		}
		System.out.println(fila);
		System.out.println(pilha);
	}
	
	
	public static boolean verificaTable(Character a,Character b) {
		boolean [][] verify ={
				          {false,false,false,false,false,false,true},
						  {false,false,true,true,true,true,true},
						  {false,false,true,true,true,true,true},
						  {false,false,true,true,true,true,true},
						  {false,false,false,false,true,true,true},
						  {false,false,false,false,true,true,true},
						  {false,false,false,false,false,false,false}
				         };
		
		
		return verify[retornaValorChar(a)][retornaValorChar(a)];
	}
	
	public static int retornaValorChar(Character c)
	{
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
	
}
