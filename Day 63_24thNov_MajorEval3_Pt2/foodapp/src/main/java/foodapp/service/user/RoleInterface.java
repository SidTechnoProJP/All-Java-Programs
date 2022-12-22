package foodapp.service.user;

import foodapp.customexceptions.InvalidCardNumberException;
import foodapp.entity.Users;

public interface RoleInterface {
    String changeRole(String gstNumber) throws InvalidCardNumberException;

    String editUser(Users users);

    String deleteAccount();
}
