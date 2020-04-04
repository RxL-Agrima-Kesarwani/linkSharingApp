package sample

class DocumentResource extends  ResourceData{
    // String document
    //Byte [] document
    //Byte[] document
    String filePath

    static constraints = {
        // Limit upload file size to 2MB
       // document maxSize: 1024 * 1024 * 2
        //MultipartFile myFile
        //static belongsTo = [resource: ResourceData]
    }
}