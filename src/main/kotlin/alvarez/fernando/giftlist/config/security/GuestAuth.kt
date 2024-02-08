package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.domain.guest.dto.GuestReference
import alvarez.fernando.giftlist.domain.guest.model.Guest
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections
import java.util.UUID

class GuestAuth(
    override val guestId: UUID,
    override val name: String,
    override val accessCode: String,
    override val giftListId: UUID,
) : UserDetails, GuestReference {
    constructor(guest: Guest) : this(
        guestId = guest.guestId,
        name = guest.name,
        accessCode = guest.accessCode,
        giftListId = guest.giftListId,
    )

    fun hasRole(role: Roles?): Boolean = role != null && this.authorities.contains(SimpleGrantedAuthority(role.name))

    override fun getAuthorities(): MutableSet<SimpleGrantedAuthority> =
        Collections.singleton(SimpleGrantedAuthority(Roles.ROLE_GUEST.name))

    override fun getPassword() = this.accessCode

    override fun getUsername() = this.accessCode

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
