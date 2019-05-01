package bon.plan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Conversation implements Serializable{

	@Id
	private int id ;
	@ManyToOne
	//@JoinColumn(name="firstMember")
	private User firstMember;
	@ManyToOne
	private User secondMember;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFirstMember() {
		return firstMember;
	}

	public void setFirstMember(User firstMember) {
		this.firstMember = firstMember;
	}

	public User getSecondMember() {
		return secondMember;
	}

	public void setSecondMember(User secondMember) {
		this.secondMember = secondMember;
	}
	
	
}
