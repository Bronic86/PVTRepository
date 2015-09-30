package by.academy.alekhno.test.production;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);
		SheikspierQuoter quoterS = context.getBean(SheikspierQuoter.class);
		quoterS.setMessage("I am Shakespire");

		TerminatorQuoter quoterT = context.getBean(TerminatorQuoter.class);
		List<String> messages = Arrays.asList("I am Terminator");
		quoterT.setMessages(messages);

		TalkingRobot tR = context.getBean(TalkingRobotImpl.class);
		tR.sayQuoters();
	}
}
