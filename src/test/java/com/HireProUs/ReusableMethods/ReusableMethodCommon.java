package com.HireProUs.ReusableMethods;

import java.awt.AWTException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.zaproxy.clientapi.core.ClientApiException;
import com.HireProUs.Engine.BaseClass;
import com.HireProUs.Engine.CommonMethod;

public class ReusableMethodCommon extends BaseClass {
	public static ReusableMethodCommon rc = new ReusableMethodCommon();

	public void SignOut() throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given User logout from the HireProUs Application");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileTab", 0);
		CommonMethod.RobustclickElementVisible("ProfileTab", "LogoutBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LogoutBtn", 0);
		CommonMethod.RobustclickElementVisible("LogoutBtn", "Username");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);

	}

	public void ScheduleInterview(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {

		testlog.info("Recruitment Team Schedule's interview for the candiadte");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "ScheduleInterviewBtn");
		testlog.info("And User clicks on ScheduleInterviewBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleInterviewBtn", 0);
		CommonMethod.RobustclickElementVisible("ScheduleInterviewBtn", "JobRequestSearch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearch", 0);
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.sendKeys("JobRequestSearch", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearchBtn", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("JobRequestList"), JrId,
				"Job Request Success Message Mismatch");
		CommonMethod.RobustclickElementVisible("JobRequestSearchBtn", "ScheduleInterviewActionBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleInterviewActionBtn", 0);
		testlog.info("And User Clicks on Action button for scheduling interview for the candidate");
		CommonMethod.RobustclickElementVisible("ScheduleInterviewActionBtn", "SelectInterviewer");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectInterviewer", 0);
		testlog.info("And User enters the mandatory details for scheduling the interview");
		CommonMethod.sendKeys("SelectInterviewer", data.getCellData(SheetName, "InterviewerName", rowNum));
		Thread.sleep(3000);
		CommonMethod.Robustclick("SelectInterviewerClickBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("SelectInterviewerClickBtn", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ModeOfInterview", 0);
		CommonMethod.selectdropdownVisibletext("ModeOfInterview",
				data.getCellData(SheetName, "Mode Of Interview", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleDate", 0);
		CommonMethod.clearAndSendKey("ScheduleDate", CommonMethod.getCurrentDate());
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewStartsFrom", 0);
		CommonMethod.sendKeys("InterviewStartsFrom", data.getCellData(SheetName, "InterviewStartsFrom", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewDuration", 0);
		CommonMethod.selectdropdownVisibletext("InterviewDuration",
				data.getCellData(SheetName, "InterviewDuration", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TimeZone", 0);
		CommonMethod.sendKeys("TimeZone", data.getCellData(SheetName, "TimeZone", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectTimeZone", 0);
		CommonMethod.Robustclick("SelectTimeZone");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewVenue", 0);
		CommonMethod.sendKeys("InterviewVenue", data.getCellData(SheetName, "InterviewVenue", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewRemarks", 0);
		CommonMethod.sendKeys("InterviewRemarks", data.getCellData(SheetName, "InterviewRemarks", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleInterviewSaveBtn", 0);
		CommonMethod.RobustclickElementVisible("ScheduleInterviewSaveBtn", "ResumeShortlistedSuccessMessage");
		testlog.info("Interview Schedule Added Successfully!");
		rc.SignOut();
	}

	private String GenerateRandomSkillSet() throws InterruptedException, IOException, ClientApiException {
		String[] skillsArray = { "Python", "Spark/Pyspark", "Azure and Databricks", "Kafka/Spark streaming",
				"JSON and No-SQL DB (Mongo DB)",

				"React JS", "Bootstrap (CSS, HTML & Java Script)", "Spring boot (Java Framework)",
				"Flask (Python framework)", "Mongo DB",

				"User Stories", "Customer Relationship Management (CRM)", "Team Coordination", "Project Management",
				"Requirements Analysis", "Business Requirements", "Documentation", "SDD (Solution Design Document)",
				"PDD (Process Design Document)" };

		List<String> skillsList = new ArrayList<>(Arrays.asList(skillsArray));
		Collections.shuffle(skillsList);

		List<String> randomSkills = skillsList.subList(0, 5); // Get 5 random skills

		String result = getRandomSkillsAsString(randomSkills);

		return result;
	}

	public static String getRandomSkillsAsString(List<String> skills) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < skills.size(); i++) {
			sb.append(skills.get(i));
			if (i < skills.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	///////////////////////////////////// Job Request Creation//////////////////////

	public void CreateJobRequest(String SheetName, int rowNum, String ErrorMessage)
			throws InterruptedException, IOException, ClientApiException, AWTException {

		testlog.info("Given User creating JobRequest");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "Jobrequest");
		testlog.info("And User clicks on JobRequest");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Jobrequest", 0);
		CommonMethod.RobustclickElementVisible("Jobrequest", "NewJobRequestBtn");
		testlog.info("And User clicks on NewJobRequestBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("NewJobRequestBtn", 0);
		CommonMethod.RobustclickElementVisible("NewJobRequestBtn", "Customer");
		testlog.info("And User clicks on Customer");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Customer", 0);
		if (ErrorMessage.equalsIgnoreCase("True")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveBtn", 0);
			CommonMethod.RobustclickElementVisible("SaveBtn", "ErrorMessage");
			testlog.info("Then User will be redirected to the Add Job Request page successfully");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ErrorMessage", 0);
			CommonMethod.negativesoftassertPageSource("Customer is required", "Customer Name Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Business Unit is required", "Business Unit Error Mismatch");
			CommonMethod.negativesoftassertPageSource("No Of Openings is required", "No Of Openings Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Currency is required", "Currency Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Project Start Date is required",
					"Project Start Date Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Placement For is required", "Placement For Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Job Description is required", "Job Description Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Mandatory Skills is required",
					"Mandatory Skills Error Mismatch");
			testlog.info(
					"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		}
		CommonMethod.selectdropdownVisibletext("Customer", data.getCellData(SheetName, "Customer", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentRole", 0);
		CommonMethod.selectdropdownVisibletext("RecruitmentRole",
				data.getCellData(SheetName, "RecruitmentRole", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BusinessUnit", 0);
		CommonMethod.selectdropdownVisibletext("BusinessUnit", data.getCellData(SheetName, "BusinessUnit", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("NoOfOpenings", 0);
		CommonMethod.sendKeys("NoOfOpenings", data.getCellData(SheetName, "NoOfOpenings", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Location", 0);
		CommonMethod.sendKeys("Location", data.getCellData(SheetName, "Location", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CurrencyType", 0);
		CommonMethod.selectdropdownVisibletext("CurrencyType", data.getCellData(SheetName, "CurrencyType", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectStartDate", 0);
		CommonMethod.clearAndSendKey("ProjectStartDate", "23-02-2024");
		System.out.println(CommonMethod.setDateFormat("23-02-2024"));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PlacementFor", 0);
		CommonMethod.selectdropdownVisibletext("PlacementFor", data.getCellData(SheetName, "PlacementFor", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("recruiterId", 0);
		CommonMethod.selectdropdownVisibletext("recruiterId", data.getCellData(SheetName, "recruiterId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobDescription", 0);
		CommonMethod.sendKeys("JobDescription", data.getCellData(SheetName, "JobDescription", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MandatorySkills", 0);
		CommonMethod.sendKeys("MandatorySkills", data.getCellData(SheetName, "MandatorySkills", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveBtn", 0);
		CommonMethod.JavascriptClickElement("SaveBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSuccessMessage", 0);
		String getJdId = CommonMethod.getattributeValueByTextContent("JobRequestSuccessMessage");
		CommonMethod.negativesoftassertFieldValid(getJdId, "Job Request Added Successfully!",
				"Job Request Success Message Mismatch");
		testlog.info("Then User verifies Job Request Success Message");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestList", 0);
		String[] splits = getJdId.split("-");
		data.setCellData(SheetName, "JrId", rowNum, splits[0].trim());
		testlog.info("Then User stores JDID in Excel");
		testlog.pass("Added Job Request and stored JDID in Excel Successfully");
	}

	public void JobRequestSearchFilter(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given User Filter the JobRequests for the specified ID");
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearch", 0);
		CommonMethod.sendKeys("JobRequestSearch", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearchBtn", 0);
		CommonMethod.RobustclickElementVisible("JobRequestSearchBtn", "JobRequestList");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestList", 0);
		testlog.info("When User Fetching the Data from Upload Table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestListTable", 0);
		List<String> val = CommonMethod.fetchTableData("JobRequestListTable");
		CommonMethod.negativesoftassertFieldValid(val.get(1), JrId, "Job Request Id doesn't match");
		testlog.pass("Then User Verfies Added Job Request in search filter Successfully");
	}

	////////////////////////////////// Adding Candidate
	////////////////////////////////// Details////////////////////////////

	public void AddCandidateTest(String sheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given User adding candidate details");
		CommonMethod.RobustclickElementVisible("AddCandidateBtn", "JobRequestReference");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestReference", 0);
//		Preparing all input Data
		Map<String, String> inputData = new HashMap<>();
		String firstname = USfaker.address().firstName();
		inputData.put("Name", firstname);
		inputData.put("LastName", USfaker.address().lastName());
		inputData.put("Email", USfaker.internet().emailAddress());
		inputData.put("Phoneno", USfaker.number().digits(10));
		inputData.put("Gender", USfaker.name().prefix().equals("Mrs.") ? "Female" : "Male");
		inputData.put("Experience", "" + new Random().nextInt(9) + 1 + "");
		inputData.put("Current Company Name", USfaker.company().name());
		inputData.put("Candidate Type", data.getCellData(sheetName, "CandidateType", rowNum));
		inputData.put("Skill Set", GenerateRandomSkillSet());
		inputData.put("CandidateResume", CandidateResume);
		inputData.put("Image Upload", CandidateImage);
		inputData.put("Image Upload", CandidateImage);

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateFirstName", 0);
		CommonMethod.sendKeys("AddCandiadateFirstName", inputData.get("Name"));
		data.setCellData(sheetName, "AddCandiadateFirstName", rowNum, firstname);

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateLastName", 0);
		CommonMethod.sendKeys("AddCandiadateLastName", inputData.get("LastName"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateContactNumber", 0);
		CommonMethod.sendKeys("AddCandiadateContactNumber", inputData.get("Phoneno"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateEmail", 0);
		CommonMethod.sendKeys("AddCandiadateEmail", inputData.get("Email"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateGender", 0);
		if (inputData.get("Gender").equals("Male")) {
			CommonMethod.ClickCheckbox("AddCandiadateGenderMale");
		} else {
			CommonMethod.ClickCheckbox("AddCandiadateGenderFemale");
		}

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateYearsofExperience", 0);
		CommonMethod.sendKeys("AddCandiadateYearsofExperience", inputData.get("Experience"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateCurrentCompanyName", 0);
		CommonMethod.sendKeys("AddCandiadateCurrentCompanyName", inputData.get("Current Company Name"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateCandidateType", 0);
		CommonMethod.selectdropdownVisibletext("AddCandiadateCandidateType", inputData.get("Candidate Type"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateSkillSet", 0);
		CommonMethod.sendKeys("AddCandiadateSkillSet", inputData.get("Skill Set"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateResumeUpload", 0);
		CommonMethod.uploadFile("AddCandiadateResumeUpload", inputData.get("CandidateResume"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveBtn", 0);
		CommonMethod.JavascriptClickElement("SaveBtn");

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateSuccessMessage", 0);
		String getSuccessMessage = CommonMethod.getattributeValueByTextContent("AddCandiadateSuccessMessage");
		CommonMethod.negativesoftassertFieldValid(getSuccessMessage, "Candidate Added Successfully!",
				"Candidate Added Success Message Mismatch");

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateImageUpload", 0);
		CommonMethod.uploadFile("AddCandiadateImageUpload", inputData.get("Image Upload"));

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateImageUploadConfirmBtn", 0);
		CommonMethod.JavascriptClickElement("AddCandiadateImageUploadCancelBtn");

		// Switch to the alert
		Alert alert = driver.switchTo().alert();
		// Get alert message text
		String alertText = alert.getText();
		System.out.println("Alert message: " + alertText);
		alert.dismiss();
		CommonMethod.refreshBrowser();

		rc.SignOut();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LoginButton", 0);

	}
////////////////////////////////////////Resume Shortlist///////////////////////////

	public void ResumeShortlist(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Interviewer shorlist the Resume");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "ResumeShortlistBtn");
		testlog.info("And User clicks on ResumeShortlistBtn ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResumeShortlistBtn", 0);
		CommonMethod.RobustclickElementVisible("ResumeShortlistBtn", "ResumeShortlistTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResumeShortlistTable", 0);
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResumeShortlistSearch", 0);
		CommonMethod.sendKeys("ResumeShortlistSearch", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResumeShortlistSearchBtn", 0);
		CommonMethod.RobustclickElementVisible("ResumeShortlistSearchBtn", "JobRequestList");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestList", 0);
		testlog.info("Fetching Data from Upload Table");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("JobRequestList"), JrId,
				"Job Request Success Message Mismatch");
		testlog.pass("Verfies Added Job Request in search filter Successful");
		testlog.info("And Interviewer Shortlist / Hold / Reject the candidate");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResumeShortlistSelectBtn", 0);
		CommonMethod.RobustclickElementVisible("ResumeShortlistSelectBtn", "ReasonForShortlist");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReasonForShortlist", 0);
		CommonMethod.sendKeys("ReasonForShortlist", data.getCellData(SheetName, "Reason For Shortlist", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResumeShortlistButton", 0);
		CommonMethod.RobustclickElementVisible("ResumeShortlistButton", "ResumeShortlistedSuccessMessage");
		testlog.info("Candidate Resume Shortlisted Successfully");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResumeShortlistedSuccessMessage", 0);
		rc.SignOut();
	}

	/////////////// Schedule InterviewR1 Process/////////////////////////////

	public void ScheduleInterviewR1(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		rc.ScheduleInterview(SheetName, rowNum);
	}

	//////////////////// Interview Process Round 1////////////////////////////

	public void InterviewProcessR1(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given Interviewer Select / Hold or Reject the candidate ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "InterviewProcessBtn");
		testlog.info("And User clicks on InterviewProcessBtn ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessBtn", "InterviewProcessTableList");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessTableList", 0);
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessSearchForJR", 0);
		CommonMethod.sendKeys("InterviewProcessSearchForJR", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessSearchForJRBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessSearchForJRBtn", "InterviewProcessTableList");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessTableList", 0);
		testlog.info("Fetching Data from Upload Table");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("InterviewProcessTableList"), JrId,
				"Job Request Success Message Mismatch");
		testlog.pass("Verfies Added Job Request in search filter Successfully");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessResultBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessResultBtn", "InterviewProcessSelectStatus");
		CommonMethod.selectdropdownVisibletext("InterviewProcessSelectStatus",
				data.getCellData(SheetName, "InterviewResultStatus", rowNum));
		CommonMethod.RobustclickElementVisible("InterviewProcessRemarks", "InterviewProcessSaveBtn");
		CommonMethod.sendKeys("InterviewProcessRemarks",
				data.getCellData(SheetName, "InterviewProcessRemarks", rowNum));
		CommonMethod.Robustclick("InterviewProcessSaveBtn");
//		CommonMethod.RobustclickElementVisible("InterviewProcessSaveBtn", "ResumeShortlistedSuccessMessage");
		testlog.info("Interview Schedule Added Successfully!");
		rc.SignOut();

	}

/////////////////////////////////Schedule InterviewR2 Process/////////////////////

	public void ScheduleInterviewR2(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		rc.ScheduleInterview(SheetName, rowNum);

	}
///////////////////////////////////// Interview Process Round 1////////////////////////////////

	public void InterviewProcessR2(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given Interviewer Select / Hold or Reject the candidate ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "InterviewProcessBtn");
		testlog.info("And User clicks on InterviewProcessBtn ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessBtn", "InterviewProcessR2Btn");
		CommonMethod.Robustclick("InterviewProcessR2Btn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessTableList", 0);
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessSearchForJR", 0);
		CommonMethod.sendKeys("InterviewProcessSearchForJR", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessSearchForJRBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessSearchForJRBtn", "InterviewProcessTableList");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessTableList", 0);
		testlog.info("Fetching Data from Upload Table");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("InterviewProcessTableList"), JrId,
				"Job Request Success Message Mismatch");
		testlog.pass("Verfies Added Job Request in search filter Successfully");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessResultBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessResultBtn", "InterviewProcessSelectStatus");
		CommonMethod.selectdropdownVisibletext("InterviewProcessSelectStatus",
				data.getCellData(SheetName, "InterviewResultStatus", rowNum));
		CommonMethod.RobustclickElementVisible("InterviewProcessRemarks", "InterviewProcessSaveBtn");
		CommonMethod.sendKeys("InterviewProcessRemarks",
				data.getCellData(SheetName, "InterviewProcessRemarks", rowNum));
		CommonMethod.Robustclick("InterviewProcessSaveBtn");
//		CommonMethod.RobustclickElementVisible("InterviewProcessSaveBtn", "ResumeShortlistedSuccessMessage");
		testlog.info("Interview Process Completed Successfully!");
		rc.SignOut();

	}

	//////////////////////////////////////// Schedule HR Interview//////////////////////////

	public void ScheduleInterviewHrRound(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {

		testlog.info("Recruitment Team Schedule's interview for the candiadte");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "ScheduleInterviewBtn");
		testlog.info("And User clicks on ScheduleInterviewBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleInterviewBtn", 0);
		CommonMethod.RobustclickElementVisible("ScheduleInterviewBtn", "JobRequestSearch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearch", 0);
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.sendKeys("JobRequestSearch", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearchBtn", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("JobRequestList"), JrId,
				"Job Request Success Message Mismatch");
		CommonMethod.RobustclickElementVisible("JobRequestSearchBtn", "ScheduleInterviewActionBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleInterviewActionBtn", 0);
		testlog.info("And User Clicks on Action button for scheduling interview for the candidate");
		CommonMethod.RobustclickElementVisible("ScheduleInterviewActionBtn", "SelectInterviewer");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectInterviewer", 0);
		testlog.info("And User enters the mandatory details for scheduling the interview");
		CommonMethod.sendKeys("SelectInterviewer", data.getCellData(SheetName, "HRInterviewerName", rowNum));
		Thread.sleep(3000);
		CommonMethod.Robustclick("SelectInterviewerClickBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("SelectInterviewerClickBtn", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ModeOfInterview", 0);
		CommonMethod.selectdropdownVisibletext("ModeOfInterview",
				data.getCellData(SheetName, "Mode Of Interview", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleDate", 0);
		CommonMethod.clearAndSendKey("ScheduleDate", CommonMethod.getCurrentDate());
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewStartsFrom", 0);
		CommonMethod.sendKeys("InterviewStartsFrom", data.getCellData(SheetName, "InterviewStartsFrom", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewDuration", 0);
		CommonMethod.selectdropdownVisibletext("InterviewDuration",
				data.getCellData(SheetName, "InterviewDuration", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TimeZone", 0);
		CommonMethod.sendKeys("TimeZone", data.getCellData(SheetName, "TimeZone", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectTimeZone", 0);
		CommonMethod.Robustclick("SelectTimeZone");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewVenue", 0);
		CommonMethod.sendKeys("InterviewVenue", data.getCellData(SheetName, "InterviewVenue", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewRemarks", 0);
		CommonMethod.sendKeys("InterviewRemarks", data.getCellData(SheetName, "HrInterviewRemarks", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScheduleInterviewSaveBtn", 0);
		CommonMethod.Robustclick("ScheduleInterviewSaveBtn");
//		CommonMethod.RobustclickElementVisible("ScheduleInterviewSaveBtn", "ResumeShortlistedSuccessMessage");
		testlog.info("Interview Schedule Added Successfully!");
		rc.SignOut();
	}

//////////////////////////////////////HR Interview Process//////////////////////////////////////////
	public void InterviewProcessHrRound(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given Interviewer Select / Hold or Reject the candidate ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "InterviewProcessBtn");
		testlog.info("And User clicks on InterviewProcessBtn ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessBtn", "InterviewProcessHrRBtnBtn");
		CommonMethod.Robustclick("InterviewProcessHrRBtnBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessTableList", 0);
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessSearchForJR", 0);
		CommonMethod.sendKeys("InterviewProcessSearchForJR", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessSearchForJRBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessSearchForJRBtn", "InterviewProcessTableList");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessTableList", 0);
		testlog.info("Fetching Data from Upload Table");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("InterviewProcessTableList"), JrId,
				"Job Request Success Message Mismatch");
		testlog.pass("Verfies Added Job Request in search filter Successfully");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InterviewProcessResultBtn", 0);
		CommonMethod.RobustclickElementVisible("InterviewProcessResultBtn", "InterviewProcessSelectStatus");
		CommonMethod.selectdropdownVisibletext("InterviewProcessSelectStatus",
				data.getCellData(SheetName, "InterviewResultStatus", rowNum));
		CommonMethod.RobustclickElementVisible("InterviewProcessRemarks", "InterviewProcessSaveBtn");
		CommonMethod.sendKeys("InterviewProcessRemarks", data.getCellData(SheetName, "HrInterviewRemarks", rowNum));
		CommonMethod.Robustclick("InterviewProcessSaveBtn");
//		CommonMethod.RobustclickElementVisible("InterviewProcessSaveBtn", "ResumeShortlistedSuccessMessage");
		testlog.info("Interview Process completed Successfully!");
		rc.SignOut();
	}

	////////////////////////////////// BU Head Approval////////////////////////////

	public void BUHeadApproval(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given BU Head Select / Hold or Reject the candidate ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "BUHeadApprovalBtn");
		testlog.info("And User clicks on BU HeadApproval Tab");
		CommonMethod.RobustclickElementVisible("BUHeadApprovalBtn", "JobRequestSearch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearch", 0);
		String JrId = data.getCellData(SheetName, "JrId", rowNum);
		CommonMethod.sendKeys("JobRequestSearch", data.getCellData(SheetName, "JrId", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BUSearchBtn", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("JobRequestList"), JrId,
				"Job Request Success Message Mismatch");
		CommonMethod.RobustclickElementVisible("BUSearchBtn", "BUSelectBtn");
		CommonMethod.RobustclickElementVisible("BUSelectBtn", "ReasonForApproval");
		CommonMethod.sendKeys("ReasonForApproval", data.getCellData(SheetName, "ReasonForApproval", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BUApproveBtn", 0);
		CommonMethod.Robustclick("BUApproveBtn");
//		CommonMethod.RobustclickElementVisible("BUApproveBtn", "ResumeShortlistedSuccessMessage");
		testlog.info("Candidate selected Successfully!");
		rc.SignOut();
	}

////////////////////////////////////Onboarding Process////////////////////////////////
	public static String UniqueEmployeeId(String sheetName, int rowNum) throws InterruptedException, IOException {
		String existingEmployeeId = data.getCellData(sheetName, "EmployeeIDByHR", rowNum);
		String[] splits = existingEmployeeId.split("-");
		int newEmpId = Integer.parseInt(splits[1]) + 1;
		String EmpId = String.format("PE-%03d", newEmpId);
		return EmpId;

	}

	public void OnboardingDetails(String SheetName, int rowNum)
			throws InterruptedException, IOException, ClientApiException {
		testlog.info("Given Recruitment Manager Onboarding the candiadte");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		CommonMethod.RobustclickElementVisible("HumanResourceTab", "RecruitmentTab");
		testlog.info("When User clicks on RecruitmentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RecruitmentTab", 0);
		CommonMethod.RobustclickElementVisible("RecruitmentTab", "OnboardDetailsTab");
		testlog.info("And User clicks on OnboardDetails Tab");
		CommonMethod.Robustclick("OnboardDetailsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("NewOnboardDetailBtn", 0);
		CommonMethod.RobustclickElementVisible("NewOnboardDetailBtn", "JobRequestSearchByName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearchByName", 0);
		String firstname = data.getCellData(SheetName, "AddCandiadateFirstName", rowNum);
		CommonMethod.sendKeys("JobRequestSearchByName", data.getCellData(SheetName, "AddCandiadateFirstName", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("JobRequestSearchByNameBtn", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("JobRequestListTable"),
				firstname, "Job Request Success Message Mismatch");
		CommonMethod.RobustclickElementVisible("JobRequestSearchByNameBtn", "OnboardDetailActionBtn");
		testlog.info("And User Clicks on Action button for filling Onboard Details");
		CommonMethod.RobustclickElementVisible("OnboardDetailActionBtn", "candidateacceptedtheofferBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("candidateacceptedtheofferBtn", 0);
		CommonMethod.selectdropdownVisibletext("candidateacceptedtheofferBtn",
				data.getCellData(SheetName, "IscandidateacceptedtheofferBtn", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EmployeeIDByHR", 0);
		CommonMethod.sendKeys("EmployeeIDByHR", UniqueEmployeeId(SheetName, rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OnboardDetailDateOfJoining", 0);
		CommonMethod.clearAndSendKey("OnboardDetailDateOfJoining", CommonMethod.getCurrentDate());
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OnboardDetailEmail", 0);
		Map<String, String> inputData = new HashMap<>();
		inputData.put("Email", USfaker.internet().emailAddress());
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddCandiadateEmail", 0);
		CommonMethod.sendKeys("OnboardDetailEmail", inputData.get("Email"));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OnboardDetailsSaveBtn", 0);
		CommonMethod.Robustclick("OnboardDetailsSaveBtn");
//		CommonMethod.RobustclickElementVisible("BUApproveBtn", "ResumeShortlistedSuccessMessage");
		testlog.info("Candidate Selected Status Added Successfully!");
		data.setCellData(SheetName, "EmployeeIDByHR", rowNum, UniqueEmployeeId(SheetName, rowNum));

	}

}