package com.HireProUs.testcases.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;


import org.testng.annotations.Parameters;

public class TC_12_OnboardDetailsTest extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.Process.TC_11_BUHeadApprovalTest.TC_11_BUHeadApproval" })
	@Parameters({ "SheetName", "rowNum" })
	public void TC_12_OnboardDetails(String SheetName, int rowNum) throws IOException {
		
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName, "HireProUs Login Functionality");
			try {
				login.Login(2);
				rc.OnboardingDetails(SheetName, rowNum);
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	}

