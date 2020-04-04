package sample

class ReadingItem {
    ResourceData resource
    Person user
    Boolean isRead = Boolean.FALSE

    static belongsTo = [user: Person, resource:ResourceData]
}

