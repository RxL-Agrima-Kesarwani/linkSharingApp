package sample

class ReadingItem {
    ResourceData resource
    User user
    Boolean isRead = Boolean.FALSE

    static belongsTo = [user: User, resource:ResourceData]
}

