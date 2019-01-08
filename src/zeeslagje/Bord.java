package zeeslagje;

class Bord {
	
//er gaat hier iets mis met je haakjes en je boolean conditions
	
	String[][] startBord=new String[10][10];
	String[][] eigenBord=new String[10][10];
	String[][] tegenBord=new String[10][10];
	Schip vldsch;
	Schip slsch1;
	Schip slsch2;
	Schip ondz1;
	Schip ondz2;
	Schip ondz3;
	Schip psch1;
	Schip psch2;
	Schip psch3;
	Schip psch4;
	int gezonkenSchepen;
	
	Schip[] schepen = {vldsch = new Schip(startBord,"vliegdekschip",6), slsch1 = new Schip(startBord,"slagschip",4),slsch2 = new Schip(startBord,"slagschip",4),ondz1 = new Schip(startBord,"onderzeeër",3),ondz2 = new Schip(startBord,"onderzeeër",3),ondz3 = new Schip(startBord,"onderzeeër",3),psch1 = new Schip(startBord,"patrouilleschip",2),psch2 = new Schip(startBord,"patrouilleschip",2),psch3 = new Schip(startBord,"patrouilleschip",2),psch4 = new Schip(startBord,"patrouilleschip",2)};	
	
	Bord() {
		String l="A";
		String n="1";
		for(int r=0;r<10;r++) {
			char b = 65;
			for(int c=0;c<10;c++,b++) {
				n=Integer.toString(r+1);
				l=Character.toString(b);
				startBord[r][c]=(l+n);
				eigenBord[r][c]=(l+n);
				tegenBord[r][c]=(l+n);
			}
		}
	}
	
	void toonEigenBord() {
		for(int r=0;r<eigenBord.length; r++) {
			System.out.println("");
			for(int c=0;c<eigenBord[r].length;c++) {
				System.out.print(eigenBord[r][c]+" ");
				if(r<9) {
					System.out.print(" ");
				}
				 else if(r==9) {
					if((eigenBord[9][c].equals(". ")) || (eigenBord[9][c].equals("S "))) {
						System.out.print(" ");
					}
				}
			}
		}
	}
	
	void toonTegenBord() {
		for(int r=0;r<tegenBord.length; r++) {
			System.out.println("");
			for(int c=0;c<tegenBord[r].length;c++) {
				System.out.print(tegenBord[r][c]+"  ");
				if(r<9) {
					System.out.print(" ");
				} else if(r==9) {
					if((tegenBord[9][c].equals("* ")) || (tegenBord[9][c].equals("X "))) {
						System.out.print(" ");
					}
				}
			}
		}
	}
	
	void toonStartBord() {
		for(int r=0;r<startBord.length; r++) {
			System.out.println("");
			for(int c=0;c<startBord[r].length;c++) {
				System.out.print(startBord[r][c]+"  ");
				if(r<9) {
					System.out.print(" ");
				}
			}
		}
	}
	

	boolean hitOrMiss(String put) {	//controleert eigenBord tegen ingevoerde coördinaten tijdens beurt van tegenspeler
		for(int r=0;r<startBord.length; r++) {
			for(int c=0;c<startBord[r].length;c++) {
				if((startBord[r][c].equalsIgnoreCase(put)) && (eigenBord[r][c].equals("S "))) {
					eigenBord[r][c]="X ";
					//eigenlijk moet je hier al de methode oproepen die de coördinaten van het geraakte schip in een "X " verandert
					return true;
				}
			}
		}
		return false;
	}
	
	
	void hit(String input) {	//past eigenBord aan tijdens beurt tegenspeler
		for(int i=0;i<schepen.length;i++) {
//			System.out.println("hit method, 1e for loop");	//test
			for(int a=0;a<schepen[i].coördinaten.length;a++) {
//				System.out.println("hit method, 2e for loop");	//test
				if(schepen[i].coördinaten[a].equalsIgnoreCase(input)) {
					schepen[i].coördinaten[a]="X ";
					if(schepen[i].gezonken(input)) {
						gezonkenSchepen++;
					}
				}
			}
		}
//		if(gezonkenSchepen==10) {
//			System.out.print("Al mijn schepen liggen nu op de zeebodem.\n");
//		}
	}
	
	boolean checkTegenBord(String s) {
		for(int r=0;r<startBord.length; r++) {
			for(int c=0;c<startBord[r].length;c++) {
				if((startBord[r][c].equalsIgnoreCase(s)) && (tegenBord[r][c].equals("S "))) {
					return true;
				}
			}
		}
		return false;
	}
	
	boolean checkReserved(String[] co) {
		int check=0;
		for(int i=0;i<co.length;i++) {
			for(int r=0;r<eigenBord.length;r++) {
				for(int c=0;c<eigenBord[r].length;c++) {
					if((co[i].equalsIgnoreCase(eigenBord[r][c]))) {
						check++;
					}
				}
			}			
		}
		if(check==co.length) {
			return true;
		}
		return false;
	}
	
	void wijzigTegenBord(boolean b, String in) {
		for(int r=0;r<tegenBord.length; r++) {
			for(int c=0;c<tegenBord[r].length;c++) {
				if((tegenBord[r][c].equalsIgnoreCase(in)) && (b==true)) {
					tegenBord[r][c]="X ";
				} else if((tegenBord[r][c].equalsIgnoreCase(in)) && (b==false)) {
					tegenBord[r][c]="* ";
				}
			}
		}
	}
	
	
	
	void reserveerEigenBord(String[] co) {
		for(int i=0;i<co.length;i++) {
			for(int r=0;r<eigenBord.length;r++) {
				for(int c=0;c<eigenBord[r].length;c++) {
					if((co[i].equalsIgnoreCase(eigenBord[r][c]))) {
						eigenBord[r][c]="S ";
					}
				}
			}			
		}
		for(int r=0;r<eigenBord.length;r++) {
			for(int c=0;c<eigenBord[r].length;c++) {
				if(eigenBord[r][c].equals("S ")) {
					if((r>0) && !(eigenBord[r-1][c].equals("S "))) {
						eigenBord[r-1][c]=". ";
					}
					if((r<9) && (!(eigenBord[r+1][c].equals("S ")))) {
						eigenBord[r+1][c]=". ";
					}
					if((c>0)&&(!(eigenBord[r][c-1].equals("S ")))) {
						eigenBord[r][c-1]=". ";
					}
					if((c<9) && (!(eigenBord[r][c+1].equals("S ")))) {
						eigenBord[r][c+1]=". ";
					}
				}
			}
		}					
	}
}
