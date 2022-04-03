package dev.facturador.pointofsale.domain.exception;

public final class PointOfSaleAlreadyExists extends Exception{
    public PointOfSaleAlreadyExists(String message){
        super(message);
    }
}
