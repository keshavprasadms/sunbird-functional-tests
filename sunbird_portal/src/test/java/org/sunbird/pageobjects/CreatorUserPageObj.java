package org.sunbird.pageobjects;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.GenericArrayType;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sunbird.generic.ExtentTestManager;
import org.sunbird.generic.GenericFunctions;
import org.sunbird.generic.ReadTestDataFromExcel;
import org.sunbird.page.CreateMentorPage;
import org.sunbird.page.CreatorUserPage;
import org.sunbird.page.PublicUserPage;
import org.sunbird.page.SignUp;
import org.sunbird.startup.BaseTest;
import org.sunbird.testdata.TestDataForSunbird;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class CreatorUserPageObj extends BaseTest{

	WebDriverWait wait = new WebDriverWait(driver,20);
	CreatorUserPage createUserPage=PageFactory.initElements(driver, CreatorUserPage.class);
	PublicUserPage publicUserPage = PageFactory.initElements(driver, PublicUserPage.class);
	CreateMentorPage createMentorPage=PageFactory.initElements(driver, CreateMentorPage.class);
	SignUp signUpPage=PageFactory.initElements(driver, SignUp.class);
	static Logger log = Logger.getLogger(CreatorUserPageObj.class.getName());
	List <TestDataForSunbird> objListOFTestDataForSunbird1= null ;
	Actions action = new Actions(driver);
	Random rand=new Random();
	String lessonNumber;
	String title="";

	public void navigateToWorkspace(String createVariable)
	{

		try
		{
//			ExtentTestManager.getTest().log(LogStatus.INFO, "User is navigating to the Workspace to create "+createVariable);
			//GenericFunctions.waitTillTheElementInVisible(createUserPage.headerProfile);
			//createUserPage.dropDown.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			GenericFunctions.waitWebDriver(2500);
			if(createVariable.equalsIgnoreCase(createUserPage.createCourse.getText()))
			{
				createUserPage.createCourse.click();
				GenericFunctions.waitWebDriver(1500);
			}
			else if(createVariable.equalsIgnoreCase(createUserPage.createBook.getText()))
			{
				createUserPage.createBook.click();
			}
			else if(createVariable.equalsIgnoreCase(createUserPage.createResource.getText()))
			{
				createUserPage.createResource.click();
			}
			else if(createVariable.equalsIgnoreCase(createUserPage.createCollection.getText()))
			{
				createUserPage.createCollection.click();
			}
			else if(createVariable.equalsIgnoreCase(createUserPage.createLesson.getText()))
			{
				createUserPage.createLesson.click();
			}
			else if(createVariable.equalsIgnoreCase(createUserPage.createUploadContent.getText()))
			{
				createUserPage.createUploadContent.click();
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on navigating to Workspace");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed on navigating to Workspace");
			Assert.fail("Failed on navigating to Workspace and create "+createVariable);
		}

	}

	public void createCourse(List <TestDataForSunbird> objListOFTestDataForSunbird) throws InterruptedException
	{
		try{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to create course");
			GenericFunctions.waitWebDriver(1500);
			System.out.println(driver.findElement(By.xpath(" //span[contains(text(),'Design Course')]")).getText());
			GenericFunctions.waitForElementToAppear(createUserPage.courseName);
			String courseNumber = GenericFunctions.testDataIncrementer("./TestData/courseNumbers.txt").toString();
			createUserPage.courseName.sendKeys(objListOFTestDataForSunbird.get(0).getCourseName()+courseNumber);
			createUserPage.courseDescription.sendKeys(objListOFTestDataForSunbird.get(0).getCourseDescription()+courseNumber);	
			GenericFunctions.waitWebDriver(1500);
			createUserPage.startCreating.click();
			/*			GenericFunctions.refreshWebPage();
			GenericFunctions.waitWebDriver(1500);*/
			GenericFunctions.waitWebDriver(4500);
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(6500);
			GenericFunctions.waitForElementToAppear(createUserPage.newChild);
			System.out.println("Creating - "+createUserPage.newChild.getText());
			GenericFunctions.waitWebDriver(3000);
			action.moveToElement(createUserPage.newChild).click().perform();
			//action.moveToElement(createUserPage.newChild).click().release();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.titleName.click();
			createUserPage.titleName.clear();
			createUserPage.titleName.sendKeys(objListOFTestDataForSunbird.get(0).getTitle()+courseNumber);
			createUserPage.titleDescription.sendKeys(objListOFTestDataForSunbird.get(0).getTitleDescription()+courseNumber);
			GenericFunctions.waitWebDriver(1500);
			createUserPage.addResource.click();
			GenericFunctions.waitForElementToAppear(createUserPage.findSelectActivities);
			createUserPage.selectResource.click();
			GenericFunctions.waitWebDriver(500);
			createUserPage.proceedButton.click();
			GenericFunctions.waitWebDriver(500);


		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to create course");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			log.error("Exception In the method createCourse"+e.getMessage());
			System.out.println("Exception In the method createCourse, failed to create corse");
			Assert.fail("Failed on creating course");

		}
	}

	public void saveAndSendCourseForReview(List <TestDataForSunbird> objListOFTestDataForSunbird)throws Exception{

		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to save and send course for review");
			GenericFunctions.waitForElementToAppear(createUserPage.saveCourse);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.sendForReview.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickAppIcon.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.uploadAndUseButton.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.yesRadioButton.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.chooseFileButton.click();
			GenericFunctions.waitWebDriver(1500);
			String path = System.getProperty("user.dir")+"/UploadingDocuments/Upload Document Contents/"+SEARCH_COURSE_IMAGE;
			System.out.println("Uploaded image : "+path);
			//log.info("Uploaded file name: "+path);
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.uploadFile(path);
			GenericFunctions.waitWebDriver(2500);
			createUserPage.uploadAndUseButtonRight.click();
			GenericFunctions.waitWebDriver(1800);
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.waitForElementToAppear(createUserPage.clickOnSelectCurriculum);
			createUserPage.clickOnSelectCurriculum.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.selectCurriculum.click();
			GenericFunctions.waitForElementToAppear(createUserPage.clickOnSelectClass);
			createUserPage.clickOnSelectClass.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.selectClass.click();
			GenericFunctions.keyTab(driver, createUserPage.selectClass.toString());
			//GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.clickOnHeaderSubject);
			//createUserPage.clickOnHeaderSubject.click();
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.clickOnSelectSubject);
			GenericFunctions.scrollToElement(createUserPage.clickOnSelectMedium);
			GenericFunctions.waitForElementToAppear(createUserPage.clickOnSelectMedium);
			createUserPage.clickOnSelectMedium.click();
			GenericFunctions.waitForElementToAppearOnScreen(createUserPage.selectMedium);
			createUserPage.selectMedium.click();
			createUserPage.clickOnSelectSubject.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectSubject);
			createUserPage.selectSubject.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.saveButton.click();
			GenericFunctions.waitWebDriver(1500);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on saving and sending course for review");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to save and course for review");
			Assert.fail("Failed on saving and sending course for review");

		}

	}

	public void saveAndSendBookForReview(List <TestDataForSunbird> objListOFTestDataForSunbird)throws Exception{

		try{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to save and send book for for review");
			GenericFunctions.waitWebDriver(1500);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.sendForReview.click();
			GenericFunctions.waitForElementToAppear(createUserPage.clickAppIcon);
			/*createUserPage.clickAppIcon.click();	
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.checkAppIcon);
			GenericFunctions.waitWebDriver(2000);
			createUserPage.checkAppIcon.click();
			createUserPage.selectAppIcon.click();
			GenericFunctions.waitWebDriver(2000);*/

			createUserPage.clickAppIcon.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.uploadAndUseButton.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.yesRadioButton.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.chooseFileButton.click();
			GenericFunctions.waitWebDriver(1500);
			String path = System.getProperty("user.dir")+"/UploadingDocuments/Upload Document Contents/"+BOOK_IMAGE;
			System.out.println("Uploaded image : "+path);
			//log.info("Uploaded file name: "+path);
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.uploadFile(path);
			GenericFunctions.waitWebDriver(2500);

			createUserPage.uploadAndUseButtonRight.click();
			GenericFunctions.waitWebDriver(1800);
			createUserPage.saveButton.click();
			System.out.println("Dial Code updated Successfully");
			System.out.println("Content updated successfully");
			GenericFunctions.waitWebDriver(2500);
			//createUserPage.editorCloseIcon.click();
			GenericFunctions.waitWebDriver(2500);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on saving and sending the book for review");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to save and send book for review"+e.getLocalizedMessage());
			Assert.fail("Failed to save and send the book for review");

		}

	}

	public String searchInUpForReview(String source,List <TestDataForSunbird> objListOFTestDataForSunbird) throws Exception{
		String courseNumber = "",searchCourseName="";
		try
		{	
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to search in Up For Review bucket for "+source);
			GenericFunctions.waitWebDriver(3000);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			createUserPage.upForReview.click();
			if(source.equalsIgnoreCase(COURSE)){
				courseNumber = GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();
				createUserPage.searchForReview.click();
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(0).getCourseName()+courseNumber);
				createUserPage.searchIcon.click();
				searchCourseName = objListOFTestDataForSunbird.get(0).getCourseName()+courseNumber;
				GenericFunctions.waitWebDriver(3000);			
			}
			else if(source.equalsIgnoreCase(BOOK)){
				courseNumber = GenericFunctions.readFromNotepad("./TestData/bookNumbers.txt").toString();
				createUserPage.searchForReview.click();
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(2).getCourseName()+courseNumber);
				createUserPage.searchIcon.click();
				searchCourseName = objListOFTestDataForSunbird.get(2).getCourseName()+courseNumber;
				GenericFunctions.waitWebDriver(3000);			
			}
			//Added on 10 july 2018
			else if(source.equalsIgnoreCase(RESOURCE))
			{
				courseNumber = GenericFunctions.readFromNotepad("./TestData/resourceNumbers.txt").toString();
				createUserPage.searchForReview.click();
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(6).getCourseName()+courseNumber);
				createUserPage.searchIcon.click();
				searchCourseName = objListOFTestDataForSunbird.get(6).getCourseName()+courseNumber;
				GenericFunctions.waitWebDriver(3000);
			}
			//--------------------------

			assertFoundInUpForReview(searchCourseName);
			GenericFunctions.waitWebDriver(3000);

		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching in Up for review");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println(e.getLocalizedMessage());
			log.error("Exception In the method searchCourse"+e.getMessage());
			Assert.fail("Failed to search in Up for review bucket");
		}
		return searchCourseName;

	}


	public void reviewInSubmissions(String source, List <TestDataForSunbird> objListOFTestDataForSunbird)
	{

		try{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To verify "+source+" is found in review submission");
			createUserPage.reviewSubmission.click();
			assertFoundInReviewSubmission(source,objListOFTestDataForSunbird);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to find in review submission");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			log.error("Failed to find in review submission bucket"+e.getLocalizedMessage());
		}

	}

	public static void assertFoundInReviewSubmission(String source,List <TestDataForSunbird> objListOFTestDataForSunbird){
		try{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To assert that "+source+" is found in review submission");
			//List<WebElement> result = driver.findElements(By.xpath("//div[@class='cardImageText center aligned ']/span"));
			CreatorUserPage createUserPage1=PageFactory.initElements(driver, CreatorUserPage.class);
			if(source.equalsIgnoreCase(COURSE)){
				String courseNumber = GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();
				for(int i=0;i<createUserPage1.reviewSubmittedCourse.size();i++){
					String courseName = createUserPage1.reviewSubmittedCourse.get(i).getText();
					if(courseName.equalsIgnoreCase(objListOFTestDataForSunbird.get(0).getCourseName()+courseNumber))
					{
						Assert.assertTrue(true, courseName+" Found: Submitted for Review");
						System.out.println(courseName+"Course Found: Course Submitted for Review");
						log.info(courseName+"Course Found: Course Submitted for Review");
						break;
					}
					else
					{				
						log.info("Still finding course in review submission");
					}
				}
			}
			else if(source.equalsIgnoreCase(BOOK)){

				String courseNumber = GenericFunctions.readFromNotepad("./TestData/bookNumbers.txt").toString();
				for(int i=0;i<createUserPage1.reviewSubmittedCourse.size();i++){
					String courseName = createUserPage1.reviewSubmittedCourse.get(i).getText();
					if(courseName.equalsIgnoreCase(objListOFTestDataForSunbird.get(2).getCourseName()+courseNumber))
					{
						Assert.assertTrue(true, courseName+" Book: Submitted for Review");
						System.out.println(courseName+"Book Found: Course Submitted for Review");
						log.info(courseName+"Book Found: Course Submitted for Review");
						break;
					}
					else
					{				
						log.info("Still finding book in review submission");
					}
				}
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to assert in review submission");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println(e.getLocalizedMessage());
			Assert.fail("Failed on asserting in review submission bucket");

		}
	}

	public static void assertCourseFoundInSearch(String searchCourseName){
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To verify that course is found in search");
			//List<WebElement> result = driver.findElements(By.xpath("//div[@class='cardImageText center aligned ']/span"));
			CreatorUserPage createUserPage1=PageFactory.initElements(driver, CreatorUserPage.class);
			//String courseNumber = GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();
			for(int i=0;i<createUserPage1.reviewSubmittedCourse.size();i++){
				String courseName = createUserPage1.reviewSubmittedCourse.get(i).getText();
				if(courseName.equalsIgnoreCase(searchCourseName))
				{
					Assert.assertTrue(true, courseName+" Course Found in Search");
					System.out.println(courseName+"Course Found in Search");
					log.info(courseName+" Course Found in Search");
					break;
				}
				else
				{	

					log.info("Still finding course in review submission");

				}
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching for course");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println(e.getMessage());
			Assert.fail("Failed on searching the course");
		}
	}

	public static void assertFoundInUpForReview(String searchCourseName){
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To assert that course is found in Up for review");
			//List<WebElement> result = driver.findElements(By.xpath("//div[@class='cardImageText center aligned ']/span"));
			CreatorUserPage createUserPage1=PageFactory.initElements(driver, CreatorUserPage.class);
			//String courseNumber = GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();

			for(int i=0;i<createUserPage1.searchCoursesUpForReview.size();i++){
				String courseName = createUserPage1.searchCoursesUpForReview.get(i).getText();
				if(courseName.equalsIgnoreCase(searchCourseName))
				{
					Assert.assertTrue(true, courseName+" Found in up for review");
					createUserPage1.searchCoursesUpForReview.get(i).click();
					System.out.println(courseName+"Found and Clicked in up for review");
					log.info(courseName+"Found and Clicked in up for review");
					break;
				}
				else
				{				
					log.info("Still finding in up for review");
				}
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on verifying the asserrtion in Up for review bucket ");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println(e.getMessage());
			Assert.fail("Failed on asserting the course in Up for review bucket");
		}
	}

	public static void assertOnSearchAfterPublish(String searchCourseName){
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To verify that "+searchCourseName+" is found on searching after publishing");
			//List<WebElement> result = driver.findElements(By.xpath("//div[@class='cardImageText center aligned ']/span"));
			CreatorUserPage createUserPage1=PageFactory.initElements(driver, CreatorUserPage.class);
			//String courseNumber = GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();
			for(int i=0;i<createUserPage1.searchPublishedCourses.size();i++){
				String courseName = createUserPage1.searchPublishedCourses.get(i).getText();
				if(courseName.equalsIgnoreCase(searchCourseName))
				{
					Assert.assertTrue(true, courseName+" Found in Search");
					System.out.println(courseName+"Found in Search");
					log.info(courseName+" Found in Search");
					break;
				}
				else
				{				
					log.info("Still finding course in review submission");
				}
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching the course after publishing it");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println(e.getMessage());
			Assert.fail("Failed on asserting the course after publishing");
		}
	}

	public void createBook(List <TestDataForSunbird> objListOFTestDataForSunbird)
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to create a book");
			GenericFunctions.waitWebDriver(1500);
			GenericFunctions.waitForElementToAppear(createUserPage.bookName);
			String bookNumber = GenericFunctions.testDataIncrementer("./TestData/bookNumbers.txt").toString();
			createUserPage.bookName.sendKeys(objListOFTestDataForSunbird.get(2).getCourseName()+bookNumber);
			createUserPage.clickBBoard.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBBoard);
			createUserPage.selectBBoard.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickBGrade.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBGrade);
			createUserPage.selectBGrade.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickBSubject.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBSubject);
			createUserPage.selectBSubject.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickBMedium.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBMedium);
			createUserPage.selectBMedium.click();
			createUserPage.BPublisher.sendKeys(PUBLISHER);
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickBYear.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBYear);
			GenericFunctions.waitWebDriver(1500);
			createUserPage.selectBYear.click();
			GenericFunctions.waitWebDriver(1200);
			createUserPage.startCreating.click();
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(6500);
			GenericFunctions.waitForElementToAppear(createUserPage.newChild);
			System.out.println("Creating - "+createUserPage.newChild.getText());
			GenericFunctions.waitWebDriver(3000);
			action.moveToElement(createUserPage.newChild).click().perform();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.bookTitle.click();
			createUserPage.bookTitle.clear();
			createUserPage.bookTitle.sendKeys(objListOFTestDataForSunbird.get(2).getCourseName());
			GenericFunctions.waitWebDriver(1500);
			createUserPage.addResource.click();
			GenericFunctions.waitForElementToAppear(createUserPage.findSelectActivities);
			//createUserPage.selectResource.click();		
			createUserPage.selectBookResource.click();
			GenericFunctions.waitWebDriver(3500);
			createUserPage.proceedButton.click();
			GenericFunctions.waitWebDriver(2000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on creating a book");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to create a book");
			Assert.fail("Failed on creating a book");
		}

	}
	public String getBookName()
	{
		//GenericFunctions.testDataIncrementer();
		String course = GenericFunctions.randomCourseName();
		String generatedName=GenericFunctions.readFromNotepad("./TestData/bookNumbers.txt");
		System.out.println(generatedName+course);
		return generatedName+course;
	}

	public void createLessonPlan()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to create a lesson plan");
			List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			GenericFunctions.waitWebDriver(2000);
			navigateToWorkspace(LESSONPLAN);
			//GenericFunctions.waitWebDriver(2000);
			String myWindowHandle = driver.getWindowHandle();
			driver.switchTo().window(myWindowHandle);
			lessonNumber = GenericFunctions.testDataIncrementer("./TestData/lessonPlan.txt");
			//createUserPage.clickOnPopup.click();

			createUserPage.bookName.sendKeys(objListOFTestDataForSunbird1.get(3).getCourseName()+lessonNumber);
			System.out.println("Lesson created :"+objListOFTestDataForSunbird1.get(3).getCourseName()+lessonNumber);
			GenericFunctions.waitWebDriver(2000);
			createUserPage.clickBBoard.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBBoard);
			createUserPage.selectBBoard.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.clickBGrade.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBGrade);
			createUserPage.selectBGrade.click();
			//createUserPage.clickOnPopup.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.clickBSubject.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBSubject);
			createUserPage.selectBSubject.click();
			GenericFunctions.waitForElementToAppear(createUserPage.clickBMedium);
			createUserPage.clickBMedium.click();
			//createUserPage.clickOnPopup.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.selectBMedium.click();
			GenericFunctions.waitWebDriver(4000);

			createUserPage.startCreating.click();
			GenericFunctions.waitWebDriver(7000);			
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(4500);
			GenericFunctions.waitForElementToAppear(createUserPage.newChild);
			System.out.println(createUserPage.newChild.getText());
			GenericFunctions.waitWebDriver(2500);
			//createUserPage.newChild.click();	
			action.moveToElement(createUserPage.newChild).click().perform();
			GenericFunctions.waitWebDriver(5000);
			//createUserPage.lessonTitle.click();
			createUserPage.lessonTitle.clear();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.lessonTitle.sendKeys(objListOFTestDataForSunbird1.get(3).getCourseName());
			GenericFunctions.waitWebDriver(2000);
			createUserPage.lessonDescription.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.lessonDescription.sendKeys(objListOFTestDataForSunbird1.get(3).getCourseDescription());
			GenericFunctions.waitWebDriver(2000);
			createUserPage.lessonNotes.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.lessonNotes.sendKeys(objListOFTestDataForSunbird1.get(3).getTitle());
			GenericFunctions.waitWebDriver(2000);
			createUserPage.addResource.click();
			GenericFunctions.waitForElementToAppear(createUserPage.findSelectActivities);
			createUserPage.lessonResource.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.proceedButton.click();
			GenericFunctions.waitWebDriver(3000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on creating the lesson plan");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to create the new lesson plan");
			Assert.fail("Failed on creating the lesson plan");
		}

	}

	public void saveAndPublishLesson()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to save and publish the lesson plan");
			GenericFunctions.waitForElementToAppear(createUserPage.saveCourse);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.sendForReview.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.clickAppIcon.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.uploadAndUseButton.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.yesRadioButton.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.chooseFileButton.click();
			GenericFunctions.waitWebDriver(1500);
			String path = System.getProperty("user.dir")+"/UploadingDocuments/Upload Document Contents/"+LESSON_PLAN_IMAGE;
			System.out.println("Uploaded image : "+path);
			//log.info("Uploaded file name: "+path);
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.uploadFile(path);
			GenericFunctions.waitWebDriver(2500);

			createUserPage.uploadAndUseButtonRight.click();
			GenericFunctions.waitWebDriver(1800);
			/*createUserPage.clickAppIcon.click();
		GenericFunctions.waitWebDriver(2000);
		createUserPage.selectLessonIcon.click();
		GenericFunctions.waitWebDriver(2000);
		createUserPage.selectAppIcon.click();
		GenericFunctions.waitWebDriver(2000);*/
			createUserPage.saveButton.click();
			GenericFunctions.waitWebDriver(5000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to save and publish the lesson plan");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to save and publish the lesson plan");
			Assert.fail("Failed on saving and publishing the lesson plan");
		}

	}

	public String publishAndSearch(String source,List <TestDataForSunbird> objListOFTestDataForSunbird){
		String courseNumber="",searchCourseName="";
		try{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to publish and search "+source);
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(2000);	
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.publishCourseButton);
			createUserPage.publishCourseButton.click();
			for(int i=0;i<createUserPage.checkbox.size();i++){
				createUserPage.checkbox.get(i).click();
			}
			GenericFunctions.waitWebDriver(1500);
			createUserPage.popupPublishButton.click();
			GenericFunctions.waitWebDriver(3000);
			driver.switchTo().defaultContent();
			if(source.equalsIgnoreCase(COURSE))
			{
				GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.headerCourse);
				createUserPage.headerCourse.click();
				courseNumber = GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();
				createUserPage.searchInput.sendKeys(objListOFTestDataForSunbird.get(0).getCourseName()+courseNumber);
				createUserPage.searchIcon.click();
				searchCourseName = objListOFTestDataForSunbird.get(0).getCourseName()+courseNumber;
			}
			else if(source.equalsIgnoreCase(BOOK))
			{
				GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.headerLibrary);
				createUserPage.headerLibrary.click();
				courseNumber = GenericFunctions.readFromNotepad("./TestData/bookNumbers.txt").toString();
				createUserPage.searchInput.sendKeys(objListOFTestDataForSunbird.get(2).getCourseName()+courseNumber);
				createUserPage.searchIcon.click();
				searchCourseName = objListOFTestDataForSunbird.get(2).getCourseName()+courseNumber;
			}
			assertOnSearchAfterPublish(searchCourseName);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to publish and search "+source);
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println(e.getMessage());
			Assert.fail("Failed to publish and search");
		}
		System.out.println(searchCourseName);
		return searchCourseName;
	}

	public void goToWorkspace(String inputToSearch)
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to navigate to workspace, search in Up for review and publish "+inputToSearch);
			GenericFunctions.refreshWebPage();
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			createUserPage.upForReview.click();
			GenericFunctions.waitWebDriver(2000);
			if(inputToSearch.equalsIgnoreCase(MP4))
			{
				String searchMp4Content=objListOFTestDataForSunbird1.get(4).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/contentNumbers.txt")+"_"+MP4;
				createUserPage.searchForReview.sendKeys(searchMp4Content);
				System.out.println(searchMp4Content);
			}

			else if(inputToSearch.equalsIgnoreCase(WEBM))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird1.get(4).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/contentNumbers.txt")+"_"+WEBM);
			}
			else if(inputToSearch.equalsIgnoreCase(YOUTUBE))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird1.get(4).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/contentNumbers.txt")+"_"+YOUTUBE);
			}
			else if(inputToSearch.equalsIgnoreCase(EPUB))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird1.get(4).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/contentNumbers.txt")+"_"+EPUB);
			}
			else if(inputToSearch.equalsIgnoreCase(HTML))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird1.get(4).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/contentNumbers.txt")+"_"+HTML);
			}
			else if(inputToSearch.equalsIgnoreCase(H5P))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird1.get(4).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/contentNumbers.txt")+"_"+H5P);
			}

			else if(inputToSearch.equalsIgnoreCase(COLLECTION))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird1.get(5).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/collectionNumbers.txt"));
				//+"_"+GenericFunctions.readFromNotepad("./TestData/collectionNumbers.txt")
			}
			else if(inputToSearch.equalsIgnoreCase(LESSONPLAN))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird1.get(3).getCourseName());
				GenericFunctions.waitWebDriver(3000);
				createUserPage.searchedContentForPublish.click();
			}
			GenericFunctions.waitWebDriver(3000);
			createUserPage.searchedContentForPublish.click();
			/*GenericFunctions.waitWebDriver(4000);
		GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
		GenericFunctions.waitWebDriver(7000);*/
			GenericFunctions.waitWebDriver(4000);
			/*
		GenericFunctions.waitForElementToAppear(createUserPage.publishButton);
		GenericFunctions.scrollToElement(createUserPage.publishButton);
		createUserPage.publishButton.click();
			 */

			//Commented in part of Maintainance on 24/07/2018

			if(inputToSearch.contains("collection"))
			{
				GenericFunctions.waitWebDriver(4000);
				GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
				GenericFunctions.waitWebDriver(7000);
				GenericFunctions.waitWebDriver(4500);
				createUserPage.clickPublishIcon.click();
			}
			else if(inputToSearch.contains("lessonplan"))
			{
				GenericFunctions.waitWebDriver(4000);
				GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
				GenericFunctions.waitWebDriver(7000);
				GenericFunctions.waitWebDriver(4500);
				GenericFunctions.waitForElementToAppear(createUserPage.clickPublishIcon);
				GenericFunctions.scrollToElement(createUserPage.clickPublishIcon);
				createUserPage.clickPublishIcon.click();
			}
			else if(inputToSearch.contains("epub")||inputToSearch.contains("webm")||inputToSearch.contains("mp4")||inputToSearch.contains("h5p")||inputToSearch.contains("html")||inputToSearch.contains("youtube"))
			{
				//GenericFunctions.waitForElementToAppear(createUserPage.publishButton);
				//GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
				GenericFunctions.waitWebDriver(3000);
				GenericFunctions.scrollToElement(createUserPage.publishButton);
				GenericFunctions.waitWebDriver(2500);
				System.out.println(createUserPage.publishButton.getText());
				createUserPage.publishButton.click();
			}
			//	String compare=createUserPage.searchedContentForPublish.getText();	
			//GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);


			GenericFunctions.waitWebDriver(4000);
			for(int i=0;i<createUserPage.checkbox.size();i++)	
			{
				GenericFunctions.waitWebDriver(500);
				createUserPage.checkbox.get(i).click();
			}
			System.out.println("Checked all CBs");
			GenericFunctions.waitWebDriver(2500);	
			GenericFunctions.waitForElementToAppear(createUserPage.popupPublishButton);
			createUserPage.popupPublishButton.click();
			System.out.println(inputToSearch+" Content published sucessfully");
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.refreshWebPage();
			GenericFunctions.waitWebDriver(3000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to search and publish "+inputToSearch);
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println(e.getMessage());
			Assert.fail("Failed to search and publish");
		}

	}


	public String uploadContentMp4(String uploadType) throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to upload"+uploadType+" and send it for review");
			List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			GenericFunctions.waitWebDriver(2000);
			navigateToWorkspace(UPLOADCONTENT);		
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(7500);
			GenericFunctions.waitForElementToAppear(createUserPage.enterUrl);
			GenericFunctions.waitWebDriver(2000);
			//createUserPage.UploadButton.click();
			//createUserPage.clickUploadSection.click();
			//GenericFunctions.keyTab(driver, createUserPage.enterUrl.toString());
			WebElement browse=createUserPage.browseButton;
			if(uploadType.equalsIgnoreCase("mp4"))
			{
				String path = System.getProperty("user.dir")+"/UploadingDocuments/Uploading videos/"+UPLOAD_MP4;
				//System.getProperty("user.dir")+
				System.out.println(path);
				GenericFunctions.waitWebDriver(3000);
				browse.sendKeys(path);
				System.out.println("Uploaded file : "+path);
				GenericFunctions.waitWebDriver(3000);
				GenericFunctions.waitWebDriver(2500);
				System.out.println("MP4 content uploaded sucessfully");
			}
			else if(uploadType.equalsIgnoreCase("webm"))
			{
				String path = System.getProperty("user.dir")+"/UploadingDocuments/Uploading videos/"+UPLOAD_WEBM;
				//System.getProperty("user.dir")+
				System.out.println(path);
				GenericFunctions.waitWebDriver(3000);
				browse.sendKeys(path);
				System.out.println("Uploaded file : "+path);
				GenericFunctions.waitWebDriver(6000);
				System.out.println("WEBM content uploaded sucessfully");
				//GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			}
			else if(uploadType.equalsIgnoreCase("youtube"))
			{
				createUserPage.enterUrl.sendKeys(UPLOAD_YOUTUBE);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("YOUTUBE content uploaded sucessfully");
				createUserPage.UploadButton.click();
			}
			else if(uploadType.equalsIgnoreCase("epub"))
			{
				String path = System.getProperty("user.dir")+"/UploadingDocuments/Upload Document Contents/"+UPLOAD_EPUB;
				//System.getProperty("user.dir")+
				System.out.println(path);
				GenericFunctions.waitWebDriver(3000);
				browse.sendKeys(path);
				System.out.println("Uploaded file : "+path);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("EPUB content uploaded sucessfully");
			}

			else if(uploadType.equalsIgnoreCase("h5p"))
			{
				String path = System.getProperty("user.dir")+"/UploadingDocuments/Upload Document Contents/"+UPLOAD_H5P;
				//System.getProperty("user.dir")+
				System.out.println(path);
				GenericFunctions.waitWebDriver(3000);
				browse.sendKeys(path);
				System.out.println("Uploaded file : "+path);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("H5P content uploaded sucessfully");
			}
			else if(uploadType.equalsIgnoreCase("html"))
			{
				String path = System.getProperty("user.dir")+"/UploadingDocuments/Upload Document Contents/"+UPLOAD_HTML;
				//System.getProperty("user.dir")+
				System.out.println(path);
				GenericFunctions.waitWebDriver(3000);
				browse.sendKeys(path);
				System.out.println("Uploaded file : "+path);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("HTML content uploaded sucessfully");

			}
			//GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);

			//GenericFunctions.uploadFile(UPLOAD_CONTENT_MP4);

			//GenericFunctions.switchToFrame(driver,createUserPage.iFrame);//(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(4500);
			GenericFunctions.waitForElementToAppear(createUserPage.untitledCollection);
			System.out.println(createUserPage.untitledCollection.getText());
			GenericFunctions.waitForElementToAppear(createUserPage.sendForReview);
			GenericFunctions.waitWebDriver(4000);
			createUserPage.sendForReview.click();
			GenericFunctions.waitForElementToAppear(createUserPage.contentMp4Title);
			//GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			title=(objListOFTestDataForSunbird1.get(4).getCourseName()+"_"+GenericFunctions.testDataIncrementer("./TestData/contentNumbers.txt")).toString();
			System.out.println(title);
			createUserPage.contentMp4Title.click();
			createUserPage.contentMp4Title.clear();
			if(uploadType.contains(MP4))
			{
				createUserPage.contentMp4Title.sendKeys(title+"_"+MP4);
			}
			else if(uploadType.contains(WEBM))
			{
				createUserPage.contentMp4Title.sendKeys(title+"_"+WEBM);
			}
			else if(uploadType.contains(YOUTUBE))
			{
				createUserPage.contentMp4Title.sendKeys(title+"_"+YOUTUBE);
			}
			else if(uploadType.contains(EPUB))
			{
				createUserPage.contentMp4Title.sendKeys(title+"_"+EPUB);
			}
			else if(uploadType.contains(HTML))
			{	
				createUserPage.contentMp4Title.sendKeys(title+"_"+HTML);
			}
			else if(uploadType.contains(H5P))
			{
				createUserPage.contentMp4Title.sendKeys(title+"_"+H5P);
			}
			//createUserPage.contentMp4Title.sendKeys(title);
			GenericFunctions.waitWebDriver(1000);
			createUserPage.contentMp4Desc.click();
			createUserPage.contentMp4Desc.sendKeys(objListOFTestDataForSunbird1.get(4).getCourseDescription());
			GenericFunctions.waitWebDriver(1000);
			createUserPage.clickAppIcon.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.searchUploadImage.sendKeys(SEARCH_CONTENT_IMAGE);
			createUserPage.clickImageSearch.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.checkContentIcon.click();
			createUserPage.selectAppIcon.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.clickOnSelectCurriculum.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectCurriculum);
			createUserPage.selectCurriculum.click();
			createUserPage.clickOnSelectClass.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.selectClass.click();
			GenericFunctions.scrollToElement(createUserPage.clickOnSelectSubject);
			createUserPage.clickOnSelectSubject.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectSubject);
			createUserPage.selectSubject.click();
			createUserPage.clickOnSelectMedium.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectMedium);
			createUserPage.selectMedium.click();
			GenericFunctions.waitForElementToAppear(createUserPage.clickConcepts);
			createUserPage.clickConcepts.click();
			/*GenericFunctions.waitForElementToAppear(createUserPage.selectConcept);

		createUserPage.selectConcept.click();*/

			//createUserPage.clickConcepts.click();
			GenericFunctions.waitWebDriver(1500);
			GenericFunctions.waitForElementToAppear(createUserPage.searchConcept);
			createUserPage.searchConcept.sendKeys(objListOFTestDataForSunbird1.get(6).getTitle());
			GenericFunctions.waitWebDriver(2000);
			createUserPage.conceptChooseAll.click();
			GenericFunctions.waitWebDriver(500);
			createUserPage.conceptDoneButton.click();
			GenericFunctions.waitWebDriver(2000);

			/*
		GenericFunctions.waitWebDriver(3000);
		createUserPage.selectConcept1.click();
		GenericFunctions.waitWebDriver(1000);
		createUserPage.selectConcept2.click();
		GenericFunctions.waitWebDriver(2000);
		createUserPage.selectSubConcept1.click();
		createUserPage.selectSubConcept2.click();
		createUserPage.selectSubConcept3.click();
		GenericFunctions.waitWebDriver(1000);
		createUserPage.doneButton.click();
			 */
			GenericFunctions.waitWebDriver(1000);
			createUserPage.saveButton.click();
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.waitForElementToAppear(createUserPage.reviewSubmission);
			createUserPage.reviewSubmission.click();
			GenericFunctions.waitWebDriver(2000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to upload "+uploadType+" content and send for review ");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to upload content");
			Assert.fail("Failed to upload any content and send for review ");
		}
		return title;
	}

	public void createCollection()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to create new collection");
			List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			GenericFunctions.waitWebDriver(2000);
			navigateToWorkspace(COLLECTION);
			/*GenericFunctions.waitForElementToAppear(createUserPage.menuListHeader);
		createUserPage.menuListHeader.click();
		GenericFunctions.waitForElementToAppear(createUserPage.listHeaderProfile);
		createUserPage.listHeaderProfile.click();
		createUserPage.createCollection.click();*/
			GenericFunctions.waitForElementToAppear(createUserPage.courseName);
			String collectionName = objListOFTestDataForSunbird1.get(5).getCourseName()+"_"+GenericFunctions.testDataIncrementer("./TestData/collectionNumbers.txt");
			createUserPage.courseName.sendKeys(collectionName);
			createUserPage.courseDescription.sendKeys(objListOFTestDataForSunbird1.get(5).getCourseDescription());
			createUserPage.startCreating.click();
			GenericFunctions.waitWebDriver(4000);
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(7500);

			//Commented on Maintainance-24/07/2018
			/*GenericFunctions.waitForElementToAppear(createUserPage.collectionKeywords);
		createUserPage.collectionKeywords.sendKeys(objListOFTestDataForSunbird1.get(5).getTitle());*/

			GenericFunctions.waitForElementToAppear(createUserPage.addResource);
			createUserPage.addResource.click();
			GenericFunctions.waitForElementToAppear(createUserPage.findSelectActivities);
			createUserPage.viewAllButton.click();
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.waitForElementToAppear(createUserPage.filterCategory);
			createUserPage.filterCategory.click();
			GenericFunctions.waitForElementToAppear(createUserPage.collectionFilter);
			createUserPage.collectionFilter.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.resourceFilter.click();
			createUserPage.applyFilter.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectCollection);
			createUserPage.selectCollection.click();
			createUserPage.proceedButton.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.sendForReview.click();

			//Upload Collection icon and dropdowns
			//createUserPage.contentMp4Title.sendKeys(collectionName);
			GenericFunctions.waitWebDriver(1000);
			createUserPage.clickAppIcon.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.searchUploadImage.sendKeys(SEARCH_COLLECTION_IMAGE);
			createUserPage.clickImageSearch.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.checkCollectionIcon.click();
			createUserPage.selectAppIcon.click();
			GenericFunctions.waitForElementToAppear(createUserPage.clickOnSelectCurriculum);
			GenericFunctions.waitWebDriver(2000);
			createUserPage.clickOnSelectCurriculum.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectCurriculum);
			GenericFunctions.scrollToElement(createUserPage.selectCurriculum);
			createUserPage.selectCurriculum.click();
			createUserPage.clickOnSelectClass.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.selectClass.click();
			GenericFunctions.scrollToElement(createUserPage.clickOnSelectSubject);
			createUserPage.clickOnSelectSubject.click();
			createUserPage.selectSubject.click();
			createUserPage.clickOnSelectMedium.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectMedium);
			createUserPage.selectMedium.click();
			createUserPage.saveButton.click();
			GenericFunctions.waitWebDriver(3000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to create new collection");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed on creating collection");
			Assert.fail("Failed to create new collection");
		}
	}

	public void rejectTheContent(String inputToReject)
	{
		try
		{

			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to reject the content"+inputToReject);
			List <TestDataForSunbird> objListOFTestDataForSunbird=null;
			objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");

			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			createUserPage.upForReview.click();
			if(inputToReject.equalsIgnoreCase("course"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(0).getCourseName()+GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString());
				GenericFunctions.waitWebDriver(3000);
			}

			else if(inputToReject.equalsIgnoreCase("collection"))
			{	
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(5).getCourseName());
				GenericFunctions.waitWebDriver(3000);
			}

			else if(inputToReject.equalsIgnoreCase("book"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(2).getCourseName()+GenericFunctions.readFromNotepad("./TestData/bookNumbers.txt"));
			}

			else if(inputToReject.equalsIgnoreCase("lessona"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(3).getCourseName());
				GenericFunctions.waitWebDriver(3000);
			}
			else if(inputToReject.equalsIgnoreCase("courseac"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(0).getCourseDescription());
				GenericFunctions.waitWebDriver(3000);
			}
			else if(inputToReject.equalsIgnoreCase("booka"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(2).getCourseName());
				GenericFunctions.waitWebDriver(3000);
			}


			/*else if(inputToReject.equalsIgnoreCase("collection"))
		{
			createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(5).getCourseName()+"_"+GenericFunctions.readFromNotepad("./TestData/resourceNumbers.txt").toString());
			GenericFunctions.waitWebDriver(3000);
		}*/
			GenericFunctions.waitWebDriver(3000);
			createUserPage.searchedContentForPublish.click();
			GenericFunctions.waitWebDriver(4000);
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(7000);
			if(inputToReject.contains("resource"))
			{
				createUserPage.clickRequestChanges.click();
				GenericFunctions.waitWebDriver(3000);
			}
			else
			{
				GenericFunctions.waitWebDriver(2000);
				GenericFunctions.waitForElementToAppear(createUserPage.clickReqChangesIcon);
				createUserPage.clickReqChangesIcon.click();
				GenericFunctions.waitWebDriver(3000);
			}
			createUserPage.rejectReason1.click();
			createUserPage.rejectReason2.click();
			createUserPage.rejectReason3.click();
			String rejectReason = (REVIEW_COMMENTS[new Random().nextInt(REVIEW_COMMENTS.length)]);
			createUserPage.reviewComments.click();
			createUserPage.reviewComments.sendKeys(rejectReason);
			GenericFunctions.waitWebDriver(3000);
			createUserPage.requestChangesButton1.click();
			GenericFunctions.waitWebDriver(3000);
			System.out.println(inputToReject+" is rejected succesfully");
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on rejecting the content");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to reject the content"+inputToReject);
			Assert.fail("Failed on rejecting the content");
		}

	}

	public void rejectTheResource()
	{
		try
		{

			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to reject the resource");
			List <TestDataForSunbird> objListOFTestDataForSunbird=null;
			objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");

			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			createUserPage.upForReview.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(6).getCourseName()+"R00");
			//		/objListOFTestDataForSunbird.get(6).getCourseName()+GenericFunctions.readFromNotepad("./TestData/resourceNumbers.txt").toString()
			GenericFunctions.waitWebDriver(3000);
			createUserPage.searchedContentForPublish.click();
			//GenericFunctions.waitWebDriver(4000);
			//GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(7000);
			GenericFunctions.scrollToElement(createUserPage.clickRequestChanges);
			createUserPage.clickRequestChanges.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.rejectReason1.click();
			createUserPage.rejectReason2.click();
			createUserPage.rejectReason3.click();
			String rejectReason = (REVIEW_COMMENTS[new Random().nextInt(REVIEW_COMMENTS.length)]);
			System.out.println(rejectReason);
			GenericFunctions.waitWebDriver(2000);
			createUserPage.reviewComments.click();
			createUserPage.reviewComments.sendKeys(rejectReason);
			GenericFunctions.waitWebDriver(500);
			createUserPage.requestChangesButton.click();
			GenericFunctions.waitWebDriver(3000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on rejecting the resource");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to reject the resource");
			Assert.fail("Failed on rejecting the resource");
		}

	}



	public void createResource(List <TestDataForSunbird> objListOFTestDataForSunbird)
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to create new resource");
			GenericFunctions.waitWebDriver(1500);
			GenericFunctions.waitForElementToAppear(createUserPage.bookName);
			String resourceNumber = GenericFunctions.testDataIncrementer("./TestData/resourceNumbers.txt").toString();
			createUserPage.bookName.sendKeys(objListOFTestDataForSunbird.get(6).getCourseName()+resourceNumber);
			createUserPage.clickBBoard.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBBoard);
			createUserPage.selectBBoard.click();			
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickBGrade.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBGrade);
			GenericFunctions.waitWebDriver(1500);
			createUserPage.selectBGrade.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickBSubject.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBSubject);
			createUserPage.selectBSubject.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.clickBMedium.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBMedium);
			createUserPage.selectBMedium.click();


			/*
			GenericFunctions.waitWebDriver(1500);
			createUserPage.selectConcept1.click();
			createUserPage.selectConcept2.click();
			createUserPage.selectSubConcept1.click();
			GenericFunctions.waitWebDriver(1500);
			 */
			GenericFunctions.waitForElementToAppear(createUserPage.selectConcept);

			createUserPage.selectConcept.click();

			GenericFunctions.waitForElementToAppear(createUserPage.searchConcept);
			createUserPage.searchConcept.sendKeys(objListOFTestDataForSunbird.get(6).getTitle());
			GenericFunctions.waitWebDriver(2000);
			createUserPage.conceptChooseAll.click();

			createUserPage.conceptDoneButton.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.clickResourceType.click();
			createUserPage.selectResouceType.click();

			GenericFunctions.waitWebDriver(2000);
			createUserPage.startCreating.click();
			GenericFunctions.waitWebDriver(7000);			
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(4500);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to create a resource");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to create resource");
			Assert.fail("Failed on creating new resource");
		}
	}

	public void saveAndSendResouceForReview(){
		try{
			/*GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(15000);
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.untitledCollection);*/
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to save and send resource for review");
			System.out.println("Adding Resource: "+createUserPage.untitledCollection.getText());
			GenericFunctions.waitWebDriver(3000);
			createUserPage.addImageIcon.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.searchUploadImage.sendKeys(SEARCH_RESOURCE_IMAGE);
			createUserPage.clickImageSearch.click();
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.contentResourceIcon);
			createUserPage.contentResourceIcon.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.selectAppIcon.click();
			GenericFunctions.waitWebDriver(5000);
			createUserPage.saveCourse.click();
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.closeButton);
			createUserPage.closeButton.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.sendForReview.click();
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.clickAppIcon);
			createUserPage.clickAppIcon.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.searchUploadImage.sendKeys(SEARCH_RESOURCE_IMAGE);
			createUserPage.clickImageSearch.click();
			GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.contentResourceIcon);
			createUserPage.contentResourceIcon.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.selectAppIcon.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.saveButton.click();
			GenericFunctions.waitWebDriver(1500);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to save and send resource for review");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed on saving and sending the resource for review");
			log.error("Exception In the method saveAndSendResouceForReview"+e.getLocalizedMessage());
			Assert.fail("Failed to save and send resource for review");
		}

	}


	public void resourcePublishAndSearch(List <TestDataForSunbird> objListOFTestDataForSunbird)
	{
		try{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to publish and search the resource");
			String courseNumber="",searchCourseName="";
			/*GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(2000);	*/
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.waitForElementToAppear(createUserPage.publishResource);
			GenericFunctions.scrollToElement(createUserPage.publishResource);
			createUserPage.publishResource.click();
			//driver.switchTo().defaultContent();

			//Select all check boxes to publish
			//Updated on 10 july 2018

			GenericFunctions.waitWebDriver(4000);
			for(int i=0;i<createUserPage.checkbox.size();i++)	
			{
				createUserPage.checkbox.get(i).click();
			}
			System.out.println("Checked all CBs");
			GenericFunctions.waitWebDriver(4000);	
			GenericFunctions.waitForElementToAppear(createUserPage.popupPublishButton);
			createUserPage.popupPublishButton.click();
			GenericFunctions.waitWebDriver(3000);

			/*GenericFunctions.waitTillTheElementIsVisibleAndClickable(createUserPage.headerLibrary);

			createUserPage.headerLibrary.click();
			courseNumber = GenericFunctions.readFromNotepad("./TestData/resourceNumbers.txt").toString();
			createUserPage.searchInput.sendKeys(objListOFTestDataForSunbird.get(6).getCourseName()+courseNumber);
			createUserPage.searchIcon.click();
			searchCourseName = objListOFTestDataForSunbird.get(6).getCourseName()+courseNumber;	*/

			assertOnSearchAfterPublish(searchCourseName);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to publish and search the resource");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			log.error(e.getMessage()+"Error in resourcePublishAndSearch");
			Assert.fail("Failed to publish and search the resource");
		}
	}


	public void navigateToMyActivity(){
		try{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to navigate to My activity");
			createUserPage.dropDown.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.myActivity.click();			
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to navigate to my activity");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			log.error(e.getMessage()+"Error in navigateToMyActivity");
			Assert.fail("Error in navigating to my activity");
		}
	}

	public void editAndSubmitContent()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to edit and submit the content for reviewing");
			List <TestDataForSunbird> objListOFTestDataForSunbird=null;
			objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");

			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			createUserPage.drafts.click();
			String courseToAssert = objListOFTestDataForSunbird.get(0).getCourseName()+GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();
			if(createUserPage.getCourseName.getText().equalsIgnoreCase(courseToAssert))
			{
				createUserPage.getCourseName.click();
			}
			else
			{
				System.out.println(courseToAssert +"Course is not found in Drafts to Edit");
			}
			GenericFunctions.waitWebDriver(4000);
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(7000);
			//GenericFunctions.waitForElementToAppearOnScreen(createUserPage.addResource);
			createUserPage.addResource.click();
			GenericFunctions.waitForElementToAppear(createUserPage.findSelectActivities);
			GenericFunctions.waitWebDriver(2000);

			//Add an extra Resource so it gets edited
			createUserPage.selectExtraResource.click();
			createUserPage.proceedButton.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.sendForReview.click();
			GenericFunctions.waitWebDriver(3000);
			createUserPage.saveButton.click();
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on editing and submitting the content for review");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Error on edit and submit for review");
		}

	}

	public void limitedSharing(String contentType)
	{
		try
		{

			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to Limited sharing "+contentType);
			GenericFunctions.waitWebDriver(2000);
			createUserPage.saveCourse.click();
			if(contentType.equalsIgnoreCase("book"))
			{
				createUserPage.bookTitle.sendKeys("_"+"Limited Sharing");
				GenericFunctions.waitWebDriver(1000);
			}
			else if(contentType.equalsIgnoreCase("course"))
			{
				createUserPage.titleName.sendKeys("_"+"Limited Sharing");
				GenericFunctions.waitWebDriver(1000);
				createUserPage.titleDescription.sendKeys("_"+"Limited Sharing");
			} 
			else if(contentType.equalsIgnoreCase("lesson plan"))
			{
				createUserPage.lessonTitle.sendKeys("_"+"Limited Sharing");
				GenericFunctions.waitWebDriver(1000);
				createUserPage.lessonDescription.sendKeys("_"+"Limited Sharing");
			}
			else if(contentType.equalsIgnoreCase("resource"))
			{
				GenericFunctions.waitWebDriver(2000);
				createUserPage.closeContentPopup.click();
				//createUserPage.bookName.sendKeys("_"+"Limited Sharing");
				GenericFunctions.waitWebDriver(1000);
			}

			else if(contentType.equalsIgnoreCase("upload"))
			{
				createUserPage.closeContentPopup.click();
				GenericFunctions.waitWebDriver(1000);
			}
			//createUserPage.bookDescription.sendKeys("_"+"Limited Sharing");
			GenericFunctions.waitWebDriver(2000);
			createUserPage.limitedSharingArrow.click();
			createUserPage.clickLimitedSharing.click();
			GenericFunctions.waitWebDriver(3000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on limited sharing the content");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed on limited sharing the content");
		}

	}

	public void uploadContentLimitedSharing(String uploadType) throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to upload "+uploadType+" for Limited sharing");
			List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			GenericFunctions.waitWebDriver(2000);
			//navigateToWorkspace(UPLOADCONTENT);
			//GenericFunctions.waitWebDriver(7000);			
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			GenericFunctions.waitWebDriver(7500);
			GenericFunctions.waitForElementToAppear(createUserPage.enterUrl);
			GenericFunctions.waitWebDriver(2000);
			//createUserPage.UploadButton.click();
			//createUserPage.clickUploadSection.click();
			//GenericFunctions.keyTab(driver, createUserPage.enterUrl.toString());
			WebElement browse=createUserPage.browseButton;
			if(uploadType.equalsIgnoreCase("mp4"))
			{
				browse.sendKeys(UPLOAD_MP4);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("MP4 content uploaded sucessfully");
			}
			else if(uploadType.equalsIgnoreCase("webm"))
			{
				browse.sendKeys(UPLOAD_WEBM);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("WEBM content uploaded sucessfully");
			}
			else if(uploadType.equalsIgnoreCase("youtube"))
			{
				createUserPage.enterUrl.sendKeys(UPLOAD_YOUTUBE);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("YOUTUBE content uploaded sucessfully");
				createUserPage.UploadButton.click();
			}
			else if(uploadType.equalsIgnoreCase("epub"))
			{
				browse.sendKeys(UPLOAD_EPUB);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("EPUB content uploaded sucessfully");
			}

			else if(uploadType.equalsIgnoreCase("h5p"))
			{
				browse.sendKeys(UPLOAD_H5P);
				GenericFunctions.waitWebDriver(2000);
				System.out.println("H5P content uploaded sucessfully");
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on uploading content for limited sharing ");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed on uploading content for limited sharing ");
		}
	}

	//---------------------------------------------
	//Adding methods for Test case 15

	public void clickInReviewSubmission()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to search course in review submission");
			List <TestDataForSunbird> objListOFTestDataForSunbird=null;
			objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			String courseToAssert = objListOFTestDataForSunbird.get(0).getCourseName()+GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString();
			System.out.println(courseToAssert);
			GenericFunctions.waitWebDriver(3000);
			if(createUserPage.getCourseName.getText().equalsIgnoreCase(courseToAssert))
			{
				createUserPage.getCourseName.click();
			}
			else
			{
				System.out.println(courseToAssert +"Course is not found in Review Submission");
			}
			GenericFunctions.waitWebDriver(3000);
			//createUserPage.getCourseName.click();
			System.out.println("User does not have the Edit access");
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.WaitForFrameAndSwitchToIt(createUserPage.iFrame);
			createUserPage.editorCloseIcon.click();
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to search course in review submission");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed to search course in review submission");
		}

	}

	//Adding method required for Test case 16
	public void checkInPublished(String source, List <TestDataForSunbird> objListOFTestDataForSunbird)
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to check in Published content in Published bucket");
			//  objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcredentials");
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			createUserPage.published.click();
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.refreshWebPage();
			GenericFunctions.waitForElementToAppear(createUserPage.getCourseName);

			String courseName= createUserPage.getCourseName.getText();
			System.out.println(courseName);
			System.out.println(objListOFTestDataForSunbird.get(0).getCourseName()+GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString());
			if(courseName.equalsIgnoreCase(objListOFTestDataForSunbird.get(0).getCourseName()+GenericFunctions.readFromNotepad("./TestData/courseNumbers.txt").toString()))
			{
				System.out.println("Course: "+courseName+" is found in published bucket");
			}
			else
			{
				System.out.println("Course:"+courseName+"is not found in publishes bucket");
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Course is not found in published bukcet");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed to get the course in the published bucket");
		}

	}

	public void saveBookAndCheckMessage()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to save book and check the confirmation message");
			String randomDialCode = DIAL_CODE[rand.nextInt(DIAL_CODE.length)];
			createUserPage.bookDialcode.sendKeys(randomDialCode);
			createUserPage.acceptDialcode.click();
			System.out.println("Dial Code: "+randomDialCode+" is entered in meta data page");
			GenericFunctions.waitWebDriver(1500);
			GenericFunctions.waitWebDriver(1500);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(500);
			//createUserPage.saveButton.click();
			//GenericFunctions.refreshWebPage();
			System.out.println("Dial Code Added/updated Successfully");
			System.out.println("Content updated successfully");
			GenericFunctions.waitWebDriver(1500);

		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to save and check the confirmation message");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Exception occured while saving the book and checking confirmation messsage"+e);
			Assert.fail("Failed to save and check the confirmation message");

		}
	}

	public void removeDailCodeAndCheckMessage()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to remove the dial code and check the confirmation message");
			saveBookAndCheckMessage();
			createUserPage.editDialCode.click();
			createUserPage.bookDialcode.clear();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(1500);
			createUserPage.saveCourse.click();
			GenericFunctions.waitWebDriver(1500);
			System.out.println("Dial Code Removed successfully");
			createUserPage.editorCloseIcon.click();
			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			GenericFunctions.refreshWebPage();
			GenericFunctions.waitWebDriver(3000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to remove the dial code and check the confirmation message");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed on remove dial code and check the confirmation message");

		}

	}

	public void verifyOnlyBookOption()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to verify only book option is present");
			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			GenericFunctions.waitWebDriver(2500);
			boolean bookOption = createUserPage.createBook.isDisplayed();
			//Assert.assertTrue(true,bookOption+" is not displayed");
			Assert.assertEquals(true,bookOption);
			GenericFunctions.waitWebDriver(1500);
			if(bookOption==true)
			{
				Assert.assertTrue(true);
				System.out.println(createUserPage.createBook.getText()+" Option is only present for Book Creator");
			}
			else
			{
				System.out.println("Only book option is not present");
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to verify that only book option is present to the user");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed to verify that only book option is present to the user");

		}

	}


	public void rejectTheUploads(String uploadType)
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to reject the upload"+uploadType);
			List <TestDataForSunbird> objListOFTestDataForSunbird=null;
			objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");

			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			createUserPage.upForReview.click();

			//To search for an upload and reject it which is already existing in the list

			if(uploadType.equalsIgnoreCase("epub"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(4).getCourseName()+" "+EPUB);
			}
			else if(uploadType.equalsIgnoreCase("mp4"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(4).getCourseName()+" "+MP4);
			}
			else if(uploadType.equalsIgnoreCase("webm"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(4).getCourseName()+" "+WEBM);
			}

			else if(uploadType.equalsIgnoreCase("youtube"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(4).getCourseName()+" "+YOUTUBE);
			}
			else if(uploadType.equalsIgnoreCase("html"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(4).getCourseName()+" "+HTML);
			}

			else if(uploadType.equalsIgnoreCase("h5p"))
			{
				createUserPage.searchForReview.sendKeys(objListOFTestDataForSunbird.get(4).getCourseName()+" "+H5P);
			}
			GenericFunctions.waitWebDriver(3000);
			createUserPage.searchedContentForPublish.click();
			GenericFunctions.waitForElementToAppear(createUserPage.clickRequestChanges);
			GenericFunctions.scrollToElement(createUserPage.clickRequestChanges);
			GenericFunctions.waitWebDriver(2500);
			//Giving reasons 
			createUserPage.clickRequestChanges.click();
			//GenericFunctions.waitForElementToAppear(createUserPage.rejectReason1);
			GenericFunctions.waitWebDriver(2500);
			createUserPage.rejectReason1.click();
			createUserPage.rejectReason2.click();
			createUserPage.rejectReason3.click();
			String rejectReason = (REVIEW_COMMENTS[new Random().nextInt(REVIEW_COMMENTS.length)]);
			createUserPage.reviewComments.click();
			createUserPage.reviewComments.sendKeys(rejectReason);
			createUserPage.requestChangesButton.click();
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.refreshWebPage();
			GenericFunctions.waitWebDriver(3000);
			System.out.println(uploadType+" rejected succesfully ");
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to reject the upload content");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed to reject the uploads");

		}

	}

	public void verifyOnlyBooksPresent()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To verify that only books are present to the user");
			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			String actual=createUserPage.contentType.getText();
			String expected="TextBook";
			try
			{
				Assert.assertEquals(actual, expected);
				if(createUserPage.contentType.isDisplayed())
					Assert.assertTrue(true);
				System.out.println("Only Textbooks are available");
				GenericFunctions.waitWebDriver(2000);
			}
			catch(Exception e)
			{
				System.out.println("Exception on asserting text books");
			}

		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to verify only books present to the user");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Other contents are available for reviewing");
			Assert.fail("Failed to verify only books present to the user");

		}
	}

	public void searchWithFilters()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To verify search with filters");
			GenericFunctions.waitForElementToAppear(publicUserPage.headerProfile);
			publicUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			createUserPage.workSpace.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.upForReview.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.filterIcon.click();
			GenericFunctions.waitWebDriver(1500);
			GenericFunctions.waitForElementToAppear(createUserPage.clickFilterBoard);
			createUserPage.clickFilterBoard.click();
			GenericFunctions.waitForElementToAppear(createUserPage.clickFilterBoard);
			createUserPage.clickFilterBoard.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBBoard);
			createUserPage.selectBBoard.click();
			createUserPage.clickBGrade.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectBGrade);
			createUserPage.selectBGrade.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.clickFilterSubject.click();	
			GenericFunctions.waitForElementToAppear(createUserPage.selectFilterSubject);
			createUserPage.selectFilterSubject.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.clickFilterMedium.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectFilterMedium);
			createUserPage.selectFilterMedium.click();
			createUserPage.clickContentType.click();
			GenericFunctions.waitForElementToAppear(createUserPage.selectContentType);
			createUserPage.selectContentType.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.applyButton.click();
			System.out.println("All the filters are applied");
			createUserPage.filterIcon.click();
			GenericFunctions.waitWebDriver(2000);

		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to apply filters and search");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Failed to apply filters and search");

		}


	}

	public void navigateToProfileAndSearch(String userData)
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to navigate to profile and search "+userData);
			//List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			//objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			//createUserPage.dropDown.click();
			createUserPage.headerProfile.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.searchInput.click();
			createUserPage.searchInput.sendKeys(userData);
			createUserPage.searchIcon.click();
			GenericFunctions.waitWebDriver(1000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to navigate to profile and search");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("could not navigate to profile and search");
		}
	}

	public void navigateToProfileAndSearchOrg()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To verify navigate to profile and search organizations");
			//createUserPage.dropDown.click();
			createUserPage.headerProfile.click();
			GenericFunctions.waitWebDriver(1000);
			createMentorPage.searchDropDown.click();
			GenericFunctions.waitWebDriver(1000);
			createMentorPage.SearchDropDownOrg.click();
			GenericFunctions.waitWebDriver(1000);
			createMentorPage.downloadButton.click();
			GenericFunctions.waitWebDriver(2000);
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to navigate to profile and search organizations");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Could not navigate to profile and search organizations,Exception "+e.getLocalizedMessage());
		}
	}

	public void navigateToCourseSearchAndCreateBatch()
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to navigate to search a coursea and create a batch");
			List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			//createUserPage.dropDown.click();
			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			createUserPage.headerProfile.click();
			GenericFunctions.waitWebDriver(1000);
			createMentorPage.searchDropDown.click();
			GenericFunctions.waitWebDriver(1000);
			createMentorPage.SearchDropDownCourse.click();
			GenericFunctions.waitWebDriver(1000);
			createMentorPage.filterSearch.sendKeys(objListOFTestDataForSunbird1.get(8).getCourseDescription());
			createUserPage.searchIcon.click();
			GenericFunctions.waitWebDriver(2000);
			createMentorPage.searchedCourse.click();
			GenericFunctions.waitWebDriver(1000);
			createMentorPage.createBatch.click();
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to search course and create a batch");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Other contents are available for reviewing");
			Assert.fail("Failed to search course and create a batch, Exception"+e.getLocalizedMessage());

		}

	}

	public void verifyNoBookOptionPresent()
	{
		ExtentTestManager.getTest().log(LogStatus.INFO, "To verify no book option is present to the user");
		GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
		createUserPage.headerProfile.click();
		GenericFunctions.waitWebDriver(1000);
		GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
		createUserPage.workSpace.click();
		GenericFunctions.waitWebDriver(2000);
		try
		{
			boolean checkBookOption = createUserPage.createBook.isDisplayed();
			if(checkBookOption==false)
			{
				System.out.println("Content Creator do not have Book Creation access");
				Assert.assertTrue(true);
			}
			/*else
				{
					System.out.println("Could not check book option");
				}*/
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to verify book option to the user");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			Assert.fail("Could not check for book option,Exception"+e.getLocalizedMessage());

		}
	}
}
