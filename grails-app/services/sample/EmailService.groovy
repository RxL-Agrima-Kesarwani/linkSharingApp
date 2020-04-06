package sample

    class EmailService {
        def mailService

        def send(String userName, String email ) {
            mailService.sendMail {
                to email
                subject "Reset Password"
                html  view: "/sample/_resetPassword", model: [userName: userName, resetUrl:
                        "http://localhost:8060/sample/resetPasswordView?userName=${userName}"]
            }
        }
    }


