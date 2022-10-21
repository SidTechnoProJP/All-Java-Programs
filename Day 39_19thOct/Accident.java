package com.example.demo.insurnance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Entity(name="accident")
@Data
@NoArgsConstructor@AllArgsConstructor
public class Accident {
    @Id
    private int report_no;
    private Date accident_date;
    private String location;


    @OneToMany(mappedBy = "accident",cascade = CascadeType.ALL)
  //  @JsonIgnore
    List<Participated> participatedList;
}
