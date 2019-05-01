package bon.plan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category implements Serializable {

	@Id
	private int id ;
}
