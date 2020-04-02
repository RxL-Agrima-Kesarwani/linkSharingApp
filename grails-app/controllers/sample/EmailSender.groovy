package sample

class EmailSender {
    def send() {
        sendMail {
            to params.address
            subject params.subject
            text params.body
        }

        flash.message = "Message sent at "+new Date()
        redirect action:"emailSender"
    }


}
