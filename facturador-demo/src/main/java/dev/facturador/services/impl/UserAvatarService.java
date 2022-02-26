package dev.facturador.services.impl;

import dev.facturador.entities.AvatarUsuario;
import dev.facturador.repository.IUserAvatarRepository;
import dev.facturador.services.IUserAvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserAvatarService implements IUserAvatarService {

    @Autowired
    private IUserAvatarRepository repository;

    @Override
    public AvatarUsuario getAvatarUsuarioByUsername(String username){
        var userAvatar = repository.findByUsuarioUsername(username);
        if(userAvatar.isEmpty()){
            return null;
        }
        return userAvatar.get();
    }
}