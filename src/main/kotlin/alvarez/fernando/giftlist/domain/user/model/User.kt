package alvarez.fernando.giftlist.domain.user.model

import alvarez.fernando.giftlist.domain.user.dto.NewUserRequest
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

@Entity
@Table
class User(
    @Id val userId: UUID,
    val name: String,
    val email: String,
    val encryptedPassword: String,
) {
    constructor(newUserRequest: NewUserRequest, passwordEncoder: PasswordEncoder): this(
        userId = UUID.randomUUID(),
        name = newUserRequest.name,
        email = newUserRequest.email,
        encryptedPassword = passwordEncoder.encode(newUserRequest.unencryptedPassword),
    )
}
