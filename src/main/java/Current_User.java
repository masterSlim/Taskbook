class Current_User {
    private static String userName;
    private static int userId;
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
        Current_User.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Current_User.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Current_User.password = password;
    }

    public static String getUserpick() {
        return userpick;
    }

    public static void setUserpick(String userpick) {
        Current_User.userpick = userpick;
    }

    public static boolean isGender() {
        return gender;
    }

    public static void setGender(boolean gender) {
        Current_User.gender = gender;
    }

    public static String getPosition() {
        return position;
    }

    public static void setPosition(String position) {
        Current_User.position = position;
    }

    public  int getPhone() {
        return phone;
    }

    public  void setPhone(int phone) {
        Current_User.phone = phone;
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        Current_User.email = email;
    }
}