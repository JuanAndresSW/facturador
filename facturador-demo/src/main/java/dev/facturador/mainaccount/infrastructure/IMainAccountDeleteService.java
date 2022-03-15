package dev.facturador.mainaccount.infrastructure;

public interface IMainAccountDeleteService {

    void deleteByUsername(String username);

    Boolean existsByUsername(String username);
}
