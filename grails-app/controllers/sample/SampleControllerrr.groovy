package sample

import grails.converters.JSON
import grails.transaction.Transactional
import grails.validation.ValidationException
import org.apache.catalina.User
import org.springframework.web.multipart.MultipartFile

import javax.annotation.Resource

class SampleController {


    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        render(view: "homePageLinkSharing.gsp")
    }

    def homePage() {
        def c = ResourceData.createCriteria()
        def results = c.list {
            maxResults(5)
            order("dateCreated", "desc")

        }
        render(view: "homePageLinkSharing.gsp", model: [information: results])
        //render ( view : "homePage.gsp")
        //RegisterForm obj = new RegisterForm(fname:"aaa" , lname :"asdsdsd")
        // Users obj=new Users(userName: "aa",lastName :"xcx")
        //obj.save()//domain ka naam
    }

    def dashboard() {
        render(view: "dashboard.gsp")
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


    def logout = {
        session.invalidate()
        redirect("action": "homePage")
        print("log out")
    }



//    def logout() {
//        Users loggedInUser = Users.findByUserName(session.userSession)
//       // Users users1 = Users.findByUserNameAndPassword(params.usernamelabel, params.passwordlabel)
//        //Users users2 = Users.findByEmailAndPassword(params.usernamelabel, params.passwordlabel)
//        //user1==null
//        session.invalidate()
//       // redirect(action: "login")
//        render(text:"log out")
//    }

//    def logout(){
//        //Users users1 = Users.findByUserNameAndPassword(params.usernamelabel, params.passwordlabel)
//        //Users users2 = Users.findByEmailAndPassword(params.usernamelabel, params.passwordlabel)
//        //session.users1=null
//        //redirect("action": 'login')
//        session.invalidate()
//        render (text :"logout")
//        println(user1)
//        println(user2)
//        println(logout)
//    }

    def addTopic() {
        Users loggedInUser = Users.findByUserName(session.userSession)
        Topic topic = new Topic(topicName: params.topicnamelabel, visibility: params.visibility, user: loggedInUser.id)
        Topic topicAdded = Topic.findByTopicNameAndUser(params.topicnamelabel,loggedInUser)
        println(topicAdded)
        if (topicAdded == null) { //When user is creating topic
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
            subscribeTopic()
            render(text: "topic saved")
        } else { //When topic already exists for that user
            render(text: "topic  already exists")
        }
    }

    def deleteTopic() {
        Users loggedInUser = Users.findByUserName(session.userSession)
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


   /* def upload(){
        def f = request.getFile('document')
        if (f.empty) {
            flash.message = 'file cannot be empty'
            render(text: 'choose another file')
            return
        }

        f.transferTo(new File('/home/agrima/Documents/document.txt'))
        response.sendError(200, 'Done')
    }*/

    def shareDocumentFinal() {
        Users loggedInUser = Users.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topiclabel)
        println(topic)
        DocumentResource documentResource = new DocumentResource(document: params.document)
        def f = request.getFile('document')
        println(",,,,,,,,,,,,,,,," + f)
        if (f.empty) {
            flash.message = 'file cannot be empty'
            render(text: 'choose another file')
            return
        }

        //f.putAt(new File('/home/agrima/Documents/document.txt'))
        f.transferTo(new File('/home/agrima/Documents/document.txt'))
        response.sendError(200, 'Done')

        //if (params.document) {
          //  upload()
        //}
        // MultipartFile multipartFile = params.document
        //documentResource.document = multipartFile.bytes
        documentResource = documentResource.save(flush: true, failOnError: true)
        println("DOCUMENT")
        println(documentResource)

        println("DOC" + documentResource)
        ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id,
                name: params.topic, documentResource: documentResource)
        println("RES" + resourceData)
        println(topic)
        // println(params.topic)
        //println(documentResource.id)
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
//        if (params.document) {
//            MultipartFile multipartFile = params.document
//            documentResource.document = multipartFile.bytes
//            //resourceData.name = doc.bytes
//
//        }
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

    def deleteLink() {
        Users loggedInUser = Users.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topicnamelabel)
      //  LinkResource linkResource = LinkResource
        println(topic)
        //Topic topic = new Topic(params.topicnamelabel, visibility: params.visibility, user: loggedInUser.id)
        println("deletingggggg")
        println("logged in user")
        println(loggedInUser)
        println("topic ki id")
        println(topic)
        if (Topic.findByUserAndTopicName(loggedInUser, topic.topicName)) {
            //Topic value = new Topic(topicName: params.topicnamelabel, user: loggedInUser.id)
            Topic topicToBeDeleted = Topic.findByTopicNameAndUser(params.topicnamelabel, loggedInUser)
            println("delete topic name " + topicToBeDeleted)
            topicToBeDeleted.delete(flush:true)
            render(text: "topic deleted")
            println("topic deleted")

        }
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
        int seriousness = SeriousnessEnum.VERY_SERIOUS.getVal()
        if (params.containsKey('selectType')) {
            seriousness = params.selectType.toInteger()
        }
        if (topic.visibility == VisibilityEnum.PUBLIC.getVal()) {
            Subscription subscription = new Subscription(subscribedTopic: params.topicnamelabel, seriousness: seriousness,
                                                            user: loggedInUser.id, topic: topic.id)

            subscription.validate()
            if (subscription.hasErrors()) {
                subscription.errors.allErrors.each {
                    println it
                }
            }
            //topic.validate(["topicName", "visibility"])
            println params
            subscription.save(flush: true)
            println("...................visible")
            println(topic.visibility)
            //topic.validate(["topicName", "visibility"])

            //render(text: "topic subscribed")
            //render(text: "subscribed")
        } else if (topic.visibility == VisibilityEnum.PRIVATE.getVal()) {
            println("TOPIC ID: " )
            //println(Topic.findByUserName)
            println("logged in user")
            println(loggedInUser)
            if (Topic.findByUserAndTopicName(loggedInUser, topic.topicName)) {
                Subscription subscription = new Subscription(subscribedTopic: params.topicnamelabel, seriousness: seriousness,
                        user: loggedInUser.id, topic: topic.id)

                subscription.validate()
                if (subscription.hasErrors()) {
                    subscription.errors.allErrors.each {
                        println it
                    }
                }
                //topic.validate(["topicName", "visibility"])
                println params
                subscription.save(flush: true)
                render (text: "subscribed")
            } else {
                render(text: "u can subscribe only when topic is public")
                println("u can subscribe only when topic is public")
            }
        } else {
            render(text: "403: Bad params")
            println("403: Bad params")
        }

 }
//    def showTopics() {
//        def topics= Topic.list()
//        [topics:topics]
//    }

    def topicsCreatedByParticularUser(){
        Users loggedInUser = Users.findByUserName(session.userSession)
        List users = Topic.findAllByUser(loggedInUser)
        println(users)
        println(Topic)
       // List list []
       // model: [list.]

       render (view:"abc", model: [ list: users.topicName])
       // def t = users.topicName.list()
        //render(text: users.topicName)
        //println(t)
    }
    def qwer(){
        render(view: "emailSender.gsp")
    }

  def subscribedTopicsOfParticularUser() {
      Users loggedInUser = Users.findByUserName(session.userSession)
      println ("users......................")
      println (loggedInUser)
      List users =Subscription.findAllByUser(loggedInUser)
      render(text: "users")
      println(users)
      println (Subscription)
      println (Subscription.get(Subscription))
//      List users =Subscription.findByUsers(loggedInUser)
//      println (users)

      //render users.list() as JSON
      //List list = java.util.Arrays.asList("users");
             // List <Users> abc =Subscription.findAllByUserId(loggedInUser.id)

     // println(abc)

    }
    def markAsRead(){
        println params
        render (text:params)
      //  resourceService.markRead(params)
        //flash.message = "Marked As Read Successfully !!! "
        //redirect controller: 'user', action: 'dashboard'

    }

    def topicPage(){
        render(view : "topic.gsp")
    }


//    def publicTopics() {
//        def criteria = Topic.createCriteria()
//        def result = criteria.list {}
//        result.each { topic ->
//            println "Topic Name: ${topic.topicName}"
//            println "visibility: ${topic.visibility}"
//        }
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

//
//    def adminTopics() {
//        def topic = Topic.list()
//        [topic: topic]
//        println(topic)
//        render(view: "viewAdminTopics.gsp", model: [topic: Topic.get(2)])
//    }
//
//
//
//
//
//
//    List<Topic> subscriptions(Users user){
//        List<Subscription> subscriptions = Subscription.createCriteria().list([max:5]){
//            eq('user',user)
//            order('dateCreated','desc')
//        }
//
//        List<Topic> topicList =[]
//        subscriptions?.each {Subscription s->
//            Topic t = s.topic
//            t.seriousness = s.seriousness
//            topicList.add(t)
//        }
//        return topicList
//    }
//
    def shareDoc() {
        Users user = Users.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topiclabel)
        def file1 = request.getFile("document")
        String dir1 = new Date()
        String dir2 = dir1.split(" ").join("")
        String dir = "/home/agrima/sample/MainApp/DocumentResource/${dir2}.pdf"
        file1.transferTo(new File(dir))
        DocumentResource dr = new DocumentResource(filePath: dir,description:params.descriptionlabel,user:user,topic:topic)
        dr.save(flush:true , failOnError:true)
        flash.message = "Document has been added successfully"
        redirect(controller:"dashboard",action: 'subscribedTopics')
    }


















    def subscribedTopicOfUser() {
        Users loggedInUser = Users.findByUserName(session.userSession)
        println(loggedInUser)
        def val = loggedInUser.id
//        List<Users> usersList = Users.findAll()
//        usersList.each {
//            println(it.userName + it.topicName)
//        }
        List<Topic> topicList = Topic.findAll()
        topicList.each{
            println (it.topicName + it.loggedInUser)
        }
        //println(Users.findAllWhere()*.topicName)
    }
}

        //def subscribedTopicOfUser = Subscription*.findAllWhere(topic :subscribedTopicOfUser)
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

    def popUp() {def logout = {
   session.invalidate()
    redirect("action": "homePage")
}
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


