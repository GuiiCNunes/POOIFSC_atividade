/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorendereco.app.testes;

import autorendereco.model.connection.ConnectionFactory;
import java.util.Date;
import java.util.List;
import autorendereco.model.domain.Autor;
import autorendereco.model.domain.Endereco;
import autorendereco.model.repository.Autores;

/**
 *
 * @author GuiiCNunes <guiicnunes@gmail.com>
 */
public class TestInsercao {

    private static Autores autores;

    public static void main(String[] args) {
        autores = new Autores(ConnectionFactory.getEmf());
        
        showAutores(autores.pesquisarAutores());
        Autor autor = getAutor(1);
        System.err.println("Autor: " + autor.getNome());
        autor.setNome("Aderbal Ramos");
        System.err.println("Autor: " + autor.getNome());
        
        autor = new Autor("Venancio", "12345678998745", new Date(0) );
        Endereco endereco = new Endereco("Aquiouali", "88057-412", 7, "complementar", "est", "cidade", autor);
        autor.setEndereco(endereco);
        salvar(autor);
        
        removerAutor(getAutor(3));
        showAutores(autores.pesquisarAutores("Ve"));
        showAutores(autores.pesquisarAutores("Ad"));
        
        ConnectionFactory.closeEMF();        
    }
    
    // Métodos para a tabela view ou controller?
    
    private static void showAutores(List<Autor> listaAutores) {
        for (Autor autor : listaAutores) {
            System.err.println("Id: " + autor.getId() + " Nome: " + autor.getNome() + " CPF: " + autor.getCpf() + " CEP: " + autor.getEndereco().getCep());
        }
    }
    
    private static Autor getAutor(long id) {
        Autor autor = autores.pesquisarAutorPorId(id);
        return autor;
    }
    
    private static List<Autor> getAll() {
        return autores.pesquisarAutores();
    }
    
    public static void atualizar(Autor autor) {
        autores.salvar(autor);
    }
    
    public static void salvar(Autor autor) {
        autores.salvar(autor);
    }
    
    public static void removerAutor(Autor autor) {
        autores.remover(autor);
    }      
}
