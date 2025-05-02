package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.dal.AddressRepository;
import fr.eni.vioyo.RestLudotheque.exceptions.BONotInDBException;
import fr.eni.vioyo.RestLudotheque.exceptions.NoIDException;
import fr.eni.vioyo.RestLudotheque.exceptions.NullBOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService serv;

    @MockitoBean
    private AddressRepository repoMock;


    @Test
    @DisplayName("test address service repo mocked - update address - valid")
    public void shouldUpdateAddressMocked(){
        Address adr = new Address("roadtest", "12345", "towntest");

        adr.setId(999);
        adr.setRoad("NEW_ROAD");
        org.mockito.Mockito.doAnswer((invocation) -> {
            Address fake_db = invocation.getArgument(0);
            fake_db.setRoad("NEW_ROAD");
            return fake_db;
        }).when(repoMock).save(adr);

        try {
            adr = this.serv.updateAddress(adr);
        } catch (Exception e) {
            assertThat(1).isEqualTo(0);
        }

        assertThat(adr.getRoad()).isEqualTo("NEW_ROAD");
    }

}
