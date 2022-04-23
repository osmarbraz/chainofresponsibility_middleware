package com.chainofresponsibility.middleware;

/**
 * Manipulador concreto.
 *
 * Verifica se há muitas solicitações de login com falha.
 */
public class ThrottlingMiddleware extends Middleware {

    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * Por favor, não que a chamada checkNext() possa ser inserida tanto no
     * início deste método e no final.
     *
     * Isso dá muito mais flexibilidade do que um simples loop sobre todo o
     * middleware objetos. Por exemplo, um elemento de uma cadeia pode alterar a
     * ordem de verifica executando sua verificação após todas as outras
     * verificações.
     */
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Requisicoes excederam o limite!");
            Thread.currentThread().stop();
        }
        return checkNext(email, password);
    }
}
