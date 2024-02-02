package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.user.dto.NewUserRequest

class FirstUserRequestDto(
    override val name: String,
    override val email: String,
    password: String,
    override val unencryptedPassword: String = password,
) : NewUserRequest
