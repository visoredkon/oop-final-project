package SIAKAD.interfaces;

import SIAKAD.constants.Role;
import SIAKAD.constants.Sex;

public interface User {
    String getUsername();

    String getPassword();

    void setPassword(String password);

    String getName();

    void setName(String name);

    Sex getSex();

    Role getRole();
}
