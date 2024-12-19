#language: es
Característica: : Product - Store

  @prueba
Esquema del escenario: Validación del precio de un producto
Dado estoy en la página de la tienda
Y me logueo con mi usuario "<user>" y clave "<clave>"
Cuando navego a la categoria "<categoria>" y subcategoria "<subCategoria>"
Y agrego <cantidad> unidades del primer producto al carrito
Entonces valido en el popup la confirmación del producto agregado
Y valido en el popup que el monto total sea calculado correctamente
Cuando finalizo la compra
Entonces valido el titulo de la pagina del carrito
Y vuelvo a validar el calculo de precios en el carrito
    Ejemplos:
      | user                     | clave          | cantidad | categoria | subCategoria |
      | ronald_exson@hotmail.com | formulario1234 | 2        | CLOTHES   | Men          |
      | ronald_exson@hotmail.com | incorrecto4    | 5        | CLOTHES   | Men          |
      | ronald_exson@hotmail.com | formulario1234 | 5        | Auto      | Men          |