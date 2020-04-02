package sample

enum SeriousnessEnum {
    VERY_SERIOUS(2),
    SERIOUS(1),
    CASUAL(0)

    final int val
    private SeriousnessEnum(int val) {
        this.val = val
    }
}