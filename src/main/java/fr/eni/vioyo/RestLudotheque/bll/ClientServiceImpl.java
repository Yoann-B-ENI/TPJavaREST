package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.dal.ClientRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository repo;

    public void addClient(Client cl, Address addr){
        cl.setAddress(addr);

        try {
            this.repo.save(cl);
        } catch (Exception e) { //TODO replace with an actual catch
            log.severe("Couldn't save client " + cl + " in DB");
            throw new RuntimeException("Couldn't save client " + cl + " in DB");
        }
    }

    public List<Client> findClientsByNameStart(String nameStart){
        return this.repo.findAllByNameStart(nameStart);
    }

    public void updateClient(Client cl){
//        Optional<Client> oldCli = this.repo.findById(cl.getId());
//        // or this.repo.existsById(cl.getId())
//        if (oldCli.isEmpty()){
//            log.severe("Client " + cl + " didn't exist in DB");
//            throw new RuntimeException("Client " + cl + " didn't exist in DB");
//        }
//        this.repo.save(cl);
        try{
            this.repo.save(cl);
        } catch (IllegalArgumentException e) {
            log.severe("Client " + cl + " is null");
            throw new RuntimeException("Client " + cl + " is null");
        } catch (OptimisticLockingFailureException e) {
            log.severe("Client " + cl + " didn't exist in DB");
            throw new RuntimeException("Client " + cl + " didn't exist in DB");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
