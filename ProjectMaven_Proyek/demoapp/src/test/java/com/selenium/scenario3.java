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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class scenario3 {
    
    String email,password,firstname,lastname,wanumber,passwordbaru;

    @Step("Prepare test data from form.txt")
    public void prepareTest(){
        Allure.step("Prepare test data from form.txt", () -> {
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
        });
    }

    public void testRegister(Boolean konfirmbenar) {
        String stepName;
        if (!konfirmbenar) {
            stepName = "Register attempt with INVALID confirmation data";
        } else {
            stepName = "Register attempt with VALID data";
        }
        Allure.step(stepName, () -> {
            // === klik Get Started ===
            App.driver.findElement(
                By.xpath("/html/body/header/div[1]/div/div[2]/a[2]")
            ).click();
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

            // === ke signup ===
            App.driver.findElement(
                By.xpath("/html/body/div[1]/div/div/div/div/div[2]/p/a")
            ).click();
            App.jedah(1);

            // === isi form ===
            App.driver.findElement(By.id("first_name")).sendKeys(firstname);
            App.driver.findElement(By.id("last_name")).sendKeys(lastname);
            App.driver.findElement(By.id("email")).sendKeys(email);
            App.driver.findElement(By.id("password")).sendKeys(password);

            // === konfirmasi password ===
            if (konfirmbenar) {
                App.driver.findElement(By.id("confirm_password")).sendKeys(password);
            } else {
                App.driver.findElement(By.id("email")).sendKeys("notemail");
                App.driver.findElement(By.id("confirm_password")).sendKeys("isiPasssalaha");
            }

            // === captcha & mobile ===
            App.driver.findElement(
                By.xpath("//*[@id='signupForm']/div[5]/div/div/div/span")
            ).click();

            App.driver.findElement(By.id("mobile")).sendKeys(wanumber);
            App.jedah(1);

            // === submit ===
            App.driver.findElement(By.id("submit")).click();
            App.jedah(3);

            // === kembali ke main page ===
            App.refreshToMainPage();
            App.jedah(3);
        });
    }

    public void testLogin(String test){

        String stepName;

        switch (test) {
            case "email":
                stepName = "Login attempt with INVALID email format";
                break;
            case "email_salah":
                stepName = "Login attempt with WRONG credentials";
                break;
            case "benar":
                stepName = "Login attempt with VALID credentials";
                break;
            default:
                stepName = "Login attempt with UNKNOWN case";
        }

        Allure.step(stepName, () -> {

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
            if(test.equals("email")){
                App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys("notemail");
            }
            else if(test.equals("email_salah")){
                App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys("salah" + email);
            }
            else{
                App.driver.findElement(By.xpath("//*[@id='email']")).sendKeys(email);
            }

            App.jedah(1);

            // === isi password ===
            App.driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
            App.jedah(1);

            // === klik login ===
            App.driver.findElement(By.xpath("//*[@id='login-form']/div[4]/button")).click();
        });
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

        Allure.step("Post-login user journey", () -> {

            /* ================= DASHBOARD ================= */
            Allure.step("Verify dashboard navigation", () -> {
                App.jedah(3);
                App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/a[1]")).click();
                App.jedah(2);
                App.driver.findElement(By.xpath("/html/body/aside/nav/a[1]")).click();
                App.jedah(2);
                App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/a[2]")).click();
                App.jedah(2);
            });

            /* ================= ORDERS ================= */
            Allure.step("Verify orders & cancel order", () -> {
                App.driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/a[2]")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("/html/body/aside/nav/a[2]")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/a[1]/button")).click();
                App.jedah(1);

                App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[1]/form/button")).click();
                App.jedah(1);
                try {
                    App.driver.switchTo().alert().accept();
                } catch (Exception ignored) {}
                App.jedah(2);
            });

            /* ================= PROPOSALS ================= */
            Allure.step("Verify proposals page", () -> {
                App.driver.findElement(By.xpath("/html/body/aside/nav/a[3]")).click();
                App.jedah(2);
            });

            /* ================= TICKETS NAV ================= */
            Allure.step("Verify tickets navigation", () -> {
                App.driver.findElement(By.xpath("/html/body/aside/nav/a[4]")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets']")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets/open']")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets/in-progress']")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("//a[@href='https://app.phptravels.com/tickets/close']")).click();
                App.jedah(1);
            });

            /* ================= CREATE TICKET ================= */
            Allure.step("Create & reply support ticket", () -> {
                App.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/a")).click();
                App.jedah(1);
                App.driver.findElement(By.id("subject")).sendKeys("Tes Tiket Subject");
                App.jedah(1);
                App.driver.findElement(By.xpath("//div[@contenteditable='true']")).sendKeys(
                    "Ini adalah isi dari tiket untuk keperluan testing."
                );
                App.jedah(1);

                new Select(App.driver.findElement(By.id("department")))
                    .selectByVisibleText("Technical Support");
                new Select(App.driver.findElement(By.id("related")))
                    .selectByVisibleText("Other");
                new Select(App.driver.findElement(By.id("priority")))
                    .selectByVisibleText("High");

                App.driver.findElement(By.id("submitBtn")).click();
                App.jedah(2);

                App.driver.findElement(By.xpath("//div[@contenteditable='true']")).sendKeys("Tes replay");
                App.jedah(1);
                App.driver.findElement(By.id("replySubmitBtn")).click();
                App.jedah(2);
            });

            /* ================= AFFILIATE ================= */
            Allure.step("Verify affiliate section", () -> {
                App.driver.findElement(By.xpath("/html/body/aside/nav/a[5]")).click();
                App.jedah(2);
            });

            /* ================= FILTER & DETAIL ================= */
            Allure.step("Verify ticket filters & detail", () -> {
                App.driver.findElement(By.xpath("/html/body/aside/nav/a[1]")).click();
                App.jedah(1);
                App.driver.findElement(By.id("columnToggleBtn")).click();
                App.jedah(1);

                for (int i = 1; i <= 6; i++) {
                    App.driver.findElement(By.xpath("//*[@id='columnCheckboxes']/label[" + i + "]/input")).click();
                    App.jedah(1);
                }

                App.driver.findElement(By.id("columnToggleBtn")).click();
                App.jedah(2);
                App.driver.findElement(By.xpath("//*[@id='tableBody']/tr/td[9]/div/a")).click();
                App.jedah(2);
            });

            /* ================= PROFILE ================= */
            Allure.step("Update profile & change password", () -> {
                App.driver.findElement(By.xpath("/html/body/aside/div[3]/a[2]")).click();
                App.jedah(1);

                new Select(App.driver.findElement(By.id("country_code")))
                    .selectByVisibleText("INDONESIA");
                App.driver.findElement(By.id("address"))
                    .sendKeys("Jl Darmo No 34 Surabaya");
                App.jedah(1);
                App.driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();
                App.jedah(1);

                App.driver.findElement(By.id("new_password")).sendKeys(passwordbaru);
                App.driver.findElement(By.id("confirm_password")).sendKeys(passwordbaru + "salah");
                App.jedah(1);
                App.driver.findElement(By.xpath("//form/button")).click();
                App.jedah(1);

                App.driver.findElement(By.id("new_password")).sendKeys(passwordbaru);
                App.driver.findElement(By.id("confirm_password")).sendKeys(passwordbaru);
                App.jedah(1);
                App.driver.findElement(By.xpath("//form/button")).click();
                App.jedah(2);
            });

            /* ================= NEWSLETTER ================= */
            Allure.step("Update newsletter preferences", () -> {
                App.driver.findElement(By.id("email_newsletter")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("//form/button")).click();
                App.jedah(2);
            });

            /* ================= LOGOUT ================= */
            Allure.step("Logout user", () -> {

                WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(20));
                JavascriptExecutor js = (JavascriptExecutor) App.driver;

                // === PASTIKAN SIDEBAR TERBUKA (kalau ada toggle) ===
                try {
                    WebElement sidebarToggle = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[contains(@aria-label,'menu') or contains(@class,'menu')]")
                        )
                    );
                    if (sidebarToggle.isDisplayed()) {
                        js.executeScript("arguments[0].click();", sidebarToggle);
                        App.jedah(1);
                    }
                } catch (Exception ignored) {}

                // === SCROLL KE BAWAH SIDEBAR ===
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                App.jedah(1);

                // === AMBIL LOGOUT BERDASARKAN HREF (PALING STABIL) ===
                WebElement logoutBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/logout')]")
                    )
                );

                // === PAKSA KLIK VIA JS (ANTI ANIMASI / OVERLAY) ===
                js.executeScript("arguments[0].scrollIntoView({block:'center'});", logoutBtn);
                App.jedah(1);
                js.executeScript("arguments[0].click();", logoutBtn);

                App.jedah(2);
            });
        });
    }


    public void testProduct(){
        Allure.step("Test Product Document", () -> {
            Allure.step("Bukan Product Doc", () -> {
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
            });

            Allure.step("Product - Doc - Dropdown", () -> {
                // === dropdown ===
                dropdownHeaderTest();
            });

            Allure.step("Product - Doc - ", () -> {
                // == tes header doc ==
                headerDoctes();
            });

            Allure.step("Product - Doc - Welcome Page", () -> {
                // === tes welcome page ===
                App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a")).click();
                App.jedah(2);

                App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
                App.jedah(2);

                App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Startup Instalation", () -> {
                // === test startup instalation ===
                WebElement installButton = App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]"));
                ((JavascriptExecutor) App.driver).executeScript(
                    "arguments[0].scrollIntoView(true);", installButton
                );
                App.jedah(1);
                installButton.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Setup", () -> {
                // === test setup ===
                App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Setup Setting", () -> {
                // === test setup setting ===
                App.driver.findElement(
                    By.xpath("//a[@href='/startup/setup/settings']")
                ).click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Setup Setting Email", () -> {
                // === test setup setting email ===
                App.driver.findElement(
                    By.xpath("//a[@href='/startup/setup/settings/email-settings']")
                ).click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Setup SMTP Setting", () -> {
                // === test setup SMTP settings ===
                App.driver.findElement(
                    By.xpath("//a[@href='/startup/setup/settings/email-settings/smtp-settings']")
                ).click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Setup SMTP", () -> {
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
            });

            WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));

            Allure.step("Product - Doc - Setup SMTP2", () -> {
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
            });

            Allure.step("Product - Doc - Setup Currencies", () -> {
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

            });

            Allure.step("Product - Doc - Setup Currencies API", () -> {
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
            });

            Allure.step("Product - Doc - Setup Markups", () -> {
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

                App.driver.findElement(
                    By.xpath("//a[@href='/startup/setup/powered-by-linkback']")
                ).click();
                App.jedah(2);

                App.driver.findElement(
                    By.xpath("//a[@href='/startup/ssl-force']")
                ).click();
                App.jedah(2);

            });

            Allure.step("Product - Doc - SSL FORCE", () -> {
                // === test ssl force ===
                App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[1]/div[2]/button")).click();
                App.jedah(1);
                App.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/main/div[2]/a[2]")).click();
                App.jedah(2);
            });

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

            Allure.step("Product - Doc - Hotels", () -> {
                // === Hotels ===
                WebElement hotels = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/hotels']")
                ));
                scrollSlowTo.accept(hotels);
                hotels.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Stuba", () -> {
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
            });

            Allure.step("Product - Doc - Hotelbeds", () -> {
                // === Hotelbeds ===
                WebElement hotelbeds = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/hotels/hotelbeds']")
                ));
                scrollSlowTo.accept(hotelbeds);
                hotelbeds.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Agoda", () -> {
                // === Agoda ===
                WebElement agoda = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/hotels/agoda']")
                ));
                scrollSlowTo.accept(agoda);
                agoda.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Flights", () -> {
                // === Flights ===
                WebElement flights = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/flights']")
                ));
                scrollSlowTo.accept(flights);
                flights.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Amadeus Self Service", () -> {
                // === Amadeus Self Service ===
                WebElement amadeusSelf = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/flights/amadeus-self-service']")
                ));
                scrollSlowTo.accept(amadeusSelf);
                amadeusSelf.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Amadeus Entreprise", () -> {
                // === Amadeus Entreprise ===
                WebElement amadeusEnt = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/flights/amadeus-entreprise']")
                ));
                scrollSlowTo.accept(amadeusEnt);
                amadeusEnt.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Duffel", () -> {
                // === Duffel ===
                WebElement duffel = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/flights/duffel']")
                ));
                scrollSlowTo.accept(duffel);
                duffel.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - ", () -> {
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
            });

            Allure.step("Product - Doc - Pkfare", () -> {
                // === Pkfare ===
                WebElement pkfare = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/flights/pkfare']")
                ));
                scrollSlowTo.accept(pkfare);
                pkfare.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Tours", () -> {
                // === Tours ===
                WebElement tours = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/tours']")
                ));
                scrollSlowTo.accept(tours);
                tours.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Viator", () -> {
                // === Viator ===
                WebElement viator = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/modules/tours/viator']")
                ));
                scrollSlowTo.accept(viator);
                viator.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - Payment Gateways", () -> {
                // === Payment Gateways ===
                WebElement payment = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/payments']")
                ));
                scrollSlowTo.accept(payment);
                payment.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - PayPal", () -> {
                // === PayPal ===
                WebElement paypal = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/payments/paypal']")
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
            });

            Allure.step("Product - Doc - Stripe", () -> {
                // === Stripe (scroll sampai bawah) ===
                WebElement stripe = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/payments/stripe']")
                ));
                scrollSlowTo.accept(stripe);
                stripe.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - For Designers", () -> {
                // === For Designers ===
                WebElement forDesigners = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/support/for-designers']")
                ));
                scrollSlowTo.accept(forDesigners);
                forDesigners.click();
                App.jedah(2);
            });
            
            Allure.step("Product - Doc - For Developers", () -> {
                // === For Designers ===
                WebElement forDesigners = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/support/for-developers']")
                ));
                scrollSlowTo.accept(forDesigners);
                forDesigners.click();
                App.jedah(2);
            });

            Allure.step("Product - Doc - API Version 9", () -> {
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
            });

            Allure.step("Product - Doc - License", () -> {
                // === License ===
                WebElement license = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/support/license']")
                ));
                scrollSlowTo.accept(license);
                license.click();
                App.jedah(2);
            });

            // ================= Powered by GitBook =================

            String mainTab = App.driver.getWindowHandle();
            App.jedah(2);

            Allure.step("Product - Doc - GitBook", () -> {
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
            });

            Allure.step("Product - Doc - Change Them", () -> {
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
            });

            Allure.step("Product - Doc - Feedback", () -> {
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
                
            });
        });
    }


    public void testThemes(){
        Allure.step("Product - Themes page", () -> {

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

        });
    }

    public void testIntegrations(){
        Allure.step("Product - Integrations page", () -> {

            // === klik Product ===
            WebElement productMenu = App.driver.findElement(
                By.xpath("//button[.//span[text()='Product']]")
            );
            productMenu.click();
            App.jedah(1);

            // === klik Integrations ===
            WebElement docsMenu = App.driver.findElement(
                By.xpath("//a[normalize-space()='Integrations']")
            );
            docsMenu.click();
            App.jedah(2);

            // ================= LANJUT 1 : SCROLL PRESISI + FILTER INTEGRATIONS =================
            JavascriptExecutor js = (JavascriptExecutor) App.driver;

            // --- scroll presisi ke filter Flights ---
            try {
                WebElement flightsFilter = App.driver.findElement(
                    By.xpath("//button[@data-filter='Flights']")
                );

                js.executeScript(
                    "const r = arguments[0].getBoundingClientRect();" +
                    "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                    flightsFilter
                );
                App.jedah(1);

            } catch (Exception e) {
                System.out.println("INFO: Scroll ke filter gagal");
            }

            // Flights
            try {
                WebElement btn = App.driver.findElement(By.xpath("//button[@data-filter='Flights']"));
                js.executeScript("arguments[0].click();", btn);
                App.jedah(2);
            } catch (Exception e) {
                System.out.println("BUG: Filter Flights tidak bisa diklik");
            }

            // Hotels
            try {
                WebElement btn = App.driver.findElement(By.xpath("//button[@data-filter='Hotels']"));
                js.executeScript("arguments[0].click();", btn);
                App.jedah(2);
            } catch (Exception e) {
                System.out.println("BUG: Filter Hotels tidak bisa diklik");
            }

            // Tours
            try {
                WebElement btn = App.driver.findElement(By.xpath("//button[@data-filter='Tours']"));
                js.executeScript("arguments[0].click();", btn);
                App.jedah(2);
            } catch (Exception e) {
                System.out.println("BUG: Filter Tours tidak bisa diklik");
            }

            // Cars / Transfer
            try {
                WebElement btn = App.driver.findElement(By.xpath("//button[@data-filter='Transfer']"));
                js.executeScript("arguments[0].click();", btn);
                App.jedah(2);
            } catch (Exception e) {
                System.out.println("BUG: Filter Cars tidak bisa diklik");
            }

            // Payment Gateways
            try {
                WebElement btn = App.driver.findElement(By.xpath("//button[@data-filter='Payment Gateway']"));
                js.executeScript("arguments[0].click();", btn);
                App.jedah(2);
            } catch (Exception e) {
                System.out.println("BUG: Filter Payment Gateways tidak bisa diklik");
            }

            // Others
            try {
                WebElement btn = App.driver.findElement(By.xpath("//button[@data-filter='Others']"));
                js.executeScript("arguments[0].click();", btn);
                App.jedah(2);
            } catch (Exception e) {
                System.out.println("BUG: Filter Others tidak bisa diklik");
            }

            // Coming Soon
            try {
                WebElement btn = App.driver.findElement(By.xpath("//button[@data-filter='Coming Soon']"));
                js.executeScript("arguments[0].click();", btn);
                App.jedah(2);
            } catch (Exception e) {
                System.out.println("BUG: Filter Coming Soon tidak bisa diklik");
            }

            // ================= LANJUT 2 =================
            WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));

            /* --- scroll sedikit --- */
            js.executeScript("window.scrollBy(0, 200)");
            App.jedah(2);

            /* ---------- All Suppliers ---------- */
            try {
                WebElement allSuppliersBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@data-filter='all']")
                    )
                );

                js.executeScript(
                    "const r = arguments[0].getBoundingClientRect();" +
                    "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                    allSuppliersBtn
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", allSuppliersBtn);
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: All Suppliers tidak bisa diklik");
            }

            /* ---------- Search Input ---------- */
            WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("searchInput"))
            );
            App.jedah(2);

            /* ================= KIWI ================= */
            try {
                searchInput.clear();
                App.jedah(2);

                searchInput.sendKeys("Kiw");
                App.jedah(2);

                WebElement kiwiLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/modules/flights/kiwi')]")
                    )
                );
                App.jedah(2);

                js.executeScript(
                    "const r = arguments[0].getBoundingClientRect();" +
                    "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                    kiwiLink
                );
                App.jedah(2);

                String mainTab = App.driver.getWindowHandle();
                js.executeScript("arguments[0].click();", kiwiLink);
                App.jedah(2);

                wait.until(d -> d.getWindowHandles().size() > 1);
                App.jedah(2);

                for (String tab : App.driver.getWindowHandles()) {
                    if (!tab.equals(mainTab)) {
                        App.driver.switchTo().window(tab);
                        App.jedah(2);
                        App.driver.close();
                        App.jedah(2);
                    }
                }

                App.driver.switchTo().window(mainTab);
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Kiwi View Details gagal");
            }

            /* ================= AGODA ================= */
            try {
                searchInput.clear();
                App.jedah(2);

                searchInput.sendKeys("Ago");
                App.jedah(2);

                WebElement agodaLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/modules/hotels/agoda')]")
                    )
                );
                App.jedah(2);

                js.executeScript(
                    "const r = arguments[0].getBoundingClientRect();" +
                    "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                    agodaLink
                );
                App.jedah(2);

                String mainTab = App.driver.getWindowHandle();
                js.executeScript("arguments[0].click();", agodaLink);
                App.jedah(2);

                wait.until(d -> d.getWindowHandles().size() > 1);
                App.jedah(2);

                for (String tab : App.driver.getWindowHandles()) {
                    if (!tab.equals(mainTab)) {
                        App.driver.switchTo().window(tab);
                        App.jedah(2);
                        App.driver.close();
                        App.jedah(2);
                    }
                }

                App.driver.switchTo().window(mainTab);
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Agoda View Details gagal");
            }

            // ================= LANJUT 3 =================

            /* ---------- Reset Filters ---------- */
            try {
                WebElement resetBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("resetBtn"))
                );

                js.executeScript(
                    "const r = arguments[0].getBoundingClientRect();" +
                    "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                    resetBtn
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", resetBtn);
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Reset Filters tidak bisa diklik");
            }

            /* ---------- Assert 0 integrations found ---------- */
            try {
                WebElement resultsCount = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("resultsCount"))
                );
                App.jedah(2);

                Assert.assertEquals(
                    resultsCount.getText(),
                    "23 integrations found",
                    "BUG: Jumlah integration tidak 23"
                );

            } catch (AssertionError ae) {
                throw ae;
            } catch (Exception e) {
                System.out.println("BUG: resultsCount tidak ditemukan");
            }

            /* ---------- Scroll perlahan sampai Live Demo ---------- */
            WebElement liveDemoBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(@href,'/demo')]")
                )
            );
            App.jedah(2);

            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0, 150)");
                App.jedah(2);
            }

            js.executeScript(
                "const r = arguments[0].getBoundingClientRect();" +
                "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                liveDemoBtn
            );
            App.jedah(2);

            js.executeScript("arguments[0].click();", liveDemoBtn);
            App.jedah(2);

            App.driver.navigate().back();
            App.jedah(2);

            /* ---------- View Pricing ---------- */
            WebElement pricingBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(@href,'/pricing')]")
                )
            );
            App.jedah(2);

            js.executeScript(
                "const r = arguments[0].getBoundingClientRect();" +
                "window.scrollBy(0, r.top - (window.innerHeight / 2));",
                pricingBtn
            );
            App.jedah(2);

            js.executeScript("arguments[0].click();", pricingBtn);
            App.jedah(2);

            App.driver.navigate().back();
            App.jedah(2);

            // ================= LANJUT 4 =================

            for (int i = 0; i < 15; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(2);
            }

            /* ---------- Privacy ---------- */
            try {
                WebElement privacyLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/privacy-statement')]")
                    )
                );

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    privacyLink
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", privacyLink);
                App.jedah(2);

                App.driver.navigate().back();
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Privacy link gagal");
            }

            /* ---------- Terms ---------- */
            try {
                WebElement termsLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/terms-and-conditions')]")
                    )
                );

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    termsLink
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", termsLink);
                App.jedah(2);

                App.driver.navigate().back();
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Terms link gagal");
            }

            /* ---------- Support ---------- */
            try {
                WebElement supportLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'direct.lc.chat')]")
                    )
                );

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    supportLink
                );
                App.jedah(2);

                String mainTab = App.driver.getWindowHandle();
                js.executeScript("arguments[0].click();", supportLink);
                App.jedah(2);

                wait.until(d -> d.getWindowHandles().size() > 1);
                App.jedah(2);

                for (String tab : App.driver.getWindowHandles()) {
                    if (!tab.equals(mainTab)) {
                        App.driver.switchTo().window(tab);
                        App.jedah(2);
                        App.driver.close();
                        App.jedah(2);
                    }
                }

                App.driver.switchTo().window(mainTab);
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Support link gagal");
            }

        });
    }

    public void testCustomizations(){
        Allure.step("Product - Customization page", () -> {

            // === klik Product ===
            WebElement productMenu = App.driver.findElement(
                By.xpath("//button[.//span[text()='Product']]")
            );
            productMenu.click();
            App.jedah(1);

            // === klik Customizations ===
            WebElement docsMenu = App.driver.findElement(
                By.xpath("//a[normalize-space()='Customization']")
            );
            docsMenu.click();
            App.jedah(2);

            // ================= LANJUT 2 =================
            JavascriptExecutor js = (JavascriptExecutor) App.driver;
            WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));

            /* ---------- scroll sedikit sampai tombol muncul ---------- */
            for (int i = 0; i < 4; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(2);
            }

            /* ---------- Live Demo ---------- */
            try {
                WebElement liveDemoBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/demo')]")
                    )
                );
                App.jedah(2);

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    liveDemoBtn
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", liveDemoBtn);
                App.jedah(2);

                App.driver.navigate().back();
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Live Demo tidak bisa diklik");
            }

            /* ---------- View Pricing ---------- */
            try {
                WebElement pricingBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/pricing')]")
                    )
                );
                App.jedah(2);

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    pricingBtn
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", pricingBtn);
                App.jedah(2);

                App.driver.navigate().back();
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: View Pricing tidak bisa diklik");
            }

            /* ---------- scroll sampai mentok ke bawah ---------- */
            for (int i = 0; i < 15; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(2);
            }

        });
    }

    public void testTechnology(){
        Allure.step("Product - Technology page", () -> {

            // === klik Product ===
            WebElement productMenu = App.driver.findElement(
                By.xpath("//button[.//span[text()='Product']]")
            );
            productMenu.click();
            App.jedah(1);

            // === klik Technology ===
            WebElement docsMenu = App.driver.findElement(
                By.xpath("//a[normalize-space()='Technology']")
            );
            docsMenu.click();
            App.jedah(2);

            // ================= LANJUT 2 =================
            JavascriptExecutor js = (JavascriptExecutor) App.driver;

            /* ===== scroll perlahan seperti manusia ===== */
            for (int i = 0; i < 14; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(2);
            }
            
            // ==== LANJUT 3 ======
            WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));

            /* ===== Live Demo ===== */
            try {
                WebElement liveDemoBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/demo')]")
                    )
                );

                // pastikan posisi aman (tengah layar)
                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    liveDemoBtn
                );
                App.jedah(2);

                // klik via JS
                js.executeScript("arguments[0].click();", liveDemoBtn);
                App.jedah(3);

                // kembali ke halaman awal
                App.driver.navigate().back();
                App.jedah(3);

            } catch (Exception e) {
                System.out.println("BUG: Live Demo tidak bisa dibuka");
            }

            /* ===== View Pricing ===== */
            try {
                WebElement pricingBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/pricing')]")
                    )
                );

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    pricingBtn
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", pricingBtn);
                App.jedah(3);

                App.driver.navigate().back();
                App.jedah(3);

            } catch (Exception e) {
                System.out.println("BUG: View Pricing tidak bisa dibuka");
            }

        });
    }

    public void testRequirement(){
        Allure.step("Product - Requirements page", () -> {
            // === klik Product ===
            WebElement productMenu = App.driver.findElement(
                By.xpath("//button[.//span[text()='Product']]")
            );
            productMenu.click();
            App.jedah(1);

            // === klik Customizations ===
            WebElement docsMenu = App.driver.findElement(
                By.xpath("//a[normalize-space()='Requirements']")
            );
            docsMenu.click();
            App.jedah(2);

            // scroll sampek bawah
            JavascriptExecutor js = (JavascriptExecutor) App.driver;

            /* ===== scroll perlahan seperti manusia ===== */
            for (int i = 0; i < 13; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(1);
            }

            // ====== klik tombol demo dama live ======
            WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));

            /* ===== Live Demo ===== */
            try {
                WebElement liveDemoBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/demo')]")
                    )
                );

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    liveDemoBtn
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", liveDemoBtn);
                App.jedah(3);

                App.driver.navigate().back();
                App.jedah(3);

            } catch (Exception e) {
                System.out.println("BUG: Tombol bland dengan background");
            }

            /* ===== View Pricing ===== */
            try {
                WebElement pricingBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@href,'/pricing')]")
                    )
                );

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    pricingBtn
                );
                App.jedah(2);

                js.executeScript("arguments[0].click();", pricingBtn);
                App.jedah(3);

                App.driver.navigate().back();
                App.jedah(3);

            } catch (Exception e) {
                System.out.println("BUG: View Pricing tidak bisa diklik");
            }

        });
    }

    public void testDemo(Boolean benar){
        String allureTitle = benar
            ? "Demo Form - VALID calculation"
            : "Demo Form - INVALID calculation";

        Allure.step(allureTitle, () -> {

            App.driver.findElement(By.xpath("/html/body/header/div[1]/div/nav/a[2]")).click();
            // === Test Demo Isi Formulir ===
            App.driver.findElement(By.xpath("//*[@id='demo-form']/div/div/div[1]/div[1]/div[2]/div[1]/div[1]/input")).sendKeys(firstname); // firstname
            App.driver.findElement(By.xpath("//*[@id='demo-form']/div/div/div[1]/div[1]/div[2]/div[1]/div[2]/input")).sendKeys(lastname); // lastname
            App.driver.findElement(By.xpath("//*[@id='demo-form']/div/div/div[1]/div[1]/div[2]/div[2]/input")).sendKeys("Institut Sains dan Teknologi Surabaya"); // buisnisname
            App.driver.findElement(By.xpath("//*[@id='demo-form']/div/div/div[1]/div[1]/div[2]/div[3]/div[2]/input")).sendKeys(wanumber); // wanumber
            App.driver.findElement(By.xpath("//*[@id='demo-form']/div/div/div[1]/div[1]/div[2]/div[4]/input")).sendKeys(email); // emailaddres
            
            // === Kerjakan cek mat ===
            WebElement el1 = App.driver.findElement(By.id("numb1")); // ambil elemen
            WebElement el2 = App.driver.findElement(By.id("numb2"));

            // ambil nilai (value → text fallback)
            String val1 = el1.getAttribute("value");
            if (val1 == null || val1.isEmpty()) {
                val1 = el1.getText();
            }

            String val2 = el2.getAttribute("value");
            if (val2 == null || val2.isEmpty()) {
                val2 = el2.getText();
            }

            // trim & validasi
            val1 = val1.trim();
            val2 = val2.trim();

            // konversi aman
            int bilangan1 = Integer.parseInt(val1);
            int bilangan2 = Integer.parseInt(val2);

            // LOGIKA ANDA
            int hasil;

            if (benar) {
                hasil = bilangan1 + bilangan2;
            } else {
                hasil = 0;
            }

            // isi ke field hasil
            WebElement resultField = App.driver.findElement(By.id("number"));
            resultField.clear();
            resultField.sendKeys(String.valueOf(hasil));

            App.jedah(2);

            // submit
            App.driver.findElement(By.xpath("//*[@id='demo']")).click();
            
            if (!benar) {
                try {
                    App.jedah(2);
                    WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));

                    // tunggu alert muncul
                    Alert alert = wait.until(ExpectedConditions.alertIsPresent());

                    // klik OK
                    alert.accept();

                    System.out.println("INFO: Alert muncul dan berhasil diklik OK");

                } catch (Exception e) {
                    System.out.println("BUG: Alert seharusnya muncul tapi tidak terdeteksi");
                }
            }
            App.jedah(3);
            if(!benar){
                return;
            }

            // kalau berhasil ntik klik 2 tombol sisa
            //*[@id="demo-form"]/div/div/div[1]/div[2]/div/div[3]/button[1]
            //*[@id="demo-form"]/div/div/div[1]/div[2]/div/div[3]/button[2]
            JavascriptExecutor js = (JavascriptExecutor) App.driver;
            WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));

            String mainTab = App.driver.getWindowHandle();

            // tombol pertama
            try {
                WebElement btn1 = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id='demo-form']/div/div/div[1]/div[2]/div/div[3]/button[1]")
                    )
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn1);
                App.jedah(2);

                js.executeScript("arguments[0].click();", btn1);
                App.jedah(3);

                // pindah ke tab baru & tutup
                for (String tab : App.driver.getWindowHandles()) {
                    if (!tab.equals(mainTab)) {
                        App.driver.switchTo().window(tab);
                        App.jedah(2);
                        App.driver.close();
                    }
                }

                // kembali ke tab utama
                App.driver.switchTo().window(mainTab);
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Tombol pertama gagal dibuka");
            }

            // tombol kedua
            try {
                WebElement btn2 = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id='demo-form']/div/div/div[1]/div[2]/div/div[3]/button[2]")
                    )
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn2);
                App.jedah(2);

                js.executeScript("arguments[0].click();", btn2);
                App.jedah(3);

                // pindah ke tab baru & tutup
                for (String tab : App.driver.getWindowHandles()) {
                    if (!tab.equals(mainTab)) {
                        App.driver.switchTo().window(tab);
                        App.jedah(2);
                        App.driver.close();
                    }
                }

                // kembali ke tab utama
                App.driver.switchTo().window(mainTab);
                App.jedah(2);

            } catch (Exception e) {
                System.out.println("BUG: Tombol kedua gagal dibuka");
            }

            // LANJUT 2
            // === lanjut buka kalau berhasil ===

            // scroll turun 20 kali
            for (int i = 0; i < 15; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(1);
            }

            // coba klik card
            App.driver.findElement(By.xpath("/html/body/main/section[6]/div/div/div[1]/figure[1]/div/div/button")).click();
            App.jedah(2);
            App.driver.findElement(By.xpath("//*[@id='lightbox']/button/span")).click();
            App.jedah(2);

            App.driver.findElement(By.xpath("/html/body/main/section[6]/div/div/div[1]/figure[2]/div/div/button")).click();
            App.jedah(2);
            App.driver.findElement(By.xpath("//*[@id='lightbox']/button/span")).click();
            App.jedah(2);

            App.driver.findElement(By.xpath("/html/body/main/section[6]/div/div/div[1]/figure[3]/div/div/button")).click();
            App.jedah(2);
            App.driver.findElement(By.xpath("//*[@id='lightbox']/button/span")).click();
            App.jedah(2);

            // scroll turun lagi
            for (int i = 0; i < 7; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(1);
            }

            // coba klik expand info
            App.driver.findElement(By.xpath("//*[@id='demoAccordion']/div[2]/button")).click();
            App.jedah(2);
            App.driver.findElement(By.xpath("//*[@id='demoAccordion']/div[3]/button")).click();
            App.jedah(2);
            App.driver.findElement(By.xpath("//*[@id='demoAccordion']/div[4]/button")).click();
            App.jedah(2);
            App.driver.findElement(By.xpath("//*[@id='demoAccordion']/div[5]/button")).click();
            App.jedah(2);
            App.driver.findElement(By.xpath("//*[@id='demoAccordion']/div[6]/button")).click();
            App.jedah(2);

            // scroll lagi
            for (int i = 0; i < 3; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                App.jedah(1);
            }

        });
    }


    // ==========================================
    // === lain lain ============================
    // ==========================================

    public void dropdownHeaderTest(){

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivb_']")).click();
        App.jedah(2);

        // === test semua satu per satu ===

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivbH1_']/div/a[4]")).click(); // vscode
        App.jedah(2);

        // akan terbuka tab baru, tutup tab itu sehingga kembali ke awal
        String mainWindow = App.driver.getWindowHandle();
        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivbH1_']/div/div[5]")).click(); // mcp
        App.jedah(3);

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivb_']")).click();
        App.jedah(2);

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivbH1_']/div/a[3]")).click(); // claude
        App.jedah(2);

        // akan terbuka tab baru, tutup tab itu sehingga kembali ke awal
        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivbH1_']/div/a[2]")).click(); // chat gpt
        App.jedah(2);

        // akan terbuka tab baru, tutup tab itu sehingga kembali ke awal
        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivbH1_']/div/a[1]")).click(); // markdown
        App.jedah(2);

        // akan terbuka tab baru, tutup tab itu sehingga kembali ke awal
        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);

        App.driver.findElement(By.xpath("//*[@id='radix-_R_8mqav5ukqiv5ubsnpfivbH1_']/div/div[2]")).click(); // copy
        App.jedah(2);
    }

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
        WebDriverWait wait = new WebDriverWait(App.driver, Duration.ofSeconds(30));
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
