import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pedido {
    private List<Pizza> pizzas;
    private static Set<Integer> idsGerados = new HashSet<>(); // Conjunto para rastrear os IDs únicos
    private int idPedido;
    private double valorTotal;
    private EstadoPedido estado; // Atributo para o estado do pedido

    // Enum para os estados do pedido
    public enum EstadoPedido {
        ABERTO,
        A_CAMINHO,
        ENTREGUE
    }

    // Construtor
    public Pedido() {
        this.pizzas = new ArrayList<>();
        this.idPedido = gerarIdUnico(); // Gera um ID único
        this.estado = EstadoPedido.ABERTO; // Inicializa o estado como ABERTO
    }

    private int gerarIdUnico() {
        int id;
        do {
            id = ThreadLocalRandom.current().nextInt(1, 1000000); // Gera um ID entre 1 e 1.000.000
        } while (idsGerados.contains(id)); // Garante que não seja repetido
        idsGerados.add(id); // Adiciona o ID ao conjunto de IDs gerados
        return id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removerPizza(int index) {
        if (index >= 0 && index < pizzas.size()) {
            pizzas.remove(index);
        }
    }

    public double calcularPrecoTotal() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.calcularPreco();
        }
        BigDecimal totalFormatado = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        valorTotal = totalFormatado.doubleValue();
        return totalFormatado.doubleValue();
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public EstadoPedido getEstado() {
        return estado; // Método para obter o estado do pedido
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado; // Método para definir o estado do pedido
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", pizzas=" + pizzas +
                ", valorTotal=" + valorTotal +
                ", estado=" + estado + // Inclui o estado no toString
                '}';
    }
}

