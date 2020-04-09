package sample

class ResourceData {

    String description
    Person createdBy
    Date dateCreated
    Date lastUpdated

    static hasMany = [readingItems: ReadingItem, resourceRatings: ResourceRating]
    static belongsTo = [user: Person, topic: Topic]

    static constraints = {
        user nullable:true

    }
}