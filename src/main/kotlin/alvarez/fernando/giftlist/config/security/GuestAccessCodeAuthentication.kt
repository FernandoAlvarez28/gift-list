package alvarez.fernando.giftlist.config.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class GuestAccessCodeAuthentication(
    val accessCode: String,
) : Authentication {
    override fun getName() = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getCredentials() = this.accessCode

    override fun getDetails() = null

    override fun getPrincipal() = null

    override fun isAuthenticated() = false

    override fun setAuthenticated(isAuthenticated: Boolean) {
        // Not implemented
    }
}
