package sample


import org.springframework.web.multipart.MultipartFile

class q {


    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        render(view: "homePageLinkSharing.gsp")
    }

    def homePage() {
        render(view: "homePageLinkSharing.gsp")
        //render ( view : "homePage.gsp")
        //RegisterForm obj = new RegisterForm(fname:"aaa" , lname :"asdsdsd")
        // Users obj=new Users(userName: "aa",lastName :"xcx")
        //obj.save()//domain ka naam
    }

    def dashboard() {
        render(view: "dashboard.gsp")
    }

    def home() {
        println("redirected flash.name")
        println(flash.name)
        println("session.name")
        println(session.name)
        render(view: "home")
    }

    def test() {
        def id = "book" + params.id
        def books = ["book1": ["id": 1, "name": "bookname1", "author": " authorName1 ", "price": 350],
                     "book2": ["id": 2, "name": "bookname2", "author": " authorName2 ", "price": 450],
                     "book3": ["id": 3, "name": "bookname3", "author": " authorName3 ", "price": 450]]
        def book = ["book": books.get(id)]
        // render(view:"test",model: ["name" : "dynamic test name"])
        // render(view:"test",model: book)
        render(view: "test", model: book)
    }

    def bookList() {
        def books = ["book1": ["id": 1, "name": "bookname1", "author": " authorName1 ", "price": 350],
                     "book2": ["id": 2, "name": "bookname2", "author": " authorName2 ", "price": 450],
                     "book3": ["id": 3, "name": "bookname3", "author": " authorName3 ", "price": 450]]
        def list = ["book1", "book2"]
        respond(books, formats: ['json'])
    }

    /* def testToHome() {
        //redirect(controller:'Sample2',action:'index')
        redirect(url: "/apple")
        // redirect(action:myaction,params:[])

    }*/

    def testToHome2() {
        forward(controller: 'home', action: 'show')

    }

    def paramsName1() {
        println(params.name)
        session.name = params.name
        render(text: "params name passed")
    }

    def paramsName2() {
        println(session.name)
        session.name = params.name
        render(text: "params name not passed")
    }

    def paramsName3() {
        println("session.name")
        println(session.name)
        flash.name = session.name
        println("flash.name")
        println(flash.name)
        redirect("action": "home")
        //render(view:home,text:"params name not passed")
    }

    def saveUser() {
        println params
        def statement = "happy"
        def requiredParams = ['firstnamelabel', 'lastnamelabel', 'emaillabel', 'usernamelabel', 'passwordlabel', 'confirmpasswordlabel']
        requiredParams.each { singleParam ->
            if (!params.containsKey(singleParam)) {
                render(text: "login failed")

                return 0
            }

        }
        Users user = new Users(firstName: params.firstnamelabel, lastName: params.lastnamelabel, email: params.emaillabel, userName: params.usernamelabel, password: params.passwordlabel, confirmPassword: params.confirmpasswordlabel)
        if (params.photo) {
            MultipartFile multipartFile = params.photo
            user.photo = multipartFile.bytes
        }
        //user.validate()

        println params
        if (user.save(flush: true)) {
            //flash.message = "SUCCESSFULLY REGISTERED"
            // render(text: "succesfully registered")
            redirect(view: "dashboard")

        } else {

            flash.error = "Registration Failed.Kindly register again..."
            redirect(view: "homePage")
            //render(text: "fail to put in DB")
        }

    }


    def login() {

        Users users1 = Users.findByUserNameAndPassword(params.usernamelabel, params.passwordlabel)
        Users users2 = Users.findByEmailAndPassword(params.usernamelabel, params.passwordlabel)
        if (users1.photo) {
            String encoded = Base64.getEncoder().encodeToString(users1.photo)
            session.setAttribute("userPhoto", encoded)
        }

        println " SESSION"

        if (users1 != null || users2 != null) {
            if (users1 != null) {
                session.userSession = users1.userName
                //redirect (view : "dashboard.gsp")

            } else if (users2 != null) {
                session.userSession = users2.userName
                //redirect (view : "dashboard.gsp")
                println("inside ELSE iffff")
                println ">>>>>> users2.userName"
                println ">>>>>> users2.userNamelabel"
            }
            //render (text:" sucessfully loged in")
            redirect("action": "dashboard")
            //redirect(view: "subscribeTopic.gsp")
        } else {

            flash.error = "Invalid username or email"
            render(text: "Invalid username or email")
        }
    }

    def addTopic() {
        Users loggedInUser = Users.findByUserName(session.userSession)
        Topic topic = new Topic(topicName: params.topicnamelabel, visibility: params.visibility, user: loggedInUser.id)
        println("     >>>>>>>")
        println("logged in user")
        println(loggedInUser.id)
        topic.validate()
        if (topic.hasErrors()) {
            topic.errors.allErrors.each {
                println it
            }
        }
        //topic.validate(["topicName", "visibility"])
        println params
        topic.save(flush: true)
        render(text: "topic saved")

    }


    def shareDocumentFinal() {

        Users loggedInUser = Users.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topiclabel)
        println(topic)
        DocumentResource documentResource = new DocumentResource(document: params.document)
        println("DOC")

        println(documentResource.id)

        documentResource = documentResource.save(flush: true, failOnError: true)
        println("DOC")

        println(documentResource.id)

        ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic, documentResource: documentResource)
        println("RES")
        println(resourceData)
        println(loggedInUser.id)
        println(topic.id)
        println(params.topic,)
        println(documentResource.id)
        //println(topic.topicName)
        println(params)
        documentResource.validate()
        if (documentResource.hasErrors()) {
            documentResource.errors.allErrors.each {
                println it
            }
        }
        // def f = params.document
        //render f.inputStream.text
        if (params.document) {
            MultipartFile doc = params.document
            documentResource.document = doc.bytes
            //resourceData.name = doc.bytes

        }
        resourceData.save(flush: true, failOnError: true)
        // shareDocument.validate(["topicName", "visibility"])

//        println params
//        linkResource.save(flush: true)
        println(".................................................................................")
        println("topic ki id")
        println(topic.id)
        render(text: "Document saved")
    }












        // Topic topic = new Topic(topicName: params.topicnamelabel, visibility: params.visibility ,user: loggedInUser.id,createdBy:loggedInUser.id)
        // Topic topic = new Topic(topicName: params.topicnamelabel, visibility: params.visibility ,user: loggedInUser.id,createdBy:loggedInUser.id)
      /*  DocumentResource shareDocument = new DocumentResource(document: params.document, user: loggedInUser.id, topic: topic.id, topicName: topic.id)
        println("     >>>>>>>")
        println("logged in user")
        println(loggedInUser.id)
        println("     zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz")
        println(topic.id)
        println(topic.topicName)

        shareDocument.validate()
        if (shareDocument.hasErrors()) {
            shareDocument.errors.allErrors.each {
                println it
            }
        }
        if (params.document) {
            MultipartFile multipartFile = params.document
            topic.document = multipartFile.bytes
        }
        // shareDocument.validate(["topicName", "visibility"])

        println params
        shareDocument.save(flush: true)
        println(".................................................................................")
        println("topic ki id")
        println(topic.id)
        render(text: "document saved")

    }*/

    def shareLink() {

        Users loggedInUser = Users.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topic)
        println(topic)
        //ResourceData resourceData = new ResourceData
        // Topic topic = new Topic(topicName: params.topicnamelabel, visibility: params.visibility ,user: loggedInUser.id,createdBy:loggedInUser.id)
        // Topic topic = new Topic(topicName: params.topicnamelabel, visibility: params.visibility ,user: loggedInUser.id,createdBy:loggedInUser.id)
        LinkResource linkResource = new LinkResource(link: params.link)
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
        println(topic.topicName)
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

    def subscribeTopicView() {
        render(view: "subscribeTopic.gsp")
    }

    def subscribeTopic() {

        Users loggedInUser = Users.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topicnamelabel)
        println("   >>>>>>>")
        println("logged in user")
        println(loggedInUser.id)
        println("topic ki id")
        println(topic.id)
        Subscription subscription = new Subscription(subscribedTopic: params.topicnamelabel, seriousness: params.selectType, user: loggedInUser.id, topic: topic.id)

        subscription.validate()
        if (subscription.hasErrors()) {
            subscription.errors.allErrors.each {
                println it
            }
        }
        //topic.validate(["topicName", "visibility"])
        println params
        subscription.save(flush: true)

        //topic.validate(["topicName", "visibility"])

        //render(text: "topic subscribed")
        //render(text: "subscribed")

    }

//    def topicsOfParticularUser() {
//        Users loggedInUser = Users.findByUserNameOrEmail(session.userSession)
//
//    }

    def publicTopics() {
        def criteria = Topic.createCriteria()
        def result = criteria.list {}
        result.each { topic ->
            println "Topic Name: ${topic.topicName}"
            println "visibility: ${topic.visibility}"
        }
//        def publicTopics() {
//        def result = Topic.createCriteria().list{
//            eq ('topicName', 'lotus')
//        }
//        eq ('topicName', 'Public')
//        //if(${topic.visibility})
//        render(text: "list")
//    }

//        def publicResult = Topic.createCriteria().list {
//            eq('topicName', 'public')
//
//        }
//    }
//        def result = Topic.createCriteria().list{
//            eq ('topicName', 'public')

//
//        def value = Topic.findAllTopicNameWhereVisibilityEquals("public")
//


//        def showPublicTopic() {
//
//            List<Topic> topicList = Topic.list()
//            return topicList
//        }
    }
    def adminTopics(){
        def topic = Topic.list()
        [topic: topic]
        println (topic)
        render(view: "viewAdminTopics.gsp", model: [topic: Topic.get(2) ])
    }

    def subscribedTopicOfUser(){
        Users loggedInUser = Users.findByUserName(session.userSession)
        println(loggedInUser)
        def val=loggedInUser.id
        def subscribedTopicOfUser = Subscription.findAllWhere(loggedInUser : loggedInUser)
        println (subscribedTopicOfUser)
//        def c = Subscription.createCriteria()
//        def results = c.list {
//            like("holderFirstName", "Fred%")
//            and {
//                between("balance", 500, 1000)
//                eq("branch", "London")
//            }
//            maxResults(10)
//            order("holderLastName", "desc")
//        }

    }

}




        /* Topic topic = new Topic()
         if(topic.visibility.equals("public")){
            render(text:"topic.name")

    }


    def shareDocument() {
        Users users1 = Users.findByUserNameAndPassword(params.usernamelabel, params.passwordlabel)
        if (user1 != null) {
            def requiredParams = ['documentlabel', 'descriptionlabel', 'topiclabel']
            requiredParams.each { singleParam ->
                if (!params.containsKey(singleParam)) {
                    render(text: "enter all fields")

                    return 0
                }

            }
            // Users user = new Users(firstName: params.firstnamelabel, lastName: params.lastnamelabel, email: params.emaillabel, userName: params.usernamelabel, password: params.passwordlabel, confirmPassword: params.confirmpasswordlabel)
            if (params.documentlabel) {
                MultipartFile multipartFile = params.documentlabel
                users1.filepath = multipartFile.bytes
            }
            users1.validate() dashboard
            users1.errors.allErrors.each {
                println "Errors" + it
            }
            println params
            if (users1.save(flush: true)) {
                render(text: "succesfully put in DB")
                //redirect (action: "dashboard")

            } else {
                render(text: "fail to put in DB")
            }

        }
    }
    //UPDATE USER KI PROFILE
    /*def editProfile() {

        render(view: "editProfilePage")
    }

    def popUp() {
        render(view: "shareLinkComponent")
    }
def logout = {
   session.invalidate()
    redirect("action": "homePage")
}
}

SESSSION:

SESSION ENDS
def logout() {
   log.info "User agent: " + request.getHeader("User-Agent")
   session.invalidate()
   redirect(action: "login")
}

}
  def beforeInterceptor = {
   println "Tracing action ${actionUri}"
}
def interceptorAction(){
   println("interceptor called")
}

def paramsBind() {
   Book book = new Book()
   bindData(book, params, [exclude: ['author']])
   println(book.name)
   render text: "params bind" + book.name + "." + book.author
}
}*/


