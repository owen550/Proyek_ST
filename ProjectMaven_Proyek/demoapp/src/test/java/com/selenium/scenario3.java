package com.selenium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;


public class scenario3 {
    
    String email,password,firstname,lastname,wanumber,passwordbaru;

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
        passwordbaru = data.get("passwordbaru");
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

    public void testLogin(String test){
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

        if(test.equals("email")){
            // === isi email ===
            App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys("notemail");
        }
        else if(test.equals("email_salah")){
            // === isi email ===
            App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys("salah" + email);
        }
        else{
            // === isi email ===
            App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys(email);
        }
        App.jedah(1);

        // === isi password ===
        App.driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        App.jedah(1);

        // === klik login ===
        App.driver.findElement(By.xpath("//*[@id='login-form']/div[4]/button")).click();
        
    }

    public void testForgetPass(){
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

        //  === forget password ===
        App.driver.findElement(By.xpath("//*[@id=\'login-form\']/div[3]/a")).click();
        App.jedah(1);

        // === email tidak valid ===
        App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys("salah"+email);
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id=\'submit-btn\']")).click();
        App.jedah(3);

        // == email valid ==
        App.driver.findElement(By.xpath("//*[@id='email']")).clear();
        App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys(email);
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id=\'submit-btn\']")).click();
        App.jedah(3);

        App.refreshToMainPage();
        App.jedah(1);
        return;
    }

    public void testPascaLogin(){
        // === cek Dashboard ===
        App.jedah(3);
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/a[1]")).click(); 
        App.jedah(2);
        App.driver.findElement(By.xpath("/html/body/aside/nav/a[1]")).click();
        App.jedah(2);
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/a[2]")).click();
        App.jedah(2);

        // === cek Orders ===
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/a[2]")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/aside/nav/a[2]")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/a[1]/button")).click();
        App.jedah(1);

        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[1]/form/button")).click();
        App.jedah(1);
        try {
            App.driver.switchTo().alert().accept(); // klik OK
            System.out.println("Alert berhasil di-OK");
        } catch (Exception e) {
            System.out.println("Tidak ada alert muncul");
        }
        App.jedah(2);

        // === cek Proporsal ===
        App.driver.findElement(By.xpath("/html/body/aside/nav/a[3]")).click();
        App.jedah(2);
        
        // === cek Tickets ===
        App.driver.findElement(By.xpath("/html/body/aside/nav/a[4]")).click();
        App.jedah(1);
        
        // === cek semua navigasi tickets
        App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets']")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets/open']")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets/in-progress']")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets/close']")).click();
        App.jedah(1);

        // === buat tiket baru ===
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/a")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id=\'subject\']")).sendKeys("Tes Tiket Subject");
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div[1]/div[3]/div/div[2]/div")).sendKeys("Ini adalah isi dari tiket untuk keperluan testing.");
        App.jedah(1);
        Select department = new Select(  // === pilih Department : Technical Support ===
            App.driver.findElement(By.xpath("//*[@id='department']"))
        );
        department.selectByVisibleText("Technical Support");
        App.jedah(1);
        Select related = new Select( // === pilih Related : Other ===
            App.driver.findElement(By.xpath("//*[@id='related']"))
        );
        related.selectByVisibleText("Other");
        App.jedah(1);
        Select priority = new Select( // === pilih Priority : High ===
            App.driver.findElement(By.xpath("//*[@id='priority']"))
        );
        priority.selectByVisibleText("High");
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id=\'submitBtn\']")).click();
        App.jedah(2);
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[5]/form/div[1]/div/div[2]/div")).sendKeys("Tes replay");
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id=\'replySubmitBtn\']")).click();
        App.jedah(2);
        
        // === cek Affiliate ===
        App.driver.findElement(By.xpath("/html/body/aside/nav/a[5]")).click();
        App.jedah(2);

        // === cek tiket yang sudah dibuat ===
        App.driver.findElement(By.xpath("/html/body/aside/nav/a[1]")).click();
        App.jedah(1);

        // === coba semua filter ===
        App.driver.findElement(By.xpath("//*[@id=\'columnToggleBtn\']")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='columnCheckboxes']/label[1]/input")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='columnCheckboxes']/label[2]/input")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='columnCheckboxes']/label[3]/input")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='columnCheckboxes']/label[4]/input")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='columnCheckboxes']/label[5]/input")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='columnCheckboxes']/label[6]/input")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='columnDropdown']/div/div[1]/button")).click();
        App.jedah(1);

        // === tutup drop down filter ===
        App.driver.findElement(By.xpath("//*[@id='columnToggleBtn']")).click(); // Klik lagi untuk tutup
        App.jedah(2);

        // === klik detail tiket ===
        App.driver.findElement(By.xpath("//*[@id=\'tableBody\']/tr/td[9]/div/a")).click();
        App.jedah(2);

        // === buka profil ===
        App.driver.findElement(By.xpath("/html/body/aside/div[3]/a[2]")).click();
        App.jedah(1);

        // === ubah profile di pengaturan ===
        Select selectNegara = new Select(App.driver.findElement(By.xpath("//*[@id='country_code']")));
        selectNegara.selectByVisibleText("INDONESIA");
        App.jedah(1);
        App.driver.findElement(By.xpath("//*[@id='address']")).sendKeys("Jl Darmo No 34 Surabaya");
        App.jedah(1);
        App.driver.findElement(By.xpath("//button[@type='submit' and contains(., 'Update')]")).click();
        App.jedah(1);

        // === ganti password ===
        App.driver.findElement(By.xpath("//*[@id='new_password']")).sendKeys(passwordbaru); // tidak valid
        App.driver.findElement(By.xpath("//*[@id='confirm_password']")).sendKeys(passwordbaru + "salah");
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/form/button")).click();
        App.jedah(1);

        App.driver.findElement(By.xpath("//*[@id='new_password']")).sendKeys(passwordbaru); // valid
        App.driver.findElement(By.xpath("//*[@id='confirm_password']")).sendKeys(passwordbaru);
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/form/button")).click();
        App.jedah(2);

        // === cek order dari profil ===
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[2]/a[1]")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/aside/div[2]/button")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/aside/div[2]/div[3]/a[1]")).click();
        App.jedah(2);

        // === ubah Newsletter Preferences ===
        App.driver.findElement(By.xpath("//*[@id=\'email_newsletter\']")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[3]/form/button")).click();
        App.jedah(2);

        // === logout ===
        App.driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[2]/a[2]")).click();
        App.refreshToMainPage();
        App.jedah(1);
    }

    public void testProduct(){
        
    }


    // ==========================================
    // === lain lain ============================
    // ==========================================
    
}
