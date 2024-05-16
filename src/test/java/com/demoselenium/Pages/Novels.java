package com.demoselenium.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Novels {
    @FindBy(css = ".o-tile-list > li")
    public List<WebElement> novels;

    @FindBy(css = "ul[class='clearfix'] .next")
    public WebElement next;

    public Novels(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
