import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    private final List<Cliente> clientes = new ArrayList<>();

    public synchronized void registrar(Cliente cliente) {
        clientes.add(cliente);
    }

    public void gerarRelatorio() {
        long maxEspera = 0;
        long maxAtendimento = 0;
        long somaTempoBanco = 0;
        long somaTempoEspera = 0;

        for (Cliente c : clientes) {
            maxEspera = Math.max(maxEspera, c.getTempoEspera());
            maxAtendimento = Math.max(maxAtendimento, c.getTempoAtendimento());
            somaTempoBanco += c.getTempoTotalNoBanco();
            somaTempoEspera += c.getTempoEspera();
        }

        int totalClientes = clientes.size();
        System.out.println("\n=== RELATÓRIO DA SIMULAÇÃO ===");
        System.out.println("Clientes atendidos: " + totalClientes);
        System.out.println("Tempo máximo de espera: " + (maxEspera / 1000.0) + " segundos");
        System.out.println("Tempo máximo de atendimento: " + (maxAtendimento / 1000.0) + " segundos");
        System.out.println("Tempo médio no banco: " + (somaTempoBanco / totalClientes / 1000.0) + " segundos");
        System.out.println("Tempo médio de espera: " + (somaTempoEspera / totalClientes / 1000.0) + " segundos");

        if ((somaTempoEspera / totalClientes) <= 120_000) {
            System.out.println("✅ Objetivo alcançado: Espera média abaixo de 2 minutos.");
        } else {
            System.out.println("❌ Objetivo NÃO alcançado: Espera média acima de 2 minutos.");
        }
    }
}
