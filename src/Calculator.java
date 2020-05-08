import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Calculator {
    String imagesPath = ("C:\\Users\\MAXIM\\IdeaProjects\\MiniProject\\Screenshots");
    private static ExtentReports extent;
    private static ExtentTest maximTests;
    private static AndroidDriver<MobileElement> driver;

    @Rule
    public TestName name = new TestName();

    //run once before test
    @BeforeClass
    public static void setUp() throws IOException {
        extent = new ExtentReports("C:\\Users\\MAXIM\\IdeaProjects\\MiniProject\\testReports\\TestReport.html");
        extent.loadConfig(new File("C:\\Users\\MAXIM\\IdeaProjects\\MiniProject\\reportConfig.xml"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Device");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        capabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
        capabilities.setCapability("newCommandTimeout", 120);
        //ip server
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"), capabilities);

        //waiting function
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    //starting test 01
    @Test
    public void Test01_Plus() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO ,"start test ");
        Thread.sleep(2000);
        //stringing the two numbers from xml
        String number = readFromFile("number");
        String number2 = readFromFile("number2");
        //convert strings to int
        int a = Integer.parseInt(number);
        int b = Integer.parseInt(number2);
        int intPlusAB = a + b;
        int num = a;
        int num2 = b;
        int reversed = 0;
        int digit = 0;
        int count = 0;
        int count2 = 0;
        int temp;
        int n1;
        //Checking if the first number is equals to zero
        if(num==0)
            driver.findElement(By.id("bt_00")).click();
        else {
          //if the number is not equals to zero

            while (num != 0) {      //code that make reversed number
                digit = num % 10;
                reversed = reversed * 10 + digit;
                num /= 10;
                count++;
            }
        }
        //checkig if last digit of number is zero
        if (count == 2 && a % 10 == 0) {
            int n3 = a / 10;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = a % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        } else if (count == 3 && a % 10 == 0 && (a / 10) % 10 == 0) {
            int n3 = a / 100;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = a % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        } else if (count == 4 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0) {
            int n3 = a / 1000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 100) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = a % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        } else if (count == 5 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0 && (a / 1000) % 10 == 0) {
            int n3 = a / 10000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 1000) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (a / 100) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            int n7 = a % 10;
            driver.findElement(By.id("bt_0" + n7)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        } else {
            //taking the reversed number and getting to the last digit if the number and every time its dividing by 10 to cut the number
            temp = reversed;
            n1 = 0;
            while (temp != 0) {
                n1 = temp % 10;
                temp /= 10;
                driver.findElement(By.id("bt_0" + n1)).click();
            }
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        }
        reversed = 0;
        digit = 0;
        //Checking if the second number is equals to zero
        if(num2==0)
            driver.findElement(By.id("bt_00")).click();
            //if the second number is not equals to zero
        else {
            while (num2 != 0) { //code that make reversed of second number
                digit = num2 % 10;
                reversed = reversed * 10 + digit;
                num2 /= 10;
                count2++;
            }
        }
        //checkig if last digit of second number is zero
        if (count2 == 2 && a % 10 == 0) {
            int n3 = b / 10;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = b % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
        } else if (count2 == 3 && b % 10 == 0 && (b / 10) % 10 == 0) {
            int n3 = b / 100;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = b % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
        } else if (count2 == 4 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0) {
            int n3 = b / 1000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 100) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = b % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
        } else if (count2 == 5 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0 && (b / 1000) % 10 == 0) {
            int n3 = b / 10000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 1000) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (b / 100) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            int n7 = b % 10;
            driver.findElement(By.id("bt_0" + n7)).click();

        } else {
        //taking the reversed of second number and getting to the last digit if the number and every time its dividing by 10 to cut the number
            temp = reversed;
            n1 = 0;
            while (temp != 0) {
                n1 = temp % 10;
                temp /= 10;
                driver.findElement(By.id("bt_0" + n1)).click();
            }
        }
        //Check if the result is equals after Essay two numbers
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal")).click();
        String text = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).getText();
        String replaceString = text.replaceAll(",", "");
        String stringValueOfAPlusB = String.valueOf(intPlusAB);
        try {
            Assert.assertEquals(stringValueOfAPlusB, replaceString);
            maximTests.log(LogStatus.PASS, "The Result is equals   ");
            maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));

        } catch (AssertionError e) {
            maximTests.log(LogStatus.FAIL, "The Result is not equals   ");
        }
        Thread.sleep(2000);
        //close caculator
        driver.closeApp();
        maximTests.log(LogStatus.INFO,"end of test " + name.getMethodName());
    }
    //end of test 01

    //start of test 02
    @Test
    public void Test02_Minus() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO ,"start test ");

        //open calculator again
        driver.launchApp();
        //stringing the two numbers from xml
        String number = readFromFile("number");
        String number2 = readFromFile("number2");
        //convert strings to int
        int a = Integer.parseInt(number);
        int b = Integer.parseInt(number2);
        int minusAB = a - b;
        int num = a;
        int num2 = b;
        int reversed = 0;
        int digit = 0;
        int count = 0;
        int count2 = 0;
        int temp;
        int n1;
        //Checking if the first number is equals to zero
        if(num==0) {
            driver.findElement(By.id("bt_00")).click();

        }
        //if the number is not equals to zero
        else {
            while (num != 0) {  //code that make reversed number
                digit = num % 10;
                reversed = reversed * 10 + digit;
                num /= 10;
                count++;
            }
        }
        //checkig if last digit of number is zero
        if (count == 2 && a % 10 == 0) {
            int n3 = a / 10;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = a % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_sub")).click();
        } else if (count == 3 && a % 10 == 0 && (a / 10) % 10 == 0) {
            int n3 = a / 100;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = a % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_sub")).click();
        } else if (count == 4 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0) {
            int n3 = a / 1000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 100) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = a % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_sub")).click();
        } else if (count == 5 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0 && (a / 1000) % 10 == 0) {
            int n3 = a / 10000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 1000) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (a / 100) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            int n7 = a % 10;
            driver.findElement(By.id("bt_0" + n7)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_sub")).click();
        } else {
        //taking the reversed number and getting to the last digit if the number and every time its dividing by 10 to cut the number
            temp = reversed;
            n1 = 0;
            while (temp != 0) {
                n1 = temp % 10;
                temp /= 10;
                driver.findElement(By.id("bt_0" + n1)).click();
            }
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_sub")).click();
        }
        reversed = 0;
        digit = 0;
        //Checking if the second number is equals to zero
        if(num2==0) {
            driver.findElement(By.id("bt_00")).click();

        }
        //if the second number is not equals to zero
        else {

            while (num2 != 0) { //code that make reversed of second number
                digit = num2 % 10;
                reversed = reversed * 10 + digit;
                num2 /= 10;
                count2++;
            }
        }
        //checkig if last digit of  second number is zero
        if (count2 == 2 && b % 10 == 0) {
            int n3 = b / 10;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = b % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
        } else if (count2 == 3 && b % 10 == 0 && (b / 10) % 10 == 0) {
            int n3 = b / 100;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = b % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
        } else if (count2 == 4 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0) {
            int n3 = b / 1000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 100) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = b % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
        } else if (count2 == 5 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0 && (b / 1000) % 10 == 0) {
            int n3 = b / 10000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 1000) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (b / 100) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            int n7 = b % 10;
            driver.findElement(By.id("bt_0" + n7)).click();

        } else {
            //taking the reversed of second number and getting to the last digit if the number and every time its dividing by 10 to cut the number
            temp = reversed;
            n1 = 0;
            while (temp != 0) {
                n1 = temp % 10;
                temp /= 10;
                driver.findElement(By.id("bt_0" + n1)).click();
            }
        }
        //Check if the result is equals after Subtraction two numbers
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal")).click();
        String text = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).getText();
        String replaceString = text.replaceAll(",", "");
        String stringValueOfAMinusB = String.valueOf(minusAB);
        try {
            Assert.assertEquals(stringValueOfAMinusB, replaceString);
            maximTests.log(LogStatus.PASS, "The Result is equals   ");
            maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }
        catch (AssertionError e) {
            maximTests.log(LogStatus.FAIL, "The Result is not equals   ");
        }
        Thread.sleep(2000);
        //close calculator
        driver.closeApp();
        maximTests.log(LogStatus.INFO,"end of test " + name.getMethodName());
    }
    //end of test 02

    //start of test 03
    @Test
    public void Test03_Mul() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO ,"start test ");
        //open calculator again
        driver.launchApp();
        //stringing the two numbers from xml
        String number = readFromFile("number");
        String number2 = readFromFile("number2");
        //convert strings to int
        int a = Integer.parseInt(number);
        int b = Integer.parseInt(number2);
        int MulAB = a * b;
        int num = a;
        int num2 = b;
        int reversed = 0;
        int digit = 0;
        int count = 0;
        int count2 = 0;
        int temp;
        int n1;
        //Checking if the number is equals to zero
        if(num==0)
            driver.findElement(By.id("bt_00")).click();
            //if the  number is not equals to zero
        else {
            while (num != 0) { //code that make reversed of number
                digit = num % 10;
                reversed = reversed * 10 + digit;
                num /= 10;
                count++;
            }
        }
        //checkig if last digit of   number is zero
        if (count == 2 && a % 10 == 0) {
            int n3 = a / 10;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = a % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_mul")).click();
        } else if (count == 3 && a % 10 == 0 && (a / 10) % 10 == 0) {
            int n3 = a / 100;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = a % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_mul")).click();
        } else if (count == 4 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0) {
            int n3 = a / 1000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 100) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = a % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_mul")).click();
        } else if (count == 5 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0 && (a / 1000) % 10 == 0) {
            int n3 = a / 10000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (a / 1000) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (a / 100) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = (a / 10) % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            int n7 = a % 10;
            driver.findElement(By.id("bt_0" + n7)).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_mul")).click();
        } else {
        //taking the reversed number and getting to the last digit if the number and every time its dividing by 10 to cut the number
            temp = reversed;
            n1 = 0;
            while (temp != 0) {
                n1 = temp % 10;
                temp /= 10;
                driver.findElement(By.id("bt_0" + n1)).click();
            }
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_mul")).click();
        }
        reversed = 0;
        digit = 0;
        //Checking if the second number is equals to zero
        if(num2==0)
            driver.findElement(By.id("bt_00")).click();
            //if the second number is not equals to zero
        else {
            while (num2 != 0) { //code that make reversed of second number
                digit = num2 % 10;
                reversed = reversed * 10 + digit;
                num2 /= 10;
                count2++;
            }
        }
        //checkig if last digit of second  number is zero
        if (count2 == 2 && b % 10 == 0) {
            int n3 = b / 10;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = b % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
        } else if (count2 == 3 && b % 10 == 0 && (b / 10) % 10 == 0) {
            int n3 = b / 100;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = b % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
        } else if (count2 == 4 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0) {
            int n3 = b / 1000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 100) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = b % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
        } else if (count2 == 5 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0 && (b / 1000) % 10 == 0) {
            int n3 = b / 10000;
            driver.findElement(By.id("bt_0" + n3)).click();
            int n4 = (b / 1000) % 10;
            driver.findElement(By.id("bt_0" + n4)).click();
            int n5 = (b / 100) % 10;
            driver.findElement(By.id("bt_0" + n5)).click();
            int n6 = (b / 10) % 10;
            driver.findElement(By.id("bt_0" + n6)).click();
            int n7 = b % 10;
            driver.findElement(By.id("bt_0" + n7)).click();

        } else {
            //taking the reversed of second number and getting to the last digit if the number and every time its dividing by 10 to cut the number
            temp = reversed;
            n1 = 0;
            while (temp != 0) {
                n1 = temp % 10;
                temp /= 10;
                driver.findElement(By.id("bt_0" + n1)).click();
            }
        }
        //Check if the result is equals after multiplication of two numbers
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal")).click();
        String text = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).getText();
        String replaceString = text.replaceAll(",", "");
        String stringValueOfAMulB = String.valueOf(MulAB);
        try {
            Assert.assertEquals(stringValueOfAMulB, replaceString);
            maximTests.log(LogStatus.PASS, "The Result is equals   ");
            maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        } catch (AssertionError e) {
            maximTests.log(LogStatus.FAIL, "The Result is not equals   ");
        }
        Thread.sleep(2000);
        //close calculator
        driver.closeApp();
        maximTests.log(LogStatus.INFO,"end of test " + name.getMethodName());
    }
    //end of test 03

    //start of test 04
    @Test
    public void Test04_Div() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO ,"start test ");
        //open calculator again
        driver.launchApp();
        //stringing the two numbers from xml
        String number = readFromFile("number");
        String number2= readFromFile("number2");
        //convert strings to int
        int a = Integer.parseInt(number);
        int b = Integer.parseInt(number2);
        int divAB = a / b;
        int num = a;
        int num2 = b;
        int reversed = 0;
        int digit = 0;
        int count = 0;
        int count2 = 0;
        int temp;
        int n1;
        //Checking if the  number is equals to zero
        if(num==0)
            driver.findElement(By.id("bt_00")).click();
            //if the second number is not equals to zero
          else {
            while (num != 0) { //code that make reversed of  number
                digit = num % 10;
                reversed = reversed * 10 + digit;
                num /= 10;
                count++;
            }
        }
        //checkig if last digit of  number is zero
            if (count == 2 && a % 10 == 0) {
                int n3 = a / 10;
                driver.findElement(By.id("bt_0" + n3)).click();
                int n4 = a % 10;
                driver.findElement(By.id("bt_0" + n4)).click();
                driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_div")).click();
            } else if (count == 3 && a % 10 == 0 && (a / 10) % 10 == 0) {
                int n3 = a / 100;
                driver.findElement(By.id("bt_0" + n3)).click();
                int n4 = (a / 10) % 10;
                driver.findElement(By.id("bt_0" + n4)).click();
                int n5 = a % 10;
                driver.findElement(By.id("bt_0" + n5)).click();
                driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_div")).click();
            } else if (count == 4 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0) {
                int n3 = a / 1000;
                driver.findElement(By.id("bt_0" + n3)).click();
                int n4 = (a / 100) % 10;
                driver.findElement(By.id("bt_0" + n4)).click();
                int n5 = (a / 10) % 10;
                driver.findElement(By.id("bt_0" + n5)).click();
                int n6 = a % 10;
                driver.findElement(By.id("bt_0" + n6)).click();
                driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_div")).click();
            } else if (count == 5 && a % 10 == 0 && (a / 10) % 10 == 0 && (a / 100) % 10 == 0 && (a / 1000) % 10 == 0) {
                int n3 = a / 10000;
                driver.findElement(By.id("bt_0" + n3)).click();
                int n4 = (a / 1000) % 10;
                driver.findElement(By.id("bt_0" + n4)).click();
                int n5 = (a / 100) % 10;
                driver.findElement(By.id("bt_0" + n5)).click();
                int n6 = (a / 10) % 10;
                driver.findElement(By.id("bt_0" + n6)).click();
                int n7 = a % 10;
                driver.findElement(By.id("bt_0" + n7)).click();
                driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_div")).click();
            } else {
        //taking the reversed o number and getting to the last digit if the number and every time its dividing by 10 to cut the number
                temp = reversed;
                n1 = 0;
                while (temp != 0) {
                    n1 = temp % 10;
                    temp /= 10;
                    driver.findElement(By.id("bt_0" + n1)).click();
                }
                driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_div")).click();
            }
            reversed = 0;
            digit = 0;
            while (num2 != 0) { //code that make reversed of second number
                digit = num2 % 10;
                reversed = reversed * 10 + digit;
                num2 /= 10;
                count2++;
            }
            //Check if you get error message after dividing by zero
            if (b == 0) {
                throw new ArithmeticException("Division by zero!");
            } else {
          //checkig if last digit of second number is zero
                if (count2 == 2 && b % 10 == 0) {
                    int n3 = b / 10;
                    driver.findElement(By.id("bt_0" + n3)).click();
                    int n4 = b % 10;
                    driver.findElement(By.id("bt_0" + n4)).click();
                } else if (count2 == 3 && b % 10 == 0 && (b / 10) % 10 == 0) {
                    int n3 = b / 100;
                    driver.findElement(By.id("bt_0" + n3)).click();
                    int n4 = (b / 10) % 10;
                    driver.findElement(By.id("bt_0" + n4)).click();
                    int n5 = b % 10;
                    driver.findElement(By.id("bt_0" + n5)).click();
                } else if (count2 == 4 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0) {
                    int n3 = b / 1000;
                    driver.findElement(By.id("bt_0" + n3)).click();
                    int n4 = (b / 100) % 10;
                    driver.findElement(By.id("bt_0" + n4)).click();
                    int n5 = (b / 10) % 10;
                    driver.findElement(By.id("bt_0" + n5)).click();
                    int n6 = b % 10;
                    driver.findElement(By.id("bt_0" + n6)).click();
                } else if (count2 == 5 && b % 10 == 0 && (b / 10) % 10 == 0 && (b / 100) % 10 == 0 && (b / 1000) % 10 == 0) {
                    int n3 = b / 10000;
                    driver.findElement(By.id("bt_0" + n3)).click();
                    int n4 = (b / 1000) % 10;
                    driver.findElement(By.id("bt_0" + n4)).click();
                    int n5 = (b / 100) % 10;
                    driver.findElement(By.id("bt_0" + n5)).click();
                    int n6 = (b / 10) % 10;
                    driver.findElement(By.id("bt_0" + n6)).click();
                    int n7 = b % 10;
                    driver.findElement(By.id("bt_0" + n7)).click();

                } else {
                    //taking the reversed o number and getting to the last digit if the second number and every time its dividing by 10 to cut the number
                    temp = reversed;
                    n1 = 0;
                    while (temp != 0) {
                        n1 = temp % 10;
                        temp /= 10;
                        driver.findElement(By.id("bt_0" + n1)).click();
                    }
                }
                //Check if the result is equals after dividing two numbers
                driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal")).click();
                String text = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).getText();
                String replaceString = text.replaceAll(",", "");
                String stringValueOfADivB = String.valueOf(divAB);
                try {
                    Assert.assertEquals(stringValueOfADivB, replaceString);
                    maximTests.log(LogStatus.PASS, "The Result is equals   ");
                    maximTests.log(LogStatus.INFO, "details", maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
                } catch (AssertionError e) {
                    maximTests.log(LogStatus.FAIL, "The Result is not equals   ");
                }
                maximTests.log(LogStatus.INFO, "end of test " + name.getMethodName());

            }
    }
    //end of test 04
    @After
    public void afterTest() {
        extent.endTest(maximTests);
    }
            //close app
            @AfterClass
            public static void close () throws InterruptedException {
                Thread.sleep(2000);
                driver.closeApp();
                extent.flush();

            }

    public static String takeScreenShot (String imagesPath){

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(imagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imagesPath + ".png";
    }
    public static String readFromFile (String keyData) throws Exception {
        File xmlFile = new File("C:\\Users\\MAXIM\\IdeaProjects\\MiniProject//configFile.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyData).item(0).getTextContent();
    }
}
