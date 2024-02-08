package alvarez.fernando.giftlist.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class GuestAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?,
    ) {
        // Empty on purpose to allow the request to reach the controller in the same URI
    }
}
