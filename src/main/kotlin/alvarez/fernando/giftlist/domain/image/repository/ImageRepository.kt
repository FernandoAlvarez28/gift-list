package alvarez.fernando.giftlist.domain.image.repository

import alvarez.fernando.giftlist.domain.image.model.Image
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ImageRepository : JpaRepository<Image, UUID> {
    fun findAllByImageIdIn(imageIds: Collection<UUID>): List<Image>
}
