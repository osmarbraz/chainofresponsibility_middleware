
import com.chainofresponsibility.middleware.Middleware;
import com.chainofresponsibility.middleware.RoleCheckMiddleware;
import com.chainofresponsibility.server.Server;
import com.chainofresponsibility.middleware.ThrottlingMiddleware;
import com.chainofresponsibility.middleware.UserExistsMiddleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // Todas as verificações estão vinculadas. 
        // O cliente pode construir várias cadeias usando o mesmo componentes.
        // Verifica se há muitas solicitações de login com falha e 2 minutos.
        Middleware middleware = new ThrottlingMiddleware(2);
        // Depois verifica se o usuário existe
        middleware.linkWith(new UserExistsMiddleware(server));
        // Depois verifica se o usuário tem permissão    
        middleware.linkWith(new RoleCheckMiddleware());

        // O servidor obtém uma cadeia do código do cliente.
        server.setMiddleware(middleware);

        // Utilizando a cadeia    
        System.out.println("Usuários registrados");
        System.out.println("admin@example.com/admin_pass");
        System.out.println("user@example.com/user_pass");
        System.out.println("");
        boolean success;
        do {
            System.out.print("Entrada do e-mail: ");
            String email = reader.readLine();
            System.out.print("Entrada da senha: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}
