package dangod.themis.model.po.authority;

import javax.persistence.*;

@Entity
@Table(name = "core_authority_menu")
public class AuthorityMenu {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String path;
    private String component;
    private String icon;
    @ManyToOne(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="parent_id",nullable=true)
    private AuthorityMenu parent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public AuthorityMenu getParent() {
        return parent;
    }

    public void setParent(AuthorityMenu parent) {
        this.parent = parent;
    }
}
