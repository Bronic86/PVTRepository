package by.academy.alekhno.spring.web.form;

import java.io.Serializable;

public class AddRoleForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private String user_id;

	private String role_id;

	private String add_remove;

	@Override
	public String toString() {
		return "AddRoleForm [user_id=" + user_id + ", role_id=" + role_id + ", add_remove="
				+ add_remove + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((add_remove == null) ? 0 : add_remove.hashCode());
		result = prime * result + ((role_id == null) ? 0 : role_id.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		AddRoleForm other = (AddRoleForm) obj;
		if (add_remove == null) {
			if (other.add_remove != null)
				return false;
		} else if (!add_remove.equals(other.add_remove))
			return false;
		if (role_id == null) {
			if (other.role_id != null)
				return false;
		} else if (!role_id.equals(other.role_id))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getAdd_remove() {
		return add_remove;
	}

	public void setAdd_remove(String add_remove) {
		this.add_remove = add_remove;
	}
}
