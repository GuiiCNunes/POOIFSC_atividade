/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorendereco.model.repository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import autorendereco.model.domain.Autor;
import autorendereco.model.domain.Endereco;

/**
 *
 * @author GuiiCNunes <guiicnunes@gmail.com>
 */
public class Autores implements Serializable{
    
    private EntityManagerFactory emf;
    private EntityManager entityManager = null;
    
    public Autores (EntityManagerFactory emf) {
        this.setEmf(emf);
    }
    
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Autor pesquisarAutorPorId(long id) {
        entityManager = emf.createEntityManager();
        return entityManager.find(Autor.class, id);
    }
    
    public List<Autor> pesquisarAutores(String nome) {
        entityManager = emf.createEntityManager();        
        String jpql = "from Autor where nome like :nome";
        
        TypedQuery<Autor> query = entityManager.createQuery(jpql, Autor.class);
        
        query.setParameter("nome", nome + "%");
        
        return query.getResultList();
    }
    
    public List<Autor> pesquisarAutores() {
        entityManager = emf.createEntityManager();
        String jpql = "from Autor";
        
        TypedQuery<Autor> query = entityManager.createQuery(jpql, Autor.class);
        
        return query.getResultList();
    }
    
    public Optional<Autor> salvar(Autor autor) {
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(autor);
            entityManager.getTransaction().commit();
            return Optional.of(autor);
        } catch (Exception e) {
            System.err.println("Stack Trace: \n" + Arrays.toString(e.getStackTrace()));
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }
    
    public void remover (Autor autor) {
        try {
            entityManager = emf.createEntityManager();
            autor = entityManager.find(Autor.class, autor.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(autor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Stack Trace: \n" + Arrays.toString(e.getStackTrace()));
        } finally {
            entityManager.close();
        }
    }
    
    public Endereco pesquisarEnderecoPorId(long id) {
        entityManager = emf.createEntityManager();
        return entityManager.find(Endereco.class, id);
    }
}
