package fr.eni.vioyo.RestLudotheque.dal;

import fr.eni.vioyo.RestLudotheque.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
