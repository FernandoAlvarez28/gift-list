package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AdminUserController {
    @GetMapping(Urls.Admin.USERS)
    fun usersPage(): ModelAndView {
        return ModelAndView(Views.Admin.USERS_LIST)
    }
}
