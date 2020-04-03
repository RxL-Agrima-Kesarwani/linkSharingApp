package sample

class EmailSenderController {
    def mailService
    def send() {
        mailService.sendMail {
            to "Agrima.Kesarwani@rxlogix.com"
            subject "subject in mail"
            body "body  mail"
        }

        flash.message = "Message sent at "+new Date()
        redirect action:"emailSender"
        //render("sent")
    }
    def test(){
     render("hello")
    }

   /* def index() {
        mailService.sendMail {
            to "Agrima.Kesarwani@rxlogix.com"
            subject "subject in mail"
            body "body  mail"
        }

        flash.message = "Message sent at "+new Date()
        redirect action:"emailSender"
    }*/

}
