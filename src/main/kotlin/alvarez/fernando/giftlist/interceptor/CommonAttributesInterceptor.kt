package alvarez.fernando.giftlist.interceptor

import alvarez.fernando.giftlist.controller.Urls
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class CommonAttributesInterceptor(
    private val messageSource: MessageSource,
) : HandlerInterceptor, WebMvcConfigurer {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        request.setAttribute("defaultAdminUri", Urls.Admin.DEFAULT_URL)
        request.setAttribute("currentUri", request.requestURI)
        request.setAttribute(
            "dateFormat",
            this.messageSource.getMessage(
                "formats.date",
                null,
                request.locale,
            ),
        )
        request.setAttribute(
            "dateTimeFormat",
            this.messageSource.getMessage(
                "formats.date-time",
                null,
                request.locale,
            ),
        )

        return true
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(this)
            .addPathPatterns(Urls.Admin.ANT_MATCHER)
            .addPathPatterns(Urls.Guests.ANT_MATCHER)
            .excludePathPatterns(Urls.Admin.LOGIN, Urls.Admin.FIRST_ACCESS)
    }
}
