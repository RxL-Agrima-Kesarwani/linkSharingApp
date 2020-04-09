package sample

import org.springframework.web.multipart.MultipartFile

class SampleController {
    //def mailService
    def emailService
    def  dashboardService
    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        if (session.userSession) {
            //  List<Topic> topicList = Topic.findByNameWhere(createdBy:session)
            List<Topic> topicList = Topic.list()
            // List <Topic> topicListLoggedInUser = Topic.findByCreatedBy(session.userSession)
            //println("topic:.................. " + topicListLoggedInUser.name)
            //render(view: "dashboard", model: [topicListLoggedInUser: topicListLoggedInUser, userInfo: userInformation()])
            render(view: "editProfilePage", model: [topicList: topicList, userInfo: userInformation()])
        } else {
            redirect(controller: "login", action: "homePage")
        }
    }

    def editProfile() {
        render(view: "editProfilePage",model: [userInfo: userInformation()])
    }
    def userInformation(){
        Person loggedInUser = Person.findByUserName(session.userSession)
        dashboardService.userInformation(loggedInUser)
    }

    def updateProfile(){
        Person loggedInUser = Person.findByUserName(session.userSession)

    }

//    def userInformation() {
//        Person loggedInUser = Person.findByUserName(session.userSession)
//        println("........" + loggedInUser)
//        // println("name:" + ${userInfo.name})
//        Integer countTopic = Topic.countByCreatedBy(loggedInUser)
//        Integer countSubscription = Subscription.countByUser(loggedInUser)
//        List<Topic> topicList = Topic.list()
//        println("topic id:" + topicList.id)
//        def value = ResourceData.createCriteria()
//        def lists = value.list {
//            eq('class', "sample.DocumentResource")
//            String fullName = loggedInUser.firstName + " " + loggedInUser.lastName
//            return [name:fullName, userName:loggedInUser.userName, countTopic:countTopic,
//                    countSubscription: countSubscription]
//        }
//    }

    def topicComponent() {
        render(view: "topicComponent")
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
            List<Topic> topicList = Topic.list()
            render(view: "dashboard.gsp", model: [topicList: topicList])
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
                redirect(action: "homePage")
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





    def addTopic() {
        Person loggedInUser = Person.findByUserName(session.userSession)
        Topic topicAdded = Topic.findByNameAndUser(params.topicnamelabel, loggedInUser)
        println(topicAdded)
        if (topicAdded) {
            //When user is creating topic
            flash.error = " topic already exists "
            redirect(action: "dashboard")
            return
        } else {
            Topic topic = new Topic(name: params.topicnamelabel, visibility: VisibilityEnum."${params.visibility}",
                    user: loggedInUser.id, createdBy: loggedInUser)

            println("     >>>>>>>")
            println("logged in user")
            println(loggedInUser.id)

            if (topic.validate()) {
                topic.save(flush: true)
                // subscribeTopic()
                subscribe(loggedInUser, topic, null)
                flash.message = "Successfully created and subscribed to topic"
                redirect(action: "dashboard")
                return
            } else {
                topic.errors.allErrors.each {
                    println it
                }
                flash.error = "Unable to add topic"
                redirect(action: "dashboard")
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
            topicToBeDeleted.delete(flush: true)
            render(text: "topic deleted")
            println("topic deleted")

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
            DocumentResource documentResource = new DocumentResource(filePath: filePath, name: "resource1",
                    description: "docAdded", createdBy: loggedInUser, topic: topic)
            if (documentResource.validate()) {
                topic.addToResources(documentResource)
                topic.save()
                println("after save")
                documentResource.save(flush: true, failOnError: true)
                println("document added successfully")
                flash.message = 'document added succesfully'
                redirect(action: "dashboard")
                return
            }
        } else {
            flash.error = " Unable to add document"
            redirect(action: "dashboard")
            return
        }


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
                    user: loggedInUser.id)
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


        def topicsCreatedByParticularUser() {
            int count = 0;
            Person loggedInUser = Person.findByName(session.userSession)

            List list = Topic.createCriteria().list() {
                eq('user', loggedInUser)
            }


            List users = Topic.findAllByUser(loggedInUser)
//        if(users.topicName)
//        {
//            count++
//        }
            println(users)
            println(Topic)
            println("no. of topics " + count)
            // List list []
            // model: [list.]

            //render (view:"abc", model: [ list: users.topicName])
            // def t = users.topicName.list()
            render(users.topicName)
            //println(t)
        }
        def qwer() {
            render(view: "emailSender.gsp")
        }

        def subscribedTopicsOfParticularUser() {
            Person loggedInUser = Person.findByUserName(session.userSession)
            println("users......................")
            println(loggedInUser)
            List users = Subscription.findAllByUser(loggedInUser)
            render(text: "users")
            println(users)
            println(Subscription)
            println(Subscription.get(Subscription))
//      List users =Subscription.findByUsers(loggedInUser)
//      println (users)

            //render users.list() as JSON
            //List list = java.util.Arrays.asList("users");
            // List <Users> abc =Subscription.findAllByUserId(loggedInUser.id)

            // println(abc)

        }
        def markAsRead() {

            Person loggedInUser = Person.findByUserName(session.userSession)
            // List readingItem = ReadingItem.findAllByUserAnd(loggedInUser)
            println(loggedInUser)
            println(readingItem)
            readingItem(resource, user: loggedInUser, isRead: true)
            //LinkResource linkResource  LinkResource(link: params.link)
            // Topic topic = Topic.findByTopicName(params.topic)
            // println(topic)
            // ReadingItem readingItem = new ReadingItem(resource,  loggedInUser)
            // ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic, linkResource: linkResource)
            println("reading item " + readingItem)
//

//        linkResource = linkResource.save(flush: true, failOnError: true)
//        println(linkResource.id)
            //ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic, linkResource: linkResource)
            //ResourceData resourceData = new ResourceData(user: loggedInUser.id, topicName: topic.id, name: params.topic)
            println("     zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz")
            // println(resourceData)

            // println params
            render(text: "Marked As Read Successfully !!! ")

            //flash.message = "Marked As Read Successfully !!! "


        }

        def topicPage() {
           // render("topic")
            render(view:"topicPage.gsp" ,model: [userInfo: userInformation()])
            //render(view: "topicPage.gsp")
            //render(view : "sample/topic/topicPage.gsp")
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
          def subscribedTopicOfUser() {
            Person loggedInUser = Person.findByUserName(session.userSession)
            println(loggedInUser)
            def val = loggedInUser.id
//        List<Users> usersList = Users.findAll()
//        usersList.each {
//            println(it.userName + it.topicName)
//        }
            List<Topic> topicList = Topic.findAll()
            topicList.each {
                println(it.name + it.loggedInUser)
            }
            //println(Users.findAllWhere()*.topicName)
        }
        def forgotPasswordView() {
            render(view: "forgotPasswordForm")
        }
        def forgotPassword() {
            Person user = Person.findByUserName(params.usernamelabel)
            if (user) {
                // mailService.sendMail {
                emailService.send(user.userName, user.email)

                flash.message = "Reset email send successfully"
                redirect(action: "forgotPasswordView")
                // render("sent")
            } else {
                flash.error = "Unable to find user by user name: ${params.usernamelabel}"
                redirect(action: "forgotPasswordView")
            }


        }
        def resetPasswordView() {
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
            def requiredParams = ['usernamelabel', 'passwordlabel', 'confirmpasswordlabel']
            requiredParams.each { singleParam ->
                if (!params.containsKey(singleParam)) {
                    flash.error = "Enter all values"
                    return 0
                }
            }
            Person user = Person.findByUserName(params.usernamelabel)

            if (user) {
                user.password = params.passwordlabel
                user.validate()
                if (user.save(flush: true)) {
                    flash.message = "SUCCESSFULLY UPDATED PASSWORD"
                    // render(text: "succesfully updated password")
                    redirect(action: "homePage")

                } else {

                    flash.error = "Failed to update password .Try again"
                    //redirect(action: "resetPasswordView")
                    // render("Failed to update password .Try again")
                    redirect(action: "resetPasswordView")
                }
            }
        }


    }












