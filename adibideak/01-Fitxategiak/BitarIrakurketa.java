import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BitarIrakurketa {

	static int TAM_FILA=32;
	static int MAX_BYTES=2048;
	InputStream is=null;

	public BitarIrakurketa(InputStream is) {
		this.is=is;
	}
	
	public void volcar() throws IOException {
		byte buffer[]=new byte[TAM_FILA];
		int irakurritakoByteak;
		int offset=0;
		do {
			irakurritakoByteak=is.read(buffer);
			System.out.format("[%5d]", offset);
			for (int i=0; i<irakurritakoByteak; i++) {
				System.out.format(" %2x", buffer[i]);
			}
			offset+=irakurritakoByteak;
			System.out.println();
		} while (irakurritakoByteak == TAM_FILA && offset < MAX_BYTES);
	}
	
	public static void main(String[] args) throws IOException {
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
		
		try (FileInputStream fis = new FileInputStream(fitxIzena)) {
				BitarIrakurketa vb = new BitarIrakurketa(fis);
				vb.volcar();
		} catch (FileNotFoundException e) {
			System.err.println("Errorea fitxategia ez da existitzen " + fitxIzena);
		} catch (IOException e) {
			System.err.println("Sarrera/Irteera errorea " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}