package persistencia;

import persistencia.bootstrap.Bootstrap;

public class App {
    public static void main(String[] args) {
        // Execute actions
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.executarRoolbackNaInsercao();
        bootstrap.showFuncionarios();

        return;
    }
}
