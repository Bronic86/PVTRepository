package by.academy.alekhno.vo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User user ;
	private Clother clother ;
	private int quantity;
	private Date ordering_day ;
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Clother getClother() {
		return clother;
	}
	
	public void setClother(Clother clother) {
		this.clother = clother;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Date getOrdering_day() {
		return ordering_day;
	}
	
	public void setOrdering_day(Date ordering_day) {
		this.ordering_day = ordering_day;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clother == null) ? 0 : clother.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((ordering_day == null) ? 0 : ordering_day.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (clother == null) {
			if (other.clother != null)
				return false;
		} else if (!clother.equals(other.clother))
			return false;
		if (id != other.id)
			return false;
		if (ordering_day == null) {
			if (other.ordering_day != null)
				return false;
		} else if (!ordering_day.equals(other.ordering_day))
			return false;
		if (quantity != other.quantity)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", clother=" + clother
				+ ", quantity=" + quantity + ", ordering_day=" + ordering_day
				+ "]";
	}
	
}
