package sample

class TopicController {
    def mailService
    def dashboardService
    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']
    def topicPage(){
        //render("hii")
        //render(view : "shareDocumentTopicComponent")
        redirect(controller: "topic", action: "index")

    }
    def index(){
        render(view: "topic")
    }
}
