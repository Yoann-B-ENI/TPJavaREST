package fr.eni.vioyo.RestLudotheque.dto;

import lombok.Data;

import java.util.List;

@Data
public class RentableGameDTO {
    private int id;
    private String title;
    private String reference;
    private int rentableCopies;
    private List<String> genres;
}
