package tbspring.models;

import tbspring.entities.UserEntity;

public class User {
    protected String directory;
    protected String userName;
    protected long userId;
    protected String userPic;
    protected UserEntity.POSITION position;
    protected byte[] phone;
    protected String email;
    protected UserEntity.GENDER gender;

    public User(String directory,
                String userName,
                long userId,
                String userPic,
                UserEntity.POSITION position,
                byte[] phone,
                String email,
                UserEntity.GENDER gender) {
        this.directory = directory;
        this.userName = userName;
        this.userId = userId;
        this.userPic = userPic;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public static User toModel(UserEntity userEntity) {
        return new User(userEntity.getDirectory(),
                userEntity.getUserName(),
                userEntity.getUserId(),
                userEntity.getUserPic(),
                userEntity.getPosition(),
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

    public UserEntity.POSITION getPosition() {
        return position;
    }

    public void setPosition(UserEntity.POSITION position) {
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
}