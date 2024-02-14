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
import alvarez.fernando.giftlist.domain.guest.util.GuestAccessCodeUrlUtil
import alvarez.fernando.giftlist.domain.guest.util.QrCodeGenerator
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.util.UUID

@Controller
class AdminGuestQrCodeController(
    private val giftListService: GiftListService,
    private val guestService: GuestService,
    private val guestAccessCodeUrlUtil: GuestAccessCodeUrlUtil,
) {
    @GetMapping(Urls.Admin.Fragments.MY_GIFT_LIST_DETAIL_GUEST_ACCESS_CODE_FRAGMENT)
    fun accessCodeModalFragment(
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

        return ModelAndView(Views.Admin.Fragments.GUEST_ACCESS_CODE_FRAGMENT)
            .addObject("giftList", giftList)
            .addObject("guest", guest)
            .addObject("accessCodeUrl", this.guestAccessCodeUrlUtil.get(guest = guest))
            .addObject(
                "accessCodeImageUri",
                Urls.processParams(
                    uri = Urls.Admin.MY_GIFT_LIST_DETAIL_GUEST_ACCESS_CODE,
                    "giftListId" to giftListId,
                    "guestId" to guestId,
                ),
            )
    }

    @GetMapping(Urls.Admin.MY_GIFT_LIST_DETAIL_GUEST_ACCESS_CODE)
    fun generateQrCodeStream(
        @PathVariable giftListId: UUID,
        @PathVariable guestId: UUID,
        @AuthenticationPrincipal userAuth: UserAuth,
        @RequestParam(defaultValue = "250") size: Int = 250,
        response: HttpServletResponse,
    ): ResponseEntity<StreamingResponseBody> {
        val (_, guest) =
            this.findGiftListAndGuest(
                giftListId = giftListId,
                userAuth = userAuth,
                guestId = guestId,
            )

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${guest.name}.svg\"")
        response.setHeader(HttpHeaders.CONTENT_TYPE, "image/svg+xml")
        return ResponseEntity(
            StreamingResponseBody {
                QrCodeGenerator.generateQrCode(
                    value = this.guestAccessCodeUrlUtil.get(guest = guest),
                    size = size,
                    output = it,
                )
            },
            HttpStatus.OK,
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
