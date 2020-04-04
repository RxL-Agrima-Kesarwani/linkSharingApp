package sample

import org.springframework.web.multipart.MultipartFile

class dashboardController {
    def mailService
    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def dashboard() {
        if (session.userSession) {
            List <Topic> topicList = Topic.list()
            render(view: "dashboard", model: [topicList:topicList])
        } else {
            redirect(controller : "login",action: "homePage")
            //redirect(action: "homePage")
        }
    }
    def addTopic() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topicAdded = Topic.findByNameAndUser(params.topicnamelabel,loggedInUser)
        println(topicAdded)
        if (topicAdded) {
            //When user is creating topic
            flash.error = " topic already exists "
            redirect(action: "dashboard")
            return
        }

        else {
            Topic topic = new Topic(name: params.topicnamelabel, visibility:VisibilityEnum."${params.visibility}",
                    user: loggedInUser.id,createdBy: loggedInUser)

            println("     >>>>>>>")
            println("logged in user")
            println(loggedInUser.id)

            if (topic.validate()) {
                topic.save(flush: true)
                // subscribeTopic()
                subscribe(loggedInUser,topic,null)
                flash.message = "Successfully created and subscribed to topic"
                redirect (action: "dashboard")
                return
            }

            else{  topic.errors.allErrors.each {
                println it }
                flash.error = "Unable to add topic"
                redirect (action: "dashboard")
                return
            }
            //topic.validate(["topicName", "visibility"])


        }
    }
    def logout = {
        session.invalidate()
        redirect("action": "homePage")
        print("log out")
    }
    def deleteTopic() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topicnamelabel)
        println(topic)
        //Topic topic = new Topic(params.topicnamelabel, visibility: params.visibility, user: loggedInUser.id)
        println("deletingggggg")
        println("logged in user")
        println(loggedInUser)
        println("topic ki id")
        println(topic)
        if (topic.visibility == VisibilityEnum.PUBLIC.getVal()) {
            //Topic value = new Topic(topicName: params.topicnamelabel, user: loggedInUser.id)
            Topic topicToBeDeleted = Topic.findByTopicNameAndUser(params.topicnamelabel, loggedInUser)
            println("delete topic name " + topicToBeDeleted)
            topicToBeDeleted.delete(flush:true)
            render(text: "topic deleted")
            println("topic deleted")

        }
    }
    def shareLink() {

        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topic)
        println(topic)
        LinkResource linkResource = new LinkResource(url: params.url)
        linkResource = linkResource.save(flush: true, failOnError: true)
        println(linkResource.id)
        ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic, linkResource: linkResource)
        //ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic)
        println("     zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz")
        println(resourceData)
        println(loggedInUser.id)
        println(topic.id)
        println(params.topic,)
        println(linkResource.id)
        println(topic.name)
        println(params)
        linkResource.validate()
        if (linkResource.hasErrors()) {
            linkResource.errors.allErrors.each {
                println it
            }
        }
        resourceData.save(flush: true, failOnError: true)
        // shareDocument.validate(["topicName", "visibility"])

//        println params
//        linkResource.save(flush: true)
        println(".................................................................................")
        println("topic ki id")
        println(topic.id)
        render(text: "Link saved")

    }
    def shareDocumentFinal() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findById(params.long('topiclabel'))
        println(topic)
        println(params)
        if(topic && params.document){
            println ("inside if")
            MultipartFile multipartFile = params.document
            String filePath = "/home/agrima/Documents/${multipartFile.getOriginalFilename()}"
            File file = new File(filePath)
            file.bytes = multipartFile.bytes
            DocumentResource documentResource = new DocumentResource(filePath:filePath,name:"resource1",
                    description: "docAdded",createdBy: loggedInUser,topic:topic)
            if (documentResource.validate()) {
                topic.addToResources(documentResource)
                topic.save()
                println("after save")
                documentResource.save(flush: true,failOnError: true)
                println("document added successfully")
                flash.message = 'document added successfully'
                redirect(controller : "dashboard",action: "dashboard")
                //redirect(action : "dashboard")
                return
            }
        }
        else{
            flash.error  = " Unable to add document"
            redirect(controller : "dashboard",action: "dashboard")
            //redirect(action : "dashboard")

        }


    }
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
//    def showTopics() {
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

}
