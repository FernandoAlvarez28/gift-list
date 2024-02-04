package alvarez.fernando.giftlist.util

import alvarez.fernando.giftlist.controller.Urls
import org.springframework.web.servlet.ModelAndView

class RedirectView(uri: String, vararg params: Pair<String, Any>) :
    ModelAndView("redirect:${Urls.processParams(uri, *params)}") {
}
