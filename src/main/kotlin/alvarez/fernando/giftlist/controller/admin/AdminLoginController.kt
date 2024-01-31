package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AdminLoginController {
    @GetMapping(Urls.Admin.LOGIN)
    fun loginPage(): ModelAndView {
        val modelAndView = ModelAndView(Views.Admin.LOGIN)

        modelAndView.addObject("postUrl", Urls.Admin.LOGIN)

        return modelAndView
    }
}
