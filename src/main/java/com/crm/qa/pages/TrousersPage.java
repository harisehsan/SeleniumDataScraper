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

public class TrousersPage extends TestBase {
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


    List <String> TrousersProductNamelst = new ArrayList<>();
   // List<String> TrousersProductPricelst = new ArrayList<>();
    List<String> TrousersImageUrllst = new ArrayList<>();
    List<String> TrousersAffiliateLinks = new ArrayList<>();

    String pageLink = "https://www.amazon.co.uk/Best-Sellers-Clothing-Mens-Trousers/zgbs/clothing/1731030031/ref=zg_bs_nav_ap_2_1730929031";

  public TrousersPage() throws FileNotFoundException {
        PageFactory.initElements(driver, this);
    }

    private void goToTrousersCatalogPage()
    {
        driver.navigate().to(pageLink);
    }

    public void getTrousersdata() throws FileNotFoundException, InterruptedException {

      goToTrousersCatalogPage();
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
            goToTrousersCatalogPage();
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
                    TrousersImageUrllst.add(imagelnk);
                }
            } catch (Exception e) {
                e.printStackTrace();
                TrousersImageUrllst.add("N/A");
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
                TrousersProductNamelst.add(proTitle);
        } catch (Exception e) {
            e.printStackTrace();
            TrousersProductNamelst.add("N/A");
        }
//        if (waitWithoutExceptionUntilPresentOfElementByLessTime(productPriceBy))
//            TrousersProductPricelst.add(productPrice.get(0).getText());
//        else if (waitWithoutExceptionUntilPresentOfElementByLessTime(salePriceBy))
//            TrousersProductPricelst.add(salePrice.getText());
//        else
//            TrousersProductPricelst.add("N/A");
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
            TrousersAffiliateLinks.add(affiliateLink);
        } catch (Exception e) {
            e.printStackTrace();
            TrousersAffiliateLinks.add("N/A");
        }

    }

    private void addingDataToCSV1() throws FileNotFoundException {
        try {
            PrintWriter pw = new PrintWriter(new File("D:\\Haris\\man fashion Web site\\Trousers.csv"));
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
          for (int i =0; i < (TrousersProductNamelst.size()); i++) {
              sb.append(i+1);
              sb.append(",");
              sb.append(TrousersProductNamelst.get(i));
//              sb.append(",");
//              sb.append(TrousersProductPricelst.get(i));
              sb.append(",");
              sb.append(TrousersImageUrllst.get(i));
              sb.append(",");
              sb.append(TrousersAffiliateLinks.get(i));
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
            PrintWriter pw = new PrintWriter(new File("D:\\Haris\\man fashion Web site\\TTrousers2.csv"));
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
            for (int i = (TrousersProductNamelst.size()/2); i < (TrousersProductNamelst.size()); i++) {
                sb.append(i+1);
                sb.append(",");
                sb.append(TrousersProductNamelst.get(i));
//              sb.append(",");
//              sb.append(TrousersProductPricelst.get(i));
                sb.append(",");
                sb.append(TrousersImageUrllst.get(i));
                sb.append(",");
                sb.append(TrousersAffiliateLinks.get(i));
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
        for (int i=0; i < TrousersProductNamelst.size(); i++) {
            System.out.println(" "+ TrousersProductNamelst.get(i)+"  "+TrousersAffiliateLinks.get(i) +"  "+TrousersImageUrllst.get(i));
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
