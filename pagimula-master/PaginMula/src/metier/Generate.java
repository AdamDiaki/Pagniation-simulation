package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generate {
	private int min = 1;
	private int max; 
	private int nbrProcessus; 
	private List<Integer> listRandom = new ArrayList<Integer>(); 
	private Random random; 
	
	public Generate(int nbrProcessus, int max) {
		this.max = max; 
		this.nbrProcessus = nbrProcessus; 
		random = new Random();
	}
	
	public void genere() {
		for(int i=0; i<nbrProcessus; i++) {
			Integer randomNumber = random.nextInt(max + 1 - min) + min;
			listRandom.add(randomNumber); 
		}
	}
	
	public List<Integer> getList() {
		return listRandom;
	}

}
