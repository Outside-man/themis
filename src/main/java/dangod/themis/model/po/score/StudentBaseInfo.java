package dangod.themis.model.po.score;

import dangod.themis.model.po.common.User;
import dangod.themis.model.po.common.UserBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "score_student_base_info")
public class StudentBaseInfo {
    @Id
    @GeneratedValue
    private long id;
    private String stuId;
    private String photo;
    private String entrance_time;
    private String political;

    @OneToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="base_id",nullable=true)
    private UserBaseInfo baseInfo;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="class_id",nullable=true)
    private Class aClass;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="dormitory_id",nullable=true)
    private Dormitory dormitory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEntrance_time() {
        return entrance_time;
    }

    public void setEntrance_time(String entrance_time) {
        this.entrance_time = entrance_time;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public UserBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(UserBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

}
