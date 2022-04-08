package dev.facturador.branch.domain.exception;

public final class BranchAlreadyExists extends Exception {
    public BranchAlreadyExists(String message) {
        super(message);
    }
}
