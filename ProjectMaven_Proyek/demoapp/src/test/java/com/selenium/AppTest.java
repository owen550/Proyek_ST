package com.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {

    // === CATATAN ====
    // App.jedah(...) = ngasih jeda ... detik
    // App -> ini static class langsung bisa di pakek

    @BeforeClass
    public void start(){
        App.startDriver(); // inisiasi web driver
        App.driver.get("https://phptravels.com/"); 
        App.jedah(2); // ini buat jedah 2 detik, ntik sesuaikan masing masing !!!
    }

    @Test(priority = 1) // ricard
    public void scenario1() throws Exception{
        scenario1.run();
    }

    @Test(priority = 2) // mikael
    public void scenario2(){
        scenario2 sc2 = new scenario2();
        sc2.NavFooter();
        sc2.NavBlog();
        sc2.NavFeaturePage();
        sc2.NavHeaderOffersToCMS();
        sc2.NavHeaderFlightToCar();
        sc2.NavPageNotFound();
    }

    @Test(priority = 3) // owen
    public void scenario3(){
        // === deklarasi ===
        scenario3 sc3 = new scenario3();
        sc3.prepareTest();
        
        // === register ===
        sc3.testRegister(false);// email dan register password confirm salah
        App.jedah(2);
        
        sc3.testRegister(true);// register yang benar
        App.jedah(2);
        
        sc3.testRegister(true);// register ke akun yang sudah ada
        App.jedah(2);

        // === login ===
        sc3.testForgetPass(); // login klik forget password benar dan salah
        App.jedah(1);

        sc3.testLogin("email"); // login dengan format email yang salah
        App.jedah(1);
        App.refreshToMainPage();
        App.jedah(1);
        
        sc3.testLogin("email_salah"); // login dengan data yang salah
        App.jedah(1);
        App.refreshToMainPage();
        App.jedah(1);
        
        sc3.testLogin("benar"); // login dengan data yang benar

        // === pasca login ===
        sc3.testPascaLogin();
        App.refreshToMainPage();
        App.jedah(1);

        // === test demo salah ===
        sc3.testDemo(false);
        App.refreshToMainPage();
        App.jedah(1);

        // === test demo benar ===
        sc3.testDemo(true);
        App.refreshToMainPage();
        App.jedah(1);

        // === production Requirement ===
        sc3.testRequirement();

        // === production Technology ===
        sc3.testTechnology();

        // === production Customization ===
        sc3.testCustomizations();

        // === product Integrations ===
        sc3.testIntegrations();

        // === product Themes ===
        sc3.testThemes();

        // === product doc ===
        sc3.testProduct();

    }

    @AfterClass
    public void end(){
        App.jedah(4); // jedah 10s
        App.quitDriver(); // tutup
    }
}
