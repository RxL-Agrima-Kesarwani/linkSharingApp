package sample

class Subscription {
    Topic topic
   //SeriousnessEnum seriousness = SeriousnessEnum.VERY_SERIOUS
    Integer seriousness
    User user
    Date dateCreated


    static belongsTo = [user: User, topic:Topic]

    static mapping = {
        sort "user"
    }
}

