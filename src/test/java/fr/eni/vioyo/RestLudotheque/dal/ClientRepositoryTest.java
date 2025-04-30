package fr.eni.vioyo.RestLudotheque.dal;

import fr.eni.vioyo.RestLudotheque.bo.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repo;

    @Test
    @DisplayName("Test repo save client 1 - insert - valid case")
    @Transactional
    public void testRepo1Valid(){
        Client cli = new Client("nametest",
                "firstnametest",
                "emailtest",
                "phonenumbertest"
        );

        Client clientSaved = repo.save(cli);

        assertThat(cli).isNotNull();
        assertThat(clientSaved).isNotNull();

        assertThat(cli.getId()).isEqualTo(0);
        assertThat(clientSaved.getId()).isNotEqualTo(0);

        assertThat(cli.getName()).isEqualTo("nametest");
        assertThat(cli.getFirstName()).isEqualTo("firstnametest");
        assertThat(cli.getEmail()).isEqualTo("emailtest");
        assertThat(cli.getPhoneNumber()).isEqualTo("phonenumbertest");
        assertThat(cli.getAddress()).isNull();
        assertThat(cli.getRentals()).isNull();

        assertThat(clientSaved.getName()).isEqualTo("nametest");
        assertThat(clientSaved.getFirstName()).isEqualTo("firstnametest");
        assertThat(clientSaved.getEmail()).isEqualTo("emailtest");
        assertThat(clientSaved.getPhoneNumber()).isEqualTo("phonenumbertest");
        assertThat(clientSaved.getAddress()).isNull();
        assertThat(clientSaved.getRentals()).isNull();
    }

    @Test
    @DisplayName("Test repo save client 2 - update - valid case")
    @Transactional
    public void testRepo2Valid(){
        Client cli = new Client("nametest",
                "firstnametest",
                "emailtest",
                "phonenumbertest"
        );

        Client clientSaved = repo.save(cli);

        clientSaved.setName("NEW_NAME");

        clientSaved = repo.save(clientSaved);

        assertThat(cli.getName()).isEqualTo("nametest");
        assertThat(clientSaved.getName()).isEqualTo("NEW_NAME");
    }

    @Test
    @DisplayName("Test repo client 3 - read - valid case")
    @Transactional
    public void testRepo3Valid(){
        Client cli = new Client("nametest",
                "firstnametest",
                "emailtest",
                "phonenumbertest"
        );
        Client clientSaved = repo.save(cli);

        Client clientDB = repo.findById(clientSaved.getId()).orElse(null);

        assertThat(clientDB).isNotNull();
        assertThat(clientDB.getId()).isNotEqualTo(0);
        assertThat(clientDB.getId()).isEqualTo(clientSaved.getId());

        assertThat(clientDB.getName()).isEqualTo(clientSaved.getName());
        assertThat(clientDB.getFirstName()).isEqualTo(clientSaved.getFirstName());
        assertThat(clientDB.getEmail()).isEqualTo(clientSaved.getEmail());
        assertThat(clientDB.getPhoneNumber()).isEqualTo(clientSaved.getPhoneNumber());
    }

    @Test
    @DisplayName("Test repo client 4 - delete - valid case")
    @Transactional
    public void testRepo5Valid(){
        Client cli = new Client("name_delete",
                "firstnametest",
                "emailtest",
                "phonenumbertest"
        );
        Client clientSaved = repo.save(cli);

        repo.delete(clientSaved);

        Client clientDB = repo.findById(clientSaved.getId()).orElse(null);
        assertThat(clientDB).isNull();
    }


}
