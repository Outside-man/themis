package dangod.themis.model.vo;

import com.alibaba.fastjson.JSON;
import dangod.themis.model.po.Inform;
import dangod.themis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
public class InformVo {

    private long id;
    private String title;
    private String content;
    private String date;
    private String author;

    public InformVo() {
    }

    public InformVo(Inform inform, String author) {
        this.id = inform.getId();
        this.title = inform.getTitle();
        this.content = inform.getContent();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        this.date = sdf.format(inform.getDate());
        if(author == null) this.author = "未知";
        else this.author = author;
    }

    public InformVo(long id, String title, String content, String date, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
