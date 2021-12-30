package com.beymen.pages;

import com.beymen.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class Pages extends TestBase {
    @FindBy(xpath = "//a[@class='o-header__userInfo--item bwi-account-o -customer']")
    WebElement hesabim;

    @FindBy(xpath = "//a[@class='o-header__userInfo--item bwi-favorite-o -favorite']")
    WebElement favorilerim;
    @FindBy(xpath = "//a[@class='o-header__userInfo--item bwi-cart-o -cart']")
    WebElement sepetim;
    @FindBy(xpath = "//input[@class='default-input o-header__search--input']")
    WebElement arama;
    @FindBy(xpath = "//button[@class='o-productMoreContent__btn btn']")
    WebElement dahaFazlaGoster;
    @FindBy(xpath = "//div[@class='col-sm-4 col-md-4 col-lg-4 o-productList__itemWrapper']")
    List<WebElement> productCardList;
    @FindBy(xpath = "//div[@class='m-variation']")
    WebElement productSizeElementParent;
    @FindBy(id = "addBasket")
    WebElement sepeteEkle;
    @FindBy(id = "priceNew")
    WebElement priceNew;
    @FindBy(xpath = "//span[@class='m-productPrice__salePrice']")
    WebElement sepetFiyati;
    @FindBy(id = "quantitySelect0")
    WebElement adetSec;
    @FindBy(xpath = "//option[@value='2']")
    WebElement secilenAdet;
    @FindBy(id = "removeCartItemBtn0")
    WebElement silButton;
    @FindBy(xpath = "//strong[@class='m-empty__messageTitle']")
    WebElement sepetBos;

    public static String aramaText = "pantolan";

    public Pages() {
        PageFactory.initElements(driver, this);
    }

    public boolean isHesabimExist() {
        wait.until(ExpectedConditions.visibilityOfAllElements(hesabim));
        return hesabim.isDisplayed();
    }
    public boolean isFavorilerimExist() {
        wait.until(ExpectedConditions.visibilityOfAllElements(favorilerim));
        return favorilerim.isDisplayed();
    }
    public boolean isSepetimExist() {
        wait.until(ExpectedConditions.visibilityOfAllElements(sepetim));
        return sepetim.isDisplayed();
    }
    //
    public boolean aramaSendKey() {
        wait.until(ExpectedConditions.visibilityOfAllElements(arama));
        arama.sendKeys(aramaText);
        arama.sendKeys(Keys.ENTER);
        return true;
    }
    public void scrollToEndPage(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickDahaFazlaGoster() {
        wait.until(ExpectedConditions.visibilityOfAllElements(dahaFazlaGoster));
        dahaFazlaGoster.click();
    }

    public void randomClickProduct(){
        Integer count = productCardList.size();
        Random rand = new Random();
        int int_random = rand.nextInt(count);
        WebElement element =productCardList.get(int_random);
        element.click();
    }

    public void selectSize() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productSizeElementParent));
        List<WebElement> elementList =productSizeElementParent.findElements(By.tagName("span"));
        for (WebElement we:elementList) {
            if(we.isEnabled()){
                we.click();
                //return;
            }
        }

    }

    public boolean fiyatKontrol() {
        wait.until(ExpectedConditions.visibilityOfAllElements(sepeteEkle));
        String priceString= priceNew.getText();
        priceString = priceString.replace("TL","");
        priceString = priceString.replace(",",".");
        priceString = priceString.replace(".","");
        Double urunSayfasiFiyat= Double.parseDouble( priceString);
        sepeteEkle.click();
        sepetim.click();
        String sepetFiyatiString =sepetFiyati.getText();
        sepetFiyatiString = sepetFiyatiString.replace("TL","");
        sepetFiyatiString = sepetFiyatiString.replace(",",".");
        sepetFiyatiString = sepetFiyatiString.replace(".","");
        Double urunSepetiFiyati= Double.parseDouble( sepetFiyatiString);

        if(!urunSayfasiFiyat.equals(urunSepetiFiyati)){
            return false;
        }
        wait.until(ExpectedConditions.visibilityOfAllElements(adetSec));
        adetSec.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(secilenAdet));
        secilenAdet.click();
        Select select = new Select(adetSec);
        WebElement element = select.getFirstSelectedOption();
        if(!element.getText().equals(secilenAdet.getText())){
            return false;
        }
        return true;
    }

    public boolean sepetKontrol() {
        wait.until(ExpectedConditions.visibilityOfAllElements(silButton));
        silButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(sepetBos));
        return sepetBos.isDisplayed();

    }
}
