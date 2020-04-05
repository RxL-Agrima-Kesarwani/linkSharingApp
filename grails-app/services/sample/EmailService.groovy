package sample

class EmailService {
    def send() {
        mailService.sendMail {
            to "Agrima.Kesarwani@rxlogix.com"
            subject "subject in mail"
            html "body  mail"
        }

        flash.message = "Message sent at "+new Date()
        //redirect action:"emailSender"
        render("sent")
    }
}
