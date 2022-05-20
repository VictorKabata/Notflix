package utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image

/*@Composable
fun koil(url: String): ImageBitmap? {
    var image by remember(url) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(url) {
        loadFullImage(url)?.let {
            image = Image.makeFromEncoded(toByteArray(it)).toComposeImageBitmap()
        }
    }

    return image
}

fun loadFullImage(source: String): BufferedImage? {
    return try {
        val url = URL(source)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 5000
        connection.connect()

        val input: InputStream = connection.inputStream
        val bitmap: BufferedImage? = ImageIO.read(input)
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun toByteArray(bitmap: BufferedImage): ByteArray {
    val baos = ByteArrayOutputStream()
    ImageIO.write(bitmap, "png", baos)
    return baos.toByteArray()
}*/

suspend fun koil(url: String): ImageBitmap? {
    val image = withContext(Dispatchers.IO) {
        try {
            HttpClient().use {
                it.get<ByteArray>(url)
            }
        } catch (e: Exception) {
            null
        }
    }
    return image?.let { Image.makeFromEncoded(it).toComposeImageBitmap() }
}
