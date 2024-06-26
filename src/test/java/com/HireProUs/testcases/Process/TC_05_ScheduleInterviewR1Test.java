package com.HireProUs.testcases.Process;
import java.io.IOException;
import org.testng.annotations.Test;
import com.HireProUs.Engine.BaseClass;
import org.testng.annotations.Parameters;

public class TC_05_ScheduleInterviewR1Test extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.Process.TC_04_ResumeShorlistTest.TC_04_00_ResumeShortlist" })
	@Parameters({ "SheetName", "rowNum" }) 
	public void TC_05_ScheduleInterviewR1(String SheetName, int rowNum) throws IOException {
		
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

