package sample

class ResourceData {
    String name
    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static hasMany = [readingItems: ReadingItem, resourceRatings: ResourceRating]
    static belongsTo = [user: User, topic: Topic]

    static constraints = {

    }
}