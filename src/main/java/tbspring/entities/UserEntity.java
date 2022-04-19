package tbspring.entities;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userId;
    protected String directory;
    @Column(unique = true)
    protected String userName;
    protected String userPic;
    protected HashSet<Role> role;
    protected Number[] phone;
    protected String email;
    @Enumerated(EnumType.ORDINAL)
    protected GENDER gender;

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public HashSet<Role> getAuthorities() {
        return role;
    }

    public void setRole(HashSet<Role> roles) {
        this.role = roles;
    }

    public void addRole(Role role) {this.role.add(role);}

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getDirectory() {
        return directory;
    }

    public UserEntity() {
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String path) {
        //TODO: сделать загрузку картинки из файла по пути аргумента
        this.userPic = path;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public Number[] getPhone() {
        return phone;
    }

    public void setPhone(Number[] phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public enum GENDER {
        MALE,
        FEMALE
    }
}