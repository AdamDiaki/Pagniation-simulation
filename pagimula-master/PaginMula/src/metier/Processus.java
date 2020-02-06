package metier;

public class Processus implements Comparable<Processus> {
	private int value; 
	private int date; 
	private boolean doublon;
	private boolean affiche;
	
	public Processus(int value) {
		this.value = value;
		date = 0;
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
	public int getDate() {
		return date;
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
	public void setDate(int date) {
		this.date = date;
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

	public boolean equalsValue(Processus p) {
		if(this.value == p.getValue()) return true; 
		else return false;
	}
	
	public int compareTo(Processus compare) {
		int compareDate =  compare.getDate();
		/* For Ascending order*/
		return this.date-compareDate;

		/* For Descending order do like this */
		//return compareage-this.studentage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Processus [value=" + value + ", date=" + date + ", doublon=" + doublon + ", affiche=" + affiche + "]";
	}

	
	
	
	

	

}
