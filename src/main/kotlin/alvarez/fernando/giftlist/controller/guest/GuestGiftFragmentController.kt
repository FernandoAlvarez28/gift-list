package alvarez.fernando.giftlist.controller.guest

import alvarez.fernando.giftlist.config.security.GuestAuth
import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.gift.service.GiftService
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.user.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.ModelAndView
import java.util.UUID

@Controller
class GuestGiftFragmentController(
    giftListService: GiftListService,
    userService: UserService,
    private val giftService: GiftService,
) : AbstractGuestController(giftListService = giftListService, userService = userService) {
    @GetMapping(Urls.Guests.GIFT_DETAIL)
    fun giftDetailFragment(
        @PathVariable guestAccessCode: String,
        @PathVariable giftId: UUID,
        @AuthenticationPrincipal guestAuth: GuestAuth,
    ): ModelAndView {
        val modelAndView = ModelAndView(Views.Guests.Fragments.GIFT_DETAIL_FRAGMENT)
        val giftList = super.getGiftListByGuest(guestAuth = guestAuth, modelAndView = modelAndView)

        val gifts =
            this.giftService.findByIdAndGiftListId(
                giftId = giftId,
                giftListId = giftList.giftListId,
            )

        // TODO implement or even remove

        return modelAndView
            .addObject("gifts", gifts)
    }
}
