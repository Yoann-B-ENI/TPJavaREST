package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//@Log
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Address extends EntityBaseClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @NonNull
    @Column(nullable = false)
    private String road;

    @NonNull
    @Column(nullable = false)
    private String zipCode;

    @NonNull
    @Column(nullable = false)
    private String town;
}
