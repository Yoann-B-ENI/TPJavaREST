package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Log
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(nullable = true, unique = true)
    private String phoneNumber;

    @OneToOne(optional = false, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", unique = true, nullable = false, updatable = false)
    private Address address;

    @OneToMany()
    @JoinColumn(name = "cli_rental_id")
    private List<Rental> rentals;
}
