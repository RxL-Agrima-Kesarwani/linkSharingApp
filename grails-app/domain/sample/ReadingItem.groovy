package sample

class ReadingItem {
    ResourceData resource
    Users user
    Boolean isRead

    static belongsTo = [user: Users, resource:ResourceData]
}

