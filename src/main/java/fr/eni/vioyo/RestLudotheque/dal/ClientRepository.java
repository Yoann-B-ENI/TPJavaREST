package fr.eni.vioyo.RestLudotheque.dal;

import fr.eni.vioyo.RestLudotheque.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT cl FROM Client cl WHERE name LIKE '?1%'")
    List<Client> findAllByNameStart(String nameStart);
}
