package sample

class ResourceData {
    String name
    String description
    Person createdBy
   // Topic topic
    Date dateCreated
    Date lastUpdated

    static hasMany = [readingItems: ReadingItem, resourceRatings: ResourceRating]
    static belongsTo = [user: Person, topic: Topic]

    static constraints = {

    }
}