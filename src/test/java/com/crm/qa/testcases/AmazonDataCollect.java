/*
 * @author Haris Ehsan
 * 
 */

package com.crm.qa.testcases;

import com.crm.qa.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

import java.io.FileNotFoundException;

public class AmazonDataCollect extends TestBase{
	TestUtil testUtil;
	TShirtsPage TShirtsPage;
	LoginPage loginPage;
    BeltsPage beltsPage;
    BoxerPage boxerPage;
    CardigansPage cardigansPage;
    CoatsPage coatsPage;
    FootwearsPage footwearsPage;
    GiletsPage giletsPage;
    HatsPage hatsPage;
    HoodiePage hoodiePage;
    JacketsPage jacketsPage;
    JeansPage jeansPage;
    ShirtsPage shirtsPage;
    SleevesPage sleevesPage;
    SlippersPage slippersPage;
    SweatshirtsPage sweatshirtsPage;
    ThermalPage thermalPage;
    TrousersPage trousersPage;
    VestsPage vestsPage;
    WatchesPage watchesPage;
    WintersPage wintersPage;



    String sheetName = "contacts";

    public AmazonDataCollect(){
        super();
    }

	
	
	@BeforeMethod
	public void setUp() throws InterruptedException, FileNotFoundException {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
        TShirtsPage = new TShirtsPage();
        beltsPage = new BeltsPage();
        boxerPage = new BoxerPage();
        cardigansPage = new CardigansPage();
        coatsPage = new CoatsPage();
        footwearsPage = new FootwearsPage();
        giletsPage = new GiletsPage();
        hatsPage = new HatsPage();
        hoodiePage = new HoodiePage();
        jacketsPage = new JacketsPage();
        jeansPage = new JeansPage();
        shirtsPage = new ShirtsPage();
        sleevesPage = new SleevesPage();
        slippersPage = new SlippersPage();
        sweatshirtsPage = new SweatshirtsPage();
        thermalPage = new ThermalPage();
        trousersPage = new TrousersPage();
        vestsPage = new VestsPage();
        watchesPage = new WatchesPage();
        wintersPage = new WintersPage();

	}
	
	@Test(priority=1)
	public void dataCollector() throws FileNotFoundException, InterruptedException {
        loginPage.signInToAmazon();
        TShirtsPage.getShirtsdata();
//        beltsPage.getBeltsdata();
        boxerPage.getBoxerdata();
        cardigansPage.getCardigansdata();
        coatsPage.getCoatsdata();
        footwearsPage.getFootwearsdata();
        giletsPage.getGiletsdata();
        hatsPage.getHatsdata();
        hoodiePage.getHoodiedata();
        jacketsPage.getJacketsdata();
        jeansPage.getJeansdata();
        shirtsPage.getSleevesdata();
        sleevesPage.getSleevesdata();
        slippersPage.getSlippersdata();
        sweatshirtsPage.getSweatshirtsdata();
        thermalPage.getThermaldata();
        trousersPage.getTrousersdata();
        vestsPage.getVestsdata();
        watchesPage.getWatchesdata();
        wintersPage.getWintersdata();
	}


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


//
	
	
	
	
}
