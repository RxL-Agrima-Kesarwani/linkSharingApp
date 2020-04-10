package sample

class PersonController {
    def dashboardService
    def editProfile() {
        render(view: "editProfilePage",model: [userInfo:  dashboardService.userInformation(Person.findByUserName(session.userSession)),userName:
                session.userSession])
    }


}