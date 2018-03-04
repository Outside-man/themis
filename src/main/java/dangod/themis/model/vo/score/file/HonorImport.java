package dangod.themis.model.vo.score.file;

import java.util.List;

public class HonorImport {
    private String stuId;
    private String realName;
    private String honorName;
    private Double honorScore;
    private Integer honorLv;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public Double getHonorScore() {
        return honorScore;
    }

    public void setHonorScore(Double honorScore) {
        this.honorScore = honorScore;
    }

    public Integer getHonorLv() {
        return honorLv;
    }

    public void setHonorLv(Integer honorLv) {
        this.honorLv = honorLv;
    }

    public HonorImport() {
    }
}
