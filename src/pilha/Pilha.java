package pilha;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Pilha<X> // LIFO
{

	private Object[] item;
	private int topo = -1;

	public Pilha(int capacidade) throws Exception {

		if (capacidade <= 0)
			throw new Exception("Tamanho invalido");
		this.item = (X[]) new Object[capacidade];
	}

	public Pilha(Pilha<X> modelo) throws Exception {
		if (modelo == null)
			throw new Exception("invalido");

		this.item = (X[]) new Object[modelo.item.length];
		for (int j = 0; j <= modelo.topo; j++)

		{
			this.guardeUmItem((X) modelo.item[j]);
		}

	}

	private X retornaItem(X x) {
		if (x instanceof Cloneable)
			return meuCloneDeX(x);
		return x;

	}

	public void guardeUmItem(X x) throws Exception {
		if (x == null)
			throw new Exception("Invalido");
		if (this.cheia())
			throw new Exception("Não cabem mais itens");
		topo++;
		this.item[topo] = retornaItem(x);
	}

	public X getUmItem() throws Exception {

		if (this.vazia())
			throw new Exception("A pilha esta vazia");

		return retornaItem((X) this.item[topo]);
	}

	public void jogueUmItemFora() throws Exception {

		if (this.vazia())
			throw new Exception("A fila esta vazia");
		this.item[topo] = null;
		topo--;
	}

	public boolean cheia() {

		if (topo == this.item.length - 1)
			return true;

		return false;
	}

	public boolean vazia() {
		if (topo == -1)
			return true;

		return false;
		// verificar se esta vazia;
	}

	public int hashCode() {
		int ret = 666;

		ret = 7 * new Integer(topo).hashCode();
		for (int i = 0; i <= this.topo; i++)
			ret = 7 * this.item[i].hashCode();

		return ret;
	}

	public String toString() {

		String arr = "[";
		for (int i = 0; i <= this.topo; i++) {

			if (this.item[i] != null) {
				if (i != 0)
					arr += ", ";
				arr += this.item[i];
			}

		}
		arr += "] |"+this.topo;
		return arr;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Pilha<X> param = null;
		try {
			param = new Pilha<X>((Pilha<X>) obj);
			if (this.topo != param.topo)
				return false;
		} catch (Exception e) {
		}
		for (int i = 0; i <= this.topo; i++)
			if (!this.item[i].equals(param.item[i]))
				return false;

		return true;
	}

	public Object clone() {
		Pilha<X> ret = null;
		try {
			ret = new Pilha<X>(this);
		} catch (Exception e) {

		}
		return ret;

	}

	private X meuCloneDeX(X x) {
		X ret = null;
		try {
			Class<?> classe = this.getClass();
			Class<?>[] tipoDoParametroFormal = new Class<?>[0];
			Method metodo = classe.getMethod("clone", tipoDoParametroFormal);
			Object[] parametroReal = new Object[0];
			ret = (X) metodo.invoke(this, parametroReal);
		} catch (NoSuchMethodException erro) {
		} catch (InvocationTargetException erro) {
		} catch (IllegalAccessException erro) {
		}

		return ret;
	}

}