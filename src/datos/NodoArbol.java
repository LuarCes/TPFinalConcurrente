package datos;

public class NodoArbol {
	public int dato;
	public NodoArbol hijoIzq;
	public NodoArbol hijoDer;
	
	
	public NodoArbol(int dato) {
		this.dato = dato;
		this.hijoIzq = null;
		this.hijoDer = null;
	}


	@Override
	public String toString() {
		return "" + dato + "-";
	}
	
	
	
}
