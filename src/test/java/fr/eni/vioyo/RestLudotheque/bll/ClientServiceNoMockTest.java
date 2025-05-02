package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.dal.ClientRepository;
import fr.eni.vioyo.RestLudotheque.exceptions.BONotInDBException;
import fr.eni.vioyo.RestLudotheque.exceptions.NoIDException;
import fr.eni.vioyo.RestLudotheque.exceptions.NullBOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientServiceNoMockTest {

    @Autowired
    private ClientService serv;

    @Autowired
    private ClientRepository repo;

    @Test
    @DisplayName("test client service no mock - update client - valid")
    @Transactional
    public void shouldUpdateClientToDB(){
        // Arrange - put a client into DB
        Client cl = new Client("nametest", "firsttest", "emailtest", "phonetest");
        Address adr = new Address("roadtest", "12345", "towntest");

        cl = this.serv.addClient(cl, adr);

        Client clDB = this.repo.findById(cl.getId()).orElse(null);
        assertThat(clDB).isNotNull();
        if (clDB != null){
            assertThat(clDB.getId()).isEqualTo(cl.getId());
            assertThat(clDB.getAddress()).isEqualTo(cl.getAddress());
        }

        cl.setName("NEW_NAME");

        // Act
        try {
            cl = this.serv.updateClient(cl);
        } catch (Exception e) {
            assertThat(1).isEqualTo(0);
        }

        // Assert
        clDB = this.repo.findById(cl.getId()).orElse(null);
        assertThat(clDB).isNotNull();
        if (clDB != null){
            assertThat(clDB.getName()).isEqualTo("NEW_NAME");
        }
    }


    @Test
    @DisplayName("test client service no mock - update client - invalid")
    @Transactional
    public void shouldntUpdateClientToDB(){
        Client cl = new Client("nametest", "firsttest", "emailtest", "phonetest");
        Address adr = new Address("roadtest", "12345", "towntest");
        cl.setAddress(adr);
        cl.setName("NEW_NAME");
        try {
            cl = this.serv.updateClient(cl);
        } catch (Exception e){
            assertThat(e).isInstanceOf(NoIDException.class);
        }

        cl.setId(999);
        try {
            cl = this.serv.updateClient(cl);
        } catch (Exception e){
            assertThat(e).isInstanceOf(BONotInDBException.class);
        }

        cl = null;
        try {
            cl = this.serv.updateClient(cl);
        } catch (Exception e){
            assertThat(e).isInstanceOf(NullBOException.class);
        }
    }


}
