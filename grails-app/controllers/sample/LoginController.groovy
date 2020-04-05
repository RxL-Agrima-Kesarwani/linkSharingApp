package sample

import org.springframework.web.multipart.MultipartFile

class LoginController {
    def mailService
    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def homePage() {
        def c = ResourceData.createCriteria()
        def recentPosts = c.list {
            maxResults(5)
            order("dateCreated", "desc")
        }
        render(view: "homePageLinkSharing", model: [information: recentPosts])
       //  render(view: "homePageLinkSharing.gsp")
    }

    def register() {
        println params
        Person user = new Person(firstName: params.firstnamelabel, lastName: params.lastnamelabel,
                email: params.emaillabel, userName: params.usernamelabel, password: params.passwordlabel,
                confirmPassword: params.confirmpasswordlabel)
        if (params.photo) {
            MultipartFile multipartFile = params.photo
            user.photo = multipartFile.bytes
        }
        println params
        if (user.save(flush: true)) {
            flash.registerMessage = "Successfully registered"
            redirect(controller : 'login',action: "homePage")

        } else {
            flash.registerError = "Registration Failed.Kindly register again."
           redirect(action: "homePage")
            }

    }

    def login() {
      Person loggedInUser = Person.findByUserNameAndPassword(params.usernamelabel,params.passwordlabel)
//         if (loggedInUser?.photo) {
//            String encoded = Base64.getEncoder().encodeToString(loggedInUser.photo)
//            session.setAttribute("userPhoto", encoded)
//        }
        if (loggedInUser != null) {
                //if (loggedInUser) {
            session.userSession = loggedInUser.userName
            flash.loginMessage = "Login successful"
            redirect(controller : 'dashboard',action: "dashboard")
            } else {
            flash.loginError = "Invalid credentials"
            redirect(action: "homePage")

            }
    }
}
