package com.example.ordersystemlab.enums;

public enum OrderSortField {

    ID("id"),
    QUANTITY("quantity");

    private final String fieldName;

    OrderSortField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static boolean isAllowed(String fieldName) {
        for (OrderSortField sortField : values()) {
            if (sortField.fieldName.equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
}