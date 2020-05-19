package com.longder.shopping.entity.enumeration;

/**
 * 商品类型枚举
 */
public enum GoodsType {
    ELECTRONICS("TROPICAL_FRUIT","热带水果","label-success"),
    BOOKS("SUBTROPICS_FRUIT","亚热带水果","label-default"),
    WOMEN_WEAR("WINTER_FRUIT","冬季水果","label-primary");

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
