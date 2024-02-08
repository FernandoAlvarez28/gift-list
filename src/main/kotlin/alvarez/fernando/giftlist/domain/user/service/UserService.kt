package alvarez.fernando.giftlist.domain.user.service

import alvarez.fernando.giftlist.domain.user.dto.NewUserRequest
import alvarez.fernando.giftlist.domain.user.model.User
import alvarez.fernando.giftlist.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun existsAny() = this.userRepository.existsAny()

    fun create(newUserRequest: NewUserRequest): User {
        return this.userRepository.save(
            User(
                newUserRequest = newUserRequest,
                passwordEncoder = this.passwordEncoder,
            ),
        )
    }

    fun findByEmail(email: String) = this.userRepository.findByEmail(email = email)

    fun findById(userId: UUID) = this.userRepository.findById(userId)
}
