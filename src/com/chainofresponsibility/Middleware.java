package com.chainofresponsibility;

/**
 * Base da classe middleware.
 *
 * A interface do handler declara um método para a construção da corrente de
 * handlers. Ela também declara um método para executar um pedido(verificação).
 */
public abstract class Middleware {

    private Middleware next;

    /**
     * Constrói cadeias de objetos de middleware.
     */
    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    /**
     * As subclasses implementarão esse método com verificações concretas.
     */
    public abstract boolean check(String email, String password);

    /**
     * Executa a verificação do próximo objeto na cadeia ou termina a travessia
     * se estivermos em último objeto na cadeia.
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
