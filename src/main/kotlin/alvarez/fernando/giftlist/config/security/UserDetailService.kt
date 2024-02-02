package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.domain.user.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailService(
    private val userService: UserService,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val adminUser = this.userService.findByEmail(email = username)

        return UserAuth(user = adminUser.orElseThrow { UsernameNotFoundException("") })
    }
}
