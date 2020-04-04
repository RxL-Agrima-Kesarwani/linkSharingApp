package sample

class ResourceRating {
    Integer score
    Person user
    ResourceData resource

    static belongsTo = [user: Person, resource:ResourceData]
}
