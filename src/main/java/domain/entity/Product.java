package domain.entity;

import core.annotations.Column;
import core.annotations.Entity;

@Entity(table="products")
public class Product {

    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

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

    @Override
    public String toString() {
        return String.format("[%d] %s", id, name);
    }
}
