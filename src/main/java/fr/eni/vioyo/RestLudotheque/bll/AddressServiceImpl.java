package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.dal.AddressRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@Log
public class AddressServiceImpl {

    @Autowired
    private AddressRepository repo;

    public void updateAddress(Address adr){
        try {
            this.repo.save(adr);
        } catch (IllegalArgumentException e) {
            log.severe("Address " + adr + " is null");
            throw new RuntimeException("Address " + adr + " is null");
        } catch (OptimisticLockingFailureException e) {
            log.severe("Address " + adr + " didn't exist in DB");
            throw new RuntimeException("Address " + adr + " didn't exist in DB");
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
