package dangod.themis.model.po.score;

import dangod.themis.model.po.common.User;

import javax.persistence.*;

@Entity
@Table(name = "score_manager")
public class Manager {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="user_id",nullable=true)
    private User user;
    private String majorList = "[]";
    private String classList = "[]";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMajorList() {
        return majorList;
    }

    public void setMajorList(String majorList) {
        this.majorList = majorList;
    }

    public String getClassList() {
        return classList;
    }

    public void setClassList(String classList) {
        this.classList = classList;
    }
}
