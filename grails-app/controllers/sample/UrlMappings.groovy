package sample

class UrlMappings {
    static mappings = {
        "/"(controller:"Login",action:"homePage")
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }
        "500"(view:'/error')
    }
}






