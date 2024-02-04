package alvarez.fernando.giftlist.domain.giftlist.repository

import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional
import java.util.UUID

interface GiftListRepository : JpaRepository<GiftList, UUID> {
    @Query("FROM GiftList WHERE giftListId = :giftListId AND userId = :userId")
    fun findOneByIdAndUser(
        @Param("giftListId") giftListId: UUID,
        @Param("userId") userId: UUID,
    ): Optional<GiftList>

    @Query("FROM GiftList WHERE userId = :userId")
    fun findAllByUserId(
        @Param("userId") userId: UUID,
    ): List<GiftList>

    @Query("FROM GiftList WHERE userId = :userId AND active = TRUE")
    fun findAllActiveByUserId(
        @Param("userId") userId: UUID,
    ): List<GiftList>
}
