package alvarez.fernando.giftlist.domain.user.dto

interface NewAdminUserRequest {
    val name: String
    val email: String
    val unencryptedPassword: String
}
