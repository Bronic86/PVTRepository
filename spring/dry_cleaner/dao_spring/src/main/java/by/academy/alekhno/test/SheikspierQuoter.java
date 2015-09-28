package by.academy.alekhno.test;


//@BenchMark
public class SheikspierQuoter implements Quoter {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void sayQuoter() {
		System.out.println("Sheikspire say:");
		System.out.println(message);
	}

}
