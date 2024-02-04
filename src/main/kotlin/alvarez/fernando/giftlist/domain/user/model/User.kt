package alvarez.fernando.giftlist.domain.user.model

import alvarez.fernando.giftlist.domain.user.dto.NewUserRequest
import alvarez.fernando.giftlist.domain.user.dto.UserReference
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

@Entity
@Table
class User(
    @Id override val userId: UUID,
    override val name: String,
    val email: String,
    val encryptedPassword: String,
): UserReference {
    constructor(newUserRequest: NewUserRequest, passwordEncoder: PasswordEncoder): this(
        userId = UUID.randomUUID(),
        name = newUserRequest.name,
        email = newUserRequest.email,
        encryptedPassword = passwordEncoder.encode(newUserRequest.unencryptedPassword),
    )
}
