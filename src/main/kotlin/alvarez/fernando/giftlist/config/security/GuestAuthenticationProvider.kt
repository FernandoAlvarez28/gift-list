package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.domain.guest.service.GuestService
import org.springframework.context.MessageSource
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.Locale

class GuestAuthenticationProvider(
    private val guestService: GuestService,
    private val messageSource: MessageSource,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val guest = this.guestService.findByAccessCode((authentication as GuestAccessCodeAuthentication).accessCode)

        if (guest.isEmpty) {
            throw UsernameNotFoundException(
                this.messageSource.getMessage("guests.access-code.invalid", null, Locale.US), // TODO locale
            )
        }

        return GuestAuthentication(GuestAuth(guest.get()))
    }

    override fun supports(authentication: Class<*>): Boolean =
        GuestAccessCodeAuthentication::class.java.isAssignableFrom(authentication)
}
