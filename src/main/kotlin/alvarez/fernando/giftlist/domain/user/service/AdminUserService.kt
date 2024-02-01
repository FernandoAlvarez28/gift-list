package alvarez.fernando.giftlist.domain.user.service

import alvarez.fernando.giftlist.domain.user.dto.NewAdminUserRequest
import alvarez.fernando.giftlist.domain.user.model.AdminUser
import alvarez.fernando.giftlist.domain.user.repository.AdminUserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminUserService(
    private val adminUserRepository: AdminUserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun existsAny() = this.adminUserRepository.existsAny()

    fun create(newAdminUserRequest: NewAdminUserRequest): AdminUser {
        return this.adminUserRepository.save(
            AdminUser(
                newAdminUserRequest = newAdminUserRequest,
                passwordEncoder = this.passwordEncoder,
            ),
        )
    }

    fun findByEmail(email: String) = this.adminUserRepository.findByEmail(email = email)
}
