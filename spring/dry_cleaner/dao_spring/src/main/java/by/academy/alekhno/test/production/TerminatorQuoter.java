package by.academy.alekhno.test.production;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

//@MyDepricated(newClass = T1000.class)
@Repository
public class TerminatorQuoter implements Quoter {

	private List<String> messages = new ArrayList<String>();

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public void sayQuoter() {
		System.out.println("Termnator say:");
		for (String message : messages) {
			System.out.println(message);
		}

	}

}
