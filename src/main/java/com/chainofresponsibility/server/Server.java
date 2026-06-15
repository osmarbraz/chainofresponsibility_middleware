package com.chainofresponsibility.server;

import com.chainofresponsibility.middleware.Middleware;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe servidora.
 */
public class Server {

    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    /**
     * O cliente passa uma cadeia de objetos para o servidor. Isso melhora a
     * flexibilidade e facilita o teste da classe do servidor.
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    /**
     * O servidor obtém e-mail e senha do cliente e envia a autorização pedido
     * para a cadeia.
     */
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("A autorização foi bem sucedida!");

            // Faça algo útil aqui para usuários autorizados.
            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
