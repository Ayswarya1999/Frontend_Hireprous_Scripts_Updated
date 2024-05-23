package com.HireProUs.testcases.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;
import com.HireProUs.ReusableMethods.HireProUsConstants;

import org.testng.annotations.Parameters;

public class TC_08_InterviewProcessR2Test extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.Process.TC_07_ScheduleInterviewR2Test.TC_07_ScheduleInterviewR2" })
	@Parameters({ "SheetName", "rowNum" })
	public void TC_08_InterviewProcessR2(String SheetName, int rowNum) throws IOException {
		
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName, "HireProUs Login Functionality");
			try {
				login.Login(4);
				rc.InterviewProcessR2(SheetName, rowNum);
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	}

