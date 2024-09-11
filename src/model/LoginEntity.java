package model;

import java.io.Serializable;
import java.util.Objects;

public class LoginEntity implements Serializable {

	String name;
	String pasword;
	String priviledge;
	
	
	
	public LoginEntity(String name, String pasword, String priviledge) {
		super();
		this.name = name;
		this.pasword = pasword;
		this.priviledge = priviledge;
	}

	public LoginEntity() {
	
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}



	public String getPasword() {
		return pasword;
	}



	public void setPasword(String pasword) {
		this.pasword = pasword;
	}



	public String getPriviledge() {
		return priviledge;
	}



	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
	}



	@Override
	public String toString() {
		return "LoginEntity [name=" + name + ", pasword=" + pasword + ", priviledge=" + priviledge + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(name, pasword, priviledge);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginEntity other = (LoginEntity) obj;
		return Objects.equals(name, other.name) && Objects.equals(pasword, other.pasword)
				&& Objects.equals(priviledge, other.priviledge);
	}
	
	
}
