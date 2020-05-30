package io.generator.barcode.project.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Details implements SuperEntity {
    private String Name;
    @Id
    private String itemCode;

    public Details() {
    }

    public Details(String name, String itemCode) {
        Name = name;
        this.itemCode = itemCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "Details{" +
                "Name='" + Name + '\'' +
                ", itemCode='" + itemCode + '\'' +
                '}';
    }
}
