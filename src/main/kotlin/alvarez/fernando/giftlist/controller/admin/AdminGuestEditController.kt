package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.config.security.UserAuth
import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.giftlist.exception.GiftListNotFoundException
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.guest.exception.GuestNotFoundException
import alvarez.fernando.giftlist.domain.guest.model.Guest
import alvarez.fernando.giftlist.domain.guest.service.GuestService
import alvarez.fernando.giftlist.dto.GuestEditRequestDto
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.UUID

@Controller
class AdminGuestEditController(
    private val giftListService: GiftListService,
    private val guestService: GuestService,
) {
    @GetMapping(Urls.Admin.Fragments.MY_GIFT_LIST_DETAIL_GUEST_EDIT_FRAGMENT)
    fun editGuestModalFragment(
        @PathVariable giftListId: UUID,
        @PathVariable guestId: UUID,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val (giftList, guest) =
            this.findGiftListAndGuest(
                giftListId = giftListId,
                userAuth = userAuth,
                guestId = guestId,
            )

        return ModelAndView(Views.Admin.Fragments.GUEST_EDIT_FRAGMENT)
            .addObject("giftList", giftList)
            .addObject("guest", guest)
            .addObject(
                "deleteGuestUri",
                Urls.processParams(
                    uri = Urls.Admin.MY_GIFT_LIST_DETAIL_GUEST_EDIT,
                    "giftListId" to giftListId,
                    "guestId" to guestId,
                ),
            )
    }

    @PostMapping(Urls.Admin.MY_GIFT_LIST_DETAIL_GUEST_EDIT)
    fun editGuestSubmit(
        @PathVariable giftListId: UUID,
        @PathVariable guestId: UUID,
        guestEditRequest: GuestEditRequestDto,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val (_, guest) =
            this.findGiftListAndGuest(
                giftListId = giftListId,
                userAuth = userAuth,
                guestId = guestId,
            )

        this.guestService.edit(
            guest = guest,
            guestEditRequest = guestEditRequest,
        )

        return RedirectView(
            Urls.processParams(
                uri = Urls.Admin.MY_GIFT_LIST_DETAIL,
                "giftListId" to giftListId,
            ),
        )
    }

    private fun findGiftListAndGuest(
        giftListId: UUID,
        userAuth: UserAuth,
        guestId: UUID,
    ): Pair<GiftList, Guest> {
        val giftList =
            this.giftListService.findByIdAndUser(
                giftListId = giftListId,
                user = userAuth,
            ).orElseThrow { GiftListNotFoundException() }

        val guest =
            this.guestService.findByIdAndGiftList(
                giftListId = giftListId,
                guestId = guestId,
            ).orElseThrow { GuestNotFoundException() }
        return Pair(giftList, guest)
    }
}
