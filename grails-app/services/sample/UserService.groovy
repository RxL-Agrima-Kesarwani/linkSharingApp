package sample

import grails.gorm.transactions.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Transactional
class UserService {
    public Person register(String firstName, String lastName, String email, String userName,
                           String password, String confirmPassword,def photo) {
        Person user = new Person(firstName:firstName, lastName:lastName, email:email, userName:userName,
                password:password, confirmPassword:confirmPassword)

        MultipartFile multipartFile = photo
        if(!multipartFile.isEmpty()) {
            user.photo = multipartFile.bytes
        }
        else{
            File defaultPhoto = new File("assets/images/displayUser.png")
            user.photo = defaultPhoto.bytes
        }
        if (user.validate()) {
            user.save(flush:true, failOnError:true)


        }
        return user
    }
}


