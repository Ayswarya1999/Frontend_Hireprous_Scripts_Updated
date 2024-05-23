package com.HireProUs.testcases.login;

import java.io.FileInputStream;
import java.io.IOException;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;


public class TC_01_LoginTest extends BaseClass {

	@Test
	

	public void TC_01_Login() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "HireProUs Login Functionality");

		try {
			login.Login(2);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;

		}
	}

}
