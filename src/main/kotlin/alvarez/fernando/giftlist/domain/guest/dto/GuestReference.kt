package alvarez.fernando.giftlist.domain.guest.dto

import java.util.UUID

interface GuestReference {
    val guestId: UUID

    val giftListId: UUID

    val name: String

    val accessCode: String
}
