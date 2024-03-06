package alvarez.fernando.giftlist.config.error

import alvarez.fernando.giftlist.controller.Urls
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class CustomErrorController : ErrorController {
    @RequestMapping(Urls.ERROR)
    fun handleError(request: HttpServletRequest): ModelAndView {
        return ModelAndView("error")
            .addObject("httpStatus", this.getStatus(request = request))
    }

    private fun getStatus(request: HttpServletRequest): HttpStatus {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)

        return if (status is Int) {
            HttpStatus.valueOf(status)
        } else {
            HttpStatus.I_AM_A_TEAPOT
        }
    }
}
