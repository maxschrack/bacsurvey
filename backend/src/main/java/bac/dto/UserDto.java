package bac.dto;

import java.util.Date;

/**
 * Created by max on 13/02/16.
 */
public class UserDto  extends EntityDto{

    private Long id;

    private String email;

    private String password;

    private Date registrationDate;

    private boolean deleted;

    public UserDto(){}

    public UserDto(Long id){
        this.id = id;
    }

    @Override
    public String getDisplayName() {
        return "user;";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (deleted != userDto.deleted) return false;
        if (!id.equals(userDto.id)) return false;
        if (!email.equals(userDto.email)) return false;
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) return false;
        return !(registrationDate != null ? !registrationDate.equals(userDto.registrationDate) : userDto.registrationDate != null);

    }
}
