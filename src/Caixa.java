import java.util.concurrent.BlockingQueue;

public class Caixa implements Runnable {
    private final BlockingQueue<Cliente> fila;
    private final Relatorio relatorio;

    public Caixa(BlockingQueue<Cliente> fila, Relatorio relatorio) {
        this.fila = fila;
        this.relatorio = relatorio;


    }

    @Override
    public void run() {
        try {

            while (!Thread.currentThread().isInterrupted()) {
                Cliente cliente = fila.take();
                cliente.setInicioAtendimento(System.currentTimeMillis());

                long tempoAtendimento = 30_000 + (long)(Math.random() * (120_000 - 30_000));
                Thread.sleep(tempoAtendimento);

                cliente.setFimAtendimento(System.currentTimeMillis());
                relatorio.registrar(cliente);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
