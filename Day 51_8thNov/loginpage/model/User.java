package example.loginpage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String name;
    private String email;
    private String password;
    private LocalDate createdDate;
    private LocalDate dateOfBirth;
    private long phoneNumber;
}
