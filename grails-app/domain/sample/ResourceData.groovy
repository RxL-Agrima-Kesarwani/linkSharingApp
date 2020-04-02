package sample

class ResourceData {
    String name
   // Users createdBy
    // Topic topicName
    Date dateCreated
    Date lastUpdated

    static hasMany = [readingItem: ReadingItem, resourceRating: ResourceRating]
    static belongsTo = [user: Users, topicName: Topic, linkResource: LinkResource, documentResource: DocumentResource]

    static constraints = {
        linkResource nullable: true
        documentResource nullable: true
    }
}