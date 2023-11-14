package entitateak;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefonoa {
	
	private String mota;

	@Column(name = "zenbakia")
	private String zenbakia;

	public Telefonoa() {}
	public Telefonoa(String mota, String zenbakia) {
		super();
		this.mota = mota;
		this.zenbakia = zenbakia;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getZenbakia() {
		return zenbakia;
	}

	public void setZenbakia(String zenbakia) {
		this.zenbakia = zenbakia;
	}
	@Override
	public String toString() {
		return "Telefonoa [mota=" + mota + ", zenbakia=" + zenbakia + "]";
	}
	
	
	
}
