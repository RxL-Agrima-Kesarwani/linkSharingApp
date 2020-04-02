package sample

class Topic {
    String topicName
    Date lastUpdated
    Date dateCreated
    Integer visibility
    static hasMany = [subscription: Subscription, resource: ResourceData]

    static belongsTo = [user: Users]

    static constraints = {
    }
    static mapping = {
        sort "topicName"

    }
}
