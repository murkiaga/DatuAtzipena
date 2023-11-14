package entitateak;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="nota")
public class Nota {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	private Ikaslea ikaslea;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "irakasgaia")
	private Irakasgaia irakasgaia;
	
	@Column(name="nota")
	private int nota;

	public Nota() {}
	public Nota(Ikaslea ikaslea, Irakasgaia irakasgaia, int nota) {
		super();
		this.ikaslea = ikaslea;
		this.irakasgaia = irakasgaia;
		this.nota = nota;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ikaslea getIkaslea() {
		return ikaslea;
	}
	public void setIkaslea(Ikaslea ikaslea) {
		this.ikaslea = ikaslea;
	}
	public Irakasgaia getIrakasgaia() {
		return irakasgaia;
	}
	public void setIrakasgaia(Irakasgaia irakasgaia) {
		this.irakasgaia = irakasgaia;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
}
