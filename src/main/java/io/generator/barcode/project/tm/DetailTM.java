package io.generator.barcode.project.tm;

public class DetailTM {
    private String itemCode;
    private String itemName;

    public DetailTM() {
    }

    public DetailTM(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "DetailTM{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
