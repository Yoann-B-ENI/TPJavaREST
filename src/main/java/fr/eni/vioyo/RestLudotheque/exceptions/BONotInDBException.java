package fr.eni.vioyo.RestLudotheque.exceptions;


import fr.eni.vioyo.RestLudotheque.bo.EntityBaseClass;

public class BONotInDBException extends Exception {

    private EntityBaseClass errBO;

    public BONotInDBException(EntityBaseClass errBO) {
        super(errBO.getClass().getName() + errBO + " didn't exist in DB when it should have");
        this.errBO = errBO;
    }
}
