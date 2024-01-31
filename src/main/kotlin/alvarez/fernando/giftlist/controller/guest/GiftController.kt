package alvarez.fernando.giftlist.controller.guest

import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class GiftController {
    @GetMapping(Urls.Guests.GIFTS)
    fun list(): ModelAndView {
        return ModelAndView(Views.Guests.GIFTS_LIST)
    }
}
