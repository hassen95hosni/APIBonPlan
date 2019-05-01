package bon.plan.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int id ;
	private String email ;
	private String password;
	private String roles ;
	@Column(name="firstname")
	private String firstName ;
	@Column(name="lastname")
	private String lastName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	//@OneToMany(targetEntity=Conversation.class,mappedBy="firstMember",fetch=FetchType.LAZY)
	//@OneToMany(targetEntity=Conversation.class,mappedBy="secondMember",fetch=FetchType.LAZY)

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	}
