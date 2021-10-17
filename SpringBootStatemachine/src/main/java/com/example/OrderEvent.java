package com.example;

public enum OrderEvent {
    TAKEOVER("TAKEOVER", "배송 업체 인계", Role.ADMIN),
    OUT_OF_STOCK("OUT_OF_STOCK", "재고부족", Role.ADMIN),
    // ...

    OFFER_RECEIPT("OFFER_RECEIPT", "주문 신청", Role.USER),
    RECEIPT_CANCEL("RECEIPT_CANCEL", "주문 취소", Role.USER),
    PURCHASE("PURCHASE", "구매", Role.USER),
    PURCHASE_CANCEL("PURCHASE_CANCEL", "구매 취소", Role.USER),
    REFUND("REFUND", "환불", Role.USER),
    EXCHANGE("EXCHANGE", "교환", Role.USER),
    RETURN("RETURN", "반품", Role.USER),
    CONFIRM("CONFIRM", "확정", Role.USER);

    private final String code;
    private final String description;
    private final Role role;

    OrderEvent(String code, String description, Role role) {
        this.code = code;
        this.description = description;
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Role getRole() {
        return role;
    }
}
