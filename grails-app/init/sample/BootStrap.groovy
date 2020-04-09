package sample

class BootStrap {
    void createUser() {
        if (!Person.count()) {
            List users = [["Agrima", "Agrima", "kesarwani", "agrimakesarwani1997@gmail.com", true, true, "Agrima1"],
                          ["Agrima2", "Agrima2", "kesarwani", "abc123@gmail.com", true, true, "Agrima21"],
                          ["Agrima4", "Agrima4", "kesarwani", "ab123@gmail.com", true, true, "Agrima41"],
                          ["Agrima3", "Agrima3", "kesarwani", "abc1234@gmail.com", false, true, "Agrima31"]]

            users.each { userList ->
                Person user = new Person(userName: userList[0], firstName: userList[1], lastName: userList[2],
                        email: userList[3],
                        isAdmin: userList[4], isActive: userList[5], password: userList[6],
                        confirmPassword: userList[6])


                    user.save(flush: true, failOnError:true)
                }
            }
        }

    void createTopic() {
        if (!Topic.count()) {
            List topics = ["Topic1", "Topic2", "Topic3", "Topic4", "Topic5"]
            Person.list().each { user ->
                [VisibilityEnum.PUBLIC, VisibilityEnum.PRIVATE].each { visibility ->
                    topics.each { topicName ->
                        Topic topic = new Topic(name: topicName, visibility: visibility, user: user, createdBy: user)
                        topic.save(flush :true, failOnError: true)
                    }
                }
            }userName
        }
    }
    void createResource() {
        if (!ResourceData.count()) {
            List resources = ["Resource1", "Resource2", "Resource3", "Resource4", "Resource5"]
            Topic.list().each{topic->
                resources.each{resourceName->
                    LinkResource linkResource = new LinkResource(description: resourceName,
                            createdBy: topic.createdBy,user:topic.createdBy, topic: topic,
                            url:"http://www.google.com")
                    linkResource.save(flush :true,failOnError: true)
                    DocumentResource documentResource = new DocumentResource(description: resourceName,
                            createdBy: topic.createdBy,user:topic.createdBy, topic: topic, filePath:"/home/agrima/document/document.txt")
                    documentResource.save(flush :true,failOnError: true)
                }
            }
        }
    }
    def init = { servletContext ->
        createUser()
        createTopic()
        createResource()
    }
    def destroy = {
    }
}




