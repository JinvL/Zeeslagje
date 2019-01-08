package zeeslagje;

import java.util.Scanner;

class Speler {	
	Bord bord;
	String naam;
	
	Speler(String naam) {
		this.naam=naam;
		bord = new Bord();
	}
	
	void plaatsSchepen(Scanner scanner, Schip[] schepen, String n) {
		System.out.println("\n"+naam+", je dient de volgende schepen op je bord te plaatsen:\n- 1 vliegdekschip (6 vakjes)\n- 2 slagschepen (4 vakjes per stuk)\n- 3 onderzeeërs (3 vakjes per stuk)\n- 4 patrouilleschepen (2 vakjes per stuk)");
		System.out.println(n+", even wegkijken, aub ;)");
		for(Schip s:schepen) {
			plaatsSchip(scanner,s);
		}
	}
	
	void plaatsSchip(Scanner scanner, Schip schip) {
		String input;
		String[] coör=new String[schip.aantalVakjes];
		int bools;
		do {
			bools=0;
			System.out.println("\n"+naam+", waar wil je je "+schip.naam+ " ("+schip.aantalVakjes+" vakjes) plaatsen? \nKies uit de beschikbare coördinaten hieronder:");
			bord.toonEigenBord();
			System.out.println("");
			for(int i=1;i<=schip.aantalVakjes;i++) {
				System.out.print("Geef je "+i+"e coördinaat op, van links naar rechts of van boven naar onder: ");
				input=scanner.next();
//				String l=String.valueOf(input.charAt(0));
//				l=l.toUpperCase();
//				input=l+String.valueOf(input.charAt(1));
//ik wilde toUpperCase() voor de zekerheid, maar ben er nog niet achter hoe ik dan het cijfer uit de String moet vangen, aangezien die soms uit 1 en soms uit 2 digits bestaat.
				coör[i-1]=input;			
			}
			if (bord.checkReserved(coör)==false) {
				System.out.println("\nDe opgegeven coördinaten kloppen niet.\nJe kan een schip niet plaatsen waar al een ander schip ligt, of vlak ernaast, eronder of erboven.\nDiagonaal mag wel. Kies uit de coördinaten die nog vrij zijn.");
				bools++;
			} else if (schip.controleerPlaatsing(coör)==false) {
				System.out.println("\nDe opgegeven coördinaten kloppen niet. \nControleer of je voldoende coördinaten hebt opgegeven voor de grootte van je schip, en in 1 kolom of 1 rij ligt.\n");
				bools++;
			}
		} while (bools!=0);
		bord.reserveerEigenBord(coör);
		for (int g=0;g<coör.length;g++) {
			schip.coördinaten[g]=coör[g];
		}
	}
		
	void gooiBom(Bord enemyBord, Scanner scanner) {
		System.out.print("\n\n"+naam+", geef aan waar op het bord van de tegenstander je een bom wil gooien: ");
		bord.toonTegenBord();
		System.out.println("");
		String invoer = scanner.next();
//hier eventueel nog checken of deze coördinaten nog niet eerder gegeven zijn: dus staat er op deze coördinaat al en "*" of een "X " op het tegenBord?		
		boolean b = enemyBord.hitOrMiss(invoer);	
		if (b) {
			System.out.println("Je hebt de vijand geraakt! Bij je volgende beurt zie je een X waar de bom is ingeslagen.");
			bord.wijzigTegenBord(true, invoer);
			enemyBord.hit(invoer);
//anders vanuit hier de boolean gezonken( ) in Schip aanroepen? Nee, dat kan niet, want ik weet niet welk schip ik moet controleren.
//aan de andere kant, dat weet Bord natuurlijk ook niet. Dus ik kan door dezelfde loop gaan in deze class.
//misschien dat het handiger is dat Bord meteen bij de plaatsing van de schepen bijhoudt welk schip op welke coördinaten staat?
//Dan kan je gewoon bij Bord checken welk schip er gezonken is
		} else if (b==false){
			System.out.println("Je hebt geen schip geraakt. Bij je volgende beurt zie je een * waar de bom in het water is gevallen.");
			bord.wijzigTegenBord(false, invoer);
		}
	}
}
