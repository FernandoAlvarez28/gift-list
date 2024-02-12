package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.config.security.UserAuth
import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.gift.exception.GiftNotFoundException
import alvarez.fernando.giftlist.domain.gift.model.Gift
import alvarez.fernando.giftlist.domain.gift.service.GiftService
import alvarez.fernando.giftlist.domain.giftlist.exception.GiftListNotFoundException
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.dto.GiftEditRequestDto
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.UUID

@Controller
class AdminGiftEditController(
    private val giftListService: GiftListService,
    private val giftService: GiftService,
) {
    @GetMapping(Urls.Admin.Fragments.MY_GIFT_LIST_DETAIL_GIFT_EDIT_FRAGMENT)
    fun editGiftModalFragment(
        @PathVariable giftListId: UUID,
        @PathVariable giftId: UUID,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val (giftList, gift) =
            this.findGiftListAndGift(
                giftListId = giftListId,
                userAuth = userAuth,
                giftId = giftId,
            )

        return ModelAndView(Views.Admin.Fragments.GIFT_EDIT_FRAGMENT)
            .addObject("giftList", giftList)
            .addObject("gift", gift)
            .addObject(
                "deleteGiftUri",
                Urls.processParams(
                    uri = Urls.Admin.MY_GIFT_LIST_DETAIL_GIFT_EDIT,
                    "giftListId" to giftListId,
                    "giftId" to giftId,
                ),
            )
    }

    @PostMapping(Urls.Admin.MY_GIFT_LIST_DETAIL_GIFT_EDIT)
    fun editGiftSubmit(
        @PathVariable giftListId: UUID,
        @PathVariable giftId: UUID,
        giftEditRequest: GiftEditRequestDto,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val (_, gift) =
            this.findGiftListAndGift(
                giftListId = giftListId,
                userAuth = userAuth,
                giftId = giftId,
            )

        this.giftService.edit(
            gift = gift,
            giftEditRequest = giftEditRequest,
        )

        return RedirectView("${Urls.Admin.MY_GIFT_LIST_DETAIL}#gift-$giftId")
    }

    private fun findGiftListAndGift(
        giftListId: UUID,
        userAuth: UserAuth,
        giftId: UUID,
    ): Pair<GiftList, Gift> {
        val giftList =
            this.giftListService.findByIdAndUser(
                giftListId = giftListId,
                user = userAuth,
            ).orElseThrow { GiftListNotFoundException() }

        val gift =
            this.giftService.findByIdAndGiftListId(
                giftListId = giftListId,
                giftId = giftId,
            ).orElseThrow { GiftNotFoundException() }
        return Pair(giftList, gift)
    }
}
