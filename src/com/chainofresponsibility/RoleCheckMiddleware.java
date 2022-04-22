package com.chainofresponsibility;

/**
 * Manipulador de Concreto.
 *
 * Verifica o papel(role) de um usuário.
 */
public class RoleCheckMiddleware extends Middleware {

    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Alo, admin!");
            return true;
        }
        System.out.println("Alo, usuario!");
        return checkNext(email, password);
    }
}
