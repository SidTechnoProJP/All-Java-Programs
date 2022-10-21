package com.example.demo.insurnance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "participated")
@Data@AllArgsConstructor@NoArgsConstructor

public class Participated {
    @Id
    private int participated_id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Person person1;

    @ManyToOne
    @JoinColumn(name = "reg_no")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "report_no")
    private Accident accident;
    private int amount;
}
