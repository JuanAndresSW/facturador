package dev.facturador.mainaccount.infrastructure.service;

public interface IMainAccountDeleteService {

    void deleteByUsername(String username);

    Boolean existsByUsername(String username);
}
