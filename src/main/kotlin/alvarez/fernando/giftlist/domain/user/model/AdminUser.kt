package alvarez.fernando.giftlist.domain.user.model

import alvarez.fernando.giftlist.domain.user.dto.NewAdminUserRequest
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

@Entity
@Table
class AdminUser(
    @Id val adminUserId: UUID,
    val name: String,
    val email: String,
    val encryptedPassword: String,
) {
    constructor(newAdminUserRequest: NewAdminUserRequest, passwordEncoder: PasswordEncoder): this(
        adminUserId = UUID.randomUUID(),
        name = newAdminUserRequest.name,
        email = newAdminUserRequest.email,
        encryptedPassword = passwordEncoder.encode(newAdminUserRequest.unencryptedPassword),
    )
}
