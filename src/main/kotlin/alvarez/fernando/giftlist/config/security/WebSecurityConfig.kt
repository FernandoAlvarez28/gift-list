package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.domain.guest.service.GuestService
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class WebSecurityConfig(
    private val configuration: AuthenticationConfiguration,
    private val guestService: GuestService,
    private val messageSource: MessageSource,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.requestMatchers(Urls.ROOT, *Urls.Resources.PUBLIC).permitAll()
                it.requestMatchers(Urls.Admin.LOGIN, Urls.Admin.FIRST_ACCESS, Urls.ERROR).permitAll()
                it.requestMatchers(Urls.Admin.ANT_MATCHER).hasAuthority(Roles.ROLE_ADMIN.name)
                it.requestMatchers(Urls.Guests.ANT_MATCHER).hasAuthority(Roles.ROLE_GUEST.name)
            }
            .formLogin {
                it.loginPage(Urls.Admin.LOGIN)
                    .loginProcessingUrl(Urls.Admin.LOGIN)
                    .defaultSuccessUrl(Urls.Admin.DEFAULT_URL)
            }
            .logout {
                it.logoutUrl(Urls.Admin.LOGOUT)
            }
            .addFilterBefore(
                this.guestAuthenticationFilter(this.authenticationManager(http)),
                UsernamePasswordAuthenticationFilter::class.java,
            )
            .build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager =
        http.getSharedObject(
            AuthenticationManagerBuilder::class.java,
        ).authenticationProvider(
            GuestAuthenticationProvider(
                guestService = this.guestService,
                messageSource = this.messageSource,
            ),
        ).build()

    @Bean
    fun guestAuthenticationFilter(authenticationManager: AuthenticationManager): AuthenticationFilter {
        val guestAuthenticationConverter =
            GuestAuthenticationConverter(
                messageSource = messageSource,
            )
        val authenticationFilter =
            AuthenticationFilter(
                authenticationManager,
                guestAuthenticationConverter,
            )
        authenticationFilter.requestMatcher = AntPathRequestMatcher(Urls.Guests.ANT_MATCHER)
        authenticationFilter.setSuccessHandler(GuestAuthenticationSuccessHandler())
        return authenticationFilter
    }

    @Bean
    fun passwordEncoder() = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
}
