package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.domain.user.service.AdminUserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AdminUserDetailService(
    private val adminUserService: AdminUserService,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val adminUser = this.adminUserService.findByEmail(email = username)

        return AdminUserAuth(adminUser = adminUser.orElseThrow { UsernameNotFoundException("") })
    }
}
