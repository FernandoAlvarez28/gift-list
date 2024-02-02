package alvarez.fernando.giftlist.domain.user.dto

interface NewUserRequest {
    val name: String
    val email: String
    val unencryptedPassword: String
}
