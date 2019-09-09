abstract class User {
    private static int userId;
    private static String userName;
    private static String password;
    private static String userpick;
    private static boolean gender;
    private static String position;
    private static int phone;
    private static String email;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        User.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getUserpick() {
        return userpick;
    }

    public static void setUserpick(String userpick) {
        User.userpick = userpick;
    }

    public static boolean isGender() {
        return gender;
    }

    public static void setGender(boolean gender) {
        User.gender = gender;
    }

    public static String getPosition() {
        return position;
    }

    public static void setPosition(String position) {
        User.position = position;
    }

    public static int getPhone() {
        return phone;
    }

    public static void setPhone(int phone) {
        User.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }
}