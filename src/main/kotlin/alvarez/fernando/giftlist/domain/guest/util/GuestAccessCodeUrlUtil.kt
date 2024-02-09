package alvarez.fernando.giftlist.domain.guest.util

import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.domain.guest.model.Guest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component("GuestAccessCodeUrlUtil")
class GuestAccessCodeUrlUtil(
    @Value("\${app.url}") val appUrl: String,
) {
    fun get(guest: Guest) =
        "${this.appUrl}${Urls.processParams(Urls.Guests.GIFT_LIST, Pair("guestAccessCode", guest.accessCode))}"
}
