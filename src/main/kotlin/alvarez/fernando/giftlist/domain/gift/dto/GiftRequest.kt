package alvarez.fernando.giftlist.domain.gift.dto

import org.springframework.web.multipart.MultipartFile

interface GiftRequest {
    val name: String
    val description: String?
    val requirement: String?
    val imageFile: MultipartFile?
}
