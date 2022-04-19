package tbspring.models;

import tbspring.entities.Role;
import tbspring.entities.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class User {
    protected String directory;
    protected String userName;
    protected long userId;
    protected String userPic;
    protected HashSet<Role> roles;
    protected Number[] phone;
    protected String email;
    protected UserEntity.GENDER gender;

    public User(String directory,
                String userName,
                long userId,
                String userPic,
                HashSet<Role> roles,
                Number[] phone,
                String email,
                UserEntity.GENDER gender) {
        this.directory = directory;
        this.userName = userName;
        this.userId = userId;
        this.userPic = userPic;
        this.roles = roles;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<Role> roles) {
        this.roles = roles;
    }

    public static User toModel(UserEntity userEntity) {
        return new User(userEntity.getDirectory(),
                userEntity.getUsername(),
                userEntity.getUserId(),
                userEntity.getUserPic(),
                userEntity.getAuthorities(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getGender());
    }

    public String getDirectory() {
        return directory;
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

    public String getUserName() {
        return userName;
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

    public UserEntity.GENDER getGender() {
        return gender;
    }

    public void setGender(UserEntity.GENDER gender) {
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
}