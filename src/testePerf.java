import fila.Fila;
import fila.Fila2;

public class testePerf {
	
	public static void main(String[] args) {
//    try {
//		Fila2<Integer> a = new Fila2<Integer>(10000000);
//		for (int i = 0; i < 10000000; i++) {
//			a.guardeUmItem(new Integer(i));
//		}
//		for (int i = 0; i < 10000000; i++) {
//			a.jogueForaUmItem();
//		}
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		
		try {
			String exp = "10+(2*3-4)^2/4+6*2";
			algebraDoMaligno.calcula(exp);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
