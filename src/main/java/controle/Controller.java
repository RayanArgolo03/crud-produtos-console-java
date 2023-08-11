package controle;

public interface Controller<T> {

    void criarRegistro(T obj);

    void buscarRegistro(int id);

    void alterarRegistro(T objAntigo, T objNovo);

    void deletarRegistro(T objNovo);

    //Chama produto comparator
    void classificarRegistros(int opc);

}
