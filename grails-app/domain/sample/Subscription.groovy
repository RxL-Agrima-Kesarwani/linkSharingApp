package sample

class Subscription {

   SeriousnessEnum seriousness = SeriousnessEnum.VERY_SERIOUS//we are giving s default value here

    Date dateCreated


    static belongsTo = [user: Person, topic:Topic]

    static mapping = {
      //  seriousness defaultValue: Seriousness.Very_SERIOUS
        sort "user"
    }
}

