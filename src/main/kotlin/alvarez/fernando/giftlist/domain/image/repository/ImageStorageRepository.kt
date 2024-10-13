package alvarez.fernando.giftlist.domain.image.repository

import alvarez.fernando.giftlist.domain.image.model.UploadResponse
import org.springframework.web.multipart.MultipartFile

interface ImageStorageRepository {
    fun uploadImage(file: MultipartFile): UploadResponse?

    fun deleteImage(publicId: String)
}
