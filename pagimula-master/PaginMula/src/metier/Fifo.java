package metier;
import java.util.ArrayList;

import java.util.List;


public class Fifo {

	int[][] matrice;

	private List<Integer> list = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> listEtape = new ArrayList<ArrayList<Integer>>(); 
	private ArrayList<Integer> listColonne = new ArrayList<Integer>(); 
	
	public Fifo() {
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(4);
		list.add(7);
		list.add(8);
		list.add(6);

		System.out.println("_________________");
		System.out.println("                 ");
		System.out.println("      FIFO       ");
		System.out.println("_________________");
		System.out.println();
		System.out.println("Tables des processus : ");
		for(int i : list) {
			System.out.print(i + "; ");
		}
		System.out.println();
		System.out.println();
		
	
		
	}
	
	public Fifo(List<Integer> liste, int tab) {
		matrice = new int[liste.size()][tab];
		System.out.println("Taille list : " + liste.size());
		this.list = liste;
			
	}
	
	public void run() {
		for(int i=0; i<matrice.length;i++) {
			int m = 0; 
			
			for(int x = 0; x<i+1; x++) {
				if(x >= matrice[0].length ) {
					if(x < list.size()) {	
						if(!testExistInColonne(matrice,i,list.get(x))){		
							System.out.println("M: "+ m + " Element x["+x+"] : "+list.get(x));
							matrice[i][m] = list.get(x);
							listColonne.remove(m);
							listColonne.add(m, list.get(x));
							m++;
							if(m == matrice[0].length) m=0;
						} else {
							list.remove(x);
							
						}
					}
				} else {
					if(!testExistInColonne(matrice,i,list.get(x))) {
						matrice[i][x] = list.get(x);
						listColonne.add(x, list.get(x));
					} else {
						list.remove(x);
						
					}
				}
				
			}
			listEtape.add(new ArrayList<Integer>(listColonne));
			listColonne.clear();
			System.out.println("Etape " + (i+1));
			int[][] tab = turfu(); 
			afficheMatrice(tab);
		}
	}
	
	public void afficheMatrice(int[][] matrice) {
		for(int i=0; i<matrice.length; i++) {
			for(int j=0; j<matrice[i].length; j++) {
				if(matrice[i][j] == 0) System.out.print("    ");
				else if(matrice[i][j] > 9) System.out.print(matrice[i][j] + "|");	
				else System.out.print(matrice[i][j] + " | ");	
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	// Sens turfu oublie moi tu croyais koi ? 
	public int[][] turfu() {
		int[][] tab = new int[matrice[0].length][matrice.length]; 
		int n =0; 
		for(int i=0; i<tab.length; i++) {
			for(int j=0; j< tab[0].length; j++) {
				tab[i][j] = matrice[j][n]; 
			}
			n++;
		}
		return tab; 
	}
	
	
	
	// Test si dans la colonne il y a deja un element qui a la même valeur 
	// Dans notre cas ne pas aloue 2 fois de la memoire au meme processus
	public boolean testExistInColonne(int[][] tab, int x, int valeur) {
		for(int i=0; i<tab[0].length; i++) {
			if(tab[x][i] == valeur) return true;
		}
		return false;
	}
	
	public ArrayList<ArrayList<Integer>> getListEtape() {
		return listEtape;
	}
	
}
