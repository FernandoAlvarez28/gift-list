package alvarez.fernando.giftlist.domain.image.service

import alvarez.fernando.giftlist.domain.image.model.Image
import alvarez.fernando.giftlist.domain.image.model.UploadResponse
import alvarez.fernando.giftlist.domain.image.repository.ImageRepository
import alvarez.fernando.giftlist.domain.image.repository.ImageStorageRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.Optional
import java.util.UUID

@Service
class ImageService(
    private val imageRepository: ImageRepository,
    private val imageStorageRepository: ImageStorageRepository,
) {
    private val logger = KotlinLogging.logger {}

    fun uploadImage(file: MultipartFile): Image? {
        val uploadResponse: UploadResponse = this.imageStorageRepository.uploadImage(file) ?: return null
        val image = Image(file, uploadResponse)
        return this.imageRepository.save(image)
    }

    fun findByIds(imageId: UUID) = this.imageRepository.findById(imageId)

    fun findAllByIds(imageIds: Collection<UUID>) = this.imageRepository.findAllByImageIdIn(imageIds)

    fun deleteImage(imageId: UUID?) {
        if (imageId == null) {
            return
        }

        try {
            val image: Optional<Image> = this.imageRepository.findById(imageId)
            if (image.isPresent) {
                this.imageStorageRepository.deleteImage(image.get().externalId)
                this.imageRepository.delete(image.get())
            }
        } catch (e: Exception) {
            this.logger.error(e) { "Error while deleting image" }
        }
    }
}
