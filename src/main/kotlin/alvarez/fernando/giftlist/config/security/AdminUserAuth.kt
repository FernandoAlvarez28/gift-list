package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.domain.user.model.AdminUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections

class AdminUserAuth(
    val email: String,
    private val encryptedPassword: String,
) : UserDetails {
    constructor(adminUser: AdminUser) : this(
        email = adminUser.email,
        encryptedPassword = adminUser.encryptedPassword,
    )

    override fun getAuthorities(): MutableSet<SimpleGrantedAuthority> =
        Collections.singleton(SimpleGrantedAuthority(Roles.ROLE_ADMIN.name))

    override fun getPassword() = this.encryptedPassword

    override fun getUsername() = this.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdminUserAuth

        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

    override fun toString(): String {
        return this.email
    }

}
