package sample

import org.springframework.web.multipart.MultipartFile

class DashboardController {
    def mailService
    def dashboardService
    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def dashboard() {
        if (session.userSession) {
            List<Topic> topicList = Topic.list()
            render(view: "dashboard", model: [topicList: topicList, userInfo: userInformation()])
        } else {
            redirect(controller: "login", action: "homePage")
        }
    }

    def addTopic() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topicAdded = Topic.findByNameAndUser(params.topicnamelabel, loggedInUser)
        println(topicAdded)
        if (topicAdded) {
            flash.topicError = "topic already exists "
            redirect(action: "dashboard")
            return
        }
        dashboardService.addTopic(loggedInUser, params.topicnamelabel, params.visibility)
        flash.topicMessage = "Topic  with name ${params.topicnamelabel} ssfully added"
        redirect(action: "dashboard")
    }

    def logout() {
        session.invalidate()
        //println("log outttt")
        redirect(controller: "login", action: "homePage")
        //print("log out")
    }

    def deleteTopic() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findByIdAndUser(params.long('topiclabel'), loggedInUser)
        println(topic)
        println("deletingggggg")
        println("logged in user")
        println(loggedInUser)
        println("topic ki id")
        println(topic)
        println("delete topic name " + topic)
        topic.delete(flush: true)
        // render(text: "topic deleted")
        redirect(action: "dashboard")
        println("topic deleted")

    }

    def shareLinkFinal() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findById(params.long('topiclabel'))
        println(topic)
        println(params)
        if (topic && params.link) {
            dashboardService.shareLinkFinal(params.link, loggedInUser,
                    topic)
            flash.linkMessage = 'Url added successfully'

            //redirect(controller : "dashboard",action: "dashboard")
            redirect(action: "dashboard")

        }
    }

    def shareDocumentFinal() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findById(params.long('topiclabel'))
        println(topic)
        println(params)
        if (topic && params.document) {
            println("inside if")
            MultipartFile multipartFile = params.document
            String filePath = "/home/agrima/Documents/${multipartFile.getOriginalFilename()}"
            File file = new File(filePath)
            file.bytes = multipartFile.bytes
            println("bytes")
            DocumentResource documentResource = new DocumentResource(filePath: filePath, name: "resource1",
                    description: "docAdded", createdBy: loggedInUser, topic: topic)
            println("bytes")
            if (documentResource.validate()) {
                println("after validate")
                topic.addToResources(documentResource)
                topic.save()
                println("after save")
                documentResource.save(flush: true, failOnError: true)
                println("document added successfully")
                flash.documentMessage = 'document added successfully'

                //redirect(controller : "dashboard",action: "dashboard")
                redirect(action: "dashboard")
                return
            } else {
                flash.documentError = " Unable to add document"
                println(" Unable to add document")

                redirect(controller: "dashboard", action: "dashboard")
                return
                //redirect(action : "dashboard")
            }
        }


    }

//
    def subscribeTopicView() {
        render(view: "subscribeTopic")
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
        subscribe(loggedInUser, topic, params.seriousness)
        redirect(action: "dashboard")
    }
    def subscribe(Person loggedInUser, Topic topic, String seriousness) {
        // if (topic.visibility == VisibilityEnum.PUBLIC.toString()) {
        Subscription subscription = new Subscription(topic: topic,
                user: loggedInUser)
        if (seriousness) {
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
    }
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

    def userInformation() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Integer countTopic = Topic.countByCreatedBy(loggedInUser)
        Integer countSubscription = Subscription.countByUser(loggedInUser)
        String fullName = loggedInUser.firstName + " " + loggedInUser.lastName

        return [name: fullName, userName: loggedInUser.userName, countTopic: countTopic,
        countSubscription:countSubscription]
    }
}

//   def showTopics() {
//        def topics= Topic.list()
//        [topics:topics]
//    }
//    def publicTopicsNotCreatedByUser(){
//        Users loggedInUser = Users.findByUserName(session.userSession)
//        List publicTopicsNotCreatedByUser = Topic.createCriteria().list(){
//            and{
//                not{'in' {"users" ,loggedInUser}}
//                eq("visibility", VisibilityEnum.PUBLIC.getVal())
//            }
//        }
//        println(publicTopicsNotCreatedByUser)
//        render(text :publicTopicsNotCreatedByUser )
//    }

//def shareDocumentFinal() {
//        Person loggedInUser = Person.findByUserName(session.userSession)
//        Topic topic = Topic.findById(params.long('topiclabel'))
//        println(topic)
//        println(params)
//        if(topic && params.document){
//           dashboardService.shareDocumentFinal(params.document,loggedInUser,topic)
//             flash.documentMessage = 'document added successfully'
//               redirect(action : "dashboard")
//
//        }
//    }
