package com.practica.lista;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;
import com.practica.genericas.Coordenada;


public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
	public void insertarNodoTemporal(PosicionPersona p) {
		NodoTemporal aux = lista;
		NodoTemporal existente = buscarNodoTemporalPorFecha(p.getFechaPosicion(), aux);
		if (existente != null) {
			insertarOIncrementarPosicion(existente, p.getCoordenada());
			return;
		}
		NodoTemporal nuevo = crearNodoTemporalConPosicion(p.getFechaPosicion(), p.getCoordenada());
		insertarNodoTemporalOrdenado(nuevo, p.getFechaPosicion());
		this.size++;
	}
	private NodoTemporal buscarNodoTemporalPorFecha(FechaHora fecha, NodoTemporal inicio) {
		NodoTemporal aux = inicio;
		while (aux != null) {
			int cmp = aux.getFecha().compareTo(fecha);
			if (cmp == 0) {
				return aux;
			}
			if (cmp > 0) {
				return null;
			}

			aux = aux.getSiguiente();
		}

		return null;
	}
	private NodoTemporal crearNodoTemporalConPosicion(FechaHora fecha, Coordenada coordenada) {
		NodoTemporal nuevo = new NodoTemporal();
		nuevo.setFecha(fecha);
		insertarOIncrementarPosicion(nuevo, coordenada);
		return nuevo;
	}
	private void insertarNodoTemporalOrdenado(NodoTemporal nuevo, FechaHora fecha) {
		NodoTemporal aux = lista;
		NodoTemporal ant = null;
		while (aux != null && aux.getFecha().compareTo(fecha) < 0) {
			ant = aux;
			aux = aux.getSiguiente();
		}
		nuevo.setSiguiente(aux);
		if (ant == null) {
			lista = nuevo;

			return;
		}
		ant.setSiguiente(nuevo);
	}
	private void insertarOIncrementarPosicion(NodoTemporal nodoTemporal, Coordenada coordenada) {
		NodoPosicion actual = nodoTemporal.getListaCoordenadas();
		if (actual == null) {
			nodoTemporal.setListaCoordenadas(new NodoPosicion(coordenada, 1, null));
			return;
		}
		NodoPosicion ant = null;
		while (actual != null) {
			if (actual.getCoordenada().equals(coordenada)) {
				actual.setNumPersonas(actual.getNumPersonas() + 1);
				return;
			}
			ant = actual;
			actual = actual.getSiguiente();
		}
		ant.setSiguiente(new NodoPosicion(coordenada, 1, null));
	}
	

	public int personasEnCoordenadas () {
		NodoPosicion aux = this.lista.getListaCoordenadas();
		if(aux==null)
			return 0;
		else {
			int cont;
			for(cont=0;aux!=null;) {
				cont += aux.getNumPersonas();
				aux=aux.getSiguiente();
			}
			return cont;
		}
	}
	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena+= ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + nodo.getNumPersonas();
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + 1;
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	@Override
	public String toString() {
		String cadena="";
		int cont;
		cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" +  aux.getFecha().getHora().toString() + " ";
			aux=aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}
	
	
	
}
