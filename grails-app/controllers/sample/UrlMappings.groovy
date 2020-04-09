package sample

class UrlMappings {
    static mappings = {
        "/"(controller:"login",action:"homePage")
        "/dashBoard"(controller:"dashboard",action:"dashboard")
        "/topic"(controller:"sample",action:"topicPage")
        "/editProfile"(controller:"sample",action:"editProfile")
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }
        "500"(view:'/error')
    }
}






