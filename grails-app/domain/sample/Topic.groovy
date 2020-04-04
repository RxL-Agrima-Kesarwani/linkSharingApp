package sample

class Topic {
    String name
    Date lastUpdated
    Date dateCreated
    VisibilityEnum visibility = VisibilityEnum.PRIVATE
    Person createdBy
   // Integer visibility
    static hasMany = [subscriptions: Subscription, resources: ResourceData]

    static belongsTo = [user: Person]

    static constraints = {
    }
    static mapping = {
        sort "name"

    }
}
