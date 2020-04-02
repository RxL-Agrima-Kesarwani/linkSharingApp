package sample

class Subscription {
    String subscribedTopic
    Integer seriousness
    Date dateCreated

    static belongsTo = [user: Users, topic:Topic]

    static mapping = {
        sort "user"
    }
}

