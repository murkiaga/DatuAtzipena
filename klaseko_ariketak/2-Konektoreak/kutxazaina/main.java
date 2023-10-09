package kutxazain_automatikoa;

import java.util.Scanner;

public class main {

	public static int aukera = -1;
	
	public static void main(String[] args) {
		DBkudeatzailea kutxabank = new DBkudeatzailea("mysql", "localhost", "3306", "kutxabank", "root", "");
		
		kutxabank.print_taula_balioak("erabiltzaile_mugimenduak", "izena", "hodei");
		
		Scanner sc = new Scanner(System.in);
		
		//TODO 2 datu basetara konexioa ezarri (kutxabank, eginda dagoela eta bbva, falta dela).
		//Garatu DBkudeatzailea.java-n dauden funtzioak
		//Saiatu banku batetik besterako transferentzia burutzen. Nola kontrolatzen dugu rollback-a?
		do {
			print_menua();
			try {
				aukera = sc.nextInt();
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
}
