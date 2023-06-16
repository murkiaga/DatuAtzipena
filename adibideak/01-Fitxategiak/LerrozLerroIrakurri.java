import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LerrozLerroIrakurri {

	public static void main(String[] args) {
		File f =new File("fitx1.txt");
		String fitxIzena = "";
		if (f.isFile()) {
			fitxIzena = "fitx1.txt";
		} else if (args.length < 1) {
			System.out.println("Parametro gisa fitxategiaren izena eman.");
			return;
		} else {
			fitxIzena = args[0];
		}
		
		try(BufferedReader fbr = new BufferedReader(new FileReader(fitxIzena))) {
			int i = 0;
			String lerroa = fbr.readLine();
			while (lerroa != null) {
				System.out.format("[%s] %s", i++, lerroa);
				System.out.println();
				lerroa = fbr.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ez da " + fitxIzena + " fitxategia existitzen.");
		} catch (IOException e) {
			System.out.println("Sarrera/Irteera errorea: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}