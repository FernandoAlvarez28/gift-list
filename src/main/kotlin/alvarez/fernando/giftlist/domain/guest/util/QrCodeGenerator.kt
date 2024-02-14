package alvarez.fernando.giftlist.domain.guest.util

import net.glxn.qrgen.javase.QRCode
import java.io.OutputStream

class QrCodeGenerator {
    companion object {
        fun generateQrCode(
            value: String,
            output: OutputStream,
            size: Int = 150,
        ) {
            QRCode
                .from(value)
                .withSize(size, size)
                .svg(output)
        }
    }
}
