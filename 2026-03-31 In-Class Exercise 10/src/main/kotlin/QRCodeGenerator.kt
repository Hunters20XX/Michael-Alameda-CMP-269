package com.example

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import java.io.ByteArrayOutputStream

/**
 * Generates a QR code and returns it as a ByteArray (in memory).
 * Great for web servers - no disk I/O needed!
 *
 * @param content The content to encode in the QR code
 * @param size The width and height of the QR code (default 300)
 * @return ByteArray containing the PNG image data
 */
fun generateQRCode(content: String, size: Int = 300): ByteArray {
    // Set encoding hints
    val hints = mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")

    // Create the QR code BitMatrix
    val bitMatrix = QRCodeWriter().encode(
        content,
        BarcodeFormat.QR_CODE,
        size,
        size,
        hints
    )

    // Create an in-memory output stream (NO disk file!)
    val outputStream = ByteArrayOutputStream()

    // Write the BitMatrix to the stream as PNG
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)

    // Convert to ByteArray - this is what we'll send via HTTP
    return outputStream.toByteArray()
}