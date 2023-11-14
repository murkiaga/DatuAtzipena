package entitateak;

import javax.persistence.Embeddable;

@Embeddable
public class Helbidea {
	
	private String herria;
	private String cp;
	private String kale_helbidea;
	private String solairua;
	
	public Helbidea() {}
	public Helbidea(String herria, String cp, String kale_helbidea, String solairua) {
		super();
		this.herria = herria;
		this.cp = cp;
		this.kale_helbidea = kale_helbidea;
		this.solairua = solairua;
	}

	@Override
	public String toString() {
		return "Helbidea [herria=" + herria + ", cp=" + cp + ", kale_helbidea=" + kale_helbidea + ", solairua="
				+ solairua + "]";
	}
	
	
}
