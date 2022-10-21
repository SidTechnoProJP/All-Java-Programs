package com.example.demo.insurnance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor@AllArgsConstructor
@Table(name="person")
public class Person {
    @Id
    private int driver_id;
    private String name;
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "own",
            joinColumns = {@JoinColumn(name = "driver_id")},
            inverseJoinColumns = {@JoinColumn(name = "reg_no")}
    )
    private List<Car> carList;


    @OneToMany(mappedBy = "person1")
    @JsonIgnore
    List<Participated> participatedList;
}
