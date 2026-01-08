package com.selenium;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.util.Set;

public class scenario2 {

    public void NavPageNotFound(){
    Allure.step("Check Page Not Found for invalid URL", () -> {
        String mainWindow = App.driver.getWindowHandle();

        App.driver.navigate().to("http://phptravels.com/hshfhs");
        App.jedah(2);

        WebElement notFoundHeader = App.driver.findElement(
            By.xpath("/html/body/main/div/div/div[2]/h2")
        );

        String headerText = notFoundHeader.getText();
        Assert.assertEquals(headerText.trim(), "Page Not Found", "Halaman tidak menampilkan pesan Page Not Found");

        App.driver.switchTo().window(mainWindow);
        App.jedah(2);

        App.refreshToMainPage();
        App.jedah(2);
    });
}

    public void NavFooter(){
        Allure.step("Navigasi Footer", () -> {
            navigateFooterDemo();
            App.jedah(2);
            navigateFooterPricing();
            App.jedah(2);
            navigateFooterFeatures();
            App.jedah(2);
            navigateFooterTechnology();
            App.jedah(2);
            navigateFooterDocumentation();
            App.jedah(2);
            navigateFooterChangelog();
            App.jedah(2);
            navigateFooterRequirements();
            App.jedah(2);
            navigateFooterMobileApps();
            App.jedah(2);
            navigateFooterContentProgram();
            App.jedah(2);
            navigateFooterAboutUs();
            App.jedah(2);
            navigateFooterContact();
            App.jedah(2);
            navigateFooterJobs();
            App.jedah(2);
            navigateFooterBlog();
            App.jedah(1);
            App.refreshToMainPage();
            App.jedah(2);
            navigateFooterFaceBook();
            App.jedah(2);
            navigateFooterTwitter();
            App.jedah(2);
            navigateFooterLinkedIn();
            App.jedah(2);
            navigateFooterYoutube();
            App.jedah(2);
            navigateFooterInstagram();
            App.jedah(2);
        });
    }

    public void NavBlog(){
        Allure.step("Baca Blog", () -> {
            navigateOpenBlog();
            App.jedah(2);
            searchNotFountBlog();
            App.jedah(2);
            searchFountBlog();
            App.jedah(2);
            readBlog();
            App.jedah(2);
            App.refreshToMainPage();
            App.jedah(2);
        });
    }

     public void NavFeaturePage(){
        Allure.step("Links di FeaturesPage", () -> {
            navigateFooterFeatures();
            App.jedah(2);

            navigateB2BTravelPortal();
            App.jedah(2);
            navigateBackToPreviousPage();
            App.jedah(2);

            navigatePHPBookingSoftware();
            App.jedah(2);
            navigateBackToPreviousPage();
            App.jedah(2);

            navigateB2Cbookingsystem();
            App.jedah(2);
            navigateBackToPreviousPage();
            App.jedah(2);


            navigateTravelAgencyManagementSystem();
            App.jedah(2);

            navigateB2C_EnterpriseTravelSoftware();
            App.jedah(2);

            OpenAPI_Integration();
            App.jedah(2);


            navigateBackToPreviousPage();
            App.jedah(2);

            navigateTravelCRM();
            App.jedah(2);
            navigateBackToPreviousPage();
            App.jedah(2);

            navigateTravelManagementSoftware();
            App.jedah(2);

            navigateTourBackOffice();
            App.jedah(2);
            navigateBackToPreviousPage();
            App.jedah(2);

            navigateTourCorprateTravel();
            App.jedah(2);
            navigateBackToPreviousPage();
            App.jedah(2);

            navigateBusinessTravelManagement();
            App.jedah(2);
            navigateBackToPreviousPage();
            App.jedah(2);

            
            App.refreshToMainPage();
            App.jedah(2);
        });
    }

    public void NavHeaderOffersToCMS(){
        Allure.step("Header Navbar CMS, NewsletterModule, BlogsModule, OffersModule", () -> {
            navigateHeaderCMS();
            App.jedah(2);
            navigateHeaderNewsletterModule();
            App.jedah(2);
            navigateHeaderBlogsModule();
            App.jedah(2);
            navigateHeaderOffersModule();
            App.jedah(2);

            App.refreshToMainPage();
            App.jedah(2);
        });
    }

    public void NavHeaderFlightToCar(){
        Allure.step("Header Navbar Flight, hotel, tour, car module", () -> {
            navigateHeaderFlightModule();
            App.jedah(2);

            navigateFlightBookingSystem();
            App.jedah(2);


            navigateFlightBookingSoftware();
            App.jedah(2);


           navigateFlightBookingEngine();
            App.jedah(2);


            navigateTBOFlightsAPIIntegration();
            App.jedah(2);

            navigateNDCflightsBookingSystem();
            App.jedah(2);

            navigateFlightTicketReservationSystem();
            App.jedah(2);

            navigateFlightTicketBookingSoftware();
            App.jedah(2);

            navigateFlightAPIStrategy();
            App.jedah(2);


            navigateHeaderHotelModule();
            App.jedah(2);


            navigateHotelBookingPortal();
            App.jedah(2);

            navigateHotelBookingEngine();
            App.jedah(2);

            navigateHotelManagementSystemSoftware();
            App.jedah(2);

            navigateHotelCheckInSoftware();
            App.jedah(2);

            navigateHotelsAPIBookingSystem();
            App.jedah(2);
            
            navigateHotelAPIIntegrationWithBookingComAndPriceline();
            App.jedah(2);

            navigateB2BHotelBookingPortal();
            App.jedah(2);
            
            navigateHotelBedsAPI_XMLIntegration();
            App.jedah(2);


            App.refreshToMainPage();
            App.jedah(2);


            navigateHeaderTourModule();
            App.jedah(2);


            navigateHeaderCarModule();
            App.jedah(2);

            navigateB2BBookingEngine();
            App.jedah(2);

            navigateTravelBookingEngine();
            App.jedah(2);

            navigateBookingEngineSoftware();
            App.jedah(2);

            navigateMobileApps();
            App.jedah(2);

            navigateTravelWebsite();
            App.jedah(2);

            navigateMultiBranchSetup();
            App.jedah(2);

            navigateAccounting();
            App.jedah(2);

            navigateOnlineBookingSystem();
            App.jedah(2);

            StripeAPIIntegration();
            App.jedah(2);

            PaypalAPIIntegration();
            App.jedah(2);

            RazorPayAPIIntegration();
            App.jedah(2);

            navigateTravelAPIIntegration();
            App.jedah(2);

            navigateSharedHosting();
            App.jedah(2);

            navigateVPS();
            App.jedah(2);

            navigateDedicatedServers();
            App.jedah(2);

            App.refreshToMainPage();
            App.jedah(2);
        });

    }
    private void navigateDedicatedServers(){
        Allure.step("Navigasi Dedicated Servers", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[5]/div/div/div[1]/p/a[3]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateVPS(){
        Allure.step("Navigasi VPS", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[5]/div/div/div[1]/p/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateSharedHosting(){
        Allure.step("Navigasi shared hosting", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[5]/div/div/div[1]/p/a[1]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateTravelAPIIntegration(){
        Allure.step("Navigasi Travel API integration", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"reservations\"]/div/div/div[1]/div[2]/div[2]/div/p/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }
    
     private void navigateOnlineBookingSystem(){
        Allure.step("Navigasi online booking system", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"reservations\"]/div/div/div[1]/p/a[5]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

     private void navigateAccounting(){
        Allure.step("Navigasi Accounting", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"reservations\"]/div/div/div[1]/p/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void RazorPayAPIIntegration(){
        Allure.step("Navigasi Razor Pay API Integration lalu cek Page Not Found", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement firstLink = App.driver.findElement(
                By.xpath("//*[@id=\"reservations\"]/div/div/div[1]/div[2]/div[1]/div/p/a[3]")
            );
            firstLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah klik Reservation link");

            WebElement secondLink = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div[2]/div/div/div/div[2]/div[2]/a")
            );
            secondLink.click();
            App.jedah(2);

            WebElement notFoundHeader = App.driver.findElement(
                By.xpath("/html/body/main/div/div/div[2]/h2")
            );
            String headerText = notFoundHeader.getText();
            Assert.assertEquals(headerText.trim(), "Page Not Found", "Halaman tidak menampilkan pesan Page Not Found");

            App.driver.close(); 
            App.driver.switchTo().window(mainWindow);
            App.jedah(2);
        });
    }

    private void PaypalAPIIntegration(){
        Allure.step("Navigasi Paypal API Integration lalu cek Page Not Found", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement firstLink = App.driver.findElement(
                By.xpath("//*[@id=\"reservations\"]/div/div/div[1]/div[2]/div[1]/div/p/a[2]")
            );
            firstLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah klik Reservation link");

            WebElement secondLink = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div[2]/div/div/div/div[2]/div[2]/a")
            );
            secondLink.click();
            App.jedah(2);

            WebElement notFoundHeader = App.driver.findElement(
                By.xpath("/html/body/main/div/div/div[2]/h2")
            );
            String headerText = notFoundHeader.getText();
            Assert.assertEquals(headerText.trim(), "Page Not Found", "Halaman tidak menampilkan pesan Page Not Found");

            App.driver.close(); 
            App.driver.switchTo().window(mainWindow);
            App.jedah(2);
        });
    }

    private void StripeAPIIntegration(){
        Allure.step("Navigasi Stripe API Integration lalu cek Page Not Found", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement firstLink = App.driver.findElement(
                By.xpath("//*[@id=\"reservations\"]/div/div/div[1]/div[2]/div[1]/div/p/a[1]")
            );
            firstLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah klik Reservation link");

            WebElement secondLink = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div[2]/div/div/div/div[2]/div[2]/a")
            );
            secondLink.click();
            App.jedah(2);

            WebElement notFoundHeader = App.driver.findElement(
                By.xpath("/html/body/main/div/div/div[2]/h2")
            );
            String headerText = notFoundHeader.getText();
            Assert.assertEquals(headerText.trim(), "Page Not Found", "Halaman tidak menampilkan pesan Page Not Found");

            App.driver.close(); 
            App.driver.switchTo().window(mainWindow);
            App.jedah(2);
        });
    }


    private void navigateMultiBranchSetup(){
        Allure.step("Navigasi multi-branch setup", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"booking-system\"]/div/div/div[1]/p/a[4]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

     private void navigateTravelWebsite(){
        Allure.step("Navigasi travel website", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"booking-system\"]/div/div/div[1]/p/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }


    private void navigateMobileApps(){
        Allure.step("Navigasi Mobile apps", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/div[1]/a[4]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }


    private void navigateBookingEngineSoftware(){
        Allure.step("Navigasi Booking engine software", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/div[1]/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

   private void navigateTravelBookingEngine(){
        Allure.step("Navigasi Travel booking engine", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/div[1]/a[1]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateB2BBookingEngine(){
        Allure.step("Navigasi B2B Booking Engine", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/p/a[1]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }


    private void navigateHotelBookingPortal(){
        Allure.step("Navigasi hotel booking portal", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"hotel-module-overview\"]/div/div/div[1]/p/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

         private void navigateHotelBookingEngine(){
        Allure.step("Navigasi hotel booking engine", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/p/a[1]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

     private void navigateHotelManagementSystemSoftware(){
        Allure.step("Navigasi hotel management system software", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/p/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateHotelCheckInSoftware(){
        Allure.step("Navigasi hotel check in software", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"hotel-calendar-system\"]/div/div/div[2]/p[2]/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateHotelsAPIBookingSystem(){
        Allure.step("Navigasi hotels API booking system", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"hotel-calendar-system\"]/div/div/div[2]/div[2]/div[1]/div/div[2]/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateHotelAPIIntegrationWithBookingComAndPriceline(){
        Allure.step("Navigasi hotels API integration with Booking com and Priceline", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"hotel-key-features\"]/div/div[1]/p/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

     private void navigateB2BHotelBookingPortal(){
        Allure.step("Navigasi B2B hotel booking portal", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"hotel-key-features\"]/div/div[2]/div[7]/p/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

     private void navigateHotelBedsAPI_XMLIntegration(){
        Allure.step("Navigasi Hotelbeds API XML integration", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[5]/div/div[1]/p/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }


     private void navigateHeaderFlightModule() {
        Allure.step("Navigasi ke Header FlightModule", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='Flight Module']")
            );

            pageLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFlightBookingSystem(){
        Allure.step("Navigasi flight booking system ", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/p/a[1]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateFlightBookingSoftware(){
        Allure.step("Navigasi flight booking software ", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/p/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateFlightBookingEngine(){
        Allure.step("Navigasi flight booking engine", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/p/a[3]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateTBOFlightsAPIIntegration(){
        Allure.step("Navigasi TBO flights API integration", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/div[2]/a[1]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateFlightTicketReservationSystem(){
        Allure.step("Navigasi flight ticket reservation system", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"advanced-search\"]/div/div/div[2]/p/a[1]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateFlightTicketBookingSoftware(){
        Allure.step("Navigasi flight ticket booking software", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"advanced-search\"]/div/div/div[2]/p/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateNDCflightsBookingSystem (){
        Allure.step("Navigasi NDC flights booking system ", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div/div/div[1]/div[2]/a[2]")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateFlightAPIStrategy (){
        Allure.step("Navigasi flight API strategy ", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("//*[@id=\"source-overview\"]/div/p/a")
            );
            Link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String strategyTab = App.driver.getWindowHandle();
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            WebElement amadeusLink = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div/div/div[1]/p/a[1]")
            );
            amadeusLink.click();
            App.jedah(2);
            Assert.assertNotEquals(App.driver.getCurrentUrl(), newUrl, "Halaman tidak berubah setelah klik Amadeus");
            App.driver.navigate().back();
            App.jedah(2);

            WebElement sabreLink = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div/div/div[1]/p/a[2]")
            );
            sabreLink.click();
            App.jedah(2);
            Assert.assertNotEquals(App.driver.getCurrentUrl(), newUrl, "Halaman tidak berubah setelah klik Sabre");
            App.driver.navigate().back();
            App.jedah(2);

            WebElement travelportLink = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div/div/div[1]/p/a[3]")
            );
            travelportLink.click();
            App.jedah(2);
            Assert.assertNotEquals(App.driver.getCurrentUrl(), newUrl, "Halaman tidak berubah setelah klik Travelport");
            App.driver.navigate().back();
            App.jedah(2);

            App.driver.close(); // close the Flight API Strategy tab itself
            App.driver.switchTo().window(mainWindow);
            App.jedah(2);
        });
    }

     private void navigateHeaderHotelModule() {
        Allure.step("Navigasi ke Header HotelModule", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='Hotel Module']")
            );

            pageLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateHeaderTourModule() {
        Allure.step("Navigasi ke Header TourModule", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='Tour Module']")
            );

            pageLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void navigateHeaderCarModule() {
        Allure.step("Navigasi ke Header CarModule", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='Car Module']")
            );

            pageLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

        });
    }

    private void navigateHeaderOffersModule() {
        Allure.step("Navigasi ke Header OffersModule", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='Offers Module']")
            );

            pageLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateHeaderNewsletterModule() {
        Allure.step("Navigasi ke Header NewsletterModule", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='Newsletter Module']")
            );

            pageLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateHeaderBlogsModule() {
        Allure.step("Navigasi ke Header BlogsModule", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='Blogs Module']")
            );

            pageLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateHeaderCMS() {
        Allure.step("Navigasi ke Header CMS", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("//button[.//span[text()='Features']]")
            );

            Actions actions = new Actions(App.driver);
            actions.moveToElement(headerLink).perform();
            App.jedah(2);

            WebElement pageLink = App.driver.findElement(
                By.xpath("//a[text()='CMS Module']")
            );

            pageLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterDemo(){
        Allure.step("Navigasi ke Footer Demo", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[2]/ul/li[1]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterPricing(){
        Allure.step("Navigasi ke Footer Pricing", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[2]/ul/li[2]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateB2BTravelPortal(){
        Allure.step("Navigasi ke B2B Travel Portal", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div/div/div/div[2]/div/div/div[1]/p[1]/a[1]")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigatePHPBookingSoftware(){
        Allure.step("Navigasi ke PHP Booking Software", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div/div/div/div[2]/div/div/div[1]/p[1]/a[2]")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateB2Cbookingsystem(){
        Allure.step("Navigasi ke B2C booking system", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[2]/div/div/div/div[2]/div/div/div[1]/p[2]/a")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateTravelCRM(){
        Allure.step("Navigasi ke Travel CRM", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[5]/div/div/div/div[2]/p[1]/a")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

     private void navigateTravelManagementSoftware(){
        Allure.step("Navigasi ke Travel Management Software", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[5]/div/div/div/div[3]/a")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

     private void navigateTourBackOffice(){
        Allure.step("Navigasi ke tour operator back office system", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[4]/div/div/div[1]/p/a[2]")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

     private void navigateTourCorprateTravel(){
        Allure.step("Navigasi corporate travel management software", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[4]/div/div/div[1]/p/a[3]")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

     private void navigateBusinessTravelManagement(){
        Allure.step("Navigasi business travel management ", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[4]/div/div/div[1]/p/a[4]")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }


    private void navigateTravelAgencyManagementSystem(){
        Allure.step("Navigasi ke Travel Agency Management System", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement Link = App.driver.findElement(
                By.xpath("/html/body/main/section[4]/div/div/div[2]/div[5]/div[2]/div[1]/span[2]/a")
            );
            Link.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateB2C_EnterpriseTravelSoftware() {
        Allure.step("Navigasi ke B2C enterprise travel software", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div[2]/div/div[1]/div[1]/a[3]")
            );
            link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }

    private void OpenAPI_Integration() {
        Allure.step("Navigasi ke OpenAPI Integration", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement link = App.driver.findElement(
                By.xpath("//*[@id=\"overview\"]/div[2]/div/div[1]/div[1]/a[4]")
            );
            link.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(2);

            String firstNewUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(firstNewUrl, oldUrl, "Halaman tidak berubah setelah klik OpenAPI");

            WebElement travelApiLink = App.driver.findElement(
                By.xpath("//*[@id=\"what-is-travel-api\"]/div/div/div[1]/p/a")
            );
            travelApiLink.click();
            App.jedah(2);

            String firstTab = App.driver.getWindowHandle();
            switchToNewTab(firstTab);
            App.jedah(2);

            String secondNewUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(secondNewUrl, firstNewUrl, "Halaman tidak berubah setelah klik Travel API");

            switchBackToMainWindow(mainWindow);
            App.jedah(2);
        });
    }



    private void navigateFooterFeatures(){
        Allure.step("Navigasi ke Footer Features", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[2]/ul/li[3]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterTechnology(){
        Allure.step("Navigasi ke Footer Technology", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[2]/ul/li[4]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterDocumentation(){
        Allure.step("Navigasi ke Footer Documentation", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[3]/ul/li[1]/a")
            );
            footerLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(1);
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
            switchBackToMainWindow(mainWindow);
        });
    }

    private void navigateFooterChangelog(){
        Allure.step("Navigasi ke Footer Changelog", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[3]/ul/li[2]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterRequirements(){
        Allure.step("Navigasi ke Footer Requirements", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[3]/ul/li[3]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterMobileApps(){
        Allure.step("Navigasi ke Footer Mobile Apps", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[3]/ul/li[4]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterContentProgram(){
        Allure.step("Navigasi ke Footer Content Program", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[3]/ul/li[5]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterAboutUs(){
        Allure.step("Navigasi ke Footer About Us", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[4]/ul/li[1]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterContact(){
        Allure.step("Navigasi ke Footer Contact", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[4]/ul/li[2]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterJobs(){
        Allure.step("Navigasi ke Footer Jobs", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[4]/ul/li[3]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterBlog(){
        Allure.step("Navigasi ke Footer Blog", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[4]/ul/li[4]/a")
            );
            footerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateFooterFaceBook(){
        Allure.step("Navigasi ke Footer Facebook", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[1]/div[3]/a[1]")
            );
            footerLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(1);
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
            switchBackToMainWindow(mainWindow);
        });
    }

    private void navigateFooterTwitter(){
        Allure.step("Navigasi ke Footer Twitter", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[1]/div[3]/a[2]")
            );
            footerLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(1);
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
            switchBackToMainWindow(mainWindow);
        });
    }

    private void navigateFooterLinkedIn(){
        Allure.step("Navigasi ke Footer LinkedIn", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[1]/div[3]/a[3]")
            );
            footerLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(1);
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
            switchBackToMainWindow(mainWindow);
        });
    }

    private void navigateFooterYoutube(){
        Allure.step("Navigasi ke Footer Youtube", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[1]/div[3]/a[4]")
            );
            footerLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(1);
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
            switchBackToMainWindow(mainWindow);
        });
    }

    private void navigateFooterInstagram(){
        Allure.step("Navigasi ke Footer Instagram", () -> {
            String mainWindow = App.driver.getWindowHandle();
            String oldUrl = App.driver.getCurrentUrl();

            WebElement footerLink = App.driver.findElement(
                By.xpath("/html/body/footer/div/div[1]/div[1]/div[3]/a[5]")
            );
            footerLink.click();
            App.jedah(2);

            switchToNewTab(mainWindow);
            App.jedah(1);
            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
            switchBackToMainWindow(mainWindow);
        });
    }

    private void navigateOpenBlog(){
        Allure.step("Buka Halaman Blog", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement headerLink = App.driver.findElement(
                By.xpath("/html/body/header/div[1]/div/nav/a[1]")
            );
            headerLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void searchNotFountBlog() {
        Allure.step("Cari Artikel Blog yang Tidak Ditemukan", () -> {
            WebElement search = App.driver.findElement(
                By.xpath("//*[@id=\"blog-search\"]")
            );
            search.clear();
            search.sendKeys("test123");

            App.jedah(2);

            WebElement resultCount = App.driver.findElement(
                By.xpath("//*[@id=\"search-results-count\"]")
            );

            Assert.assertEquals(
                resultCount.getText().trim(),
                "No articles found",
                "Search results message did not match expected 'No articles found'"
            );
        });
    }

    private void searchFountBlog() {
        Allure.step("Cari Artikel Blog yang Ditemukan", () -> {
            WebElement search = App.driver.findElement(
                By.xpath("//*[@id=\"blog-search\"]")
            );
            search.clear();
            search.sendKeys("test");

            App.jedah(2);

            WebElement resultCount = App.driver.findElement(
                By.xpath("//*[@id=\"search-results-count\"]")
            );

            Assert.assertEquals(
                resultCount.getText().trim(),
                "1 article found",
                "Search results message did not match expected '1 article found'"
            );
        });
    }
    
    private void readBlog(){
        Allure.step("Baca Artikel Blog", () -> {
            String oldUrl = App.driver.getCurrentUrl();

            WebElement blogLink = App.driver.findElement(
                By.xpath("//*[@id=\"blog-grid\"]/article[13]/div/div[2]/a")
            );
            blogLink.click();
            App.jedah(2);

            String newUrl = App.driver.getCurrentUrl();
            Assert.assertNotEquals(newUrl, oldUrl, "Halaman tidak berubah setelah di klik");
        });
    }

    private void navigateBackToPreviousPage() {
    Allure.step("Kembali ke halaman sebelumnya", () -> {
        App.driver.navigate().back();
    });
}


    private void switchBackToMainWindow(String mainWindow){
        Set<String> allWindows = App.driver.getWindowHandles();
        
        for(String window : allWindows){
            if(!window.equals(mainWindow)){
                App.driver.switchTo().window(window);
                App.driver.close();
            }
        }
        
        App.driver.switchTo().window(mainWindow);
    }

    private void switchToNewTab(String mainWindow){
        Set<String> allWindows = App.driver.getWindowHandles();
        
        for(String window : allWindows){
            if(!window.equals(mainWindow)){
                App.driver.switchTo().window(window);
                break;
            }
        }
    }
}