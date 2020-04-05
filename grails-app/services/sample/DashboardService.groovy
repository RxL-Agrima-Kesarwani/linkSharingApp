package sample

import org.springframework.web.multipart.MultipartFile

class DashboardService {
    def addTopic() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topicAdded = Topic.findByNameAndUser(params.topicnamelabel,loggedInUser)
        println(topicAdded)
        if (topicAdded) {
            //When user is creating topic
            //  flash.error = " topic already exists "
            flash.topicError = "topic already exists "
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
                flash.topicMessage = "Successfully created and subscribed to topic"
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
            println("bytes")
            DocumentResource documentResource = new DocumentResource(filePath:filePath,name:"resource1",
                    description: "docAdded",createdBy: loggedInUser,topic:topic)
            println("bytes")
            if (documentResource.validate()) {
                println("after validate")
                topic.addToResources(documentResource)
                topic.save()
                println("after save")
                documentResource.save(flush: true,failOnError: true)
                println("document added successfully")
                flash.documentMessage = 'document added successfully'

                //redirect(controller : "dashboard",action: "dashboard")
                redirect(action : "dashboard")
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
}
