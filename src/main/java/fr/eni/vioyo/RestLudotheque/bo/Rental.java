package fr.eni.vioyo.RestLudotheque.bo;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

//@Log
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @NonNull
    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = true)
    private Date endDate;

    @NonNull
    @Column(nullable = false)
    private Float dailyPriceAtStart;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "copy_id")
    private GameCopy copy;
}
