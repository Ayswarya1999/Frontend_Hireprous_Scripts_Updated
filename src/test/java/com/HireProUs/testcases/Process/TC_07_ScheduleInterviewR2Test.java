package com.HireProUs.testcases.Process;
import java.io.IOException;
import org.testng.annotations.Test;
import com.HireProUs.Engine.BaseClass;
import org.testng.annotations.Parameters;

public class TC_07_ScheduleInterviewR2Test extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.Process.TC_06_InterviewProcessR1Test.TC_06_InterviewProcessR1" })
	@Parameters({ "SheetName", "rowNum" })
	public void TC_07_ScheduleInterviewR2(String SheetName, int rowNum) throws IOException {
		
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName, "HireProUs Login Functionality");
			try {
				login.Login(2);
				rc.ScheduleInterview(SheetName, rowNum);
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	}

