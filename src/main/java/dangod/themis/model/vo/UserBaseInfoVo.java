package dangod.themis.model.vo;

import dangod.themis.model.po.UserBaseInfo;

public class UserBaseInfoVo {
    private long id;
    private String realname;
    private String email;
    private String sex;

    public UserBaseInfoVo() {
    }

    public UserBaseInfoVo(UserBaseInfo baseInfo) {
        this.id = baseInfo.getUser().getId();
        this.realname = baseInfo.getName();
        this.email = baseInfo.getEmail();
        this.sex = baseInfo.getSex();
    }

    public UserBaseInfoVo(long id, String realname, String email, String sex) {
        this.id = id;
        this.realname = realname;
        this.email = email;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
