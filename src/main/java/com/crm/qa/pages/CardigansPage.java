package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CardigansPage extends TestBase {
    @FindBy(className = "p13n-sc-truncated") public List<WebElement> catalogProductNamelbl;
    @FindBy(id = "sp-cc-accept") public List <WebElement> cookieAcceptBtn;
    @FindBy(id = "glow-ingress-line1") public WebElement locationNameHeaderlbl;
    @FindBy(id = "GLUXSignInButton") public List <WebElement> addressbtn;
    @FindBy(id = "GLUXZipUpdateInput") public WebElement postalCodetxt;
    @FindBy(css = "#GLUXZipUpdate .a-button-input") public WebElement postalSubmitButton;
    @FindBy(id = "glow-ingress-line2") public WebElement locationNameFooterlbl;
    @FindBy(id = "productTitle") public WebElement productTitle;
    @FindBy(id = "priceblock_ourprice") public List <WebElement> productPrice;
    @FindBy(id = "landingImage") public WebElement productImage;
    @FindBy(id = "nav-link-accountList") public WebElement signInbtn;
    @FindBy (id = "ap_email") public WebElement emailtxt;
    @FindBy (id = "continue") public WebElement continuebtn;
    @FindBy (id = "ap_password") public WebElement passwordtxt;
    @FindBy (id = "signInSubmit") public WebElement signInSubmit;
    @FindBy(css = "a[title='Text']") public WebElement textLinkbutton;
    @FindBy(id = "amzn-ss-text-shortlink-textarea") public WebElement affiliateShortLlink;
    @FindBy(id = "//span[contains(text(),'Short Link')]") public WebElement shortLink;
    @FindBy(id = "priceblock_saleprice") public WebElement salePrice;
    @FindBy(xpath = "//li[@class='zg-item-immersion']") public List <WebElement> catalogSection;

    public By catalogProductNamelblBy = By.className("p13n-sc-truncated");
    public By cookieAcceptBtnBy = By.id("sp-cc-accept");
    public By postalCodetxtBy = By.id("GLUXZipUpdateInput");
    public By postalSubmitButtonBy = By.cssSelector("#GLUXZipUpdate .a-button-input");
    public By productTitleBy = By.id("productTitle");
    public By productPriceBy = By.id("priceblock_ourprice");
    public By locationNameFooterlblBy = By.id("glow-ingress-line2");
    public By UklocationFooterlblBy = By.xpath("//span[contains(text(),'London WC2N 5DU')]");
    public By signInbtnBy = By.id("nav-link-accountList");
    public By emailtxtBy = By.id("ap_email");
    public By passwordtxtBy = By.id("ap_password");
    public By affiliateShortLinkBy = By.id("amzn-ss-text-shortlink-textarea");
    public By affiliateLinkTextBy = By.xpath("//*[contains(text(),'https://amzn.to')]");
    public By salePriceBy = By.id("priceblock_saleprice");


    List <String> CardigansProductNamelst = new ArrayList<>();
   // List<String> CardigansProductPricelst = new ArrayList<>();
    List<String> CardigansImageUrllst = new ArrayList<>();
    List<String> CardigansAffiliateLinks = new ArrayList<>();

    String pageLink = "https://www.amazon.co.uk/Best-Sellers-Clothing-Mens-Cardigans/zgbs/clothing/1730983031/ref=zg_bs_nav_ap_3_1730982031";

  public CardigansPage() throws FileNotFoundException {
        PageFactory.initElements(driver, this);
    }

    private void goToCardigansCatalogPage()
    {
        driver.navigate().to(pageLink);
    }

    public void getCardigansdata() throws FileNotFoundException, InterruptedException {

      goToCardigansCatalogPage();
      waitUntilPageReady();
      waitUntilPresentOfElementBy(catalogProductNamelblBy);
        getImages();
      for (int i=0;i<(catalogProductNamelbl.size()/2);i++)
        {
            waitUntilPresentOfElementBy(catalogProductNamelblBy);

            catalogProductNamelbl.get(i).click();
            waitUntilPageReady();
//            if (i==0) {
//              //  checkForAcceptCookie();
//                changeLocationToLondon();
//            }
            StoreCatelogData();
            goToCardigansCatalogPage();
//            waitUntilPageReady();
        }
        addingDataToCSV1();
//        addingDataToCSV2();
     //   showCatalogData();
    }

    private void getImages() {
        for (int i=1;i<=26;i++) {
            try {
               if(i!=7){
                    String imagelnk = driver.findElement(By.cssSelector(".zg-item-immersion:nth-child("+(i)+") img")).getAttribute("src");
                    if (imagelnk.contains(","))
                        imagelnk = imagelnk.replaceAll(",", "+++");
                    CardigansImageUrllst.add(imagelnk);
                }
            } catch (Exception e) {
                e.printStackTrace();
                CardigansImageUrllst.add("N/A");
            }
        }
    }
    
    private void StoreCatelogData()
    {
        String proTitle = "";
        String affiliateLink = "";
        int tries = 0;
        waitUntilPresentOfElementBy(productTitleBy);
        try {
            proTitle = productTitle.getText();
            if (proTitle.contains(","))
            {
                proTitle = productTitle.getText().replaceAll(","," & ");
            }
            if (proTitle.length() > 80)
            {
                proTitle = proTitle.substring(0,80);
                proTitle = proTitle.concat("...");
            }
                CardigansProductNamelst.add(proTitle);
        } catch (Exception e) {
            e.printStackTrace();
            CardigansProductNamelst.add("N/A");
        }
//        if (waitWithoutExceptionUntilPresentOfElementByLessTime(productPriceBy))
//            CardigansProductPricelst.add(productPrice.get(0).getText());
//        else if (waitWithoutExceptionUntilPresentOfElementByLessTime(salePriceBy))
//            CardigansProductPricelst.add(salePrice.getText());
//        else
//            CardigansProductPricelst.add("N/A");
        try {
          do {
              textLinkbutton.click();
          }
           while(!waitWithoutExceptionUntilPresentOfElementByLessTime(affiliateShortLinkBy));
                waitUntilPresentOfElementBy(affiliateShortLinkBy);
            do {
                waitUntilPresentOfElementBy(affiliateLinkTextBy);
                affiliateLink = affiliateShortLlink.getText();
                tries++;
            } while (affiliateLink.equalsIgnoreCase("") && tries < 20);
            CardigansAffiliateLinks.add(affiliateLink);
        } catch (Exception e) {
            e.printStackTrace();
            CardigansAffiliateLinks.add("N/A");
        }

    }

    private void addingDataToCSV1() throws FileNotFoundException {
        try {
            PrintWriter pw = new PrintWriter(new File("D:\\Haris\\man fashion Web site\\Cardigans.csv"));
            StringBuilder sb = new StringBuilder();
            sb.append("Sr.No.");
            sb.append(",");
            sb.append("Product Name");
            sb.append(",");
//            sb.append("Price");
//            sb.append(",");
            sb.append("Image URL");
            sb.append(",");
            sb.append("Affiliate URL");
            sb.append("\r\n");
          for (int i =0; i < (CardigansProductNamelst.size()); i++) {
              sb.append(i+1);
              sb.append(",");
              sb.append(CardigansProductNamelst.get(i));
//              sb.append(",");
//              sb.append(CardigansProductPricelst.get(i));
              sb.append(",");
              sb.append(CardigansImageUrllst.get(i));
              sb.append(",");
              sb.append(CardigansAffiliateLinks.get(i));
              sb.append("\r\n");
          }
            pw.write(sb.toString());
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(">>>>>>>>>>>>>> Hello file not found");
        }
    }

    private void addingDataToCSV2() throws FileNotFoundException {
        try {
            PrintWriter pw = new PrintWriter(new File("D:\\Haris\\man fashion Web site\\TCardigans2.csv"));
            StringBuilder sb = new StringBuilder();
            sb.append("Sr.No.");
            sb.append(",");
            sb.append("Product Name");
            sb.append(",");
//            sb.append("Price");
//            sb.append(",");
            sb.append("Image URL");
            sb.append(",");
            sb.append("Affiliate URL");
            sb.append("\r\n");
            for (int i = (CardigansProductNamelst.size()/2); i < (CardigansProductNamelst.size()); i++) {
                sb.append(i+1);
                sb.append(",");
                sb.append(CardigansProductNamelst.get(i));
//              sb.append(",");
//              sb.append(CardigansProductPricelst.get(i));
                sb.append(",");
                sb.append(CardigansImageUrllst.get(i));
                sb.append(",");
                sb.append(CardigansAffiliateLinks.get(i));
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(">>>>>>>>>>>>>> Hello file not found");
        }
    }

    private void showCatalogData()
    {
        for (int i=0; i < CardigansProductNamelst.size(); i++) {
            System.out.println(" "+ CardigansProductNamelst.get(i)+"  "+CardigansAffiliateLinks.get(i) +"  "+CardigansImageUrllst.get(i));
        }
    }

    private void changeLocationToLondon() {
      waitUntilPageReady();
      waitUntilPresentOfElementBy(locationNameFooterlblBy);
        while (!locationNameFooterlbl.getText().toLowerCase().contains("london"))
        {
            locationNameHeaderlbl.click();
            waitUntilPresentOfElementBy(postalCodetxtBy);
            postalCodetxt.clear();
            postalCodetxt.sendKeys("WC2N 5DU");
            waitUntilClickable(postalSubmitButtonBy);
            postalSubmitButton.click();
            waitUntilPageReady();
            waitWithoutExceptionUntilPresentOfElementByLessTime(UklocationFooterlblBy);
        }
    }

    private void checkForAcceptCookie() {
        if (isExist(cookieAcceptBtnBy))
            cookieAcceptBtn.get(0).click();
    }

}
