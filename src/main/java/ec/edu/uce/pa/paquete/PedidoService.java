package ec.edu.uce.pa.paquete;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped // Delego el control al contenedor ArC
// @RequestScoped // Para aplicaciones web
// @Singleton
public class PedidoService {

    @Inject
    @Named("mail")
    private Notificacion notificacionCorreo;

    @Inject
    @Named("sms")
    private Notificacion notificacionSMS;

    public void realizar(Pedido pedido) {

        System.out.println("realizando pedido....");
        System.out.println("Pedido realizado para el cliente: " + pedido.getCliente());
        System.out.println("Producto: " + pedido.getProducto());
        System.out.println("Total: " + pedido.getTotal());
        System.out.println("Correo: " + pedido.getCorreo());
        System.out.println("Pedido realizado con éxito.");

        String cuerpo = "Estimado " + pedido.getCliente() + ", su pedido de " +
                pedido.getProducto() + " por un total de $" + pedido.getTotal() +
                " ha sido realizado con éxito.";

        // Notificación de pedido por correo electrónico
        this.notificacionCorreo.enviar(pedido.getCorreo(), cuerpo);
        this.notificacionCorreo.setCantidad(this.notificacionCorreo.getCantidad() + 1);
        System.out.println("Cantidad de correos enviados Pedido: " + this.notificacionCorreo.getCantidad());
        this.notificacionCorreo.setNombre("Correo de Pedido");

        // Notificación de pedido por SMS
        this.notificacionSMS.enviar(pedido.getTelefono(), cuerpo);
    }
}
