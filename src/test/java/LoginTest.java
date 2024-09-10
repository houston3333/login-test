import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class LoginTest extends BaseTest {

    @Test
    public static void test1() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/div")).isDisplayed());
    }

    @Test
    public static void test2() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public static void test3() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    @Test
    public static void test4() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public static void test5() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public static void test6() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("locked_out_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public static void test7() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("problem_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        List<WebElement> images = driver.findElements(By.tagName("img"));
        
        boolean result  = true;
        for (WebElement image : images) {
            result = Objects.equals(image.getAttribute("naturalWidth"), "0");
            if (!result) {
                break;
            } 
        }
        Assert.assertFalse(result);
    }

    @Test
    public static void test8() {
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("performance_glitch_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        long startTime = System.currentTimeMillis();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"inventory_filter_container\"]/div")));
        long endTime = System.currentTimeMillis();
        boolean result = endTime - startTime < 5;
        Assert.assertFalse(result);
    }
}
