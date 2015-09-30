package by.academy.alekhno.vo;

import java.io.Serializable;

public class State implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String state;
	
	
	public State() {
	}

	
	
	public State(int id, String state) {
		this.id = id;
		this.state = state;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		State other = (State) obj;
		if (id != other.id)
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
		return "State [id=" + id + ", state=" + state + "]";
	}
	
	

}
