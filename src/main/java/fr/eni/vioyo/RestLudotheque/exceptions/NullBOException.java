package fr.eni.vioyo.RestLudotheque.exceptions;


import fr.eni.vioyo.RestLudotheque.bo.EntityBaseClass;

public class NullBOException extends Exception {

    private Class<? extends EntityBaseClass> errClass;

    public NullBOException(Class<? extends EntityBaseClass> errClass) {
        super(errClass.getName() + " was found null where it shouldn't have been");
        this.errClass = errClass;
    }
}
