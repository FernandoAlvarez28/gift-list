package alvarez.fernando.giftlist.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

class GuestAuthorizationFilter(uriPattern: String) : AbstractAuthenticationProcessingFilter(uriPattern) {
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        TODO("Not yet implemented")
    }
}
