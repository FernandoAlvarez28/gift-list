package alvarez.fernando.giftlist.config.security

import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang3.StringUtils
import org.springframework.context.MessageSource
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationConverter

class GuestAuthenticationConverter(
    private val messageSource: MessageSource,
) : AuthenticationConverter {
    override fun convert(request: HttpServletRequest): Authentication {
        var guestAccessCode = request.requestURI.removePrefix("/guests/")
        guestAccessCode = StringUtils.substringBefore(guestAccessCode, "/")

        if (StringUtils.isBlank(guestAccessCode)) {
            throw UsernameNotFoundException(
                this.messageSource.getMessage("guests.access-code.invalid", null, request.locale),
            )
        }

        return GuestAccessCodeAuthentication(guestAccessCode)
    }
}
