package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.config.security.UserAuth
import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.gift.service.GiftService
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.guest.service.GuestService
import alvarez.fernando.giftlist.dto.GiftListRequestDto
import alvarez.fernando.giftlist.dto.GiftRequestDto
import alvarez.fernando.giftlist.dto.GuestRequestDto
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.UUID

@Controller
class AdminGiftListController(
    private val giftListService: GiftListService,
    private val giftService: GiftService,
    private val guestService: GuestService,
) {
    @GetMapping(Urls.Admin.MY_GIFT_LISTS)
    fun giftListsPage(
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        return ModelAndView(Views.Admin.GIFT_LISTS_LIST)
            .addObject("giftLists", this.giftListService.findAllByUser(user = userAuth))
            .addObject("newGiftListUri", Urls.Admin.MY_GIFT_LISTS_NEW)
    }

    @GetMapping(Urls.Admin.MY_GIFT_LIST_DETAIL)
    fun giftListDetailPage(
        @PathVariable giftListId: UUID,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val giftList =
            this.giftListService.findByIdAndUser(
                giftListId = giftListId,
                user = userAuth,
            )

        if (giftList.isEmpty) {
            // TODO not found message
            return RedirectView(Urls.Admin.MY_GIFT_LISTS)
        }

        return ModelAndView(Views.Admin.GIFT_LIST_DETAIL)
            .addObject("giftList", giftList.get())
            .addObject("gifts", this.giftService.findAllByGiftList(giftListId))
            .addObject("guests", this.guestService.findAllByGiftList(giftListId))
    }

    @GetMapping(Urls.Admin.MY_GIFT_LISTS_NEW)
    fun newGiftListPage(
        @AuthenticationPrincipal userAuth: UserAuth,
    ) = ModelAndView(Views.Admin.GIFT_LISTS_NEW)
        .addObject("newGiftListPostUri", Urls.Admin.MY_GIFT_LISTS_NEW)

    @PostMapping(Urls.Admin.MY_GIFT_LISTS_NEW)
    fun newGiftListSubmit(
        giftListRequest: GiftListRequestDto,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val createdGiftList =
            this.giftListService.create(
                giftListRequest = giftListRequest,
                user = userAuth,
            )

        return RedirectView(Urls.Admin.MY_GIFT_LIST_DETAIL, Pair("giftListId", createdGiftList.giftListId))
    }

    @PostMapping(Urls.Admin.MY_GIFT_LIST_DETAIL_NEW_GIFT)
    fun addGiftToList(
        @PathVariable giftListId: UUID,
        giftRequest: GiftRequestDto,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val giftList =
            this.giftListService.findByIdAndUser(
                giftListId = giftListId,
                user = userAuth,
            )

        if (giftList.isEmpty) {
            return RedirectView(Urls.Admin.MY_GIFT_LISTS)
        }

        val gift = this.giftService.create(
            giftRequest = giftRequest,
            giftList = giftList.get(),
        )

        return RedirectView("${Urls.Admin.MY_GIFT_LIST_DETAIL}#gift-${gift.giftId}")
    }

    @PostMapping(Urls.Admin.MY_GIFT_LIST_DETAIL_NEW_GUEST)
    fun addGuestToList(
        @PathVariable giftListId: UUID,
        guestRequest: GuestRequestDto,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val giftList =
            this.giftListService.findByIdAndUser(
                giftListId = giftListId,
                user = userAuth,
            )

        if (giftList.isEmpty) {
            return RedirectView(Urls.Admin.MY_GIFT_LISTS)
        }

        val guest = this.guestService.create(
            guestRequest = guestRequest,
            giftList = giftList.get(),
        )

        return RedirectView("${Urls.Admin.MY_GIFT_LIST_DETAIL}#guest-${guest.guestId}")
    }
}
