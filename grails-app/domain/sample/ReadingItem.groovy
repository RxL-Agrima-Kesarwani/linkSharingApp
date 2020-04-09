package sample

class ReadingItem {

    Boolean isRead = Boolean.FALSE
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: Person, resource:ResourceData]
}

