package com.hb.utilities.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File

object BitmapUtils {


    var IMAGE_CAPTURE_URI: Uri? = null

    /**
     * This method is used to convert Drawable to Bitmap
     *
     * @param drawable
     */

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable) {
            val bitmapDrawable = drawable
            if (bitmapDrawable.bitmap != null) {
                return bitmapDrawable.bitmap
            }
        }
        bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    /**
     * This method is used to get the mutable bitmap
     *
     * @param bitmap
     * @return
     */

    fun getMutableBitMap(bitmap: Bitmap): Bitmap? {
        return bitmap.copy(Bitmap.Config.ARGB_8888, true)
    }


    /**
     * This method is used to get base64 string from bitmap
     *
     * @param image - is bitmap which we required base64 data of it
     * @return
     */

    fun getBase64FromBitmap(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 60, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }



    fun setSelectedImage(
        mOrignalBitmap: Bitmap,
        mContext: Context,
        imagePath: String?
    ): Bitmap? {
        return try {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            if (manufacturer.equals("samsung", ignoreCase = true) || model.equals(
                    "samsung",
                    ignoreCase = true
                )
            ) {
                rotateBitmap(mContext, mOrignalBitmap, imagePath)
            } else {
                mOrignalBitmap
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mOrignalBitmap
        }
    }

    /**
     * This method is used to rotate bitmap
     *
     * @param mContext
     * @param mBit - bitmap which we want to rotate
     * @param imagePath
     * @return
     */
    fun rotateBitmap(
        mContext: Context,
        mBit: Bitmap,
        imagePath: String?
    ): Bitmap {
        val rotation = getCameraPhotoOrientation(
            mContext,
            IMAGE_CAPTURE_URI,
            imagePath
        )
        val matrix = Matrix()
        matrix.postRotate(rotation.toFloat())
        return Bitmap.createBitmap(mBit, 0, 0, mBit.width, mBit.height, matrix, true)
    }

    fun getCameraPhotoOrientation(
        context: Context,
        imageUri: Uri?,
        imagePath: String?
    ): Int {
        var rotate = 0
        try {
            try {
                if (imageUri != null) context.contentResolver.notifyChange(imageUri, null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val imageFile = File(imagePath)
            val exif = ExifInterface(imageFile.absolutePath)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
                ExifInterface.ORIENTATION_NORMAL -> rotate = 0
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rotate
    }

    fun getResizeImage(
        mContext: Context,
        scalingLogic: ScalingUtilities.ScalingLogic,
        rotationNeeded: Boolean,
        mCurrentPhotoPath: String,
        width: Int,
        height: Int
    ): Bitmap? {
        return try {
            val dstWidth: Int
            val dstHeight: Int
            val bmOptions = BitmapFactory.Options()
            bmOptions.inJustDecodeBounds = true
            BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)
            bmOptions.inJustDecodeBounds = false
            dstWidth = width
            dstHeight = height
            if (bmOptions.outWidth < dstWidth && bmOptions.outHeight < dstHeight) {
                val bitmap: Bitmap
                bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)
                setSelectedImage(bitmap, mContext, mCurrentPhotoPath)
            } else {
                var unscaledBitmap = ScalingUtilities.decodeResource(
                    mCurrentPhotoPath,
                    dstWidth,
                    dstHeight,
                    scalingLogic
                )
                val matrix = Matrix()
                if (rotationNeeded) {
                    matrix.setRotate(
                        getCameraPhotoOrientation(
                            mContext,
                            Uri.fromFile(File(mCurrentPhotoPath)),
                            mCurrentPhotoPath
                        ).toFloat()
                    )
                    unscaledBitmap = Bitmap.createBitmap(
                        unscaledBitmap!!, 0, 0, unscaledBitmap.width, unscaledBitmap.height,
                        matrix, false
                    )
                }
                val scaledBitmap = ScalingUtilities.createScaledBitmap(
                    unscaledBitmap!!,
                    dstWidth,
                    dstHeight,
                    scalingLogic
                )
                unscaledBitmap.recycle()
                scaledBitmap
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}