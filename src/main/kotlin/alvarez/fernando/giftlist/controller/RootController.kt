package alvarez.fernando.giftlist.controller

import alvarez.fernando.giftlist.config.security.Roles
import alvarez.fernando.giftlist.config.security.UserAuth
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RootController {
    @GetMapping(Urls.ROOT)
    fun redirectUser(
        @AuthenticationPrincipal userAuth: UserAuth?,
    ) = RedirectView(
        uri =
            if (userAuth?.hasRole(Roles.ROLE_ADMIN) == true) {
                Urls.Admin.DEFAULT_URL
            } else {
                Urls.Admin.LOGIN
            },
    )
}
