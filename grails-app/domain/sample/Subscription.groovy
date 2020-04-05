package sample

class Subscription {
    Topic topic
   SeriousnessEnum seriousness = SeriousnessEnum.VERY_SERIOUS//we are giving s default value here
    Person user
    Date dateCreated


    static belongsTo = [user: Person, topic:Topic]

    static mapping = {
        sort "user"
    }
}

