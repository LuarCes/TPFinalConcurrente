package test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import datos.ArbolBinario;

public class TpConcurrente implements Runnable {
	private static ArbolBinario arbol = new ArbolBinario();

	public static void main(String[] args) {

		// ----------------------------------------------------------------------------------------------------
		System.out.println("------------- Recorrido de Arbol Binario // InOrden ------------------- \n");

		System.out.println(" --> Programacion secuencial \n");


		// ---- Agrego nodos al arbol
		arbol.agregarNodo(24);
		arbol.agregarNodo(38);
		arbol.agregarNodo(12);
		arbol.agregarNodo(27);
		arbol.agregarNodo(18);
		arbol.agregarNodo(9);
		arbol.agregarNodo(45);
		arbol.agregarNodo(2);
		arbol.agregarNodo(10);
		arbol.agregarNodo(40);
		

		// ---- Recorrido InOrden

		double tiempoInicial, tiempoFinal;
		
		
		tiempoInicial = System.nanoTime(); // Guardo el tiempo en el que se ejecutó System.nanoTime()

		arbol.inOrden(arbol.raiz); //Se llama a la función inOrden

		tiempoFinal = System.nanoTime() - tiempoInicial; // Cálculo de la diferencia entre la ejecución del segundo System.nanoTime() y el primero
		
		System.out.print("\nSecuencial : Se recorrieron "+ arbol.tam +" nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n");
		

		// ----------------------------------------------------------------------------------------------------
		
		System.out.println("\n --> Programacion concurrente \n");
  

		// Creo un pool de hilos. En este caso, se crean dos hilos
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		
		tiempoInicial = System.nanoTime(); 
		//Se asigna tarea a cada hilo, asignando el lado del árbol sobre el cuál actuarán
		executor.submit(() -> arbol.inOrden(arbol.raiz.hijoIzq)); //Un hilo recorre el lado izquierdo de árbol
		executor.submit(() -> arbol.inOrden(arbol.raiz.hijoDer)); //El otro hilo recorre el lado derecho
		
		tiempoFinal = System.nanoTime() - tiempoInicial;
		//Antes de continuar, se debe esperar a que los hilos terminen de ejecutarse
		executor.shutdown();
		try {
		    if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
		        executor.shutdownNow();
		    }
		} catch (InterruptedException e) {
		    executor.shutdownNow();
		}
		
		
			
        System.out.print("\nConcurrente : Se recorrieron "+ arbol.tam + " nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n");
        
        
        
        
        System.out.println("\n ------------------------------------------------------------------------------ \n");
		System.out.println("\n --> Más ejemplos \n");
  
        
		 System.out.println("\n -----------------------> Ejemplo 2 <------------------------------------------\n");
        //------- Agrego más nodos al árbol para un nuevo caso
        Random rand = new Random(System.nanoTime());
        
        for(int i=0; i<90; i++) { //Para crear un árbol de 100 nodos
        	arbol.agregarNodo( rand.nextInt(10,100)); //Se agrega cada nodo con un número random entre 10 y 100
        }
        
        // Ejecución secuencial
        tiempoInicial = System.nanoTime(); 

		arbol.inOrden(arbol.raiz); 

		tiempoFinal = System.nanoTime() - tiempoInicial; 
		
		System.out.print("\nSecuencial : Se recorrieron "+ arbol.tam +" nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n\n");
		
		
		//Ejecución concurrente
		//Creo un segundo pool de dos hilos
		ThreadPoolExecutor executor2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		
		tiempoInicial = System.nanoTime();
		//Lanzo cada hilo
		executor2.submit(() -> arbol.inOrden(arbol.raiz.hijoIzq));
		executor2.submit(() -> arbol.inOrden(arbol.raiz.hijoDer));
		
		tiempoFinal = System.nanoTime() - tiempoInicial;
		
		executor2.shutdown();
		try {
		    if (!executor2.awaitTermination(1, TimeUnit.MINUTES)) {
		        executor2.shutdownNow();
		    }
		} catch (InterruptedException e) {
		    executor2.shutdownNow();
		}
		
		
		
        System.out.print("\nConcurrente : Se recorrieron "+ arbol.tam + " nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n\n");
        
        
        
        
        System.out.println("\n -----------------------> Ejemplo 3 <------------------------------------------\n");
        
        for(int i=0; i<900; i++) { //Árbol de 1000 nodos
        	arbol.agregarNodo( rand.nextInt(100,1000));
        }
        
        //Ejecución secuencial
        tiempoInicial = System.nanoTime(); 

		arbol.inOrden(arbol.raiz); 

		tiempoFinal = System.nanoTime() - tiempoInicial;

		System.out.print("\nSecuencial : Se recorrieron "+ arbol.tam +" nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n\n");
		
		//Ejecución concurrente
		ThreadPoolExecutor executor3 = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		
        tiempoInicial = System.nanoTime();
        
		executor3.submit(() -> arbol.inOrden(arbol.raiz.hijoIzq));
		executor3.submit(() -> arbol.inOrden(arbol.raiz.hijoDer));
		
		tiempoFinal = System.nanoTime() - tiempoInicial;
		
		executor3.shutdown();
		try {
		    if (!executor3.awaitTermination(1, TimeUnit.MINUTES)) {
		        executor3.shutdownNow();
		    }
		} catch (InterruptedException e) {
		    executor3.shutdownNow();
		}
		
		
		
        System.out.print("\nConcurrente : Se recorrieron "+ arbol.tam + " nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n\n");
        
        
        
        System.out.println("\n -----------------------> Ejemplo 4 <------------------------------------------\n");
        
        for(int i=0; i<9000; i++) { //Árbol de 10000 nodos
        	arbol.agregarNodo( rand.nextInt(100,1000));
        }
        
        
        //Ejecución secuencial
        tiempoInicial = System.nanoTime();

		arbol.inOrden(arbol.raiz);

		tiempoFinal = System.nanoTime() - tiempoInicial;

		System.out.print("\nSecuencial : Se recorrieron "+ arbol.tam +" nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n\n");
		
		//Ejecución concurrente
		ThreadPoolExecutor executor4 = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		
        tiempoInicial = System.nanoTime();
        
		executor4.submit(() -> arbol.inOrden(arbol.raiz.hijoIzq));
		executor4.submit(() -> arbol.inOrden(arbol.raiz.hijoDer));
		
		tiempoFinal = System.nanoTime() - tiempoInicial;
		
		executor4.shutdown();
		try {
		    if (!executor4.awaitTermination(1, TimeUnit.MINUTES)) {
		        executor4.shutdownNow();
		    }
		} catch (InterruptedException e) {
		    executor4.shutdownNow();
		}
		
		
		
        System.out.print("\nConcurrente : Se recorrieron "+ arbol.tam + " nodos y se tardó un total de -->" + tiempoFinal / 1000 + " nanosegundos\n\n");
        
		}
	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

}