package scripts;

import org.testng.annotations.Test;

import seleniumcore.BaseTest;

public class Demo extends BaseTest {
	
	
	@Test
    public void test() {
		Startlog("Hello Buddy");
		System.out.println("Conf done");
	}

}
