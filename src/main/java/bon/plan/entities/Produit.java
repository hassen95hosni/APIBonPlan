package bon.plan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produit implements Serializable {

	@Id
	private int id ;
	private String name;
	private String descriptionCourt;
	private String descriptionLong;
	private Long planId;
	private float prix;
	
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptionCourt() {
		return descriptionCourt;
	}
	public void setDescriptionCourt(String descriptionCourt) {
		this.descriptionCourt = descriptionCourt;
	}
	public String getDescriptionLong() {
		return descriptionLong;
	}
	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
}
