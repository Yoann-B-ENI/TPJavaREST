package fr.eni.vioyo.RestLudotheque.bo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClientTest {

    @Test
    @DisplayName("Test create client 1 - no args constructor - valid case")
    public void testCreate1Valid(){
        Client cl = null;

        cl = new Client();

        assertThat(cl).isNotNull();
        assertThat(cl.getId()).isNull();
        assertThat(cl.getName()).isNull();
        assertThat(cl.getFirstName()).isNull();
        assertThat(cl.getEmail()).isNull();
        assertThat(cl.getPhoneNumber()).isNull();
        assertThat(cl.getAddress()).isNull();
        assertThat(cl.getRentals()).isNull();
    }

    @Test
    @DisplayName("Test create client 2 - required args constructor - valid case")
    public void testCreate2Valid(){
        Client cl = null;

        cl = new Client("nametest", "firstnametest", "emailtest", "phonenumbertest");

        assertThat(cl).isNotNull();
        assertThat(cl.getId()).isNull();
        assertThat(cl.getName()).isEqualTo("nametest");
        assertThat(cl.getFirstName()).isEqualTo("firstnametest");
        assertThat(cl.getEmail()).isEqualTo("emailtest");
        assertThat(cl.getPhoneNumber()).isEqualTo("phonenumbertest");
        assertThat(cl.getAddress()).isNull();
        assertThat(cl.getRentals()).isNull();
    }





}
