package by.academy.alekhno.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	@NotEmpty(message = "Please enter your login.")
	@Email(message = "Your login must be e-mail.")
	private String login;

	@NotEmpty(message = "Please enter your password.")
	@Pattern(regexp = "^[\\w\\d]*$", message = "Incorrect symbols in password. Change please.")
	@Size(min = 4, max = 12, message = "Password must be contain more then 4 and less then 12 symbols")
	private String password;

	@NotEmpty(message = "Please enter your first name.")
	@Size(min = 1, max = 15, message = "First name must be contain more then 1 and less then 15 symbols")
	private String firstName;

	@NotEmpty(message = "Please enter your second name.")
	@Size(min = 1, max = 15, message = "Second name must be contain more then 1 and less then 15 symbols")
	private String secondName;

	// @NotEmpty(message = "Please enter your telephone number.")
	// @Pattern(regexp = "^[0-9]{12}$", message =
	// "Incorrect symbols in data. Write only 12 decimals (375292766536).")
	private long telephone;

	private Set<Role> roles;

	public User() {
	}

	public User(int id, String login, String password, String firstName, String secondName,
			long telephone, Set<Role> roles) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
		this.telephone = telephone;
		this.roles = roles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + (int) (telephone ^ (telephone >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (telephone != other.telephone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", firstName="
				+ firstName + ", secondName=" + secondName + ", telephone=" + telephone
				+ ", roles=" + roles + "]";
	}

}
