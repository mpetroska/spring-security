package lt.bt.security.security.entity.dto;

import lt.bt.security.security.entity.User;
import lt.bt.security.security.validation.UniqUsername;

public class UserDto {

    private String firstname;

    private String lastname;

    @UniqUsername
    private String username;

    private String password;

    public UserDto() {
    }

    public UserDto(User user) {
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setUsername(user.getUsername());
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
