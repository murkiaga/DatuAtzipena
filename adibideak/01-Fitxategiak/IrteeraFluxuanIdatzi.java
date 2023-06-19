import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class IrteeraFluxuanIdatzi {

	public static void main(String[] args) {
		String fitxIzena= "fluxu_irteera.txt";
		File f = new File (fitxIzena);
		if(f.exists()) {
			System.out.println(fitxIzena + " fitxategia existitzen da.");
			return;
		}
		
		try {
			BufferedWriter bfw = new BufferedWriter(new FileWriter(f));
			bfw.write("Hau testu fitxategi bat da.");
			bfw.newLine();
			bfw.write("agian ez dago guztiz zuzen.");
			bfw.newLine();
			bfw.close();
			
			bfw = new BufferedWriter(new FileWriter(f, true));
			bfw.write("baina konpondu daiteke.");
			bfw.newLine();
			bfw.close();
 		} catch(IOException e) {
 			System.out.println(e.getMessage());
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
	}
}