package com.ads3.auto_center.estruturas;

import java.util.ArrayList;
import java.util.List;

public class TabelaHashEncadeada<V> {

    private static class Entrada<V> {
        String chave;
        List<V> valores;
        Entrada<V> proxima;

        Entrada(String chave) {
            this.chave = chave;
            this.valores = new ArrayList<>();
        }
    }

    private static final int CAPACIDADE_PADRAO = 16;

    @SuppressWarnings("unchecked")
    private final Entrada<V>[] baldes;
    private int tamanho;

    public TabelaHashEncadeada() {
        this(CAPACIDADE_PADRAO);
    }

    @SuppressWarnings("unchecked")
    public TabelaHashEncadeada(int capacidade) {
        baldes = new Entrada[capacidade];
    }

    public void inserir(String chave, V valor) {
        if (chave == null) return;
        int indice = indiceDoBalde(chave);
        Entrada<V> atual = baldes[indice];
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                atual.valores.add(valor);
                tamanho++;
                return;
            }
            atual = atual.proxima;
        }
        Entrada<V> nova = new Entrada<>(chave);
        nova.valores.add(valor);
        nova.proxima = baldes[indice];
        baldes[indice] = nova;
        tamanho++;
    }

    public List<V> buscar(String chave) {
        if (chave == null) return new ArrayList<>();
        int indice = indiceDoBalde(chave);
        Entrada<V> atual = baldes[indice];
        while (atual != null) {
            if (atual.chave.equals(chave)) return new ArrayList<>(atual.valores);
            atual = atual.proxima;
        }
        return new ArrayList<>();
    }

    public boolean remover(String chave) {
        if (chave == null) return false;
        int indice = indiceDoBalde(chave);
        Entrada<V> atual = baldes[indice];
        Entrada<V> anterior = null;
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                if (anterior == null) {
                    baldes[indice] = atual.proxima;
                } else {
                    anterior.proxima = atual.proxima;
                }
                tamanho -= atual.valores.size();
                return true;
            }
            anterior = atual;
            atual = atual.proxima;
        }
        return false;
    }

    public int tamanho() {
        return tamanho;
    }

    private int indiceDoBalde(String chave) {
        return Math.abs(chave.hashCode()) % baldes.length;
    }
}