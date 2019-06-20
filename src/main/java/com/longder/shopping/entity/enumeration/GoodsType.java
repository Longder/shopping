package com.longder.shopping.entity.enumeration;

/**
 * 商品类型枚举
 */
public enum GoodsType {
    ELECTRONICS("ELECTRONICS","电子产品","label-success"),
    BOOKS("BOOKS","图书","label-default"),
    WOMEN_WEAR("WOMEN_WEAR","女装","label-primary"),
    MEN_WEAR("MEN_WEAR","男装","label-info"),
    FURNITURE("FURNITURE","家居","label-warning");

    /**
     * 名称
     */
    private String name;
    /**
     * 展示名
     */
    private String displayName;
    /**
     * 标签样式
     */
    private String label;

    GoodsType(String name, String displayName, String label) {
        this.name = name;
        this.displayName = displayName;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
