package alvarez.fernando.giftlist.domain.image.repository

import alvarez.fernando.giftlist.domain.image.model.UploadResponse
import org.springframework.web.multipart.MultipartFile

class ImageStorageRepositoryDefaultImpl: ImageStorageRepository {
    override fun uploadImage(file: MultipartFile): UploadResponse? {
        return null
    }

    override fun deleteImage(publicId: String) {
    }
}
