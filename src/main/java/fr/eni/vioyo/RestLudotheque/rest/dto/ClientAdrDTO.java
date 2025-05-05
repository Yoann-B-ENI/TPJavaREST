package fr.eni.vioyo.RestLudotheque.rest.dto;


import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import lombok.Data;

@Data
public class ClientAdrDTO {

    private Client client;
    private Address address;
}
