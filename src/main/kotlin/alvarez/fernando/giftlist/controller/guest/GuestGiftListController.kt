package alvarez.fernando.giftlist.controller.guest

import alvarez.fernando.giftlist.config.security.GuestAuth
import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.gift.service.GiftService
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.guestgiftchoise.model.GuestGiftChoice
import alvarez.fernando.giftlist.domain.guestgiftchoise.service.GuestGiftChoiceService
import alvarez.fernando.giftlist.domain.user.service.UserService
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.UUID

@Controller
class GuestGiftListController(
    giftListService: GiftListService,
    userService: UserService,
    private val giftService: GiftService,
    private val giftChoiceService: GuestGiftChoiceService,
) : AbstractGuestController(giftListService = giftListService, userService = userService) {
    @GetMapping(Urls.Guests.GIFT_LIST)
    fun giftListPage(
        @PathVariable guestAccessCode: String,
        @AuthenticationPrincipal guestAuth: GuestAuth,
    ): ModelAndView {
        val modelAndView = ModelAndView(Views.Guests.GIFT_LIST)
        val giftList = super.getGiftListByGuest(guestAuth = guestAuth, modelAndView = modelAndView)

        val gifts = this.giftService.findAllByGiftList(giftListId = giftList.giftListId)

        val chosenGiftsIds = GuestGiftChoice.mapGiftIds(this.giftChoiceService.findAllByGuest(guest = guestAuth))

        return modelAndView
            .addObject("gifts", gifts)
            .addObject("chosenGiftsIds", chosenGiftsIds)
    }

    @PostMapping(Urls.Guests.GIFT_TOGGLE_CHOICE)
    fun toggleChoiceSubmit(
        @PathVariable guestAccessCode: String,
        @PathVariable giftId: UUID,
        @AuthenticationPrincipal guestAuth: GuestAuth,
    ): ModelAndView {
        val modelAndView = RedirectView("${Urls.Guests.GIFT_LIST}#gift-$giftId")
        val giftList =
            super.getGiftListByGuest(
                guestAuth = guestAuth,
                modelAndView = modelAndView,
            )

        this.giftService.findByIdAndGiftListId(
            giftId = giftId,
            giftListId = giftList.giftListId,
        ).ifPresent {
            this.giftChoiceService.toggleChoice(
                guest = guestAuth,
                gift = it,
            )
        }

        return modelAndView
    }
}
