package com.selenium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class scenario3 {
    
    String email,password,firstname,lastname,wanumber;

    public void prepareTest(){
        // === baca data dari txt ===
        Map<String, String> data = new HashMap<>();
        String filePath = "form.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(":");
                if (split.length == 2) {
                    data.put(split[0].trim(), split[1].trim());
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca file txt: " + e.getMessage());
        }

        // === set semua nilai var ===
        email = data.get("email");
        password = data.get("password");
        firstname = data.get("firstname");
        lastname = data.get("lastname");
        wanumber = data.get("wanumber");
    }

    public void testRegister(Boolean konfirmbenar) {
         // === klik Get Started ===
        App.driver.findElement(By.xpath("/html/body/header/div[1]/div/div[2]/a[2]")).click();
        App.jedah(2);

        // === PINDAH KE TAB BARU ===
        String parentWindow = App.driver.getWindowHandle();

        for (String window : App.driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                App.driver.switchTo().window(window);
                break;
            }
        }

        App.jedah(1);

        //  === ke signup ===
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/p/a")).click();
        App.jedah(1);

        // === isi form ===
        App.driver.findElement(By.xpath("//*[@id=\'first_name\']")).sendKeys(firstname);
        App.driver.findElement(By.xpath("//*[@id=\'last_name\']")).sendKeys(lastname);
        App.driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys(email);
        App.driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys(password);
        // === konfirmasi password ===
        if(konfirmbenar){// klo konfiorm benar
            App.driver.findElement(By.xpath("//*[@id=\'confirm_password\']")).sendKeys(password);
        }
        else{ // salah
            App.driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys("notemail");
            App.driver.findElement(By.xpath("//*[@id=\'confirm_password\']")).sendKeys("isiPasssalaha");
        }
        App.driver.findElement(By.xpath("//*[@id=\'signupForm\']/div[5]/div/div/div/span")).click();
        App.driver.findElement(By.xpath("//*[@id=\'mobile\']")).sendKeys(wanumber);
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        App.jedah(3);

        // === tutup tab sekarang ===
        App.refreshToMainPage();
        App.jedah(3);
    }

    public void testLogin(){
        // === klik Get Started ===
        App.driver.findElement(By.xpath("/html/body/header/div[1]/div/div[2]/a[2]")).click();
        App.jedah(2);

        // === PINDAH KE TAB BARU ===
        String parentWindow = App.driver.getWindowHandle();

        for (String window : App.driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                App.driver.switchTo().window(window);
                break;
            }
        }

        App.jedah(1);

        // === isi email ===
        App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys(email);
        App.jedah(1);

        // === isi password ===
        App.driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        App.jedah(1);

        // === klik login ===
        App.driver.findElement(By.xpath("//*[@id='login-form']/div[4]/button")).click();
    }

    public void testDemo(){
        
    }

    public void testProduct(){
        
    }


    // ==========================================
    // === lain lain ============================
    // ==========================================
    
}
