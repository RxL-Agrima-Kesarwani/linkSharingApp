package sample

import org.springframework.web.multipart.MultipartFile

class SampleController {
     def mailService
       static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
      //  redirect(action: "homePage")
    }

    def homePage() {
        def c = ResourceData.createCriteria()
        def recentPosts = c.list {
            maxResults(5)
            order("dateCreated", "desc")
        }
        render(view: "homePageLinkSharing.gsp", model: [information: recentPosts])
       // render(view: "homePageLinkSharing.gsp")
    }

    def dashboard() {
        if (session.userSession) {
            List <Topic> topicList = Topic.list()
            render(view: "dashboard.gsp", model: [topicList:topicList])
        } else {
            redirect(action: "homePage")
        }
    }

    def register() {
        println params
        def statement = "happy"
        def requiredParams = ['firstnamelabel', 'lastnamelabel', 'emaillabel', 'usernamelabel', 'passwordlabel',
                              'confirmpasswordlabel']
        requiredParams.each { singleParam ->
            if (!params.containsKey(singleParam)) {
                flash.error = "Registration Failed.Kindly register again..."
                redirect(action : "homePage")
                return 0
            }

        }
        Person user = new Person(firstName: params.firstnamelabel, lastName: params.lastnamelabel,
                email: params.emaillabel, userName: params.usernamelabel, password: params.passwordlabel,
                confirmPassword: params.confirmpasswordlabel)
        if (params.photo) {
            MultipartFile multipartFile = params.photo
            user.photo = multipartFile.bytes
        }
        //user.validate()
        println params
        if (user.save(flush: true)) {
            flash.message = "SUCCESSFULLY REGISTERED"
            // render(text: "succesfully registered")
            redirect(action: "homePage")

        } else {
            flash.error = "Registration Failed.Kindly register again..."
            redirect(action: "homePage")
            //render(text: "fail to put in DB")
        }

    }

    def login() {

        Person loggedInUser = Person.findByUserNameAndPassword(params.usernamelabel,
                params.passwordlabel)
       // Person users2 = Person.findByEmailAndPassword(params.usernamelabel, params.passwordlabel)
        if (loggedInUser?.photo) {
            String encoded = Base64.getEncoder().encodeTopasswordlabelString(loggedInUser.photo)
            session.setAttribute("userPhoto", encoded)
        }
        println " SESSION"



        if (loggedInUser != null) {
            session.userSession = loggedInUser.userName
             //render (text:" sucessfully loged in")
            redirect("action": "dashboard")
            //redirect(view: "_subscribeTopic.gsp")
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
                flash.message = 'document added succesfully'
                redirect(action : "dashboard")
                return
            }
        }
        else{
            flash.error  = " Unable to add document"
            redirect(action : "dashboard")
            return
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
       // resourceData.save(flush: true, failOnError: true)
        // shareDocument.validate(["topicName", "visibility"])

//        println params
//        linkResource.save(flush: true)
//        println(".................................................................................")
//        println("topic ki id")
//        println(topic.id)
//        render(text: "Document saved")
//
//    }






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

    def deleteLink() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findByTopicName(params.topicnamelabel)
        //  LinkResource linkResource = LinkResource
        println(topic)
        //Topic topic = new Topic(params.topicnamelabel, visibility: params.visibility, user: loggedInUser.id)
        println("deletingggggg")
        println("logged in user")
        println(loggedInUser)
        println("topic ki id")
        println(topic)
        if (Topic.findByUserAndTopicName(loggedInUser, topic.name)) {
            //Topic value = new Topic(topicName: params.topicnamelabel, user: loggedInUser.id)
            Topic topicToBeDeleted = Topic.findByTopicNameAndUser(params.topicnamelabel, loggedInUser)
            println("delete topic name " + topicToBeDeleted)
            topicToBeDeleted.delete(flush:true)
            render(text: "topic deleted")
            println("topic deleted")

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


    def topicsCreatedByParticularUser(){
        int count=0;
        Person loggedInUser = Person.findByUserName(session.userSession)

        List list = Topic.createCriteria().list(){
            eq('user',loggedInUser)
        }



        List users = Topic.findAllByUser(loggedInUser)
//        if(users.topicName)
//        {
//            count++
//        }
        println(users)
        println(Topic)
        println ("no. of topics " + count)
        // List list []
        // model: [list.]

        //render (view:"abc", model: [ list: users.topicName])
        // def t = users.topicName.list()
        render(users.topicName)
        //println(t)
    }
    def qwer(){
        render(view: "emailSender.gsp")
    }

    def subscribedTopicsOfParticularUser() {
        Person loggedInUser = Person.findByUserName(session.userSession)
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

        Person loggedInUser = Person.findByUserName(session.userSession)
        List readingItem = ReadingItem.findAllById(loggedInUser)
        println(loggedInUser)
        println(readingItem)
        readingItem(resource,user: loggedInUser,isRead: true)
        //LinkResource linkResource  LinkResource(link: params.link)
        // Topic topic = Topic.findByTopicName(params.topic)
        // println(topic)
        // ReadingItem readingItem = new ReadingItem(resource,  loggedInUser)
        // ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic, linkResource: linkResource)
        println ("reading item " + readingItem)
//

//        linkResource = linkResource.save(flush: true, failOnError: true)
//        println(linkResource.id)
        //ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic, linkResource: linkResource)
        //ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic)
        println("     zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz")
        // println(resourceData)

        // println params
        render (text:"Marked As Read Successfully !!! ")

        //flash.message = "Marked As Read Successfully !!! "


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
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topic = Topic.findByName(params.topiclabel)
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
        Person loggedInUser = Person.findByUserName(session.userSession)
        println(loggedInUser)
        def val = loggedInUser.id
//        List<Users> usersList = Users.findAll()
//        usersList.each {
//            println(it.userName + it.topicName)
//        }
        List<Topic> topicList = Topic.findAll()
        topicList.each{
            println (it.name + it.loggedInUser)
        }
        //println(Users.findAllWhere()*.topicName)
    }
    def forgotPasswordView(){
        render (view: "forgotPasswordForm")
    }
    def forgotPassword(){
        Person user = Person.findByUserName(params.usernamelabel)
       if (user){
               mailService.sendMail {
                   to user.email
                   subject "Reset Password"
                   html  view: "/sample/_resetPassword", model: [userName: user.userName, resetUrl:
                           "http://localhost:8060/sample/resetPassword?userName=${user.userName}"]

               }
               flash.message = "Reset email send successfully"
               redirect (action:"forgotPasswordView")
              // render("sent")
           }

        else{
          flash.error = "Unable to find user by user name: ${params.usernamelabel}"
           redirect (action:"forgotPasswordView")
       }


    }
    def resetPasswordView(){
         render(view: "resetPasswordForm")
    }
//    def resetPassword() {
//        Person user = Person.findByUserName(session.userSession)
//        //render("abcd")
//        //println(user)
//        //Person user = Person.findByUserName(userName: params.usernamelabel, password: params.passwordlabel,
//                //confirmPassword: params.confirmpasswordlabel)
//        println("user " + user)
//        if(user.password.equals(user.confirmPassword)){
//            user.password = params.passwordlabel
//        }
//   else{
//       flash.error = "Password and confirm password does not match"
//    }
//
//    }


    def resetPassword() {
         def requiredParams = ['usernamelabel', 'passwordlabel','confirmpasswordlabel']
        requiredParams.each { singleParam ->
            if (!params.containsKey(singleParam)) {
                flash.error = "Enter all values"
                return 0
            }
        }
        Person user = Person.findByUserName(params.usernamelabel)

        if(user){
            user.password=params.passwordlabel
            user.validate()
            if(user.save(flush: true)){
                flash.message = "SUCCESSFULLY UPDATED PASSWORD"
               // render(text: "succesfully updated password")
                redirect(action: "homePage")

            }
            else {

                flash.error = "Failed to update password .Try again"
                //redirect(action: "resetPasswordView")
               // render("Failed to update password .Try again")
                redirect(action : "resetPasswordView")
            }

        }
//        println("reset")
//         user.validate()
//        println params
//        def userName = params.usernamelabel
//        def password = params.passwordlabel
//        Person.executeUpdate("update Person set password = '${password}' where userName = '${userName }'")
//        println("done")

        /*if (user.save(flush: true)) {
            println("inside if")
            flash.message = "SUCCESSFULLY UPDATED PASSWORD"
             render(text: "succesfully updated password")
            //redirect(action: "homePage")

        } else {
            println("inside else")
            flash.error = "Failed to update password .Try again"
            //redirect(action: "resetPasswordView")
            render("Failed to update password .Try again")
        }*/

    }
}

//def subscribedTopicOfUser = Subscription*.findAllWhere(topic :subscribedTopicOfUser)
//println (subscribedTopicOfUser)
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




