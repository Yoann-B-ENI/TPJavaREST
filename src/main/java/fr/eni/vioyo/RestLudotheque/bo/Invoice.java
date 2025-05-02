package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

//@Log
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Invoice extends EntityBaseClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(nullable = true)
    private Date paidDate;

    @NonNull
    @Column(nullable = false)
    private Float amount;

    @NonNull
    @OneToMany()
    @JoinColumn(name = "inv_rental_id")
    private List<Rental> rentals;
}
