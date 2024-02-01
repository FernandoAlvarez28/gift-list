package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.user.service.AdminUserService
import alvarez.fernando.giftlist.dto.FirstAdminUserRequestDto
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AdminLoginController(
    private val adminUserService: AdminUserService,
) {
    @GetMapping(Urls.Admin.LOGIN)
    fun loginPage(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ModelAndView {
        if (userDetails != null) {
            return RedirectView(Urls.Admin.USERS)
        } else if (!this.adminUserService.existsAny()) {
            return RedirectView(Urls.Admin.FIRST_ACCESS)
        }

        return ModelAndView(Views.Admin.LOGIN).addObject("postUrl", Urls.Admin.LOGIN)
    }

    @GetMapping(Urls.Admin.FIRST_ACCESS)
    fun firstUserRegistrationPage(@AuthenticationPrincipal userDetails: UserDetails?): ModelAndView {
        if (userDetails != null) {
            return RedirectView(Urls.Admin.USERS)
        } else if (this.adminUserService.existsAny()) {
            return RedirectView(Urls.Admin.LOGIN)
        }

        return ModelAndView(Views.Admin.FIRST_ACCESS).addObject("postUrl", Urls.Admin.FIRST_ACCESS)
    }

    @PostMapping(Urls.Admin.FIRST_ACCESS)
    fun firstUserRegistrationSubmit(
        @AuthenticationPrincipal userDetails: UserDetails?,
        requestDto: FirstAdminUserRequestDto,
    ): ModelAndView {
        if (userDetails != null) {
            return RedirectView(Urls.Admin.USERS)
        } else if (!this.adminUserService.existsAny()) {
            this.adminUserService.create(newAdminUserRequest = requestDto)
        }

        return RedirectView(Urls.Admin.LOGIN)
    }
}
