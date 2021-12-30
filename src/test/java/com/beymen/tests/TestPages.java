package com.beymen.tests;

import com.beymen.base.TestBase;
import com.beymen.pages.Pages;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPages extends TestBase {
    Pages pages;
    public TestPages() {
        super();
    }

    @BeforeClass
    public void setUp() {
        initialization();
        pages =new Pages();

    }
    @Test(priority = 1)
    public void validHesabim() {
        boolean hesabimExist = pages.isHesabimExist();
        Assert.assertTrue(hesabimExist);
    }
    @Test(priority = 2)
    public void validFavorilerim() {
        boolean favorilerimExist = pages.isFavorilerimExist();
        Assert.assertTrue(favorilerimExist);
    }
    @Test(priority = 3)
    public void validSepetim() {
        boolean sepetimExist = pages.isSepetimExist();
        Assert.assertTrue(sepetimExist);
    }

    @Test(priority = 4)
    public void sendKeyArama() {
        pages.aramaSendKey();
    }

    @Test(priority = 5)
    public void clickDahaFazlaGoster() {
        pages.scrollToEndPage();
        pages.clickDahaFazlaGoster();
    }
    @Test(priority = 5)
    public void randomProductSelect() {
        pages.randomClickProduct();
    }

    @Test(priority = 6)
    public void selectSize() {
        pages.selectSize();
    }

    @Test(priority = 7)
    public void fiyatKontrol() {
        Assert.assertTrue(pages.fiyatKontrol());
    }

    @Test(priority = 8)
    public void sepetKontrol() {
        Assert.assertTrue(pages.sepetKontrol());
    }

    @AfterClass
    public void tearDown() {
        System.out.println("tearDown Page");
        //driver.quit();
    }
}
