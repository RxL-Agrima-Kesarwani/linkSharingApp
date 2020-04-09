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

