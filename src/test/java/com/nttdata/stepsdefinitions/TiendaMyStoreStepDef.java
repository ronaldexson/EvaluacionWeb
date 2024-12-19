package com.nttdata.stepsdefinitions;

import com.nttdata.steps.TiendaMyStoreStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.WebDriver;

public class TiendaMyStoreStepDef {



    private WebDriver driver;
    TiendaMyStoreStep tiendaStep;


    @Dado("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        tiendaStep = new TiendaMyStoreStep(driver);
        tiendaStep.ingresarUrlTienda();

        
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String clave) {
        tiendaStep.iniciarSesion(usuario,clave);

    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subCategoria) {
        tiendaStep.seleccionoCategoriaSubCategoria(categoria,subCategoria);
    }



    @Y("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        tiendaStep.agregoCantidad(cantidad);
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmacionDelProductoAgregado() {
        tiendaStep.validoConfirmacionPopup();
        
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        tiendaStep.validoMontoTotalCorrectoPopup();
    }

    @Cuando("finalizo la compra")
    public void finalizoLaCompra() {
        tiendaStep.finalizarCompra();

    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        tiendaStep.validoTituloPagCarrito();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        tiendaStep.validoCalculoPrecioEnCarrito();
    }


}
