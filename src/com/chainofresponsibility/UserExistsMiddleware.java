package com.chainofresponsibility;

/**
 * Manipulador de concreto.
 *
 * Verifica se existe um usuário com as credenciais fornecidas.
 */
public class UserExistsMiddleware extends Middleware {

    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("Este e-mail nao esta registrado!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Senha errada!");
            return false;
        }
        return checkNext(email, password);
    }
}
