package sample

enum VisibilityEnum {
    PUBLIC(1),
    PRIVATE(0)

    final int val
    private VisibilityEnum(int val) {
        this.val = val
    }
}