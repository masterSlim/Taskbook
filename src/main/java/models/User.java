package models;

public class User {
    private String userName;
    private int userId;
    private String password;
    private String userpick;
    private  boolean gender;
    private  String position;
    private  long phone;
    private  String email;
    private static String directory;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        User.directory = directory;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public  void setUserName(String userName) {
        this.userName = userName;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public  String getUserpick() {
        return userpick;
    }

    public  void setUserpick(String userpick) {
        this.userpick = userpick;
    }

    public  boolean isGender() {
        return gender;
    }

    public  void setGender(boolean gender) {
        this.gender = gender;
    }

    public  String getPosition() {
        return position;
    }

    public  void setPosition(String position) {
        this.position = position;
    }

    public  long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        this.email = email;
    }
}