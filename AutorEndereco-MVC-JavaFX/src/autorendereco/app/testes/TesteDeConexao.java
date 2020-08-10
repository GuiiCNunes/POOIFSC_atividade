/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorendereco.app.testes;

import autorendereco.model.connection.ConnectionFactory;
import autorendereco.model.repository.Autores;

/**
 *
 * @author GuiiCNunes <guiicnunes@gmail.com>
 */
public class TesteDeConexao {
    private static Autores autores;
    public static void main(String[] args) {
        autores = new Autores(ConnectionFactory.getEmf());
        System.err.println("Ok");
    }
}
