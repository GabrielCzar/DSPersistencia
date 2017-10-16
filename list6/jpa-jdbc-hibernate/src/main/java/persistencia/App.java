package persistencia;

import persistencia.query.JPQLNativeCriteriaNamed;

public class App {
    public static void main(String[] args) {
        // Execute actions

        // JPQLNativeCriteriaNamed para consultas da questao 5
        JPQLNativeCriteriaNamed q = new JPQLNativeCriteriaNamed();
        q.allNomesDependentesNamed("C");

        // QueryJPADao para consultas da questoes 4 e 7

    }
}
