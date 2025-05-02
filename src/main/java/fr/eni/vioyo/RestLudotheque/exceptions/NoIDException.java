package fr.eni.vioyo.RestLudotheque.exceptions;


import fr.eni.vioyo.RestLudotheque.bo.EntityBaseClass;

public class NoIDException extends Exception {

    private EntityBaseClass errBO;

    public NoIDException(EntityBaseClass errBO) {
        super(errBO.getClass().getName() + errBO + " was found with no ID (<=0) where it should have had one");
        this.errBO = errBO;
    }

}
