package br.com.starcode.comparator;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorExample {

	public static void main(String[] args) {
		
		Cliente[] clientes = {
				new Cliente("Bruno", 30),
				new Cliente("Maria", 28),
				new Cliente("Carlos", 40)
		};
		
		//ordenar por nome
		
		/* Java 8: Arrays.sort(clientes, (Cliente b1, Cliente b2) -> b1.getNome().compareTo(b2.getNome())); */
		Arrays.sort(clientes, new Comparator<Cliente>() {
			@Override
			public int compare(Cliente b1, Cliente b2) {
				//TODO testar nulos
				return b1.getNome().compareTo(b2.getNome()); //delegar para comparador da String
			}
		});
		imprime(clientes);
		
		//ordenar por idade
		/* Java 8: 
		Arrays.sort(clientes, (Cliente b1, Cliente b2) -> {
			if (b1.getIdade() > b1.getIdade()) return 1;
			if (b1.getIdade() < b2.getIdade()) return -1;
			return 0;
		});  */
		Arrays.sort(clientes, new Comparator<Cliente>() {
			@Override
			public int compare(Cliente b1, Cliente b2) {
				//TODO testar nulos
				if (b1.getIdade() > b1.getIdade()) return 1;
				if (b1.getIdade() < b2.getIdade()) return -1;
				return 0;
			}
		});
		imprime(clientes);
			
		}
		
		private static void imprime(Cliente[] clientes) {
			for (Cliente cliente : clientes) {
				System.out.print(cliente.getNome());
				System.out.print("(" + cliente.getIdade() + "),");
			}
			System.out.println();
		}
	
}
