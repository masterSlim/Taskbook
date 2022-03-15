package tbspring.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userId;
    protected String directory;
    protected String userName;
    protected String userPic;
    protected POSITION position;
    protected byte[] phone;
    protected String email;
    protected GENDER gender;
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

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public POSITION getPosition() {
        return position;
    }

    public void setPosition(POSITION position) {
        this.position = position;
    }

    public byte[] getPhone() {
        return phone;
    }

    public void setPhone(byte[] phone) {
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

    public enum POSITION {
        MANAGER,
        EXECUTOR
    }
}