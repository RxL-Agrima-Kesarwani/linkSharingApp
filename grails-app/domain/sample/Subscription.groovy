package sample

class Subscription {
    Topic topic
   SeriousnessEnum seriousness = SeriousnessEnum.VERY_SERIOUS
   // Integer seriousness
    Person user
    Date dateCreated


    static belongsTo = [user: Person, topic:Topic]

    static mapping = {
        sort "user"
    }
}

