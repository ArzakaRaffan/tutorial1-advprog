package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest{
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    //Made a product with functional test and check if its already in the list table of products
    @Test
    void testCreateProductFunctional(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        WebElement listButton = driver.findElement(By.id("createProductButton"));
        listButton.click();

        WebElement productNameTextField = driver.findElement(By.id("nameInput"));
        productNameTextField.clear();
        String newProductName = "Sampo Cap Bambang";
        productNameTextField.sendKeys(newProductName);

        WebElement productQuantityTextField = driver.findElement(By.id("quantityInput"));
        productQuantityTextField.clear();
        String newProductQuantity = "100";
        productQuantityTextField.sendKeys(newProductQuantity);

        WebElement submitProductButton = driver.findElement(By.id("submitProductButton"));
        submitProductButton.click();

        Thread.sleep(200);
        List<WebElement> productName = driver.findElements(By.xpath("//td[contains(text(), 'Sampo Cap Bambang')]"));
        List<WebElement> productQuantity = driver.findElements(By.xpath("//td[contains(text(), 100)]"));

        assertEquals("Sampo Cap Bambang", productName.getFirst().getText());
        assertEquals("100", productQuantity.getFirst().getText());
    }
}