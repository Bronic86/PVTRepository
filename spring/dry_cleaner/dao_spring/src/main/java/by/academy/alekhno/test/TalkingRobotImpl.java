package by.academy.alekhno.test;

import java.util.ArrayList;
import java.util.List;

//@Transaction
//@BenchMark
public class TalkingRobotImpl implements TalkingRobot {

	private List<Quoter> quoters = new ArrayList<Quoter>();

	public List<Quoter> getQuoters() {
		return quoters;
	}

	public void setQuoters(List<Quoter> quoters) {
		this.quoters = quoters;
	}

	@Override
	public String toString() {
		return "TalkingRobot [quoters=" + quoters + "]";
	}

	@Override
	// @BenchMark
	public void sayQuoters() {
		for (Quoter quoter : quoters) {
			quoter.sayQuoter();
		}
	}

}
