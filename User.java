public class User {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String major;
    private String level;

    public User(String username, String password, String fullName, String email, String major, String level) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.major = major;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
