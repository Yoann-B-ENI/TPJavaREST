package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.dal.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService serv;

    @MockitoBean
    private ClientRepository repoMock;

    @Test
    @DisplayName("test client service repo mocked - create client - valid")
    public void shouldAddClientToDBMocked(){
        Client cl = new Client("nametest", "firsttest", "emailtest", "phonetest");
        Address adr = new Address("roadtest", "12345", "towntest");

        org.mockito.Mockito.doAnswer((invocation) -> {
            Client cli = invocation.getArgument(0);
            cli.setId(999);
            return cli;
        }).when(repoMock).save(cl);

        // ----
        this.serv.addClient(cl, adr);

        assertThat(cl.getId()).isNotNull();
        assertThat(cl.getId()).isEqualTo(999);
    }

}
