package metier;

import java.util.ArrayList;
import java.util.List;

public class testLru {

	public testLru() {
	
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2); 
		list.add(3); 
		list.add(4); 
		list.add(3); 
		list.add(5); 
		list.add(3); 
		Lru l = new Lru(list, 2); 
		l.run();
		
	}

}
