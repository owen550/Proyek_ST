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
    public void scenario1(){
        
    }

    @Test(priority = 2) // mikhail
    public void scenario2(){
        
    }

    @Test(priority = 3) // owen
    public void scenario3(){
        
    }

    @AfterClass
    public void end(){
        App.jedah(10); // jedah 10s
        App.quitDriver(); // tutup
    }
}
