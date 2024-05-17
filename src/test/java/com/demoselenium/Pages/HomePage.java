package com.demoselenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    // logout user
    @FindBy(css = ".logout-nav-area li[class='btn-sign-in']")
    public WebElement signIn;

    // login user
    @FindBy(css = ".login-nav-area li > a[data-action-label='My Library']")
    public WebElement myLibrary;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
