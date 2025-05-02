package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.exceptions.BONotInDBException;
import fr.eni.vioyo.RestLudotheque.exceptions.NoIDException;
import fr.eni.vioyo.RestLudotheque.exceptions.NullBOException;

import java.util.List;

public interface ClientService {
    Client addClient(Client cl, Address addr);

    List<Client> findClientsByNameStart(String nameStart);

    Client updateClient(Client cl) throws NoIDException, NullBOException, BONotInDBException;
}
