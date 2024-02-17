package alvarez.fernando.giftlist.controller.admin

import alvarez.fernando.giftlist.config.security.UserAuth
import alvarez.fernando.giftlist.controller.Urls
import alvarez.fernando.giftlist.controller.Views
import alvarez.fernando.giftlist.domain.giftlist.exception.GiftListNotFoundException
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.dto.GiftListEditRequestDto
import alvarez.fernando.giftlist.util.RedirectView
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.UUID

@Controller
class AdminGiftListEditController(
    private val giftListService: GiftListService,
) {
    @GetMapping(Urls.Admin.Fragments.MY_GIFT_LIST_EDIT_FRAGMENT)
    fun editGiftListModalFragment(
        @PathVariable giftListId: UUID,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val giftList =
            this.giftListService.findByIdAndUser(
                giftListId = giftListId,
                user = userAuth,
            ).orElseThrow { GiftListNotFoundException() }

        return ModelAndView(Views.Admin.Fragments.GIFT_LIST_EDIT_FRAGMENT)
            .addObject("giftList", giftList)
            .addObject(
                "editGiftListUri",
                Urls.processParams(
                    uri = Urls.Admin.MY_GIFT_LIST_EDIT,
                    "giftListId" to giftListId,
                ),
            )
    }

    @PostMapping(Urls.Admin.MY_GIFT_LIST_EDIT)
    fun editGiftListSubmit(
        @PathVariable giftListId: UUID,
        giftListEditRequest: GiftListEditRequestDto,
        @AuthenticationPrincipal userAuth: UserAuth,
    ): ModelAndView {
        val giftList =
            this.giftListService.findByIdAndUser(
                giftListId = giftListId,
                user = userAuth,
            ).orElseThrow { GiftListNotFoundException() }

        this.giftListService.edit(
            giftList = giftList,
            giftListEditRequest = giftListEditRequest,
        )

        return RedirectView(Urls.Admin.MY_GIFT_LIST_DETAIL)
    }
}
