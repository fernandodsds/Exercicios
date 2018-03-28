import java.util.StringTokenizer;

import fila.Fila;
import pilha.Pilha;

public class algebraDoMaligno {
	

	public static void calcula(String str) throws Exception {
		StringTokenizer exp = new StringTokenizer(str, "+-*/^()", true);
		Fila<String> fila =new Fila<String>(20);
		Pilha<String> pilha =new Pilha<String>(20);

		while (exp.hasMoreTokens()) {
			
	
			String next =  (String) exp.nextElement();			
		
		try {
				double tente = Double.parseDouble(next);
				fila.guardeUmItem(next);
			} catch (Exception e) {
				if(next.charAt(0) =='(' || pilha.vazia())
					pilha.guardeUmItem(next);
				else
				{
					for(;;)
					{
						boolean tab = verificaTable(pilha.getUmItem().charAt(0), next.charAt(0));
						if(next.equals(")")) {
							boolean encontrou = false;
								while(!encontrou)
								{
									if(pilha.getUmItem().equals("(")) 
									{
										pilha.jogueUmItemFora();
										encontrou =true;
									}
									fila.guardeUmItem(pilha.getUmItem());
									pilha.jogueUmItemFora();
									
								}
								break;
								
							}
						
						
						if(tab)
						{
							fila.guardeUmItem(pilha.getUmItem());
							pilha.jogueUmItemFora();
						}
						else
						{						
							pilha.guardeUmItem(next);
							break;
						
						}
					}
				}
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
