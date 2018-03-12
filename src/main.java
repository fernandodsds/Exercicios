public class main {
  public static void main(String[] args) {
    try {
		Fila<String> X = new Fila<String>(12);	
		X.guardeUmItem("João");
		Fila<String> Y = (Fila<String>) X.clone();
		
		if (Y.equals(X)) 
			System.out.println("iguais");
		X.guardeUmItem("Moises");
		System.out.println(X.getUmItem());
		
		if (!Y.equals(X)) 
			System.out.println("não iguais");
		
		System.out.println(X);
		X.jogueForaUmItem();
		System.out.println(X);
		System.out.println(Y);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }
}
