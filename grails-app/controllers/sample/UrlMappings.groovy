package sample

class UrlMappings {
    static mappings = {
        "/"(controller:"sample")
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }
        "500"(view:'/error')
    }
}






