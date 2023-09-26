
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class fitxategia_alderantziz_bistaratu {

	public static void main(String[] args) {
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<String> lista = new ArrayList<String>();
		
		try {
			f = new File("kaixo.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String lerroa;
			
			//Lerroak listan kargatu
			while((lerroa = br.readLine()) != null) {
				lista.add(lerroa);
			}
			for(int i=lista.size()-1; i>=0; i--) { //Lerroak atzetik aurrera irakurri
				for(int j=lista.get(i).length()-1; j>=0; j--) { //Lerroko karaktereak atzetik aurrera irakurri
					System.out.print(lista.get(i).charAt(j));
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
	}
}