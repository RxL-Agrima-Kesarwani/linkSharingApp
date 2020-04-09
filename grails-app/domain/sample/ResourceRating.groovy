package sample

class ResourceRating {
    Integer score


    static belongsTo = [user: Person, resource:ResourceData]
}
