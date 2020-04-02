package sample

class BootStrap {

    def init = { servletContext ->
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




    }
    def destroy = {
    }
}
