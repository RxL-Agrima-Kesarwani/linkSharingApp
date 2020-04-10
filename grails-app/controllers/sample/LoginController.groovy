package sample

import org.springframework.web.multipart.MultipartFile

class LoginController {
    def mailService
    def userService
    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']
    static defaultAction = "homePage"

    def homePage() {
        def c = ResourceData.createCriteria()
        def recentPosts = c.list {
            maxResults(5)
            order("dateCreated", "desc")
        }
        println(recentPosts.size())
        //println("full name" + user.getFullName())
        //render(view: "homePageLinkSharing", model: [recentPosts: userInfo(), topPosts: userInfo()])
       render(view: "homePageLinkSharing", model: [recentPosts: recentPosts, topPosts: recentPosts])
    }
    def register() {
        Person user = userService.register(params.firstnamelabel, params.lastnamelabel,
                params.emaillabel, params.usernamelabel,
                params.passwordlabel, params.confirmpasswordlabel,params.photo)

            if (user.validate()) {
                flash.registerMessage = "Successfully registered"
            } else {
                flash.registerError = "Registration Failed.Kindly register again."

            }
        redirect(controller: 'login', action: "homePage")
        }
    def resetPasswordView() {
        render(view: "resetPasswordForm",model:[userName:params.userName])
    }
    def forgotPasswordView() {
        render(view: "forgotPasswordForm")
    }
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
        def login() {
            Person loggedInUser =  Person.fetchPerson(params.usernamelabel,params.passwordlabel)
            println("logged in now")
            if (loggedInUser?.photo) {
                //if (this.photo) {
                String encoded = Base64.getEncoder().encodeToString(loggedInUser.photo)
                session.setAttribute("userPhoto", encoded)
            }
            if (loggedInUser != null) {
                session.userSession = loggedInUser.userName

                flash.loginMessage = "Login successful"
                redirect(controller: 'dashboard', action: "dashboard")
            } else {
                flash.loginError = "Invalid credentials"
                redirect(action: "homePage")

            }
        }

        def userInfo() {
            Person loggedInUser = Person.findByUserName(session.userSession)
            List<Topic> topicList = Topic.list()
            def value = ResourceData.createCriteria()
            def lists = value.list {
                eq('class',"sample.DocumentResource")
            }
            println(lists)
            String fullName = loggedInUser.firstName + " " + loggedInUser.lastName
            return [name: fullName, userName: loggedInUser.userName]
        }

    }

