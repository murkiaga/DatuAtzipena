package entitateak;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="ikaslea")
public class Ikaslea {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="izena")
	private String izena;
	
	@Column(name="abizena")
	private String abizena;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "nan_id")
	private NortasunAgiria nan;
	
	@OneToMany(mappedBy = "ikaslea", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Nota> notak = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Irakasgaia> irakasgaiak = new ArrayList<>();
	
	@Embedded
	private Helbidea helbidea;
	
	@ElementCollection
	private List<Telefonoa> telefonoak = new ArrayList<>();

	public Ikaslea() {}
	public Ikaslea(String izena, String abizena) {
		super();
		this.izena = izena;
		this.abizena = abizena;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public NortasunAgiria getNan() {
		return nan;
	}
	public void setNan(NortasunAgiria nan) {
		this.nan = nan;
	}
	public List<Nota> getNotak() {
		return notak;
	}
	public void setNotak(List<Nota> notak) {
		this.notak = notak;
	}
	public List<Irakasgaia> getIrakasgaiak() {
		return irakasgaiak;
	}
	public void setIrakasgaiak(List<Irakasgaia> irakasgaiak) {
		this.irakasgaiak = irakasgaiak;
	}
	public Helbidea getHelbidea() {
		return helbidea;
	}
	public void setHelbidea(Helbidea helbidea) {
		this.helbidea = helbidea;
	}
	public List<Telefonoa> getTelefonoak() {
		return telefonoak;
	}
	public void setTelefonoak(List<Telefonoa> telefonoak) {
		this.telefonoak = telefonoak;
	}
	
	@Override
	public String toString() {
		return "Ikaslea [id=" + id + ", izena=" + izena + ", abizena=" + abizena + ", nan=" + nan + ", notak=" + notak
				+ ", irakasgaiak=" + irakasgaiak + ", helbidea=" + helbidea + ", telefonoak=" + telefonoak + "]";
	}
	
	
	
	
}
