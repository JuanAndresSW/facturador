
package com.example.sga.datos;

import com.example.sga.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

//Defino que es un EJB de tipo Stateless
@Stateless
public class PersonaDaoImpl implements PersonaDao{
    
    //Objeto para conectarse con JPA
    @PersistenceContext(unitName="PersonaService")
    EntityManager em;

    //Notese la simplicidad a comparacion del codigo JDBC
    
    //Este metodo es equivalente a Ordernar todo por ID y luego hace un Select All
    @Override
    public List<Persona> findAllPersona() {
        return em.createNamedQuery("Persona.findAll").getResultList();
        //No requiere otra accion ya que trabaja directamente con objetos y con datos de tablas SQL
    }

    //Meto equivalente a SellectById
    @Override
    public Persona findPersonaById(Persona persona) {
        return em.find(Persona.class, persona.getIdPersona());
    }
    
    //Select pero por email
    @Override
    public Persona findPersonaByEmail(Persona persona){
        Query query = em.createNamedQuery("Persona.findByEmail");
        query.setParameter("email", persona.getEmail());
       return (Persona) query.getSingleResult();
    }

    //Inserta un objeto en la base de datos
    @Override
    public void insertPersona(Persona persona) {
        em.persist(persona);
    }

    //"Actualiza el objeto" a ciencia exacta busca el objeto con el mismo id y lo sincroniza
    @Override
    public void updatePersona(Persona persona) {
        em.merge(persona);
    }

    //Sincroniza los objeto y para que tengan el mismo estado y luego los borra
    @Override
    public void deletePersona(Persona persona) {
        em.remove(em.merge(persona));
    }
}
