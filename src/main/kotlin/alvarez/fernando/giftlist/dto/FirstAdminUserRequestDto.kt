package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.user.dto.NewAdminUserRequest

class FirstAdminUserRequestDto(
    override val name: String,
    override val email: String,
    password: String,
    override val unencryptedPassword: String = password,
) : NewAdminUserRequest {
}
