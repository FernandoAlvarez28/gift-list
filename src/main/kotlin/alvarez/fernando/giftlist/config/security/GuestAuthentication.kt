package alvarez.fernando.giftlist.config.security

import org.springframework.security.core.Authentication

class GuestAuthentication(
    val guestAuth: GuestAuth,
) : Authentication {
    override fun getName() = this.guestAuth.name

    override fun getAuthorities() = this.guestAuth.authorities

    override fun getCredentials() = this.guestAuth.accessCode

    override fun getDetails() = null

    override fun getPrincipal() = this.guestAuth

    override fun isAuthenticated() = true

    override fun setAuthenticated(isAuthenticated: Boolean) {
        // Not implemented
    }
}
