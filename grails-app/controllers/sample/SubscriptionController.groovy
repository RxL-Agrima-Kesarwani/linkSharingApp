package sample

class SubscriptionController {
    def subscribeTopicView(){
        render (view:"subscribeTopic")
    }

    def subscribeTopic() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findByName(params.topicnamelabel)
        println("   >>>>>>>")
        println("logged in user")
        println(loggedInUser.id)
        println("topic ki id")
        println(topic.id)
        //String seriousness = SeriousnessEnum.VERY_SERIOUS//as if not mentioned it has to be very serious
        subscribe(loggedInUser,topic,params.seriousness)
        redirect(action: "dashboard")


    }
    def subscribe(Person loggedInUser,Topic topic,String seriousness){
        // if (topic.visibility == VisibilityEnum.PUBLIC.toString()) {
        Subscription subscription = new Subscription(topic: topic,
                user: loggedInUser.id)
        if(seriousness){
            subscription.seriousness = SeriousnessEnum."${seriousness}"
        }


        if (subscription.validate()) {
            subscription.save(flush: true)
            flash.message = "Subscribed"
        }
        //topic.validate(["topicName", "visibility"])
        println params

        println("...................visible")
        println(topic.visibility)
        //topic.validate(["topicName", "visibility"])

        //render(text: "topic subscribed")
        //render(text: "subscribed")
        //}
//        else if (topic.visibility == VisibilityEnum.PRIVATE.toString()) {
//            println("TOPIC ID: " )
//            //println(Topic.findByUserName)
//            println("logged in user")
//            println(loggedInUser)
//            if (Topic.findByUserAndTopicName(loggedInUser, topic.name)) {
//                Subscription subscription = new Subscription(topic: topic,
//                        seriousness: SeriousnessEnum."${seriousness}",
//                        user: loggedInUser.id)
//
//
//                if (subscription.validate()) {
//                    subscription.save(flush: true)
//                }
//                //topic.validate(["topicName", "visibility"])
//                println params
//
//               // render (text: "subscribed")
//            }
//        }


    }

}

