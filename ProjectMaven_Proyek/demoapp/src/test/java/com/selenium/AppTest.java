package com.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

    // template tes
    // @Test
    // public void testSayHello(){
    //     // === Assert.assertEquals('input','pembanding') // untuk membandingkan expected result dengan actal result
    //     // App app=new App(); // ini cara deklarasi
    //     // String result=app.sayHello();
    //     // Assert.assertEquals("YApping", "He Dupe Joke A Wee");
    // }

    @Test
    public void testAddNumbers(){
        App app=new App();
        int hasil=app.addNumbers(3, 2);
        Assert.assertEquals(hasil, 10);
    }
}
