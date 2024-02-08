package alvarez.fernando.giftlist.config.locale

import alvarez.fernando.giftlist.controller.Urls
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import java.util.Locale

@Configuration
class LocaleConfig(
    private val defaultLocale: Locale,
    private val encoding: String,
) : WebMvcConfigurer {
    @Autowired
    constructor(
        @Value("\${app.default-locale.language:en}") defaultLocaleLanguage: String,
        @Value("\${app.default-locale.country:US}") defaultLocaleCountry: String,
        @Value("\${spring.messages.encoding}") encoding: String,
    ) : this(
        defaultLocale = Locale(defaultLocaleLanguage, defaultLocaleCountry),
        encoding = encoding,
    )

    @Bean
    fun localeResolver(): LocaleResolver {
        val localeResolver = CookieLocaleResolver("language")
        localeResolver.setDefaultLocale(this.defaultLocale)
        return localeResolver
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = "language"
        return localeChangeInterceptor
    }

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasenames("classpath:messages")
        messageSource.setDefaultEncoding(this.encoding)
        messageSource.setDefaultLocale(this.defaultLocale)
        return messageSource
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(this.localeChangeInterceptor())
            .excludePathPatterns(*Urls.Resources.PUBLIC)
    }
}
