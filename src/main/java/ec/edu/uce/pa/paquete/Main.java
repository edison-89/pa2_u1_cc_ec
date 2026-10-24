package ec.edu.uce.pa.paquete;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Inject
    private PedidoService perdidoService;

    @Inject 
    private CompraService compraService;
    public static void main(String[] args) {
        Quarkus.run(Main.class, args);
    }

    @Override
    public int run(String... args) {
        System.out.println("Iniciando la aplicación Quarkus...");

        Pedido pedido = new Pedido("Juan Pérez", "Laptop", 1500.00, "juan.perez@example.com"); // creamos manualmente el
        pedido.setTelefono("098676565555");
        this.perdidoService.realizar(pedido);

        Compra compra = new Compra("María López", "Smartphone", 800.00, "maria.lopez@example.com");
        compra.setTelefono("098676565555");
        this.compraService.realizar(compra);       

        return 0;
    }
}
