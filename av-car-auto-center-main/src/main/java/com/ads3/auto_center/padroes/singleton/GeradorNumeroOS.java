package com.ads3.auto_center.padroes.singleton;

import com.ads3.auto_center.business.ordemDeServico.IOrdemDeServicoRepository;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class GeradorNumeroOS {

    private static volatile GeradorNumeroOS instancia;

    private final AtomicInteger contador;
    private int anoCorrente;

    private GeradorNumeroOS(long ultimoSequencial) {
        this.contador = new AtomicInteger((int) ultimoSequencial);
        this.anoCorrente = LocalDate.now().getYear();
    }

    public static synchronized void inicializar(IOrdemDeServicoRepository repository) {
        if (instancia == null) {
            long ultimo = repository.findUltimoSequencialOS();
            instancia = new GeradorNumeroOS(ultimo);
        }
    }

    public static GeradorNumeroOS getInstance() {
        if (instancia == null) {
            throw new IllegalStateException(
                "GeradorNumeroOS não foi inicializado. " +
                "Verifique se OrdemDeServicoService.inicializarGerador() foi executado.");
        }
        return instancia;
    }

    public synchronized String proximoNumero() {
        int anoAtual = LocalDate.now().getYear();
        if (anoAtual != anoCorrente) {
            anoCorrente = anoAtual;
            contador.set(0);
        }
        return String.format("OS-%d-%04d", anoCorrente, contador.incrementAndGet());
    }

    public synchronized int proximoSequencial() {
        int anoAtual = LocalDate.now().getYear();
        if (anoAtual != anoCorrente) {
            anoCorrente = anoAtual;
            contador.set(0);
        }
        return contador.incrementAndGet();
    }
}