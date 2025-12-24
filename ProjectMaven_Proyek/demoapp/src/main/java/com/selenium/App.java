package com.selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {
    // deklarasikan dulu
    public static WebDriver driver;
    public static WebDriverWait wait;
    
    // buka browser
    public static void startDriver() { // saat pertama kali jalan panggil ini
        if(driver == null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(); // siapin chromedriver
            driver.manage().window().maximize(); // kalau di run langsung maximize
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // di set 10 detik
        }
        else{
            System.out.println("driber Sudah Ada");
        }
    }

    // tutup browser
    public static void quitDriver() {
        if(driver != null){
            driver.quit();
        }
    }

    public static void jedah(Integer second) { // pemberi jedah
        try {
            Thread.sleep(second * 1000);
            // System.out.println("\n Jedah Selesai \n");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void refreshToMainPage(){
        String parent = null;
        // ambil parent window (yang pertama)
        for (String window : driver.getWindowHandles()) {
            parent = window;
            break;
        }
        // tutup semua tab kecuali parent
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        // balik ke parent
        driver.switchTo().window(parent);
        // load ulang homepage
        driver.get("https://phptravels.com/");
    }

    public static void closeTabIni(){
        App.driver.close(); // tutup tab saat ini
        String parentWindow = App.driver.getWindowHandle(); // kembali ke window awal
        App.driver.switchTo().window(parentWindow);
    }

}
