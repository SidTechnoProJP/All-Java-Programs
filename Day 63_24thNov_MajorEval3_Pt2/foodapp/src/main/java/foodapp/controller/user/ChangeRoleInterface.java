package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.customexceptions.InvalidCardNumberException;
import foodapp.entity.Users;
import foodapp.service.user.RoleInterface;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ChangeRoleInterface {

    @Autowired
    private RoleInterface roleInterface;

    @PatchMapping("/updateUserRole")
    public ResponseEntity<String> changeRole(@RequestHeader String gstNumber) throws InvalidCardNumberException {
        return ResponseEntity.of(Optional.of(roleInterface.changeRole(gstNumber)));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> edit(@ModelAttribute Users user){
        return ResponseEntity.of(Optional.of(roleInterface.editUser(user)));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> removeAccount(){
        return ResponseEntity.of(Optional.of(roleInterface.deleteAccount()));
    }
}
