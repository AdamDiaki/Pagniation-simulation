package metier;

public class ProcessusLfu implements Comparable<ProcessusLfu> {

	private int value; 
	private int frequence; 
	private boolean doublon;
	private boolean affiche;
	
	public ProcessusLfu(int value) {
		this.value = value;
		frequence = 0;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the date
	 */
	public int getFrequence() {
		return frequence;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @param date the date to set
	 */
	public void setFrequence(int freq) {
		this.frequence = freq;
	}

	
	
	
	/**
	 * @return the doublon
	 */
	public boolean isDoublon() {
		return doublon;
	}

	/**
	 * @param doublon the doublon to set
	 */
	public void setDoublon(boolean doublon) {
		this.doublon = doublon;
	}
	
	
	

	/**
	 * @return the affiche
	 */
	public boolean isAffiche() {
		return affiche;
	}

	/**
	 * @param affiche the affiche to set
	 */
	public void setAffiche(boolean affiche) {
		this.affiche = affiche;
	}

	public boolean equalsValue(ProcessusLfu p) {
		if(this.value == p.getValue()) return true; 
		else return false;
	}
	
	public int compareTo(ProcessusLfu compare) {
		int compareDate =  compare.getFrequence();
		/* For Ascending order*/
		return this.frequence-compareDate;

		/* For Descending order do like this */
		//return compareage-this.studentage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Processus [value=" + value + ", frequence=" + frequence + ", doublon=" + doublon + ", affiche=" + affiche + "]";
	}

}
