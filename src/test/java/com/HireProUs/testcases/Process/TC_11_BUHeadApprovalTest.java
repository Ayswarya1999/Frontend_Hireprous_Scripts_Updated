package com.HireProUs.testcases.Process;
import java.io.IOException;
import org.testng.annotations.Test;
import com.HireProUs.Engine.BaseClass;
import org.testng.annotations.Parameters;

public class TC_11_BUHeadApprovalTest extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.Process.TC_10_InterviewProcessHrRoundTest.TC_10_InterviewProcessHrRound" })
	@Parameters({ "SheetName", "rowNum" })
	public void TC_11_BUHeadApproval(String SheetName, int rowNum) throws IOException {
		
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName, "HireProUs Login Functionality");
			try {
				login.Login(3);
				rc.BUHeadApproval(SheetName, rowNum);
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	}

