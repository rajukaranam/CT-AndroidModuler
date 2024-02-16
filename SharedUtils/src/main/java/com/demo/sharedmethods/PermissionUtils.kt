package com.demo.sharedmethods

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

// PermissionUtils.kt in your shared module
object PermissionUtils {

    const val CAMERA_PERMISSION_REQUEST_CODE = 101
    const val LOCATION_PERMISSION_REQUEST_CODE = 102

    fun requestCameraPermission(activity: Activity) {
        requestPermission(activity, android.Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE)
    }

    fun requestLocationPermission(activity: Activity) {
        requestPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_REQUEST_CODE)
    }

    fun checkAndRequestCameraPermission(activity: Activity) {
        checkAndRequestPermission(
            activity,
            android.Manifest.permission.CAMERA,
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    fun checkAndRequestLocationPermission(activity: Activity) {
        checkAndRequestPermission(
            activity,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun requestPermission(activity: Activity, permission: String, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            }
        }
    }

    private fun checkAndRequestPermission(activity: Activity, permission: String, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            }
        }
    }
}
