package zeeslagje;

class Schip {
	
	String[][] startBord = new String[10][10];
	String[] coördinaten;
	int aantalVakjes;
	String naam;
	
	Schip(String[][] startbord, String naam, int aantalVakjes) {
		this.startBord=startbord;
		this.aantalVakjes=aantalVakjes;
		this.naam=naam;
		coördinaten=new String[aantalVakjes];
	}
	
	boolean controleerPlaatsing(String[] co) {
		int[] row=new int[aantalVakjes];
		int[] column=new int[aantalVakjes];
		int i=0;
		for(int r=0;r<startBord.length;r++) {
			for(int c=0;c<startBord[r].length;c++) {
				for(int p=0;p<aantalVakjes;p++) {
					if((startBord[r][c].equalsIgnoreCase(co[p]))) {
						row[i]=r;
						column[i]=c;
						i++;
					}
				}	
			}
		}
		if(((row[0]==row[1])&&(column[aantalVakjes-1]-column[0]+1==aantalVakjes))||((column[0]==column[1])&&(row[aantalVakjes-1]-row[0]+1==aantalVakjes))) {
			return true;
		}
		return false;
	}
	
	
	boolean gezonken(String input) {
		int counter=0;
		for(String coör : coördinaten) {
			if(coör.equals("X ")) {
				counter++;
			}		
		}
		System.out.println("counter: "+counter+" aantalVakjes: "+aantalVakjes);
		return(counter==aantalVakjes);
	}
}
