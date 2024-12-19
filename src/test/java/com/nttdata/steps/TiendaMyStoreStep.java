package com.nttdata.steps;

import com.nttdata.page.TiendaMyStorePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class TiendaMyStoreStep {

    WebDriver driver;
    private int reservaCantidad = 0;
    WebDriverWait wait;



    public TiendaMyStoreStep(WebDriver driver) {
        this.driver = driver;

    }

    public void ingresarUrlTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");
        screenShot();
    }

    public void iniciarSesion(String usuario, String clave) {
        WebElement enlaceInicioSesion = driver.findElement(TiendaMyStorePage.enlaceIniciarSesion);
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.enlaceIniciarSesion));
        enlaceInicioSesion.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.txtUser));
        WebElement txtUser = driver.findElement(TiendaMyStorePage.txtUser);
        txtUser.sendKeys(usuario);


        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.txtPassword));
        WebElement txtPassword = driver.findElement(TiendaMyStorePage.txtPassword);
        txtPassword.sendKeys(clave);


        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.btnLogin));
        WebElement btnLogin = driver.findElement(TiendaMyStorePage.btnLogin);
        btnLogin.click();


        try {
            // Intentar encontrar el elemento usando XPath
            WebElement validoLogin = driver.findElement(TiendaMyStorePage.validoLogin);
            throw new RuntimeException("Login no valido");
        } catch (NoSuchElementException e) {
            // Si el elemento no se encuentra, se captura la excepción
        }

    }

    public void seleccionoCategoriaSubCategoria(String categoria, String subCategoria) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        WebElement sCategoria = driver.findElement(TiendaMyStorePage.sCategoria);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.sCategoria));



        String textoCategoria = sCategoria.getText();

        System.out.println("Categoria es: " + textoCategoria);
        Assert.assertEquals("Categoria debe ser CLOTHES: ", categoria, textoCategoria);
        sCategoria.click();


        if (subCategoria.contains("Men")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.sSubCategoriaMen));
            WebElement sSubCategoriaMen = driver.findElement(TiendaMyStorePage.sSubCategoriaMen);
            sSubCategoriaMen.click();
        }
        if (subCategoria.contains("Women")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.sSubCategoriaWomen));
            WebElement sSubCategoriaWomen = driver.findElement(TiendaMyStorePage.sSubCategoriaWomen);
            sSubCategoriaWomen.click();
            Assert.assertEquals("Solo se contemplo la subCategoria Men y se obtuvo :" + subCategoria, "Men", subCategoria);

        }


    }


    public void agregoCantidad(int cantidad) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.selectProductDefault));
        WebElement productDefault = driver.findElement(TiendaMyStorePage.selectProductDefault);

        productDefault.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.txtCantidad));
        WebElement txtCantidad = driver.findElement(TiendaMyStorePage.txtCantidad);


        txtCantidad.sendKeys(Keys.CONTROL + "a");  // Selecciona todo el texto
        txtCantidad.sendKeys(Keys.BACK_SPACE);
        txtCantidad.sendKeys(String.valueOf(cantidad));
        reservaCantidad = cantidad;

        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.btnAddCarrito));
        WebElement btnAddCarrito = driver.findElement(TiendaMyStorePage.btnAddCarrito);

        btnAddCarrito.click();


    }


    public void validoConfirmacionPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.textoConfirmacionPopup));
        WebElement textoConfirmacionPopup = driver.findElement(TiendaMyStorePage.textoConfirmacionPopup);
        String textoPopupConfirmacion = textoConfirmacionPopup.getText();
        Assert.assertTrue(textoPopupConfirmacion.contains("Producto añadido correctamente a su carrito de compra"));


    }

    public void metodoCalculoMontoTotal(By byPrecioActual, By byPrecioTotal){
        wait.until(ExpectedConditions.visibilityOfElementLocated(byPrecioActual));
        WebElement precioPopup = driver.findElement(byPrecioActual);
        double precioActual = Double.parseDouble(precioPopup.getText().replaceAll("[^0-9.]", ""));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byPrecioTotal));
        WebElement totalPopup = driver.findElement(byPrecioTotal);
        double totalActual = Double.parseDouble(totalPopup.getText().replaceAll("[^0-9.]", ""));
        double calculoMulti = totalActual / reservaCantidad;
        Assert.assertEquals("Validar que la la división del total con la cantidad  dara el precio Actual: ", precioActual, calculoMulti, 0.0001);
        System.out.println("Precio actual " + precioActual + ", es igual a la división del total " + totalActual + " con la cantidad " + reservaCantidad + ", dando el precio del producto " +  calculoMulti);


    }


    public void validoMontoTotalCorrectoPopup() {
        metodoCalculoMontoTotal(TiendaMyStorePage.precioPopup,TiendaMyStorePage.totalPopup);

    }

    public void finalizarCompra() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.btnFinalizarCompra));
        WebElement btnFinalizarCompra = driver.findElement(TiendaMyStorePage.btnFinalizarCompra);
        btnFinalizarCompra.click();


    }


    public void validoTituloPagCarrito() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TiendaMyStorePage.txtValidoTitutloCarrito));
        WebElement txtValidoTitutloCarrito = driver.findElement(TiendaMyStorePage.txtValidoTitutloCarrito);
        Assert.assertEquals("Valido titulo : ", "CARRITO", txtValidoTitutloCarrito.getText());
        System.out.println("Titulo página Carrito: "+txtValidoTitutloCarrito.getText());

    }

    public void validoCalculoPrecioEnCarrito() {
        metodoCalculoMontoTotal(TiendaMyStorePage.precioFinal,TiendaMyStorePage.totalFinal);

    }
}
