package com.HireProUs.ReusableMethods;

import java.io.IOException;

import org.zaproxy.clientapi.core.ClientApiException;

import com.HireProUs.Engine.BaseClass;
import com.HireProUs.Engine.CommonMethod;

public class ReusableMethodsLogin extends BaseClass {

	public void Login(int rownum) throws IOException, InterruptedException {
		testlog.info("Given User navigate to HireProUs Dashboard page");
		String Username = data.getCellData("Login", "UserName", rownum);
		String Password = data.getCellData("Login", "Password", rownum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.sendKeys("Username", Username);
		System.out.println(Username);
		CommonMethod.findElementWithRelative("LoginButton", "Password", "above").sendKeys(Password);
		testlog.info("When User enters username and password");
		testlog.info("Sending Username: " + Username);
		testlog.info("Sending Password: " + Password);
		Thread.sleep(1000);
		CommonMethod.scrolldowntoElement("LoginButton");
		CommonMethod.RobustclickElementVisible("LoginButton", "HumanResourceTab");
		testlog.info("And User clicks on Sign IN button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		testlog.info("Then User will be redirected to Dashboard page");
		testlog.pass("Verfies Login Successful");
	}



}
