package by.academy.alekhno.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"springSource.xml");

		// Quoter quoter = context.getBean("SheikspierQuoter",
		// SheikspierQuoter.class);
		// quoter.sayQuoter();
		// quoter = (TerminatorQuoter) context.getBean("TerminatorQuoter");
		// quoter.sayQuoter();
		TalkingRobot robot = context.getBean("talkingRobot", TalkingRobotImpl.class);
		robot.sayQuoters();
	}

}
