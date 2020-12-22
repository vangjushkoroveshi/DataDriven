package Base;

import org.openqa.selenium.By;

public class WebConstants {
    public static final By USERNAME_INPUT = By.id("user-name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By PRODUCTS_LABEL = By.className("product_label");
    public static final By PRODUCTS_LIST = By.className("inventory_item");
    public static final By PRODUCTS_NAME = By.className("inventory_item_name");
    public static final By PRODUCTS_PRICE = By.className("inventory_item_price");
    public static final By ADD_TO_CART_BTN = By.className("btn_inventory");
    public static final By CART_BADGE = By.className("shopping_cart_badge");
    public static final By CART_ICONE = By.xpath("//*[@id='shopping_cart_container']/a");
    public static final By PRODUCT_IMG = By.xpath("//div[@class='inventory_details_container']/img");
    public static final By PRODUCTS_DETAILS_NAME = By.className("inventory_details_name");
    public static final By PRODUCTS_DETAILS_PRICE = By.className("inventory_details_price");

}
