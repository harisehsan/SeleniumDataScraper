package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends TestBase {
    @FindBy(id = "nav-link-accountList") public WebElement signInbtn;
    @FindBy (id = "ap_email") public WebElement emailtxt;
    @FindBy (id = "continue") public WebElement continuebtn;
    @FindBy (id = "ap_password") public WebElement passwordtxt;
    @FindBy (id = "signInSubmit") public WebElement signInSubmit;

    public By signInbtnBy = By.id("nav-link-accountList");
    public By emailtxtBy = By.id("ap_email");
    public By passwordtxtBy = By.id("ap_password");


    public LoginPage() throws FileNotFoundException {
        PageFactory.initElements(driver, this);
    }

    private void goToShirtsCatalogPage()
    {
        driver.navigate().to("https://www.amazon.co.uk/Best-Sellers-Clothing-Mens-Tops-Shirts/zgbs/clothing/1731025031/ref=zg_bs_nav_ap_2_1730929031");
    }

    public void signInToAmazon() throws InterruptedException {
        goToShirtsCatalogPage();
        waitUntilClickable(signInbtnBy);
        signInbtn.click();
        waitUntilPresentOfElementBy(emailtxtBy);
        emailtxt.sendKeys("harisehsan213@gmail.com");
        continuebtn.click();
        waitUntilPresentOfElementBy(passwordtxtBy);
        passwordtxt.sendKeys("Harry@5791");
        signInSubmit.click();
        Thread.sleep(15000);
    }
}
