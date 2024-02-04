package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.user.service.UserService
import alvarez.fernando.giftlist.dto.FirstUserRequestDto
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AdminLoginController(
    private val userService: UserService,
) {
    @GetMapping(Urls.Admin.LOGIN)
    fun loginPage(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ModelAndView {
        if (userDetails != null) {
            return RedirectView(Urls.Admin.DEFAULT_URL)
        } else if (!this.userService.existsAny()) {
            return RedirectView(Urls.Admin.FIRST_ACCESS)
        }

        return ModelAndView(Views.Admin.LOGIN).addObject("loginPostUri", Urls.Admin.LOGIN)
    }

    @GetMapping(Urls.Admin.FIRST_ACCESS)
    fun firstUserRegistrationPage(@AuthenticationPrincipal userDetails: UserDetails?): ModelAndView {
        if (userDetails != null) {
            return RedirectView(Urls.Admin.DEFAULT_URL)
        } else if (this.userService.existsAny()) {
            return RedirectView(Urls.Admin.LOGIN)
        }

        return ModelAndView(Views.Admin.FIRST_ACCESS).addObject("firstAccessPostUri", Urls.Admin.FIRST_ACCESS)
    }

    @PostMapping(Urls.Admin.FIRST_ACCESS)
    fun firstUserRegistrationSubmit(
        @AuthenticationPrincipal userDetails: UserDetails?,
        requestDto: FirstUserRequestDto,
    ): ModelAndView {
        if (userDetails != null) {
            return RedirectView(Urls.Admin.DEFAULT_URL)
        } else if (!this.userService.existsAny()) {
            this.userService.create(newUserRequest = requestDto)
        }

        return RedirectView(Urls.Admin.LOGIN)
    }
}
