package zeeslagje;

import java.util.Scanner;

public class Zeeslagje {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Speler 1, wat is je naam? ");
		String naam1=scanner.next();
		System.out.print("Speler 2, wat is je naam? ");
		String naam2=scanner.next();
		
		Speler speler1 = new Speler(naam1);
		Speler speler2 = new Speler(naam2);
		
		speler1.plaatsSchepen(scanner, speler1.bord.schepen,speler2.naam);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		speler2.plaatsSchepen(scanner, speler2.bord.schepen,speler1.naam);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		
//		for(int b=0;0<speler1.bord.schepen.length;b++) {
//			for (String z : speler1.bord.schepen[b].coördinaten) {
//				System.out.println(z);
//			}
//		}
		
		while((speler1.bord.gezonkenSchepen<10) && (speler2.bord.gezonkenSchepen<10)) {
			speler1.gooiBom(speler2.bord, scanner);
			speler2.gooiBom(speler1.bord, scanner);
			
		}
		if(speler1.bord.gezonkenSchepen==10) {
			System.out.println("Gefeliciteerd, "+speler2.naam+", je hebt "+speler1.naam+" verslagen!");
		} else if(speler2.bord.gezonkenSchepen==10) {
			System.out.println("Gefeliciteerd, "+speler1.naam+", je hebt "+speler2.naam+" verslagen!");
		}
		scanner.close();
	}
}
