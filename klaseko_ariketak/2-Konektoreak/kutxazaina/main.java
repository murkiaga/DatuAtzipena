package kutxazain_automatikoa;

import java.util.Scanner;

public class main {

	private static int aukera = -1;
	private static DBkudeatzailea kutxabank = null;
	private static DBkudeatzailea bbva = null;
	
	public static void main(String[] args) {
		kutxabank = new DBkudeatzailea("mysql", "localhost", "3306", "kutxabank", "root", "");
		bbva = new DBkudeatzailea("mysql", "localhost", "3306", "bbva", "root", "");
		//kutxabank.print_taula_balioak("erabiltzaile_mugimenduak", "izena", "hodei");
		
		Scanner sc = new Scanner(System.in);
		
		//TODO 2 datu basetara konexioa ezarri (kutxabank, eginda dagoela eta bbva, falta dela).
		//Garatu DBkudeatzailea.java-n dauden funtzioak
		//Saiatu banku batetik besterako transferentzia burutzen. Nola kontrolatzen dugu rollback-a?
		do {
			print_menua();
			try {
				aukera = sc.nextInt();
				switch (aukera) {
					case 0:
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						transferentzia_inter_banko("hodei", 5);
						break;
					default:
						System.out.println("Aukera ez egokia");
				}
			} catch (Exception e) {
				sc.nextLine();
				aukera = -1;
			}
		} while(aukera != 0);
		
		System.out.println("Agur!");
	}
	
	public static void print_menua() {
		System.out.println("Zer nahi duzu egin?");
		System.out.println("1-Nire saldoa ikusi");
		System.out.println("2-Nire mugimenduak kontsultatu");
		System.out.println("3-Dirua sartu");
		System.out.println("4-Dirua atera");
		System.out.println("5-Transferentzia bat burutu beste bati");
		System.out.println("6-Transferentzia bat egin beste banku batera");
		System.out.println("0-Irten");
	}
	
	public static void transferentzia_inter_banko(String erabiltzailea, int kantitatea) {
		//Kutxabank datu basetik bbva datu basera transferentzia bat egiten du.
		try {
			kutxabank.setAutocommita(false);
			bbva.setAutocommita(false);
			kutxabank.set_erabiltzaileari_mugimendu_berria(erabiltzailea, -kantitatea);
			bbva.set_erabiltzaileari_mugimendu_berria(erabiltzailea, kantitatea);
			kutxabank.egin_commit();
			bbva.egin_commit();
			System.out.println("Banku arteko transferentzia behar bezala burutu da");
		} catch (Exception e) {
			try {
				kutxabank.egin_rollback();
				bbva.egin_rollback();
				System.out.println("ROLLBACK egin da");
			} catch (Exception ex) {
				System.err.println("Errorea. Ezin izan da commit eta rollback egin.");
				System.err.println(ex.getLocalizedMessage());
			}
		}	
	}
}
