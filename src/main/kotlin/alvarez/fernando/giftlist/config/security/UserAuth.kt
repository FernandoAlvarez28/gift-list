package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.domain.user.dto.UserReference
import alvarez.fernando.giftlist.domain.user.model.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections
import java.util.UUID

class UserAuth(
    override val userId: UUID,
    override val name: String,
    val email: String,
    private val encryptedPassword: String,
) : UserDetails, UserReference {
    constructor(user: User) : this(
        userId = user.userId,
        name = user.name,
        email = user.email,
        encryptedPassword = user.encryptedPassword,
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

        other as UserAuth

        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

    override fun toString(): String {
        return this.email
    }

}
