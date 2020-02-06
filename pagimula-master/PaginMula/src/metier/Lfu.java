package metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lfu {

	private List<ProcessusLfu> list = new ArrayList<ProcessusLfu>();
	private int[][] matrice;
	private ArrayList<ArrayList<ProcessusLfu>> listEtape = new ArrayList<ArrayList<ProcessusLfu>>(); 
	private ArrayList<ProcessusLfu> listColonne = new ArrayList<ProcessusLfu>(); 

	public Lfu() {
		// Init

		matrice = new int[9][5];
		ProcessusLfu p1 = new ProcessusLfu(1); 
		ProcessusLfu p2 = new ProcessusLfu(2); 
		ProcessusLfu p3 = new ProcessusLfu(3); 
		ProcessusLfu p4 = new ProcessusLfu(4); 
		ProcessusLfu p5 = new ProcessusLfu(1); 
		ProcessusLfu p6 = new ProcessusLfu(9); 
		ProcessusLfu p7 = new ProcessusLfu(7); 
		ProcessusLfu p8 = new ProcessusLfu(6); 
		ProcessusLfu p9 = new ProcessusLfu(12); 

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
	
	public Lfu(List<Integer> liste, int nbr) {
		matrice = new int[liste.size()][nbr];
		for(Integer i : liste) {
			ProcessusLfu p = new ProcessusLfu(i); 
			list.add(p);
		}
		for(ProcessusLfu p : list) {
			System.out.println(p.toString());
		}
	}

	public void run() {

		System.out.println("_________________");
		System.out.println("                 ");
		System.out.println("       LFU       ");
		System.out.println("_________________");
		System.out.println();
		System.out.println("Tables des processus : ");
		for(ProcessusLfu p : list) {
			System.out.print(p.getValue() + "; ");
		}
		System.out.println();
		System.out.println();

		for(int i=0; i<matrice.length;i++) {
			int indexTab = 0;
			for(int x = 0; x<i+1; x++) {
				int value = list.get(x).getValue();
				if(x < list.size()) {
					// System.out.println("x : " + x + "; indexTab = " + indexTab);
					if(indexTab >= matrice[0].length ) {
						if(!addMatrice(matrice, i, value, x, indexTab, true)) indexTab--;
					} else {
						if(!addMatrice(matrice, i, value, x, indexTab,false)) indexTab--;
					}
				}
				indexTab++;
			}
			
			for(ProcessusLfu p : list) {
				p.setFrequence(0);
			}
			System.out.println("Etape " + (i+1) + " :");
			int[][] tab = turfu(); 
			listEtape.add(new ArrayList<ProcessusLfu>(listColonne));
			listColonne.clear();
			afficheMatrice(tab);
		}

	}
	
	public boolean addMatrice(int[][] matrice,int i,int value, int indexList, int indexTab, boolean sup) {
		// depasse la taille 
		if(sup) {
			// s'il existe déjà ou non 
			if(!testExistInColonne(matrice,i,value)){	
			
				int n = getProcessusWithMinFrequence(); 
				int m = getIndexInColumn(matrice,i,n);
				int first = firstOccurenceWithValue(matrice[i][m]); 
				list.get(first).setAffiche(false);
				list.get(indexList).setAffiche(true);
				listColonne.remove(m);
				listColonne.add(m, list.get(indexList)); 
				matrice[i][m] = value;
				setFrequence(indexList);
				return true;
			} else {
				// Quand il y a doublon
				int m = getIndexInColumn(matrice,i,  value);
				int n = firstOccurence(m); 
				// System.out.println("First occurence : " + n );
				setFrequence(n);
				list.get(indexList).setDoublon(true);
				return false;
			}
		} else {
			
			if(!testExistInColonne(matrice,i,value)) {
				list.get(indexList).setAffiche(true);
				listColonne.add(indexTab, list.get(indexList));
				
				matrice[i][indexTab] = value;
				setFrequence(indexList);		
				return true;
			} else {
				// Quand il y a doublon
				int m = getIndexInColumn(matrice, i, value);
				int n = firstOccurence(m); 
				// System.out.println("First occurence : " + n );
				
				setFrequence(n);
				list.get(indexList).setDoublon(true);
				return false;
	
				
			}
		}
	}
	
	public void setFrequence(int x) {
		int n = list.get(x).getFrequence();
		list.get(x).setFrequence(n+1);
		// System.out.println(list.get(x).toString());
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

	public int getProcessusWithMinFrequence() {
		List<ProcessusLfu> list2 = new ArrayList<ProcessusLfu>(); 
		list2.addAll(list);
	
		Collections.sort(list2);
		
		// for(ProcessusLfu p : list2) System.out.println(p.toString());

		int n = 0; 
		int i = -1; 
		do {
			i++;
			n = list2.get(i).getFrequence();
		} while(list2.get(i).isAffiche() == false || n == 0);
		
		
		System.out.println("Processus avec la plus petite frequence : " + list2.get(i).toString());
		return list2.get(i).getValue();
	}

	public int getIndexInColumn(int[][] tab,int x, int value) {
		for(int i=0; i<tab[0].length; i++) {
			if(tab[x][i] == value) {
				return i;
			}
		}
		return 0;
	}

	public boolean getExistFrequence(int frequence) {
		for(ProcessusLfu p : list) {
			if(p.getFrequence() == frequence) return true;
		}
		return false;
	}
	
	public List<ProcessusLfu> getList() {
		return list;
	}
	
	public ArrayList<ArrayList<ProcessusLfu>> getListEtape() {
		return listEtape;
	}
}
