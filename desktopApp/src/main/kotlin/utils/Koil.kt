package utils


/*fun koil(url:String):ImageBitmap {
    *//*val image = ktorHttpClient.use { client ->
        client.get<ByteArray>(url)
    }
    return Image.makeFromEncoded(image).asImageBitmap()*//*

    val url = URL(url)
    val connection = url.openConnection() as HttpURLConnection
    connection.connect()

    val inputStream = connection.inputStream
    val bufferedImage = ImageIO.read(inputStream)

    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", stream)
    val byteArray = stream.toByteArray()

    return Image.makeFromEncoded(byteArray).asImageBitmap()
}*/
