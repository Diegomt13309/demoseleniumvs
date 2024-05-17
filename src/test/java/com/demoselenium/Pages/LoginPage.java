package com.demoselenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css = "#mailAddress")
    public WebElement email;

    @FindBy(css = "#password")
    public WebElement password;

    @FindBy(css = "#recaptchaLoginBtn")
    public WebElement signInButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
