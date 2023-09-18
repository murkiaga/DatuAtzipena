import java.io.File;

public class direktorioa_listatu {

	public static void main(String[] args) {
		
		File direktorioa = new File("/");
		System.out.println(direktorioa.isDirectory()); //Direktorioa al da?
		
		String[] fitxategiak = direktorioa.list();
	    
	    if (fitxategiak == null || fitxategiak.length == 0) {
	        System.out.println("Ez dago fitxategirik emandako karpetan");
	        return;
	    }
	    else {
	        for (int i=0; i< fitxategiak.length; i++) {
	            System.out.println(fitxategiak[i]);
	        }
	    }
	}

}