package alvarez.fernando.giftlist.domain.image.repository

import alvarez.fernando.giftlist.domain.image.model.UploadResponse
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@ConditionalOnProperty(value = ["app.image-storage.implementation"], havingValue = "cloudinary")
@Component
@Suppress("ktlint:standard:argument-list-wrapping")
class ImageStorageRepositoryCloudinaryImpl(
    @Value("\${app.image-storage.implementation.cloudinary.cloud-name}") cloudName: String,
    @Value("\${app.image-storage.implementation.cloudinary.api.key}") apiKey: String,
    @Value("\${app.image-storage.implementation.cloudinary.api.secret}") apiSecret: String,
    @Value("\${app.image-storage.implementation.cloudinary.remote-folder}") private val remoteFolder: String,
    private val cloudinary: Cloudinary =
        Cloudinary(
            ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
            ),
        ),
) : ImageStorageRepository {
    override fun uploadImage(file: MultipartFile): UploadResponseCloudinaryImpl {
        val uploadResult: Map<Any?, Any?> =
            this.cloudinary
                .uploader()
                .upload(file.bytes, ObjectUtils.asMap("folder", this.remoteFolder))

        return UploadResponseCloudinaryImpl(
            uploadResult["public_id"].toString(),
            uploadResult["url"].toString(),
        )
    }

    override fun deleteImage(publicId: String) {
        this.cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap())
    }
}

class UploadResponseCloudinaryImpl(
    override val externalId: String,
    override val publicUrl: String,
) : UploadResponse
