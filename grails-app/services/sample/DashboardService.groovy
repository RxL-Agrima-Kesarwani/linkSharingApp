package sample

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile
@Transactional
class DashboardService {
    public Topic addTopic(Person loggedInUser, String topicName, String visibility) {
        Topic topic = new Topic(name: topicName, visibility: VisibilityEnum."${visibility}",
                user: loggedInUser.id, createdBy: loggedInUser)
        if (topic.validate()) {
            topic.save(flush: true, failOnError: true)
            Subscription subscription = subscribe(loggedInUser, topic, null)
            topic.addToSubscriptions(subscription)

        }
        return topic
    }

    public Subscription subscribe(Person loggedInUser, Topic topic, String seriousness) {
        // if (topic.visibility == VisibilityEnum.PUBLIC.toString()) {
        Subscription subscription = new Subscription(topic: topic,
                user: loggedInUser.id)
        if (seriousness) {
            subscription.seriousness = SeriousnessEnum."${seriousness}"
        }
        if (subscription.validate()) {
            subscription.save(flush: true)
        }
        println("...................visible")
        println(topic.visibility)
        return subscription

    }
    def logout = {
        session.invalidate()
        redirect("action": "homePage")
        print("log out")
    }



    public DocumentResource shareDocumentFinal(String filePath,Person loggedInUser,Topic topic) {

            DocumentResource documentResource = new DocumentResource(filePath: filePath, name: "resource1",
                    description: "docAdded", createdBy: loggedInUser, topic: topic,user :loggedInUser)
            println("bytes")
            if (documentResource.validate()) {
                println("after validate")
                topic.addToResources(documentResource)
                topic.save()
                println("after save")
                documentResource.save(flush: true, failOnError: true)
                println("document added successfully")
            }
            return documentResource
        }

        public LinkResource shareLinkFinal(String url,Person loggedInUser,
                                                    Topic topic) {
            LinkResource linkResource = new LinkResource(url: url, createdBy: loggedInUser,
                    name: "url", description: "url added",
                    topic: topic, user: loggedInUser)
            println("url")
            if (linkResource.validate()) {
                println("after validate")
                topic.addToResources(linkResource)
                topic.save()
                println("after save")
                linkResource.save(flush: true, failOnError: true)
        }
        return linkResource
    }

}