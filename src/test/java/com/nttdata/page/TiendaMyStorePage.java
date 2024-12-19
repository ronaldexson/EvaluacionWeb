package com.nttdata.page;

import org.openqa.selenium.By;

public class TiendaMyStorePage {

    public static By enlaceIniciarSesion = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span");
    public static By txtUser = By.xpath("//input[@id=\"field-email\"]");
    public static By txtPassword = By.xpath("//input[@id=\"field-password\"]");
    public static By btnLogin = By.id("submit-login");

    public static By sCategoria = By.xpath("//*[@id=\"category-3\"]/a");
    public static By sSubCategoriaMen = By.xpath("//*[@id=\"left-column\"]/div[1]/ul/li[2]/ul/li[1]/a");
    public static By sSubCategoriaWomen = By.xpath("//*[@id=\"left-column\"]/div[1]/ul/li[2]/ul/li[2]/a");

    public static By selectProductDefault = By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[1]/a/picture/img");

    public static By txtCantidad = By.xpath("//*[@id=\"quantity_wanted\"]");
    public static By btnAddCarrito = By.xpath("//button[@class=\"btn btn-primary add-to-cart\"]");

     public static By textoConfirmacionPopup = By.xpath("//*[contains(text(), 'Producto añadido correctamente a su carrito de compra')]");

    public static By precioPopup = By.xpath("//p[@class=\"product-price\"]");
    public static By totalPopup = By.xpath("(//span[@class=\"value\"])[1]");

    public static By btnFinalizarCompra = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a");

    public static By txtValidoTitutloCarrito = By.xpath("//h1[contains(text(), 'Carrito')]");

    public static By precioFinal = By.xpath("//span[@class=\"price\"]");
    public static By totalFinal = By.xpath("(//span[@class=\"value\"])[5]");

    public static By validoLogin = By.xpath("//*[@id=\"content\"]/section/div/ul/li");

}
