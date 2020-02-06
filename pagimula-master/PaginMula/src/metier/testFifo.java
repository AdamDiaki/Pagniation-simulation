package metier;

import java.util.ArrayList;
import java.util.List;

public class testFifo {

	public testFifo() {
	
	}

	public static void main(String[] args) {
	
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2); 
		list.add(3); 
		list.add(4); 
		list.add(3); 
		list.add(5); 
		list.add(3); 
		Fifo f = new Fifo(list, 2); 
		f.run();
		
	}

}
