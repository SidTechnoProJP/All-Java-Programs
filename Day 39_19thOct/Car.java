package com.example.demo.insurnance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name="car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    private int reg_no;
    private String model;
    private int year;

    @ManyToMany(mappedBy = "carList")
    @JsonIgnore
    private List<Person> personList;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    List<Participated> participatedList;

}
