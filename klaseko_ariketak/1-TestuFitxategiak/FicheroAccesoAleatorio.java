// Tamaina finkoko luzeradun erregistroak fitxategietan
import java.io.File;
import java.io. RandomAccessFile;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

public class FicheroAccesoAleatorio {

	private File f;
	private List<Pair<String, Integer>> campos;
	private long longReg;
	private long numReg= 0;

	FicheroAccesoAleatorio (String nomFich, List<Pair<String, Integer>> campos) throws IOException {
		this.campos = campos;
		this.f= new File(nomFich);
		longReg = 0;

		for (Pair<String, Integer> campo: campos) {
			this.longReg += campo.getValue();
		}
		if(f.exists()) {
			this.numReg = f.length()/this.longReg;
		}
	}

	public long getNumReg() {
		return numReg;
	}

	public void txertatu(Map<String, String> reg) throws IOException {
		txertatu(reg, this.numReg++);
	}



	public void txertatu (Map<String, String> reg, long pos) throws IOException
	{
		try(RandomAccessFile faa = new RandomAccessFile(f, "rws")){
			faa.seek(pos * this.longReg);
			for (Pair<String, Integer> campo: this.campos) {
				String nomCampo=campo.getKey();
				Integer longCampo = campo.getValue();
				String valorCampo = reg.get(nomCampo);
				if (valorCampo == null) {
					valorCampo = "";
				}
				String valorCampoForm = String.format("%1$-" + longCampo + "s",  valorCampo);
				faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
			}
		}
	}
	
	public String get_izena(Map<String, String> reg, long pos) throws IOException
	{
		//TODO ikasleek. Posizio batetako erregistro baten izena bueltatzea
		return "";
	}
	
	public static void main(String[] args) {
		List campos = new ArrayList();
		campos.add(new Pair("DNI", 9));
		campos.add(new Pair("IZENA", 32));
		campos.add(new Pair("CP", 5));


		try {
				FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio("fic_acceso_alest.dat", campos);
				Map reg = new HashMap();
				
				reg.put("DNI", "567050120");
				reg.put("IZENA", "SAMPER");
				reg.put("CP", "22810");
				faa.txertatu(reg);
				System.out.println("Txertatuta " + reg.toString());
				reg.clear();
				
				reg.put("DNI", "789475148");
				reg.put("IZENA", "JOHN BRAUN");
				reg.put("CP", "48280");
				faa.txertatu(reg);
				System.out.println("Txertatuta " + reg.toString());
				reg.clear();
				
				reg.put("DNI", "475896854");
				reg.put("IZENA", "PAULINO");
				reg.put("CP", "48280");
				faa.txertatu(reg);
				System.out.println("Txertatuta " + reg.toString());
				reg.clear();

		}catch (IOException e) {
				System.err.println("Error de B/S:" + e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}