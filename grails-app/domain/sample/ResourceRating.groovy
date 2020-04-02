package sample

class ResourceRating {
    Integer score
    Users user
    ResourceData resource

    static belongsTo = [user: Users, resource:ResourceData]
}
