package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.dal.AddressRepository;
import fr.eni.vioyo.RestLudotheque.exceptions.BONotInDBException;
import fr.eni.vioyo.RestLudotheque.exceptions.NoIDException;
import fr.eni.vioyo.RestLudotheque.exceptions.NullBOException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@Log
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository repo;

    @Override
    public Address updateAddress(Address adr) throws NoIDException, NullBOException, BONotInDBException {
        if (adr == null){
            NullBOException excp = new NullBOException(Address.class);
            log.severe(excp.getMessage());
            throw excp;
        }
        if (adr.getId() <= 0){
            NoIDException excp = new NoIDException(adr);
            log.severe(excp.getMessage());
            throw excp;
        }

        try {
            // # # # #
            adr = this.repo.save(adr);
            // # # # #
        } catch (IllegalArgumentException e) {
            NullBOException excp = new NullBOException(Address.class);
            log.severe(excp.getMessage());
            throw excp;
        } catch (OptimisticLockingFailureException e) {
            BONotInDBException excp = new BONotInDBException(adr);
            log.severe(excp.getMessage());
            throw excp;
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }

        return adr;
    }

}
