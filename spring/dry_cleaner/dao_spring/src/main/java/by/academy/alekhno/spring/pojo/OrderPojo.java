package by.academy.alekhno.spring.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class OrderPojo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "ordering_day", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date ordering_day;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserPojo user;

	@ManyToOne
	@JoinColumn(name = "clother_id")
	private ClotherPojo clother;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private StatePojo state;

	public OrderPojo() {

	}

	public OrderPojo(int id, int quantity, Date ordering_day, UserPojo user, ClotherPojo clother,
			StatePojo state) {
		this.id = id;
		this.quantity = quantity;
		this.ordering_day = ordering_day;
		this.user = user;
		this.clother = clother;
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((clother == null) ? 0 : clother.hashCode());
		result = prime * result + id;
		result = prime * result + ((ordering_day == null) ? 0 : ordering_day.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		OrderPojo other = (OrderPojo) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
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
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", quantity=" + quantity + ", ordering_day=" + ordering_day
				+ ", user=" + user + ", clother=" + clother + ", state=" + state + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserPojo getUser() {
		return user;
	}

	public void setUser(UserPojo user) {
		this.user = user;
	}

	public ClotherPojo getClother() {
		return clother;
	}

	public void setClother(ClotherPojo clother) {
		this.clother = clother;
	}

	public StatePojo getState() {
		return state;
	}

	public void setState(StatePojo state) {
		this.state = state;
	}

}
