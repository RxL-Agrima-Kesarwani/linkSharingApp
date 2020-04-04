package sample

public enum SeriousnessEnum {
    VERY_SERIOUS(2),
    SERIOUS(1),
    CASUAL(0)

    final int val
     SeriousnessEnum(int val) {
        this.val = val
    }
}