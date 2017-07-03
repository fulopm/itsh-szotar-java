package hu.itsh.gyakorlat.szotar.statistics;

public class Statistics {
	
	int helyes = 0;
	int helytelen = 0;
	int osszes = 0;
	double szazalek = 0;
	
	
	public Statistics(int helyes, int helytelen, int osszes, double szazalek) {
		this.helyes = helyes;
		this.helytelen = helytelen;
		this.osszes = osszes;
		this.szazalek = szazalek;
	}
	
	public Statistics(int helyes, int helytelen){
		this.helyes += helyes;
		this.helytelen += helytelen;
		this.osszes += helyes + helytelen;
		this.szazalek += ((double)(this.helyes)/(double)(this.osszes))*100.0;
		System.out.println();
	}
	
	public Statistics(int[] data) {
		this(data[0], data[1]);
	}
	
	public Statistics(){
		helyes = 0;
		helytelen = 0;
		osszes = 0;
		szazalek = 0;
	}


	public int getHelyes() {
		return helyes;
	}


	public void setHelyes(int helyes) {
		this.helyes = helyes;
	}


	public int getHelytelen() {
		return helytelen;
	}


	public void setHelytelen(int helytelen) {
		this.helytelen = helytelen;
	}


	public int getOsszes() {
		return osszes;
	}


	public void setOsszes(int osszes) {
		this.osszes = osszes;
	}


	public double getSzazalek() {
		return szazalek;
	}


	public void setSzazalek(int szazalek) {
		this.szazalek = szazalek;
	}
	
	public void reset() {
		this.helyes = 0;
		this.helytelen = 0;
		this.osszes = 0;
		this.szazalek = 0;
	}
	
	
	
}
