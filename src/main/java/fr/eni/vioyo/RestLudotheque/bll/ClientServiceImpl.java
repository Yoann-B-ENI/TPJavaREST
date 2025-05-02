package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.dal.ClientRepository;
import fr.eni.vioyo.RestLudotheque.exceptions.BONotInDBException;
import fr.eni.vioyo.RestLudotheque.exceptions.NoIDException;
import fr.eni.vioyo.RestLudotheque.exceptions.NullBOException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository repo;

    @Override
    public Client addClient(Client cl, Address adr) throws NullBOException, BONotInDBException {
        cl.setAddress(adr);

        try {
            cl = this.repo.save(cl);
        } catch (IllegalArgumentException e) {
            NullBOException excp = new NullBOException(Client.class);
            log.severe(excp.getMessage());
            throw excp;
        } catch (OptimisticLockingFailureException e) {
            BONotInDBException excp = new BONotInDBException(cl);
            log.severe(excp.getMessage());
            throw excp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return cl;
    }

    @Override
    public List<Client> findClientsByNameStart(String nameStart){
        return this.repo.findAllByNameStart(nameStart);
    }

    @Override
    public Client updateClient(Client cl) throws NoIDException, NullBOException, BONotInDBException {
        if (cl == null){
            NullBOException excp = new NullBOException(Client.class);
            log.severe(excp.getMessage());
            throw excp;
        }
        if (cl.getId() <= 0){
            NoIDException excp = new NoIDException(cl);
            log.severe(excp.getMessage());
            throw excp;
        }

        try{
            // # # # #
            cl = this.repo.save(cl);
            // # # # #
        } catch (IllegalArgumentException e) {
            NullBOException excp = new NullBOException(Client.class);
            log.severe(excp.getMessage());
            throw excp;
        } catch (OptimisticLockingFailureException e) {
            BONotInDBException excp = new BONotInDBException(cl);
            log.severe(excp.getMessage());
            throw excp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return cl;
    }


}
