package fr.eni.vioyo.RestLudotheque.dal;

import fr.eni.vioyo.RestLudotheque.bo.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientRepositoryTest {

    ClientRepository repo;

    @Test
    @DisplayName("Test repo save client 1 - valid case")
    public void testRepo1Valid(){
        Client cli = new Client("nametest",
                "firstnametest",
                "emailtest",
                "phonenumbertest"
        );

        Client clientSaved = repo.save(cli);

        assertThat(cli).isNotNull();
        assertThat(clientSaved).isNotNull();

        assertThat(cli.getId()).isNull();
        assertThat(clientSaved.getId()).isNotNull();

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
}
