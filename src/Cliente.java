import java.util.concurrent.atomic.AtomicInteger;

public class Cliente {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private final int id;
    private final long chegada;
    private long inicioAtendimento;
    private long fimAtendimento;

    public Cliente() {
        this.id = idGenerator.incrementAndGet();
        this.chegada = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public long getChegada() {
        return chegada;
    }

    public void setInicioAtendimento(long inicioAtendimento) {
        this.inicioAtendimento = inicioAtendimento;
    }

    public void setFimAtendimento(long fimAtendimento) {
        this.fimAtendimento = fimAtendimento;
    }

    public long getTempoEspera() {
        return inicioAtendimento - chegada;
    }

    public long getTempoAtendimento() {
        return fimAtendimento - inicioAtendimento;
    }

    public long getTempoTotalNoBanco() {
        return fimAtendimento - chegada;
    }
}
