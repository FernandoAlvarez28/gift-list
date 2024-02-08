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

@Controller
class GuestGiftListController(
    giftListService: GiftListService,
    userService: UserService,
    private val giftService: GiftService,
) : AbstractGuestController(giftListService = giftListService, userService = userService) {
    @GetMapping(Urls.Guests.GIFT_LIST)
    fun giftListPage(
        @PathVariable guestAccessCode: String,
        @AuthenticationPrincipal guestAuth: GuestAuth,
    ): ModelAndView {
        val modelAndView = ModelAndView(Views.Guests.GIFT_LIST)
        val giftList = super.getGiftListByGuest(guestAuth = guestAuth, modelAndView = modelAndView)

        val gifts = this.giftService.findAllByGiftList(giftListId = giftList.giftListId)

        return modelAndView
            .addObject("gifts", gifts)
    }

    /*
    @PostMapping(Urls.Guests.GIFT_PROMISE)
     fun promiseGiftSubmit(
         @PathVariable guestAccessCode: String,
         @PathVariable giftId: UUID,
         @AuthenticationPrincipal guestAuth: GuestAuth,
     ): ModelAndView {
         val giftList = super.getGiftListByGuest(guestAuth)

         // TODO implement

         return RedirectView("${Urls.Guests.GIFT_LIST}#gift-$giftId")
     }
     */
}
