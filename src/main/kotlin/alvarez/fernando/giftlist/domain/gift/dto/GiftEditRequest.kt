package alvarez.fernando.giftlist.domain.gift.dto

interface GiftEditRequest {
    val name: String
    val description: String?
    val requirement: String?
}
