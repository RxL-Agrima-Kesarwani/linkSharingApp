package  sample
class User {

    String firstName
    String lastName
    String email
    String userName
    String password
    String confirmPassword
    Byte [] photo

    static transients = ['confirmPassword']
    Boolean admin
    Boolean active

    Date lastUpdated
    Date dateCreated

    static hasMany = [topics: Topic, subscriptions: Subscription, resources: ResourceData, readingitems: ReadingItem, resouceRatings: ResourceRating]

    static constraints = {

        firstName blank :false, nullable: false
        lastName blank: false, nullable: false
        userName blank: false, nullable: false
        email unique: true,email :true
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
        photo nullable:true , maxSize: 10*1024*1024
        //admin nullable:true
    }
    static mapping = {
        sort "firstName"
        lastName  column: 'lname'
        //autoTimeStamp:false
       // photo(size:0..5000000)  // to store files upto 5MB approx
        }
       // photo(photo(sqlType:"BLOB") :"BLOB")
    }

   /*static mapping = {
        table 'Users'
        firstName column: 'fname', type: 'text'
        lastName  column: 'lname', type: 'text'
        userName  column: 'uname', type: 'text'
       id name: 'personId'
        autoTimestamp(false)
        sort("firstName": "desc")
        version(false)
        addresses cascade: 'all-delete-orphan'
    }




}*/
