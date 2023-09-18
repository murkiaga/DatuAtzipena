package Fitxategiekin_lanean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import javax.sound.sampled.Line;

public class utf8tik_kodifikazioa_aldatu {

	public static void main(String[] args) {
		
		//Kontuan izan behar da irakurketa UTF8ko fitxategi bati egingo diogula (defektuzko kodifikazioa).
		File irakurketa_fitx = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		//Idazketarako aldagaiak:
		OutputStreamWriter fosw = null;
		
		try {
			irakurketa_fitx = new File("UTF8.txt");
			fr = new FileReader(irakurketa_fitx);
			br = new BufferedReader(fr);
			
			//Idazketa kodifikazio berrian egiteko aldagaia hasieratu:
			fosw = new OutputStreamWriter(new FileOutputStream("out-ISO-8859-1.txt"), "ISO-8859-1");
			
			//Lerroz lerro joango gara idazten beste fitxategi batean kodifikazio ezberdinarekin
			String lerroa;
			while( (lerroa=br.readLine()) !=  null) {
				fosw.write(lerroa); //lerro jauziak? eskuz jarri behar ditugu: 
				fosw.write('\n');
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				br.close();
				fr.close();
				fosw.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
