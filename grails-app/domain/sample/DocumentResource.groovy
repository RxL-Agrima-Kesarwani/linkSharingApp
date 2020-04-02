package sample

import org.springframework.web.multipart.MultipartFile

class DocumentResource {
    // String document
    //Byte [] document
    byte[] document

    static constraints = {
        // Limit upload file size to 2MB
        document maxSize: 1024 * 1024 * 2
        //MultipartFile myFile
        //static belongsTo = [resource: ResourceData]
    }
}