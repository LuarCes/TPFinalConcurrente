package datos;

public class ArbolBinario {
	public NodoArbol raiz;
	public int tam;

	public ArbolBinario() {
		this.raiz = null;
		this.tam = 0;
	}

	// Metodo para insertar un nodo en el arbol
	public void agregarNodo(int dato) {
		NodoArbol nuevo = new NodoArbol(dato);
		if (raiz == null) {
			raiz = nuevo;
			this.tam ++;
		} else {
			NodoArbol aux = raiz;
			NodoArbol padre;

			while (true) {
				padre = aux;
				if (dato < aux.dato) {
					aux = aux.hijoIzq;
					if (aux == null) {
						padre.hijoIzq = nuevo;
						this.tam ++;
						return;
					}
				} else {
					aux = aux.hijoDer;
					if (aux == null) {
						padre.hijoDer = nuevo;
						this.tam ++;
						return;
					}
				}
			}
		}
	}

	public void inOrden(NodoArbol raiz) {
		if (raiz != null) {
			inOrden(raiz.hijoIzq);
			System.out.print(raiz);
			inOrden(raiz.hijoDer);
		}
	}
	
	
}
