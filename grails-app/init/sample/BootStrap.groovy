package sample

class BootStrap {

    void createUser() {
        if (!Person.count()) {
            List users = [["Agrima2", "Agrima2", "kesarwani", "abc123@gmail.com", true, true, "Agrima21"],
                          ["Agrima3", "Agrima3", "kesarwani", "abc1234@gmail.com", false, true, "Agrima31"]]

            users.each { userList ->
                Person user = new Person(userName: userList[0], firstName: userList[1], lastName: userList[2],
                        email: userList[3],
                        isAdmin: userList[4], isActive: userList[5], password: userList[6],
                        confirmPassword: userList[6])

                if (user.validate()) {
                    user.save(flush: true)
                }
                else{
                    user.errors.allErrors.each{
                        println it.toString()
                    }
                }
            }
        }
    }




    def init = { servletContext ->
        createUser()
    }
    def destroy = {
    }
}


/*  if(!Role.findByAuthority("ROLE_ADMIN")){
Role role = new Role()
role.authority = "ROLE_ADMIN"
if(role.save(flush: true)) {
    println "Role Created"
} else {
    println "Error in creating role"
}
} else {
println "Role Exists"
}
if(!User.count()) {
User user = new User()
user.username = "Agrima.Kesarwani"
user.password = "Agrima.Kesarwani"
if(user.save(flush: true)){
    println "User created"
    println "Assigning Role"
    Role role = Role.findByAuthority("ROLE_ADMIN")
    UserRole userRole = new UserRole()
    userRole.user = user
    userRole.role = role
    if(userRole.save(flush: true)) {
        println "Role Assigned"
    } else {
        println "Error in assinging role"
    }
} else {
    println "Error in creating User"
}
} else {
println "User Exists"
}*/




