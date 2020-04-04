package sample

class Topic {
    String name
    Date lastUpdated
    Date dateCreated
    //VisibilityEnum visibility = VisibilityEnum.PRIVATE
    User createdBy
    Integer visibility
    static hasMany = [subscription: Subscription, resource: ResourceData]

    static belongsTo = [user: User]

    static constraints = {
    }
    static mapping = {
        sort "name"

    }
}
