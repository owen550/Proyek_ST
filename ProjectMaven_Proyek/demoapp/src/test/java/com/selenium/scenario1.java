package com.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.AllureId;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("PHPTravels Website Automation")
@Feature("Company Menu End-to-End Testing")

public class scenario1 {
        private static JavascriptExecutor je = (JavascriptExecutor) App.driver;
        private static Actions actions = new Actions(App.driver);
        private static String mainWindow = App.driver.getWindowHandle();

@Test(description = "End-to-End Automation Scenario for Company Pages")
@Story("Company Navigation & Validation Flow")
@Severity(SeverityLevel.CRITICAL)
        public static void run() throws Exception {
        CompanyClientsPage();
        CompanyAboutPage();
        CompanyTeamPageandViewOpenings();
        CompanyContactPageandInteractions();
        MeetingPage();
        CompanyCareerandJobs();
        CompanyPartners();
        CompanyMediaKit();
        }

@Story("Company - Clients Page")
@Severity(SeverityLevel.NORMAL)

     private static void CompanyClientsPage() throws Exception  {
    // ============================
    // STEP 1: HOVER MENU COMPANY
    // ============================

    WebElement companyButton = App.wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[.//span[text()='Company']]")
            )
    );

    WebElement companyGroup = companyButton.findElement(
            By.xpath("./ancestor::div[contains(@class,'relative') and contains(@class,'group')]")
    );

    actions.moveToElement(companyGroup).perform();
    App.jedah(2);

    // ============================
    // STEP 2: CLICK CASE STUDIES / CLIENTS
    // ============================
    WebElement clientsLink = companyGroup.findElement(
            By.xpath(".//a[contains(text(),'Case Studies')]")
    );

    clientsLink.click();
    App.jedah(2);

    // ============================
    // STEP 3: VALIDASI HALAMAN CLIENTS (ASSERT VERSION)
    // ============================

    // ---------- VALIDASI TITLE ----------
String expectedTitle = "Clients - Phptravels";
String actualTitle = App.driver.getTitle();
Assert.assertEquals(
        actualTitle,
        expectedTitle,
        "❌ ERROR: Title halaman Clients tidak sesuai"
);

takeScreenshot("title_validation");

    // ---------- VALIDASI META TITLE ----------
WebElement metaTitle = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("meta[name='title']")
        )
);

String expectedMetaTitle = "Clients - Phptravels";
String actualMetaTitle = metaTitle.getAttribute("content");

Assert.assertEquals(
        actualMetaTitle,
        expectedMetaTitle,
        "❌ ERROR: Meta title tidak sesuai"
);

takeScreenshot("meta_title_validation");

    // ---------- VALIDASI CANONICAL ----------
WebElement canonical = App.driver.findElement(
        By.cssSelector("link[rel='canonical']")
);

String expectedCanonical = "https://phptravels.com/clients";
String actualCanonical = canonical.getAttribute("href");
Assert.assertEquals(
        actualCanonical,
        expectedCanonical,
        "❌ ERROR: Canonical URL tidak sesuai"
);

takeScreenshot("canonical_validation");

    // ---------- VALIDASI URL BROWSER ----------
String currentUrl = App.driver.getCurrentUrl();
Assert.assertTrue(
        currentUrl.contains("/clients"),
        "❌ ERROR: URL browser tidak mengarah ke halaman Clients"
);
takeScreenshot("url_validation");

    // ============================
    // STEP 4: PERPINDAHAN ANTAR WEBSITE (BATCH 5)
    // ============================

    // simpan window utama (Clients page)
String mainWindow = App.driver.getWindowHandle();

    // ambil semua tombol "Visit Website"
List<WebElement> visitButtons = App.driver.findElements(
       By.xpath("//section[contains(@class,'py-12')]//div[contains(@class,'group bg-white')]//a[.//span[text()='open_in_new']]")
);

    // ukuran batch
int batchSize = 3;

    // loop per batch
for (int i = 0; i < visitButtons.size(); i += batchSize) {

    // ============================
    // BUKA 5 WEBSITE SEKALIGUS
    // ============================
    int end = Math.min(i + batchSize, visitButtons.size());

    for (int j = i; j < end; j++) {
        visitButtons.get(j).click();
        App.jedah(1);
    }

    // ============================
    // PINDAH & TUTUP TAB BARU
    // ============================
    for (String window : App.driver.getWindowHandles()) {
        if (!window.equals(mainWindow)) {
            App.driver.switchTo().window(window);
            App.jedah(5);
            App.driver.close();
        }
    }

    // ============================
    // KEMBALI KE HALAMAN CLIENTS
    // ============================
    App.driver.switchTo().window(mainWindow);
    App.jedah(2);

    // ============================
    // TEST TAMBAHAN: VALIDASI CLIENTS GRID
    // ============================

WebElement clientsSection = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("section.py-12")
        )
);

Assert.assertTrue(
        clientsSection.isDisplayed(),
        "❌ ERROR: Section Clients tidak tampil"
);

takeScreenshot("grid_clients_validations");
}

List<WebElement> clientCards = App.driver.findElements(
        By.cssSelector("section.py-12 .group.bg-white")
);

Assert.assertTrue(
        clientCards.size() > 0,
        "❌ ERROR: Tidak ada client card yang ditemukan"
);

takeScreenshot("client_cards_validations");

WebElement firstCard = clientCards.get(0);

    // Nama perusahaan
WebElement companyName = firstCard.findElement(
        By.cssSelector("h3")
);

Assert.assertTrue(
        companyName.isDisplayed(),
        "❌ ERROR: Nama perusahaan tidak tampil di client card"
);

takeScreenshot("company_clients_validations");

App.driver.getWindowHandle();


    }

    @Story("Company - About Page")
@Severity(SeverityLevel.NORMAL)


    private static void CompanyAboutPage() throws Exception {

    // ======================================================
    // STEP 1: HOVER ULANG MENU COMPANY
    // ======================================================
    WebElement companyButton = App.wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[.//span[text()='Company']]")
            )
    );

    WebElement companyGroup = companyButton.findElement(
            By.xpath("./ancestor::div[contains(@class,'relative') and contains(@class,'group')]")
    );

    actions.moveToElement(companyGroup).perform();
    App.jedah(1);

    // ======================================================
    // STEP 2: CLICK ABOUT US
    // ======================================================
    WebElement aboutLink = App.wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//a[contains(@href,'/about-us')]")
            )
    );

    actions.moveToElement(aboutLink)
            .pause(Duration.ofMillis(500))
            .click()
            .perform();

    App.jedah(2);

    // ======================================================
    // STEP 3: VALIDASI MASUK HALAMAN ABOUT
    // ======================================================
    Assert.assertTrue(
            App.driver.getCurrentUrl().contains("/about-us"),
            "❌ ERROR: Tidak berhasil masuk halaman About Us"
    );

    System.out.println("✔ Berhasil masuk halaman About Us");

    // ======================================================
    // STEP 4: FOTO GALERI (INDEX 0)
    // ======================================================
    List<WebElement> photos = App.wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("div.grid a[href*='/company/2025']")
            )
    );

    Assert.assertTrue(photos.size() >= 8, "❌ ERROR: Foto galeri kurang");

    WebElement firstPhoto = photos.get(0);
    actions.moveToElement(firstPhoto).pause(Duration.ofMillis(500)).click().perform();
    App.jedah(1);

    // ======================================================
    // STEP 5: NAVIGASI FOTO
    // ======================================================
    WebElement nextBtn = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.mfp-arrow-right")
            )
    );

    WebElement prevBtn = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.mfp-arrow-left")
            )
    );

    nextBtn.click(); App.jedah(1);
    prevBtn.click(); App.jedah(1);

    for (int i = 1; i < 8; i++) {
        nextBtn.click();
        App.jedah(1);
    }

    // ======================================================
    // STEP 6: CLOSE GALLERY
    // ======================================================
    App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.mfp-close")
            )
    ).click();

    App.jedah(1);

    // ======================================================
    // STEP 7: CONTACT US (MAILTO)
    // ======================================================
    try {
        WebElement mailBtn = App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@href,'mailto:')]")
                )
        );

        actions.moveToElement(mailBtn)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();

        App.jedah(2);

        App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.mfp-close")
                )
        ).click();

        App.jedah(1);

    } catch (Exception e) {
        System.out.println("Mail client tidak muncul — dilewati");
    }

    // ======================================================
    // STEP 9: HERO HEADER SECTION (VISIBILITY TEST)
    // ======================================================
WebElement heroSection = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("section.py-4.bg-background")
        )
);

Assert.assertTrue(
        heroSection.isDisplayed(),
        "❌ ERROR: Hero About section tidak tampil"
);

System.out.println("✔ Hero About section tampil");

// ======================================================
// STEP 10: FEATURE PILLS (HOVER TEST SAJA)
// ======================================================
List<WebElement> featurePills = App.driver.findElements(
        By.cssSelector("div.flex.flex-wrap.gap-2\\.5 > div")
);

System.out.println("Jumlah feature pills ditemukan: " + featurePills.size());

for (WebElement pill : featurePills) {
    actions.moveToElement(pill)
            .pause(Duration.ofMillis(500))
            .perform();
}

System.out.println("✔ Feature pills berhasil di-hover satu per satu");
}

@Story("Company - Team Page")
@Severity(SeverityLevel.NORMAL)

private static void CompanyTeamPageandViewOpenings() throws Exception {
// ======================================================
// STEP: MEET OUR TEAM (STABIL & AMAN)
// ======================================================
WebElement teamBtn = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'/the-team')]")
        )
);

// scroll agar benar-benar terlihat
je.executeScript("arguments[0].scrollIntoView({block:'center'});", teamBtn);
App.jedah(1);

// klik via JavaScript (hindari timeout & intercepted)
je.executeScript("arguments[0].click();", teamBtn);
App.jedah(2);

// tutup popup / overlay jika ada
try {
    WebElement closeBtn = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.mfp-close")
            )
    );
    closeBtn.click();
    App.jedah(1);
} catch (Exception e) {
    System.out.println("Tidak ada popup team yang perlu ditutup");
}

    // ======================================================
    // STEP 1: APPLY TODAY (THE TEAM PAGE)
    // ======================================================
    WebElement applyTodayBtn = App.wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(@href,'breezy.hr') and contains(.,'Apply')]")
            )
    );

    // hover
    actions.moveToElement(applyTodayBtn)
            .pause(Duration.ofMillis(500))
            .perform();

    App.jedah(1);

    // scroll + click via JS (karena target=_blank)
    je.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            applyTodayBtn
    );

    App.jedah(1);

    je.executeScript(
            "arguments[0].click();",
            applyTodayBtn
    );

    App.jedah(3);

    // ======================================================
    // STEP 2: PINDAH KE TAB BREEZY
    // ======================================================
    String mainWindow = App.driver.getWindowHandle();

    for (String win : App.driver.getWindowHandles()) {
        if (!win.equals(mainWindow)) {
            App.driver.switchTo().window(win);
            break;
        }
    }

    App.jedah(2);

    // ======================================================
    // STEP 3: VIEW OPENINGS (BREEZY PAGE)
    // ======================================================
    WebElement viewOpeningsBtn = App.wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(@href,'#positions') and contains(.,'View Openings')]")
            )
    );

    // hover
    actions.moveToElement(viewOpeningsBtn)
            .pause(Duration.ofMillis(500))
            .perform();

    App.jedah(1);

    // scroll + click via JS (aman dari overlay/banner)
    je.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            viewOpeningsBtn
    );

    App.jedah(1);

    je.executeScript(
            "arguments[0].click();",
            viewOpeningsBtn
    );

    App.jedah(3);

    // ======================================================
    // STEP 4: TUTUP TAB BREEZY & KEMBALI KE THE TEAM
    // ======================================================
    App.driver.close();
    App.driver.switchTo().window(mainWindow);

    App.jedah(2);

      // ======================================================
    // STEP B: FEATURE PILLS (HOVER ONLY)
    // ======================================================
    List<WebElement> featurePills = App.driver.findElements(
            By.cssSelector("div.inline-flex.items-center.gap-2.group")
    );

    for (WebElement pill : featurePills) {
        actions.moveToElement(pill)
                .pause(Duration.ofMillis(500))
                .perform();
        App.jedah(3);
    }

    takeScreenshot("feature_pills_hovered");

    // ======================================================
    // STEP C: TEAM STATS (VALIDASI RINGAN)
    // ======================================================
    List<WebElement> stats = App.driver.findElements(
            By.cssSelector("section.py-8.bg-white .grid > div")
    );

    Assert.assertTrue(
            stats.size() == 4,
            "❌ ERROR: Jumlah team stats tidak sesuai"
    );

    takeScreenshot("team_stats_validation");

    // ======================================================
    // STEP D: CURRENT TEAM CARD (HOVER 3 SAMPLE)
    // ======================================================
    List<WebElement> teamCards = App.driver.findElements(
            By.cssSelector("section.bg-gray-50 .group.bg-white")
    );

    Assert.assertTrue(
            teamCards.size() > 5,
            "❌ ERROR: Team card terlalu sedikit"
    );

    for (int i = 0; i < 3; i++) {
        actions.moveToElement(teamCards.get(i))
                .pause(Duration.ofMillis(3000))
                .perform();
        App.jedah(2);
    }

    takeScreenshot("current_team_cards_hover");

    // ======================================================
    // STEP E: HALL OF FAME (GRAYSCALE → COLOR HOVER)
    // ======================================================
    List<WebElement> hallOfFameCards = App.driver.findElements(
            By.cssSelector("section.bg-gradient-to-br .group.text-center")
    );

    for (int i = 0; i < Math.min(3, hallOfFameCards.size()); i++) {
        actions.moveToElement(hallOfFameCards.get(i))
                .pause(Duration.ofMillis(3000))
                .perform();
        App.jedah(2);
    }

    takeScreenshot("hall_of_fame_hover");

    // ======================================================
    // STEP F: COMPANY VALUES (HOVER)
    // ======================================================
    List<WebElement> valueCards = App.driver.findElements(
            By.cssSelector("section.bg-muted\\/30 .bg-card")
    );

    for (WebElement card : valueCards) {
        actions.moveToElement(card)
                .pause(Duration.ofMillis(3000))
                .perform();
        App.jedah(2);
    }

    takeScreenshot("company_values_hover");

}

@Story("Company - Contact Page")
@Severity(SeverityLevel.NORMAL)


private static void CompanyContactPageandInteractions() throws Exception {
    // ======================================================
    // STEP G: NAVIGATE TO CONTACT (Company Dropdown)
    // ======================================================
    WebElement companyDropdown = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Company')]")
            )
    );
    companyDropdown.click();
    App.jedah(1);

    WebElement contactLink = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'contact')]")
            )
    );
    contactLink.click();
    App.jedah(3);

     // ======================================================
    // 1️⃣ VALIDASI CONTACT CARDS
    // ======================================================
List<WebElement> contactCards = App.wait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.grid.md\\:grid-cols-2.lg\\:grid-cols-4 a.group")
        )
);

Assert.assertEquals(
        contactCards.size(),
        4,
        "❌ ERROR: Jumlah contact card tidak sesuai"
);

takeScreenshot("contact_cards_visible");

    // ======================================================
    // 2️⃣ LIVE SUPPORT — HASH NAVIGATION (NO POPUP)
    // ======================================================
WebElement liveSupport = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a[href='#live-chat']")
        )
);

actions.moveToElement(liveSupport)
        .pause(Duration.ofMillis(1000))
        .perform();

liveSupport.click();
App.jedah(2);

Assert.assertTrue(
        App.driver.getCurrentUrl().contains("#live-chat"),
        "❌ ERROR: Live chat hash tidak aktif"
);


    // ======================================================
    // STEP A: LIVE CHAT (HASH ONLY)
    // ======================================================
WebElement liveChat = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a[href='#live-chat']")
        )
);
liveChat.click();
App.jedah(1);

Assert.assertTrue(
        App.driver.getCurrentUrl().contains("#live-chat"),
        "❌ Live chat hash tidak aktif"
);

    // ============================
    // STEP 3: GOOGLE MAPS – SWITCH IFRAME
    // ============================
    // switch ke iframe Google Maps
WebElement mapFrame = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//iframe[contains(@src,'google.com/maps')]")
        )
);
App.driver.switchTo().frame(mapFrame);

    // gunakan body iframe sebagai anchor (PALING AMAN)
WebElement mapBody = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(By.tagName("body"))
);

    // ============================
    // PAN MAP (EMBED)
    // ============================

    // PAN LEFT
actions.moveToElement(mapBody)
        .moveByOffset(-100, 0)
        .click()
        .build()
        .perform();
App.jedah(3);
App.jedah(1);

    // PAN UP
actions.moveByOffset(0, -100)
        .click()
        .build()
        .perform();
App.jedah(3);
App.jedah(1);

    // PAN RIGHT
actions.moveByOffset(100, 0)
        .click()
        .build()
        .perform();
App.jedah(3);
App.jedah(1);

    // PAN DOWN
actions.moveByOffset(0, 100)
        .click()
        .build()
        .perform();
App.jedah(3);

    // ============================
    // STEP 4: VIEW LARGER MAP (SATU-SATUNYA LINK VALID DI EMBED)
    // ============================

WebElement viewLargerMap = App.wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(.,'View larger map')]")
        )
);
viewLargerMap.click();

    // tunggu tab baru terbuka
App.wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    // ============================
    // PINDAH KE TAB GOOGLE MAPS FULL
    // ============================

for (String win : App.driver.getWindowHandles()) {
    if (!win.equals(mainWindow)) {
        App.driver.switchTo().window(win);
        break;
    }
}

App.jedah(4); // observasi halaman maps full

    // OPTIONAL: validasi ringan (TIDAK WAJIB KLIK)
    // contoh: pastikan URL maps
    // Assert.assertTrue(App.driver.getCurrentUrl().contains("google.com/maps"));

    // tutup tab Google Maps
App.driver.close();

    // kembali ke halaman Contact
App.driver.switchTo().window(mainWindow);

    // keluar dari iframe
App.driver.switchTo().defaultContent();

App.jedah(2);

    // ============================
    // STEP H: WRITE FOR US
    // ============================

WebElement writeForUs = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'write-for-us')]")
        )
);

    // scroll aman
je.executeScript("arguments[0].scrollIntoView({block:'center'});", writeForUs);
App.jedah(1);

    // buka di tab baru via JS
je.executeScript("window.open(arguments[0].href, '_blank');", writeForUs);

    // tunggu tab baru
App.wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    // handle tab baru
for (String win : App.driver.getWindowHandles()) {
    if (!win.equals(mainWindow)) {
        App.driver.switchTo().window(win);
        App.jedah(2);
        App.driver.close();
    }
}

    // kembali ke halaman Contact
App.driver.switchTo().window(mainWindow);

    // screenshot akhir
takeScreenshot("contact_page_completed");
}

@Story("Company - Meeting Page")
@Severity(SeverityLevel.CRITICAL)

private static void MeetingPage() throws Exception {

    // ======================================================
    // STEP I: MEETING — HOVER + NAVIGASI
    // ======================================================
    WebElement meetingLink = App.wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(@href,'/meeting') and contains(.,'Schedule')]")
            )
    );

    je.executeScript("arguments[0].scrollIntoView({block:'center'});", meetingLink);
    App.jedah(1);

    actions.moveToElement(meetingLink)
            .pause(Duration.ofMillis(700))
            .perform();

    meetingLink.click();
    App.wait.until(ExpectedConditions.urlContains("meeting"));
    App.jedah(2);

      String mainWindow = App.driver.getWindowHandle();

      WebElement calendlyFrame = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//iframe[contains(@src,'calendly')]")
        )
);

App.driver.switchTo().frame(calendlyFrame);
App.jedah(2);
        // ======================================================
        // STEP 1: SELECT EVENT TYPE — 15 MINUTES
        // ======================================================
        WebElement meetingType = App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@data-id,'event-type') and contains(.,'15 Minutes')]")
                )
        );
        je.executeScript("arguments[0].scrollIntoView({block:'center'});", meetingType);
        meetingType.click();
        App.jedah(2);

        // ======================================================
        // STEP 2: NEXT MONTH
        // ======================================================
        WebElement nextMonth = App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@aria-label='Go to next month']")
                )
        );
        nextMonth.click();
        App.jedah(2);

        // ======================================================
        // STEP 3: PREVIOUS MONTH
        // ======================================================
        WebElement prevMonth = App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@aria-label='Go to previous month']")
                )
        );
        prevMonth.click();
        App.jedah(2);

        // ======================================================
        // STEP 4: NEXT MONTH AGAIN
        // ======================================================
        nextMonth.click();
        App.jedah(2);

        // ======================================================
        // STEP 5: TIMEZONE DROPDOWN
        // ======================================================
        WebElement timezoneBtn = App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("timezone-field")
                )
        );
        timezoneBtn.click();
        App.jedah(2);

        // ======================================================
        // STEP 6: CHECKBOX
        // ======================================================
        // STEP 6 — TOGGLE CHECKBOX (ANTI INTERCEPT)
WebElement timezoneLabel = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@class='b1rtbe9g']/ancestor::label")
        )
);


        je.executeScript("arguments[0].scrollIntoView({block:'center'});", timezoneLabel);

App.jedah(1);

timezoneLabel.click();
App.jedah(2);

        // ======================================================
        // STEP 7: SEARCH UTC
        // ======================================================
        WebElement tzSearch = App.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='timezone-menu']//input")
                )
        );
        tzSearch.sendKeys("utc");
        App.jedah(2);

        // ======================================================
        // STEP 8: SELECT UTC TIME
        // ======================================================
        WebElement utcBtn = App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(.,'UTC Time')]")
                )
        );
        utcBtn.click();
        App.jedah(2);

        // ======================================================
        // STEP 9: SELECT DATE 18
        // ======================================================
        WebElement date = App.wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'booking-kit_button-bookable') and not(@disabled)]")
        )
);
date.click();
App.jedah(2);

        // ======================================================
        // STEP 10: SELECT TIME 7:00pm
        // ======================================================
      // STEP — SELECT FIRST AVAILABLE TIME SLOT (DYNAMIC)
WebElement timeSlot = App.wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-container='time-button' and not(@disabled)]")
        )
);

        je.executeScript("arguments[0].scrollIntoView({block:'center'});", timeSlot);

App.jedah(1);

timeSlot.click();
App.jedah(2);
        // ======================================================
        // STEP 11: NEXT
        // ======================================================
        WebElement nextBtn = App.wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(.,'Next')]")
                )
        );
        nextBtn.click();
        App.jedah(2);

        // ======================================================
        // STEP 12: LOAD EXTERNAL DATA FILE
        // ======================================================
        File dataFile = new File(System.getProperty("user.dir") + "/test-data/meeting-inputs.json");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = mapper.readValue(new FileInputStream(dataFile), Map.class);

        // ======================================================
// STEP 13: ADD GUESTS
// ======================================================
WebElement addGuest = App.wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Add Guests'] or contains(.,'Add Guests')]")
        )
);
addGuest.click();
App.jedah(2);


        // ======================================================
        // STEP 14: FILL FORM
        // ======================================================
        WebElement nameInput = App.wait.until(
        ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='full_name_input']")
        )
);
nameInput.sendKeys(data.get("name"));
App.jedah(2);

WebElement emailInput = App.wait.until(
        ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='email_input']")
        )
);
emailInput.sendKeys(data.get("email"));
App.jedah(2);

WebElement guestEmail = App.wait.until(
        ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='invitee_guest_input']")
        )
);
guestEmail.sendKeys(data.get("guest_email"));
guestEmail.sendKeys(Keys.ENTER);
App.jedah(2);

WebElement phoneInput = App.wait.until(
        ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='tel' and @autocomplete='tel']")
        )
);

actions.moveToElement(phoneInput).click().perform();
phoneInput.clear();
phoneInput.sendKeys(data.get("whatsapp_number"));
App.jedah(2);


WebElement description = App.wait.until(
        ExpectedConditions.visibilityOfElementLocated(
               By.xpath("//div[contains(@class,'d1dzuwnm')]//textarea")
        )
);
description.sendKeys(data.get("description"));
App.jedah(2);



        // ======================================================
        // STEP 15: TERMS (NEW TAB)
        // ======================================================
        App.driver.findElement(By.xpath("//a[contains(@href,'/terms')]")).click();
        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.jedah(2);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);
        App.jedah(2);

        // ======================================================
        // STEP 16: PRIVACY (NEW TAB)
        // ======================================================
        // tunggu iframe Calendly muncul
App.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
    By.xpath("//iframe[contains(@src,'calendly')]")
));

// sekarang baru element ADA
App.wait.until(ExpectedConditions.elementToBeClickable(
    By.xpath("//a[contains(@href,'/privacy')]")
)).click();

for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.jedah(2);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);
        App.jedah(2);

        // ======================================================
        // STEP 17: SUBMIT
        // ======================================================
//          App.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
//     By.xpath("//iframe[contains(@src,'calendly')]")
// ));
//         WebElement scheduleBtn = App.wait.until(
//     ExpectedConditions.elementToBeClickable(
//         By.xpath("//form//button[@type='submit']")
//     )
// );

// scheduleBtn.click();
//         App.jedah(2);

//         boolean realCaptchaPresent =
//     App.driver.findElements(
//         By.xpath("//iframe[contains(@src,'recaptcha') or contains(@src,'hcaptcha') or contains(@src,'challenges')]")
//     ).size() > 0;


//     if (!realCaptchaPresent) {
//     List<WebElement> continueBtns = App.driver.findElements(
//         By.xpath("//button[.//text()[contains(.,'Continue')]]")
//     );

//     if (!continueBtns.isEmpty()) {
//         WebElement btn = continueBtns.get(0);

//         App.wait.until(ExpectedConditions.elementToBeClickable(btn));
//         btn.click();
//     }
// }

// if (realCaptchaPresent) {
//     System.out.println("CAPTCHA detected. Please solve it manually.");

//     // pause sampai user menyelesaikan captcha
//     new WebDriverWait(App.driver, Duration.ofMinutes(3))
//         .until(ExpectedConditions.invisibilityOfElementLocated(
//             By.xpath("//iframe[contains(@src,'recaptcha') or contains(@src,'hcaptcha') or contains(@src,'challenges')]")
//         ));

//     System.out.println("CAPTCHA solved manually. Continuing test...");
// }

        // ======================================================
        // STEP 18: OPEN INVITATION
        // ======================================================

//          App.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
//     By.xpath("//iframe[contains(@src,'calendly')]")
// ));

//         WebElement openInvite = App.wait.until(
//                 ExpectedConditions.elementToBeClickable(
//                         By.id("booking_sniper_link_cta")
//                 )
//         );
//         openInvite.click();
//        for (String win : App.driver.getWindowHandles()) {
//             if (!win.equals(mainWindow)) {
//                 App.driver.switchTo().window(win);
//                 App.jedah(2);
//                 App.driver.close();
//             }
//         }
//         App.driver.switchTo().window(mainWindow);
//         App.jedah(2);

        App.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
    By.xpath("//iframe[contains(@src,'calendly')]")
));

WebElement calendlyLink = App.wait.until(
    ExpectedConditions.presenceOfElementLocated(
        By.xpath("//a[contains(@href,'calendly.com')]")
    )
);

// klik aman via JS
je.executeScript("arguments[0].scrollIntoView({block:'center'});", calendlyLink);
je.executeScript("arguments[0].click();", calendlyLink);

// handle tab baru
for (String win : App.driver.getWindowHandles()) {
    if (!win.equals(mainWindow)) {
        App.driver.switchTo().window(win);
        App.jedah(2);
        App.driver.close();
    }
}

App.driver.switchTo().window(mainWindow);
App.jedah(2);
    }

@Story("Company - Career Page")
@Severity(SeverityLevel.MINOR)

private static void CompanyCareerandJobs() throws Exception {
// tombol Company (berdasarkan teks)
WebElement companyBtn = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//header//button[.//span[normalize-space()='Company']]")
        )
);

// hover Company
actions.moveToElement(companyBtn)
        .pause(Duration.ofMillis(700))
        .perform();

App.jedah(2);

// link Careers / Jobs (berdasarkan href + text)
WebElement careersLink = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//header//a[contains(@href,'/jobs') and contains(normalize-space(),'Careers')]")
        )
);

// hover Careers / Jobs
actions.moveToElement(careersLink)
        .pause(Duration.ofMillis(700))
        .perform();

App.jedah(2);

// klik Careers / Jobs
careersLink.click();

// tunggu halaman jobs
App.wait.until(ExpectedConditions.urlContains("jobs"));
App.jedah(2);

// ======================================================
// STEP K: JOB LISTING — HOVER EACH CARD
// ======================================================

List<WebElement> jobCards = App.wait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class,'grid') and contains(@class,'gap-4')]//div[contains(@class,'border') and contains(@class,'rounded-lg')]")
        )
);

// validasi minimal ada job
Assert.assertTrue(
        jobCards.size() > 0,
        "❌ ERROR: Job card tidak ditemukan"
);

// hover satu per satu
for (int i = 0; i < jobCards.size(); i++) {

    WebElement card = jobCards.get(i);

    // scroll ke card agar terlihat
    je.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            card
    );

    App.jedah(1);

    // hover card
    actions.moveToElement(card)
            .pause(Duration.ofMillis(700))
            .perform();

    App.jedah(2);
}
}

@Story("Company - Partners Page")
@Severity(SeverityLevel.NORMAL)

private static void CompanyPartners() throws Exception {
 // buka dropdown Company (hover + click aman)
    WebElement companyDropdown = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[normalize-space()='Company']]")
            )
    );
    companyDropdown.click();
    App.jedah(1);

    // klik link Partners (tidak buka tab baru)
    WebElement partnersLink = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='https://phptravels.com/partners' and normalize-space()='Partners']")
            )
    );
    partnersLink.click();

    // tunggu URL berubah ke halaman Partners
    App.wait.until(ExpectedConditions.urlContains("/partners"));

     String mainWindow = App.driver.getWindowHandle();

    // locator grid partner (robust, tanpa index)
    List<WebElement> partners = App.wait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class,'grid')]//a[.//h3]")
        )
);

    for (WebElement partner : partners) {

        App.wait.until(ExpectedConditions.elementToBeClickable(partner)).click();

        App.jedah(2);

        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.driver.close();
            }
        }

        App.driver.switchTo().window(mainWindow);
    }

    // Affiliate Program link
 WebElement affiliateLink = App.wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.xpath("//p[contains(.,'Affiliate Program')]//a[contains(@href,'affiliate')]")
        )
);

// scroll aman ke tengah layar
je.executeScript("arguments[0].scrollIntoView({block:'center'});", affiliateLink);

App.jedah(1);

// ============================
// OPEN VIA JS → PASTI TAB BARU
// ============================
je.executeScript("window.open(arguments[0].href, '_blank');", affiliateLink);

// ============================
// HANDLE TAB BARU
// ============================
App.wait.until(ExpectedConditions.numberOfWindowsToBe(2));

for (String win : App.driver.getWindowHandles()) {
    if (!win.equals(mainWindow)) {
        App.driver.switchTo().window(win);

        App.jedah(2); // tunggu 2 detik sesuai instruksi

        App.driver.close();
    }
}

// ============================
// KEMBALI KE PARTNERS PAGE
// ============================
App.driver.switchTo().window(mainWindow);

}

@Story("Company - Media Kit Page")
@Severity(SeverityLevel.NORMAL)

private static void CompanyMediaKit() throws Exception {
String mainWindow = App.driver.getWindowHandle();

    // ============================
    // STEP 1: OPEN MEDIA KIT
    // ============================
   // buka dropdown Company (hover + click aman)
    WebElement companyDropdown = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[normalize-space()='Company']]")
            )
    );
    companyDropdown.click();
    App.jedah(1);

    // klik link Partners (tidak buka tab baru)
    WebElement partnersLink = App.wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='https://phptravels.com/media-kit' and normalize-space()='Media Kit']")
            )
    );
    partnersLink.click();

    // tunggu URL berubah ke halaman Partners
    App.wait.until(ExpectedConditions.urlContains("/media-kit"));


    // ============================
    // STEP 2A: BRAND LOGOS (SECTION 2)
    // ============================
    List<WebElement> brandLogos = App.wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//section[.//h2[contains(.,'Brand Logos')]]//a[contains(@href,'.png') and .//span[text()='download']]")
            )
    );

    for (WebElement logo : brandLogos) {
        String href = logo.getAttribute("href");
        je.executeScript("window.open(arguments[0], '_blank');", href);

        App.wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.jedah(2);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);
    }

    // ============================
    // STEP 2B: BRAND ICONS (SECTION 3)
    // ============================
    List<WebElement> brandIcons = App.wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//section[.//h2[contains(.,'Brand Icons')]]//a[contains(@href,'.png') and .//span[text()='download']]")
            )
    );

    for (WebElement icon : brandIcons) {
        String href = icon.getAttribute("href");
        je.executeScript("window.open(arguments[0], '_blank');", href);

        App.wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String win : App.driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                App.driver.switchTo().window(win);
                App.jedah(2);
                App.driver.close();
            }
        }
        App.driver.switchTo().window(mainWindow);
    }

    // ============================
    // STEP 2C: BRAND COLORS (HEX + RGB)
    // ============================
    List<WebElement> colorCodes = App.wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//section[.//h2[contains(.,'Brand Colors')]]//code[@onclick]")
            )
    );

    for (WebElement code : colorCodes) {
        je.executeScript("arguments[0].scrollIntoView({block:'center'});", code);
        je.executeScript("arguments[0].click();", code);
        App.jedah(2);
    }

    // ============================
    // STEP 3: USAGE GUIDELINES VALIDATION
    // ============================

    // Title validation
    WebElement guidelinesTitle = App.wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//section//h2[contains(.,'Usage') and contains(.,'Guidelines')]")
            )
    );
    Assert.assertTrue(guidelinesTitle.getText().contains("Usage"));
    takeScreenshot("media_kit_guidelines_title");

    // Card count validation
    List<WebElement> guidelineCards = App.wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//section[.//h2[contains(.,'Usage')]]//div[contains(@class,'rounded-xl')]")
            )
    );
    Assert.assertEquals(guidelineCards.size(), 4);
    takeScreenshot("media_kit_guidelines_card_count");

    // Content validation: Do & Don't
    WebElement doCard = App.wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'rounded-xl')][.//h3[text()='Do']]")
            )
    );
    Assert.assertTrue(doCard.getText().contains("Use official brand colors"));

    WebElement dontCard = App.wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'rounded-xl')][.//h3[contains(.,\"Don't\")]]")
            )
    );
    Assert.assertTrue(dontCard.getText().contains("Alter logo proportions"));

    takeScreenshot("media_kit_guidelines_content");

    WebElement backBtn = App.wait.until(
    ExpectedConditions.presenceOfElementLocated(
        By.xpath("//button[@onclick='history.back()']")
    )
);

// scroll ke posisi aman
je.executeScript("arguments[0].scrollIntoView({block:'center'});", backBtn);

// klik via JS (bypass overlay)
je.executeScript("arguments[0].click();", backBtn);

}

@Attachment(value = "{name}", type = "image/png")
// ============================
// HELPER: SCREENSHOT ASSERT
// ============================
private static void takeScreenshot(String fileName) {
    try {
        File src = ((TakesScreenshot) App.driver).getScreenshotAs(OutputType.FILE);

        File dir = new File("screenshots");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File dest = new File(dir, fileName + "_" + timeStamp + ".png");

        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        System.out.println("Screenshot Error: " + e.getMessage());
    }
}
}

    

