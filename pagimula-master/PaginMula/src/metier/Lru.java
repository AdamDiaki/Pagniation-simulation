package metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lru {

	private List<Processus> list = new ArrayList<Processus>();
	private int[][] matrice;
	private String text = ""; 
	private ArrayList<Processus> listColonne = new ArrayList<Processus>(); 
	private ArrayList<ArrayList<Processus>> listEtape = new ArrayList<ArrayList<Processus>>(); 

	public Lru() {
		// Init

		matrice = new int[8][5];
		Processus p1 = new Processus(1); 
		Processus p2 = new Processus(2); 
		Processus p3 = new Processus(3); 
		Processus p4 = new Processus(4); 
		Processus p5 = new Processus(1); 
		Processus p6 = new Processus(9); 
		Processus p7 = new Processus(7); 
		Processus p8 = new Processus(6); 
		Processus p9 = new Processus(12); 

		list.add(p1); 
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		

	}
	
	public Lru(List<Integer> liste, int nbr) {
		matrice = new int[liste.size()][nbr];
		for(Integer i : liste) {
			Processus p = new Processus(i); 
			list.add(p);
		}
		for(Processus p : list) {
			System.out.println(p.toString());
		}
	}
	

	public void run() {
		

		System.out.println("_________________");
		System.out.println("                 ");
		System.out.println("       LRU       ");
		System.out.println("_________________");
	
		System.out.println();
		System.out.println("Tables des processus : ");
					text += "Tables des processus :";

		for(Processus p : list) {
			System.out.print(p.getValue() + "; ");
		}
		System.out.println();
		System.out.println();

		for(int i=0; i<matrice.length;i++) {
			int date = 1;
			int indexTab = 0;

			for(int x = 0; x<i+1; x++) {
				for(Processus p : list) {
					System.out.println(p.toString());
				}
				int value = list.get(x).getValue();

				if(x < list.size()) {
					if(indexTab >= matrice[0].length ) {
						if(!addMatrice(matrice,i, value, x, indexTab, date, true)) indexTab--;
					} else {
						if(!addMatrice(matrice,i, value, x, indexTab, date, false)) indexTab--;
					}
					date++;
				}
				indexTab++;
			}	
						
			System.out.println("Etape " + (i+1));
			int[][] tab = turfu(); 
			
			for(Processus p : listColonne) {
				System.out.println(p.toString());
			}
			
			listEtape.add(new ArrayList<Processus>(listColonne));
			listColonne.clear();
			afficheMatrice(tab);
		}

	}
	
	public boolean addMatrice(int[][] matrice,int i,int value, int indexList, int indexTab, int date, boolean sup) {
		// depasse la taille 
		if(sup) {
			// s'il existe déjà ou non 
			if(!testExistInColonne(matrice,i,value)){	
			
				int n = getProcessusWithMinDate(); 
				int m = getIndexInColumn(matrice,i,n);
				int first = firstOccurenceWithValue(matrice[i][m]); 
	
				list.get(first).setAffiche(false);
				listColonne.remove(m);
				listColonne.add(m, list.get(indexList));
				matrice[i][m] = value;
				setDate(indexList, date, indexList);
				return true;
			} else {
				// Quand il y a doublon
				int m = getIndexInColumn(matrice,i,  value);
				int n = firstOccurence(m); 
				// System.out.println("First occurence : " + n );
				setDate(indexList, date, n);
				//list.remove(indexList);
				list.get(indexList).setDoublon(true);
				return false;
			}
		} else {
			
			if(!testExistInColonne(matrice,i,value)) {
				// list.get(indexList).setAffiche(true);
				listColonne.add(indexTab, list.get(indexList));
				
				matrice[i][indexTab] = value;
				setDate(indexList, date, indexList);	
				
				return true;
			} else {
				// Quand il y a doublon
				int m = getIndexInColumn(matrice, i, value);
				int n = firstOccurence(m); 
				// System.out.println("First occurence : " + n );
				
				setDate(indexList, date, n);
				//list.remove(indexList);
				
				list.get(indexList).setDoublon(true);
				return false;
	
				
			}
		}
	}

	
	public void setDate(int x, int date, int n) {
		if(list.get(x).getDate() < date) {
			if(getExistDate(date)) date++; 
			list.get(n).setDate(date);
			list.get(n).setAffiche(true);
			//System.out.println(list.get(n).toString());
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

	public int firstOccurence(int numProcessus) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(numProcessus).equalsValue(list.get(i))) return i;
		}
		return 0;
	}
	
	public int firstOccurenceWithValue(int value) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getValue() == value) return i; 
		}
		return 0;
	}

	public int getProcessusWithMinDate() {
		List<Processus> list2 = new ArrayList<Processus>(); 
		list2.addAll(list);
	
		Collections.sort(list2);

		int n = 0; 
		int i = -1; 
		do {
			i++;
			n = list2.get(i).getDate();
		} while(list2.get(i).isAffiche() == false || n == 0);
		
		
		System.out.println("Processus avec la plus petite date : " + list2.get(i).toString());
		return list2.get(i).getValue();
	}

	public int getIndexInColumn(int[][] tab,int x, int value) {
		for(int i=0; i<tab[0].length; i++) {
			if(tab[x][i] == value) return i;
		}
		return 0;
	}

	public boolean getExistDate(int date) {
		for(Processus p : list) {
			if(p.getDate() == date) return true;
		}
		return false;
	}
	
	public String getText() {
		return text;
	}
	
	public List<Processus> getList() {
		return list;
	}
	
	public ArrayList<ArrayList<Processus>> getListEtape() {
		return listEtape;
	}
	

}
