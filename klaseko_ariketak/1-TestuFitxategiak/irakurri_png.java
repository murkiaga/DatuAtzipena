import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class irakurri_png {

	public static void main(String[] args) throws IOException {
		FileInputStream fim = null;
		BufferedInputStream bis = null;
		
		byte[] pngSignature = {(byte) 137, 80, 78, 71, 13, 10, 26, 10}; //cabecera de PNG en decimal
        byte[] ihdrSignature = {73, 72, 68, 82};
		
		try {
			fim = new FileInputStream("Selection_009.png");
			bis = new BufferedInputStream(fim);
			
			byte[] byteak = new byte[8];
			
			bis.read(byteak);
 
			for (int i=0; i < byteak.length; i++) {
				System.out.print(Byte.toUnsignedInt(byteak[i]) + " "); //MUA OJO
				//MUA System.out.print(byteak[i] + " ");
			}
			
		
			System.out.println();
			if (!Arrays.equals(byteak, pngSignature)) {
            	System.out.println("No es un fichero PNG");
                System.exit(-1);
            } else {
            	System.out.println("Es un fichero PNG");
            }
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				bis.close();
				fim.close();
			} catch (Exception e) {
				
			}
		}
	}
}