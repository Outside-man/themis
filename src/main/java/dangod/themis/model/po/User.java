package dangod.themis.model.po;

import dangod.themis.util.MD5Util;

import javax.persistence.*;

@Entity
@Table(name = "common_user")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String salt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt() {
        this.salt = String.valueOf((int)(Math.random()*901+100));
    }

    public void updatePassword(String password) {
        this.password = MD5Util.MD5(password+this.salt);
    }


    public User(String username, String password) {
        this.salt = String.valueOf((int)(Math.random()*901+100));
        this.username = username;
        this.password = MD5Util.MD5(password+this.salt);
    }

    public User() {
    }

    public User(long id, String username, String password) {
        this.id = id;
        this.salt = String.valueOf((int)(Math.random()*901+100));
        this.username = username;
        this.password = MD5Util.MD5(password+this.salt);
    }

}
