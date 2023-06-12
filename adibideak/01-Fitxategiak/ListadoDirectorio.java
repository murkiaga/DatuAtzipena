import java.io.File;

public class ListadoDirectorio {
	public static void main(String[] args) {
		String ruta=".";
		
		if(args.length>=1)
			ruta=args[0]; //Baldin "ruta" parametroa pasatu bada deian
		
		File fich=new File(ruta);
		
		if (!fich.exists()) {
			System.out.println("Ez da fitxategia edo direktorioa existitzen ("+ruta+").");
		} else {
			if (fich.isFile()) {
				System.out.println(ruta+" fitxategi bat da");
			} else {
				System.out.println(ruta+" direktorio bat da. Bere edukia:");
				File[] fitxategiak=fich.listFiles(); //adi, fitxategi eta direktorioak
				for (File f: fitxategiak) {
					String textoDescr = "";
					if (f.isDirectory()) 
						textoDescr = "/";
					else if (f.isFile())
						textoDescr = "_";
					//String textoDescr=f.isDirectory() ? "/":
					//f.isFile() ? "_": "?";
					System.out.println("("+textoDescr+") "+f.getName());
				}
			}
		}
	}
}