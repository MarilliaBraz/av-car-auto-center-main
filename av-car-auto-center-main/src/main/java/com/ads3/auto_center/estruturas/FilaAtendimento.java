package com.ads3.auto_center.estruturas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FilaAtendimento<T> implements Iterable<T> {

    private static class No<T> {
        T valor;
        No<T> proximo;

        No(T valor) {
            this.valor = valor;
        }
    }

    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public void enfileirar(T valor) {
        No<T> novo = new No<>(valor);
        if (fim == null) {
            inicio = novo;
        } else {
            fim.proximo = novo;
        }
        fim = novo;
        tamanho++;
    }

    public T desenfileirar() {
        if (inicio == null) return null;
        T valor = inicio.valor;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;
        tamanho--;
        return valor;
    }

    public T peek() {
        return inicio == null ? null : inicio.valor;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public List<T> paraLista() {
        List<T> lista = new ArrayList<>(tamanho);
        No<T> atual = inicio;
        while (atual != null) {
            lista.add(atual.valor);
            atual = atual.proximo;
        }
        return lista;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorFila();
    }

    private class IteradorFila implements Iterator<T> {

        private No<T> atual = inicio;

        @Override
        public boolean hasNext() {
            return atual != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("A fila não possui mais elementos.");
            }
            T valor = atual.valor;
            atual = atual.proximo;
            return valor;
        }
    }
}