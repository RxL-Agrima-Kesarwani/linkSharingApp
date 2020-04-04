package sample

class ResourceRating {
    Integer score
    User user
    ResourceData resource

    static belongsTo = [user: User, resource:ResourceData]
}
