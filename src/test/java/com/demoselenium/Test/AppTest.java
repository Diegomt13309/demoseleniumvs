package com.demoselenium.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoselenium.Clases.Novel;
import com.demoselenium.Pages.HomePage;
import com.demoselenium.Pages.LoginPage;
import com.demoselenium.Pages.Novels;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private WebDriver driver;
    private Novels novelPage;
    private HomePage homePage;
    private LoginPage loginPage;
    public List<Novel> novelList = new ArrayList<>();
    public static int count = 0;

    /**
     * Rigorous Test :-)
     * 
     * @throws InterruptedException
     */
    @BeforeMethod
    public void seleniumDemoFirst() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1));
        driver.get("https://global.bookwalker.jp/categories/3/");
        novelPage = new Novels(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

    }

    @Test(enabled = false)
    public void getAllNovels() {
        boolean flag = true;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gdpr-pop button"))).click();
        do {
            for (WebElement element : novelPage.novels) {
                String name = element.findElement(By.tagName("h2")).getText();
                String price = element.findElement(By.className("a-tile-price")).getText();
                String quantity;
                List<WebElement> quantityElements = element.findElements(By.className("a-tile-series-number"));
                if (quantityElements.isEmpty()) {
                    quantity = "1 Book";
                } else {
                    quantity = quantityElements.get(0).getText();
                }
                novelList.add(new Novel(name, price, quantity));
            }
            try {
                novelPage.next.click();
            } catch (NoSuchElementException e) {
                flag = false;
            }
        } while (flag);
        novelList.forEach(e -> {
            System.out.println("Name: " + e.getName() + "\n" + "Price: " + e.getPrice() + "\n" + "Quantity: "
                    + e.getQuantity() + "\n");
        });
        this.exportExcel(novelList);
    }

    @Test()
    public void LoginIntoPage() throws InterruptedException {
        homePage.signIn.click();
        loginPage.email.sendKeys("mafivij897@huleos.com");
        loginPage.password.sendKeys("123456789!");
        loginPage.signInButton.click();
        // fix this
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".login-nav-area li > a[data-action-label='My Library']")))
                .click();
        assertEquals(homePage.myLibrary.getText(), "My Library");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void exportExcel(List<Novel> novels) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Novels");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 6000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 20);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Price");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Quantity");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        novels.stream().forEach(e -> {
            Row row = sheet.createRow(++count);
            Cell cell = row.createCell(0);
            cell.setCellValue(e.getName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(e.getPrice());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(e.getQuantity());
            cell.setCellStyle(style);
        });

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "NovelsTest.xlsx";

        try {
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            System.out.println("Cannont Create");
        }

    }
}
