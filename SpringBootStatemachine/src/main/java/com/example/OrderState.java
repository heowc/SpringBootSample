package com.example;

public enum OrderState {
    RECEIPTING("RECEIPTING", "접수"),
    ORDER_PENDING("PENDING", "주문 대기"),
    DELIVERY_PENDING("DELIVERY_PENDING", "배송 대기"),
    DELIVERY_PROCEEDING("DELIVERY_PROCEEDING", "배송 중"),
    CANCEL("CANCEL", "취소"),
    COMPLETED("COMPLETED", "완료");

    private final String code;
    private final String description;

    OrderState(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
