package alvarez.fernando.giftlist.config.security

import alvarez.fernando.giftlist.controller.Urls
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class WebSecurityConfig(
    private val configuration: AuthenticationConfiguration,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.requestMatchers(*Urls.Resources.PUBLIC).permitAll()
                it.requestMatchers(Urls.Admin.LOGIN, Urls.Admin.FIRST_ACCESS).permitAll()
                it.requestMatchers(Urls.Admin.ANT_MATCHER).hasAuthority(Roles.ROLE_ADMIN.name)
                it.requestMatchers(Urls.Guests.LOGIN).anonymous()
                it.requestMatchers(Urls.Guests.ANT_MATCHER).hasAuthority(Roles.ROLE_GUEST.name)
            }
            .formLogin {
                it.loginPage(Urls.Admin.LOGIN)
                    .loginProcessingUrl(Urls.Admin.LOGIN)
                    .defaultSuccessUrl(Urls.Admin.USERS)
            }
            .addFilterBefore(this.guestAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun guestAuthorizationFilter(): GuestAuthorizationFilter {
        val guestAuthorizationFilter = GuestAuthorizationFilter(uriPattern = Urls.Guests.ANT_MATCHER)
        guestAuthorizationFilter.setAuthenticationManager(this.configuration.authenticationManager)
        return guestAuthorizationFilter
    }

    @Bean
    fun passwordEncoder() = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
}
