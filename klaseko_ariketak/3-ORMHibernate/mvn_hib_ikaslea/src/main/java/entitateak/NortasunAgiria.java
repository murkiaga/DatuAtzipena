package entitateak;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="nortasun_agiria")
public class NortasunAgiria {

	@Id
	@Column(name="nan_zkia", length=10)
	private String nan_zkia;
	
	@Column(name="jaio_urtea")
	private String jaio_urtea;
	
	@Column(name="iraungitze_data")
	private String iraungitze_data;
	
	@OneToOne(
			mappedBy = "nan",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
		)
	private Ikaslea ikaslea;

	public NortasunAgiria() {}
	public NortasunAgiria(String nan_zkia, String jaio_urtea, String iraungitze_data) {
		super();
		this.nan_zkia = nan_zkia;
		this.jaio_urtea = jaio_urtea;
		this.iraungitze_data = iraungitze_data;
	}
	public String getNan_zkia() {
		return nan_zkia;
	}
	public void setNan_zkia(String nan_zkia) {
		this.nan_zkia = nan_zkia;
	}
	public String getJaio_urtea() {
		return jaio_urtea;
	}
	public void setJaio_urtea(String jaio_urtea) {
		this.jaio_urtea = jaio_urtea;
	}
	public String getIraungitze_data() {
		return iraungitze_data;
	}
	public void setIraungitze_data(String iraungitze_data) {
		this.iraungitze_data = iraungitze_data;
	}
	public Ikaslea getIkaslea() {
		return ikaslea;
	}
	public void setIkaslea(Ikaslea ikaslea) {
		this.ikaslea = ikaslea;
	}
	
	
	
}
