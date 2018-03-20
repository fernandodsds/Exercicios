package fila;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.sql.rowset.spi.TransactionalWriter;

public class Fila2 <X> implements Cloneable //FIFO
{

    private Object[] item;
    private int ultimo = -1;

    public Fila2 (int capacidade) throws Exception
    {
        if (capacidade<=0)
            throw new Exception ("Tamanho invalido");
        this.item =  (X[])new Object [capacidade];

    }

        public Fila2  (Fila2 <X> modelo) throws Exception
        {
          if (modelo==null)
              throw new Exception ("invalido");

          this.item = (X[]) new Object [modelo.item.length];
          for (int j = 0; j <= modelo.ultimo; j++)       	  
          
          {
        	  this.guardeUmItem((X) modelo.item[j]);
      	  }

        }
		private X retornaItem(X x){
			if (x instanceof Cloneable)
				return  meuCloneDeX(x);
			return x;
			
		}
    public void guardeUmItem (X x) throws Exception
    {
    	if(x == null)
    		throw new Exception("Invalido");
    	if (this.cheia())
    		throw new Exception("Não cabem mais itens");
    	ultimo++;
    	this.item[ultimo] = retornaItem(x);
  	}

    public X getUmItem () throws Exception
    {
    	if(this.vazia())
    		throw new Exception("A Fila2 esta vazia");
    	
		return (X) this.item[0];
        //validar se existem coisas no vetor (topo = -1?)
        //retornar o primeiro valor guardado
    }

    public void jogueForaUmItem () throws Exception
    {
    	if(this.vazia())
    		throw new Exception("A Fila2 esta vazia");
    	
    	for (int i = 0; i < this.ultimo; i++) 
			this.item[i]= retornaItem((X) this.item[i+1]);
    	this.item[this.ultimo] = null;
    	this.ultimo--;
        //validar se existe algo guardado em Item
        //remover de Item o primeiro valor guardado
    }

    public boolean cheia ()
    {
    	if(ultimo == this.item.length-1)
    		return true;

        return false;

       
    }

    public boolean vazia ()
    {
    	if(ultimo == -1)
    		return true;

        return false;
    }

    public String toString ()
    {
    	 String arr = "[";
   	  for (int i = 0; i <= this.ultimo; i++) {
   		
   		 if (this.item[i]!=null){
   			 if (i!=0)
   				 arr += ", ";
   			 arr += this.item[i];
   		 }
   		
   	}
   	  arr+= "]";
   	  return arr;
    }


    public int hashCode()
    {
    	int ret = 666;
    	
    	ret = 7* new Integer(ultimo).hashCode();
    	for (int i = 0; i <= this.ultimo; i++) 
    		ret = 7* this.item[i].hashCode();
		
    	
    	
		return ret;


    }


    public boolean equals (Object obj)
    {
    	if(obj == this)
    		return true;
    	if(obj == null)
    		return false;
    	
    	if(this.getClass() != obj.getClass())
    		return false;
    	
    	
    	Fila2<X> param = null;
		try {
			param = new Fila2<X>((Fila2<X>)obj);
			if(this.ultimo != param.ultimo)
				return false;
		} catch (Exception e) {
		}
    	for (int i = 0; i <= this.ultimo; i++) 
			if(!this.item[i].equals(param.item[i]))
				return false;
    	
		return true;		
    }


    public Object clone ()
    {
    	Fila2<X> ret =null;
    	try {
			ret = new Fila2<X>(this);
		} catch (Exception e) {
			
		}
		return ret;


    }


    private  X meuCloneDeX (X x)
    {
    	X ret =null;
      try
      {
          Class<?> classe = this.getClass();
          Class<?>[] tipoDoParametroFormal = new Class<?> [0];
          Method metodo                    = classe.getMethod ("clone", tipoDoParametroFormal);
          Object[] parametroReal           = new Object [0];
          ret= (X) metodo.invoke (this, parametroReal);
      }
      catch (NoSuchMethodException erro)
      {}
      catch (InvocationTargetException erro)
      {}
      catch (IllegalAccessException erro)
      {}
      
      return ret;
    }
}