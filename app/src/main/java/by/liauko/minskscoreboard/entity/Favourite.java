package by.liauko.minskscoreboard.entity;

import java.io.Serializable;

public class Favourite implements Serializable {
    private Long id;
    private String name;
    private String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favourite favourite = (Favourite) o;

        if (!id.equals(favourite.id)) return false;
        if (!name.equals(favourite.name)) return false;
        return link.equals(favourite.link);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }
}
