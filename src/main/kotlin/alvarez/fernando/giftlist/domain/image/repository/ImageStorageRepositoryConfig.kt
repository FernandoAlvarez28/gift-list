package alvarez.fernando.giftlist.domain.image.repository

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ImageStorageRepositoryConfig {
    @Bean
    @ConditionalOnMissingBean
    fun imageStorageRepositoryDefaultImpl(): ImageStorageRepository = ImageStorageRepositoryDefaultImpl()
}
