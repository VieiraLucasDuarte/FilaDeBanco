import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numeroDeCaixas = 5;
        BlockingQueue<Cliente> fila = new LinkedBlockingQueue<>();
        Relatorio relatorio = new Relatorio();
        ExecutorService caixas = Executors.newFixedThreadPool(numeroDeCaixas);

        for (int i = 0; i < numeroDeCaixas; i++) {
            caixas.submit(new Caixa(fila, relatorio));
        }

        //alterado para 2 minutos
        long duracaoSimulacao = 2 * 60 * 1000L;
        ;
        long inicio = System.currentTimeMillis();

        while (System.currentTimeMillis() - inicio < duracaoSimulacao) {
            fila.put(new Cliente());

            long tempoEntreClientes = 5_000 + (long)(Math.random() * (50_000 - 5_000));
            Thread.sleep(tempoEntreClientes);
        }


        Thread.sleep(10_000);
        caixas.shutdownNow();
        caixas.awaitTermination(5, TimeUnit.SECONDS);

        relatorio.gerarRelatorio();
    }
}
