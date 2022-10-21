package com.spring.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(                      //creates a new table in the database so hibernate will create it
        name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_generator"
    )

    private long studentId;
    private String firstName;
    private String lastName;

    @Column(                        //changes the name of the column
            name = "email_address",
            nullable = false
    )
    private String emailId; //should have unique value

    @Embedded
    private Guardian guardian;
}
