package bon.plan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City implements Serializable {
	@Id
	private int id ;

}
