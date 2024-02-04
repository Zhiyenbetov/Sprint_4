package pageobject;

import config.SeleniumConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FrequentQuestionsPageObject {

    private final WebDriver driver;

    // Локатор для вопросов на странице
    private final By frequentQuestionsLocator = By.className("accordion__button");

    // Локатор контейнера вопроса и ответа
    private final By questionElementContainerLocator = By.xpath("./../..");

    // Локатор ответа на вопрос внутри контейнера
    private final By answerElementLocator = By.xpath(".//div[contains(@class, 'accordion__panel')]");

    public FrequentQuestionsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getAnswerFor(String question) {
        List<WebElement> questionElements = driver.findElements(frequentQuestionsLocator);

        for (WebElement questionElement : questionElements) {

            if (questionElement.getText().contains(question)) {

                // Скролл до вопроса. Дальше нужно еще прокрутить на 200-300 пикселей, чтобы баннер про куки не перехватил клик.
                Actions scrollToQuestion = new Actions(driver);
                scrollToQuestion.scrollToElement(questionElement);
                scrollToQuestion.scrollByAmount(0, 300);
                scrollToQuestion.build().perform();

                questionElement.click();
                WebElement questionElementContainer = questionElement.findElement(questionElementContainerLocator);
                WebElement answerElement = questionElementContainer.findElement(answerElementLocator);

                new WebDriverWait(driver, Duration.ofSeconds(SeleniumConstants.MAX_WEB_DRIVER_WAIT_SECONDS))
                        .until(ExpectedConditions.visibilityOf(answerElement));

                return answerElement.getText();
            }

        }

        throw new IllegalArgumentException("Не найден вопрос на странице! Вопрос: " + question);
    }

}
