package by.academy.alekhno.test.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Transaction
//@BenchMark
@Service
public class TalkingRobotImpl implements TalkingRobot {

	@Autowired
	private List<Quoter> quoters;

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
	// @PostConstruct
	public void sayQuoters() {
		for (Quoter quoter : quoters) {
			quoter.sayQuoter();
		}
	}

}
