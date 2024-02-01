package alvarez.fernando.giftlist.util

import org.springframework.web.servlet.ModelAndView

class RedirectView(uri: String) : ModelAndView("redirect:$uri")
