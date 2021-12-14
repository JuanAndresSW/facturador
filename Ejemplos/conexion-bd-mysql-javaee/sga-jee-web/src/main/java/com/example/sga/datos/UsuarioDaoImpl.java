
package com.example.sga.datos;

import com.example.sga.domain.Persona;
import com.example.sga.domain.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class UsuarioDaoImpl implements UsuarioDao{

    @PersistenceContext(unitName="PersonaService")
    EntityManager em;
    
    @Override
    public List<Usuario> findAllUsuario() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    @Override
    public Usuario findUsuarioById(Usuario user) {
        return em.find(Usuario.class, user.getIdUsuario());
    }

    @Override
    public Usuario findUsuarioByUsername(Usuario user) {
        Query query = em.createNamedQuery("Usuario.findByUsername");
        query.setParameter("username", user.getUsername());
        return (Usuario) query.getSingleResult();
    }

    @Override
    public void insertUsuario(Usuario user) {
        em.persist(user);
    }

    @Override
    public void updateUsuario(Usuario user) {
        em.merge(user);
    }

    @Override
    public void deleteUsuario(Usuario user) {
        em.remove(em.merge(user));
    }
    
    public Usuario miPruebaBuscarConFind(Usuario user){
        return em.find(Usuario.class, user.getUsername());
    }
    
}
