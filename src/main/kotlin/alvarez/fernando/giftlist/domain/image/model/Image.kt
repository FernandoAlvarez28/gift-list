package alvarez.fernando.giftlist.domain.image.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table
class Image(
    @Id val imageId: UUID,
    val name: String,
    val size: Long,
    val contentType: String,
    val externalId: String,
    val url: String,
    val createdAt: LocalDateTime,
) {
    constructor(file: MultipartFile, uploadResponse: UploadResponse) : this(
        imageId = UUID.randomUUID(),
        name = file.originalFilename ?: "unknown",
        size = file.size,
        contentType = file.contentType!!,
        externalId = uploadResponse.externalId,
        url = uploadResponse.publicUrl,
        createdAt = LocalDateTime.now(),
    )
}
