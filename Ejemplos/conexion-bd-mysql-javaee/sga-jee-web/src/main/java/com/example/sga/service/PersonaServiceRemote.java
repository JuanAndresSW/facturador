
package com.example.sga.service;

import com.example.sga.domain.Persona;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface PersonaServiceRemote {
    public List<Persona> listarPersona();
    public Persona encontrarPersona(Persona persona);
    public Persona encontrarPersonaPorEmail(Persona persona);
    public void registrarPersona(Persona persona);
    public void modificarPersona(Persona persona);
    public void eliminarPersona(Persona persona); 
}
