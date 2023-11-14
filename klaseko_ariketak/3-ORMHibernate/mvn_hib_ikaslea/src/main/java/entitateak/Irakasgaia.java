package entitateak;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="irakasgaia")
public class Irakasgaia {
	@Id
	@Column(name="irakasgai_kodea", length=8)
	private String irakasgai_kodea;
	
	@Column(name="izena")
	private String izena;
	
	@ManyToMany(mappedBy = "irakasgaiak")
	private List<Ikaslea> ikasleak = new ArrayList<>();

	
	public Irakasgaia() {} 
	
	public Irakasgaia(String kodea, String izena) {
		this.irakasgai_kodea = kodea;
		this.izena = izena;
	}
	
	public String getIrakasgai_kodea() {
		return irakasgai_kodea;
	}

	public void setIrakasgai_kodea(String irakasgai_kodea) {
		this.irakasgai_kodea = irakasgai_kodea;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public List<Ikaslea> getIkasleak() {
		return ikasleak;
	}

	public void setIkasleak(List<Ikaslea> ikasleak) {
		this.ikasleak = ikasleak;
	}

	@Override
	public String toString() {
		return "Irakasgaia [irakasgai_kodea=" + irakasgai_kodea + ", izena=" + izena + ", ikasleak=" + ikasleak + "]";
	}
	
}
