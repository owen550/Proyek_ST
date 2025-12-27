package com.selenium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
        // === klik Product ===
        WebElement productMenu = App.driver.findElement(
            By.xpath("//button[.//span[text()='Product']]")
        );
        productMenu.click();
        App.jedah(1);

        // === klik Docs ===
        WebElement docsMenu = App.driver.findElement(
            By.xpath("//a[normalize-space()='Docs']")
        );
        docsMenu.click();
        App.jedah(2);

        // == tes header doc ==
        headerDoctes();
        System.out.println("Lanjut");

        // === tes welcome page ===
        App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a")).click();
        App.jedah(2);

        // === test unsub ===
        // !!! NANTI DIPERBAIKI (ERROR START VIDIO)
        // App.driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[5]/button")).click();
        // App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
        App.jedah(2);

        // === test startup download ===
        // !!! NANTI DIPERBAIKI (ERROR START VIDIO)
        // App.driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[5]/button")).click();
        // App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
        App.jedah(2);

        // === test startup instalation ===
        WebElement installButton = App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]"));
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].scrollIntoView(true);", installButton
        );
        App.jedah(1);
        installButton.click();
        App.jedah(2);

        // === test setup ===
        App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
        App.jedah(2);

        // === test setup setting ===
        App.driver.findElement(
            By.xpath("//a[@href='/startup/setup/settings']")
        ).click();
        App.jedah(2);

        // === test setup setting email ===
        App.driver.findElement(
            By.xpath("//a[@href='/startup/setup/settings/email-settings']")
        ).click();
        App.jedah(2);

        // === test setup SMTP settings ===
        App.driver.findElement(
            By.xpath("//a[@href='/startup/setup/settings/email-settings/smtp-settings']")
        ).click();
        App.jedah(2);

        // === test setup SMTP ===
        WebElement smtp2goApi = App.driver.findElement(
            By.xpath("//a[@href='/startup/setup/settings/email-settings/smtp2go-api']")
        );
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].scrollIntoView(true);", smtp2goApi
        );
        App.jedah(1);
        smtp2goApi.click();
        App.jedah(2);

        WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(10));

        // === test setup SMTP2 ===
        WebElement smtp2goLink = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='https://smtp2go.com']")
            )
        );
        smtp2goLink.click();
        App.jedah(2);
        App.driver.navigate().back();
        App.jedah(2);

        WebElement verifiedSenders = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'verified_senders')]")
            )
        );
        verifiedSenders.click();
        App.jedah(2);
        App.driver.navigate().back();
        App.jedah(2);

        // === test setup currencies ===
        WebElement currenciesBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/startup/setup/settings/currencies']")
            )
        );
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", currenciesBtn
        );
        App.jedah(1);
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].click();", currenciesBtn
        );
        App.jedah(2);

        // === test setup currencies API ===
        WebElement currencyApi = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'apilayer.com/marketplace/currency_data-api')]")
            )
        );
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", currencyApi
        );
        App.jedah(1);
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].click();", currencyApi
        );
        App.jedah(2);
        App.driver.navigate().back();
        App.jedah(2);

        // === test setup markups ===
        WebElement markupsBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/startup/setup/markups']")
            )
        );
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", markupsBtn
        );
        App.jedah(1);
        ((JavascriptExecutor) App.driver).executeScript(
            "arguments[0].click();", markupsBtn
        );
        App.jedah(2);

        // === test setup markup ===
        // !!! NANTI DIPERBAIKI (ERROR START VIDIO)
        // WebElement playMarkup = App.driver.findElement(
        //     By.xpath("//button[contains(@class,'ytp-large-play-button')]")
        // );
        // ((JavascriptExecutor) App.driver).executeScript(
        //     "arguments[0].scrollIntoView(true);", playMarkup
        // );
        // App.jedah(1);
        // playMarkup.click();
        // App.jedah(2);

        App.driver.findElement(
            By.xpath("//a[@href='/startup/setup/powered-by-linkback']")
        ).click();
        App.jedah(2);

        // === test setup linkback ===
        // !!! NANTI DIPERBAIKI (ERROR START VIDIO)
        // WebElement playLinkback = App.driver.findElement(
        //     By.xpath("//button[contains(@class,'ytp-large-play-button')]")
        // );
        // playLinkback.click();
        // App.jedah(2);

        App.driver.findElement(
            By.xpath("//a[@href='/startup/ssl-force']")
        ).click();
        App.jedah(2);

        // === test ssl force ===
        App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[1]/div[2]/button")).click();
        App.jedah(1);
        App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
        App.jedah(2);

        // === NANTIK LANJUT MODULES ===
        JavascriptExecutor js = (JavascriptExecutor) App.driver;

        // === helper scroll pelan ke element ===
        java.util.function.Consumer<WebElement> scrollSlowTo = (el) -> {
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            for (int i = 0; i < 3; i++) {
                js.executeScript("window.scrollBy(0, 80);");
                App.jedah(1);
            }
        };

        // === Hotels ===
        WebElement hotels = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/hotels']")
        ));
        scrollSlowTo.accept(hotels);
        hotels.click();
        App.jedah(2);

        // === Stuba ===
        WebElement stuba = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/hotels/stuba']")
        ));
        scrollSlowTo.accept(stuba);
        stuba.click();
        App.jedah(2);

        // === link https://stuba.com (FIXED) ===
        WebElement stubaLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[@href='https://stuba.com']")
        ));
        scrollSlowTo.accept(stubaLink);
        js.executeScript("arguments[0].click();", stubaLink);
        App.jedah(2);

        App.driver.navigate().back();
        App.jedah(2);

        // === Hotelbeds ===
        WebElement hotelbeds = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/hotels/hotelbeds']")
        ));
        scrollSlowTo.accept(hotelbeds);
        hotelbeds.click();
        App.jedah(2);

        // === Agoda ===
        WebElement agoda = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/hotels/agoda']")
        ));
        scrollSlowTo.accept(agoda);
        agoda.click();
        App.jedah(2);

        // === Flights ===
        WebElement flights = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/flights']")
        ));
        scrollSlowTo.accept(flights);
        flights.click();
        App.jedah(2);

        // === Amadeus Self Service ===
        WebElement amadeusSelf = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/flights/amadeus-self-service']")
        ));
        scrollSlowTo.accept(amadeusSelf);
        amadeusSelf.click();
        App.jedah(2);

        // === Amadeus Entreprise ===
        WebElement amadeusEnt = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/flights/amadeus-entreprise']")
        ));
        scrollSlowTo.accept(amadeusEnt);
        amadeusEnt.click();
        App.jedah(2);

        // === Duffel ===
        WebElement duffel = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/flights/duffel']")
        ));
        scrollSlowTo.accept(duffel);
        duffel.click();
        App.jedah(2);

        // === Kiwi ===
        WebElement kiwi = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/flights/kiwi']")
        ));
        scrollSlowTo.accept(kiwi);
        kiwi.click();
        App.jedah(2);

        // === link https://tequila.kiwi.com (FIXED) ===
        WebElement kiwiApi = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[@href='https://tequila.kiwi.com/']")
        ));
        scrollSlowTo.accept(kiwiApi);
        js.executeScript("arguments[0].click();", kiwiApi);
        App.jedah(2);

        App.driver.navigate().back();
        App.jedah(2);

        // === Pkfare ===
        WebElement pkfare = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/flights/pkfare']")
        ));
        scrollSlowTo.accept(pkfare);
        pkfare.click();
        App.jedah(2);

        // === Tours ===
        WebElement tours = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/tours']")
        ));
        scrollSlowTo.accept(tours);
        tours.click();
        App.jedah(2);

        // === Viator ===
        WebElement viator = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/modules/tours/viator']")
        ));
        scrollSlowTo.accept(viator);
        viator.click();
        App.jedah(2);

        // === Payment Gateways ===
        WebElement payment = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/payment-gateways']")
        ));
        scrollSlowTo.accept(payment);
        payment.click();
        App.jedah(2);

        // === PayPal ===
        WebElement paypal = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/payment-gateways/paypal']")
        ));
        scrollSlowTo.accept(paypal);
        paypal.click();
        App.jedah(2);

        // === link developer.paypal.com (external) ===
        WebElement paypalDev = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[@href='https://developer.paypal.com/home']")
        ));
        scrollSlowTo.accept(paypalDev);
        js.executeScript("arguments[0].click();", paypalDev);
        App.jedah(2);

        App.driver.navigate().back();
        App.jedah(2);

        // === Stripe (scroll sampai bawah) ===
        WebElement stripe = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/payment-gateways/stripe']")
        ));
        scrollSlowTo.accept(stripe);
        stripe.click();
        App.jedah(2);

        // === For Designers ===
        WebElement forDesigners = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/support/for-designers']")
        ));
        scrollSlowTo.accept(forDesigners);
        forDesigners.click();
        App.jedah(2);

        // === For Developers ===
        WebElement forDevelopers = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/support/for-developers']")
        ));
        scrollSlowTo.accept(forDevelopers);
        forDevelopers.click();
        App.jedah(2);

        // === API Version 9 ===
        WebElement apiV9 = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/support/for-developers/api-version-9']")
        ));
        scrollSlowTo.accept(apiV9);
        apiV9.click();
        App.jedah(2);

        // === link Postman docs (external) ===
        WebElement postmanDoc = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[contains(@href,'documenter.getpostman.com')]")
        ));
        scrollSlowTo.accept(postmanDoc);
        js.executeScript("arguments[0].click();", postmanDoc);
        App.jedah(2);

        App.driver.navigate().back();
        App.jedah(2);

        // === Submit Ticket ===
        WebElement submitTicket = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/support/submit-ticket']")
        ));
        scrollSlowTo.accept(submitTicket);
        submitTicket.click();
        App.jedah(2);

        // === License ===
        WebElement license = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='/support/license']")
        ));
        scrollSlowTo.accept(license);
        license.click();
        App.jedah(2);

        // == bagian utama udah lanjut tes sisanya yang belum ==
        // ================= Powered by GitBook =================

        String mainTab = App.driver.getWindowHandle();
        App.jedah(2);

        // klik Powered by GitBook (open new tab)
        WebElement poweredGitbook = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(.,'Powered by GitBook')]")
                )
        );
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", poweredGitbook);
        App.jedah(2);
        js.executeScript("arguments[0].click();", poweredGitbook);
        App.jedah(2);

        // pindah ke tab baru
        for (String tab : App.driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                App.driver.switchTo().window(tab);
                break;
            }
        }
        App.jedah(2);

        // tutup tab baru & balik ke tab utama
        App.driver.close();
        App.jedah(2);
        App.driver.switchTo().window(mainTab);
        App.jedah(2);

        // ================= Theme Switch =================
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        App.jedah(2);

        // light
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Switch to light theme']")
                ))
        );
        App.jedah(2);

        // system
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Switch to system theme']")
                ))
        );
        App.jedah(2);

        // dark
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Switch to dark theme']")
                ))
        );
        App.jedah(2);

        // ================= Feedback YES =================
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Yes, it was!']")
                ))
        );
        App.jedah(2);

        // isi textarea YES
        WebElement textareaYes = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("page-feedback-comment")
                )
        );
        textareaYes.sendKeys("Test Saya Puas");
        App.jedah(2);

        // submit
        textareaYes.sendKeys(Keys.ENTER);
        App.jedah(2);

        // ================= Sidebar Submit Ticket =================
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[@href='/support/submit-ticket']")
                ))
        );
        App.jedah(2);

        // ================= Sidebar License =================
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[@href='/support/license']")
                ))
        );
        App.jedah(2);

        // ================= Feedback NO =================
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        App.jedah(2);

        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='No']")
                ))
        );
        App.jedah(2);

        // isi textarea NO
        WebElement textareaNo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("page-feedback-comment")
                )
        );
        textareaNo.sendKeys("Tes saya tidak puas");
        App.jedah(2);

        // submit
        textareaNo.sendKeys(Keys.ENTER);
        App.jedah(2);

        // === LANJUTIN NANTIK ===
        // === TEST SELECTION COPY ===
        
    }

    public void testThemes(){
        // === klik Product ===
        WebElement productMenu = App.driver.findElement(
            By.xpath("//button[.//span[text()='Product']]")
        );
        productMenu.click();
        App.jedah(1);

        // === klik Themes ===
        WebElement docsMenu = App.driver.findElement(
            By.xpath("//a[normalize-space()='Themes']")
        );
        docsMenu.click();
        App.jedah(2);

        // ================= FREE DOWNLOAD (ANTI NAVBAR) =================
        JavascriptExecutor js = (JavascriptExecutor) App.driver;

        try {
            // scroll bertahap agar card ter-load
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, 300)");
                App.jedah(1);
            }

            // cari tombol berdasarkan icon download (PALING STABIL)
            WebElement freeDownloadBtn = App.driver.findElement(
                By.xpath("//span[text()='download']/ancestor::button")
            );

            // paksa posisi: tengah layar (hindari navbar)
            js.executeScript(
                "const rect = arguments[0].getBoundingClientRect();" +
                "window.scrollBy(0, rect.top - (window.innerHeight / 2));",
                freeDownloadBtn
            );
            App.jedah(1);

            // simpan URL sebelum klik
            String before = App.driver.getCurrentUrl();

            // KLIK VIA JAVASCRIPT (TEMBUS NAVBAR)
            js.executeScript("arguments[0].click();", freeDownloadBtn);
            App.jedah(3);

            // cek respon
            String after = App.driver.getCurrentUrl();

            if (before.equals(after)) {
                System.out.println("BUG: Free Download tak ada respon");
            } else {
                System.out.println("INFO: Free Download ada respon");
            }

        } catch (Exception e) {
            System.out.println("BUG: Free Download tidak bisa ditemukan / diklik");
        }

        // {LANJUT 2}

        /* ---------- Buy Now $100 (PERTAMA) ---------- */
        try {
            WebElement buyNowBtn = App.driver.findElement(
                By.xpath("//a[@onclick='showEnterpriseAlert(event)']")
            );

            // scroll ke tengah layar (hindari navbar)
            js.executeScript(
                "const r = arguments[0].getBoundingClientRect();" +
                "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                buyNowBtn
            );
            App.jedah(1);

            // WAJIB klik via JS
            js.executeScript("arguments[0].click();", buyNowBtn);
            App.jedah(2);

        } catch (Exception e) {
            System.out.println("BUG: Buy Now pertama tidak bisa diklik");
        }

        /* ---------- Cancel ---------- */
        try {
            WebElement cancelBtn = App.driver.findElement(
                By.xpath("//button[@onclick='closeAlert()']")
            );

            js.executeScript("arguments[0].click();", cancelBtn);
            App.jedah(2);

        } catch (Exception e) {
            System.out.println("BUG: Tombol Cancel tidak muncul / tidak bisa diklik");
        }

        /* ---------- Buy Now $100 (KEDUA) ---------- */
        try {
            WebElement buyNowBtn = App.driver.findElement(
                By.xpath("//a[@onclick='showEnterpriseAlert(event)']")
            );

            js.executeScript(
                "const r = arguments[0].getBoundingClientRect();" +
                "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                buyNowBtn
            );
            App.jedah(1);

            js.executeScript("arguments[0].click();", buyNowBtn);
            App.jedah(2);

        } catch (Exception e) {
            System.out.println("BUG: Buy Now kedua tidak bisa diklik");
        }

        /* ---------- Buy Enterprise ---------- */
        try {
            String beforeUrl = App.driver.getCurrentUrl();

            WebElement buyEnterpriseBtn = App.driver.findElement(
                By.xpath("//button[@onclick='redirectToEnterprise()']")
            );

            js.executeScript("arguments[0].click();", buyEnterpriseBtn);
            App.jedah(3);

            String afterUrl = App.driver.getCurrentUrl();

            if (!beforeUrl.equals(afterUrl)) {
                App.driver.navigate().back();
                App.jedah(3);
            } else {
                System.out.println("BUG: Buy Enterprise tidak membuka halaman baru");
            }

        } catch (Exception e) {
            System.out.println("BUG: Buy Enterprise tidak bisa diklik");
        }

    }

    public void testIntegrations(){
        
    }


    // ==========================================
    // === lain lain ============================
    // ==========================================

    public void headerDoctes(){
        // === klik header link 1 ===
        App.driver.findElement(
            By.xpath("//*[@id='site-header']/div/div/div/div[3]/a[1]")
        ).click();
        App.jedah(2);
        App.driver.navigate().back();
        App.jedah(2);

        // === klik header link 2 ===
        App.driver.findElement(
            By.xpath("//*[@id='site-header']/div/div/div/div[3]/a[2]")
        ).click();
        App.jedah(2);
        App.driver.navigate().back();
        App.jedah(2);

        // === klik header link 3 ===
        App.driver.findElement(
            By.xpath("//*[@id='site-header']/div/div/div/div[3]/a[3]")
        ).click();
        App.jedah(2);
        App.driver.navigate().back();
        App.jedah(2);

        // klik input search
        WebElement searchInput = App.driver.findElement(
            By.xpath("//*[@id='site-header']/div/div/div/div[2]/div/div/div[1]/input")
        );
        searchInput.click();
        App.jedah(1);

        // klik hasil search
        App.driver.findElement(
            By.xpath("//*[@id='search-results-_R_98qiv5ubsnpfivb_-0']/div[2]")
        ).click();
        App.jedah(2);

        // klik dropdown hasil (radix)
        WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(10));
        WebElement firstQuestion = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(@href,'?ask=')])[1]")
            )
        );
        firstQuestion.click();
        App.jedah(2);

        // =============================
        // === BUG TESTING ===
        // =============================
        // BUG: icon svg tidak bekerja dengan baik

        try {
            WebDriverWait waitBug = new WebDriverWait(App.driver, Duration.ofSeconds(5));
            WebElement bugIconWrapper = waitBug.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@id='site-header']/div/div/div/div[2]/div/div/div[1]/div[1]")
                )
            );
            // pakai JS click agar tidak tergantung clickable state
            ((JavascriptExecutor) App.driver).executeScript(
                "arguments[0].click();", bugIconWrapper
            );
            System.out.println("BUG TEST: Icon search berhasil diklik");
        } catch (Exception e) {
            System.out.println(
                "BUG TEST: Icon search TIDAK bisa diklik (expected bug). Pesan: "
                + e.getClass().getSimpleName()
            );
        }
        App.jedah(2);

        // =============================
        // === klik logo / home ===
        // =============================
        App.driver.findElement(
            By.xpath("//*[@id=\'site-header\']/div/div/div/div[1]/a")
        ).click();
        App.jedah(2);
    }
    
}
