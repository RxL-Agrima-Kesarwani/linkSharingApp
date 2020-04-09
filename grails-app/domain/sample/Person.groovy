package  sample
class Person {

    String firstName
    String lastName
    String email
    String userName
    String password
    String confirmPassword
    Byte[] photo
    Boolean isAdmin
    Boolean isActive

    Date lastUpdated
    Date dateCreated
    static transients = ['confirmPassword','fullName']
    static hasMany = [topics: Topic, subscriptions: Subscription, resources: ResourceData,
                      readingItems: ReadingItem, resouceRatings: ResourceRating]

    static constraints = {

        firstName blank: false
        lastName blank: false
        userName blank: false
        email unique: true, email: true
        password blank: false
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, obj ->
            if (!obj.id && (obj.password != val || !val)) {
                return 'password.do.not.match.confirmPassword'
            }
        })
        password(size: 5
                ..15, blank: false, validator: { val, obj ->
            if (val?.equalsIgnoreCase(obj.firstName)) {
                String suggestion = obj.firstName.reverse()
                return ["password.cannot.be.firstname", suggestion]
            }
        })
        photo nullable: true, maxSize: 10 * 1024 * 1024
        isAdmin nullable: true
        isActive nullable: true
    }
    static mapping = {
        sort "firstName"
        lastName column: 'lname'
        }
    public static Person fetchPerson(String userName, String password) {
        //Person.createCriteria().get{
         Person.createCriteria().get{
            or{
                eq('userName',userName )
                eq('email',userName )
            }
            eq('password',password )
        }
    }
  String  getFullName(){
        firstName + " " +lastName
    }

}

