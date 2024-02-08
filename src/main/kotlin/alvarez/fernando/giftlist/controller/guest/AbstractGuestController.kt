package alvarez.fernando.giftlist.controller.guest

import alvarez.fernando.giftlist.config.security.GuestAuth
import alvarez.fernando.giftlist.domain.giftlist.exception.GiftListNotFoundException
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.user.service.UserService
import org.springframework.web.servlet.ModelAndView

abstract class AbstractGuestController(
    protected val giftListService: GiftListService,
    protected val userService: UserService,
) {
    fun getGiftListByGuest(
        guestAuth: GuestAuth,
        modelAndView: ModelAndView,
    ): GiftList {
        val giftList =
            this.giftListService.findByIdAndGuest(giftListId = guestAuth.giftListId, guest = guestAuth)

        if (giftList.isEmpty) {
            throw GiftListNotFoundException() // TODO message
        }

        modelAndView.addObject("giftList", giftList.get())
        modelAndView.addObject(
            "giftListUser",
            this.userService.findById(giftList.get().userId).orElse(null),
            // GiftList's Foreign Key will enforce that User is not null
        )

        return giftList.get()
    }
}
