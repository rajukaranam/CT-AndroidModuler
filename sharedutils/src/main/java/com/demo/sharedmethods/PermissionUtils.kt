package com.demo.sharedmethods

import android.Manifest
import android.R
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity


// PermissionUtils.kt in your shared module
object PermissionUtils {
    const val REQUEST_CODE_ASK_STORAGE_PERMISSIONS = 121
    const val REQUEST_CODE_ASK_CONTACTS_PERMISSIONS = 123
    const val REQUEST_CODE_ASK_LOCATION_PERMISSIONS = 124
    const val REQUEST_CODE_ASK_CAMERA_PERMISSIONS = 125
    const val REQUEST_CODE_ASK_NOTIFICATION_PERMISSIONS = 130
    const val REQUEST_CODE_ASK_WRITE_CONTACTS_PERMISSIONS = 129
    const val CAMERA_PERMISSION_REQUEST_CODE = 101
    const val LOCATION_PERMISSION_REQUEST_CODE = 102

    fun requestCameraPermission(activity: Activity) {
        requestPermission(
            activity,
            android.Manifest.permission.CAMERA,
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    fun requestLocationPermission(activity: Activity) {
        requestPermission(
            activity,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            LOCATION_PERMISSION_REQUEST_CODE
        )
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
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            }
        }
    }

    private fun checkAndRequestPermission(
        activity: Activity,
        permission: String,
        requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            }
        }
    }


    fun checkPermission(activity: Activity, permission: String, from: String): Boolean {
        var myPermission = ""
        var requestCode = 0
        when (permission) {
            "CONTACTS" -> {
                myPermission = Manifest.permission.READ_CONTACTS
                requestCode = REQUEST_CODE_ASK_CONTACTS_PERMISSIONS
            }

            "WRITE_CONTACTS" -> {
                myPermission = Manifest.permission.WRITE_CONTACTS
                requestCode = REQUEST_CODE_ASK_WRITE_CONTACTS_PERMISSIONS
            }

            "LOCATION" -> {
                myPermission = Manifest.permission.ACCESS_FINE_LOCATION
                requestCode = REQUEST_CODE_ASK_LOCATION_PERMISSIONS
            }

            "STORAGE" -> {
                myPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                } else {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                }
                requestCode = REQUEST_CODE_ASK_STORAGE_PERMISSIONS
            }

            "CAMERA" -> {
                myPermission = Manifest.permission.CAMERA
                requestCode = REQUEST_CODE_ASK_CAMERA_PERMISSIONS
            }

            "NOTIFICATIONS" -> {
                myPermission = Manifest.permission.POST_NOTIFICATIONS
                requestCode = REQUEST_CODE_ASK_NOTIFICATION_PERMISSIONS
            }
        }
        if (ActivityCompat.checkSelfPermission(activity, myPermission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    myPermission
                )
            ) {
                if (isPermissionPopup) showPermissionPopup(
                    activity,
                    from,
                    permission,
                    myPermission,
                    requestCode
                )
                return false
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        myPermission
                    )
                ) {
                    //if (Pref.getIn().isFirstTimeAskingPermission(from)) {
                        if (isPermissionPopup) showPermissionPopup(
                            activity,
                            from,
                            permission,
                            myPermission,
                            requestCode
                        )
                   /* } else {
                        if (isPermissionPopup) showPermissionPopup(
                            activity,
                            from + "Never",
                            permission,
                            myPermission,
                            requestCode
                        )
                    }*/
                    return false
                }
            }
        }
        return true
    }

    var isPermissionPopup = true

    private fun showPermissionPopup(
        activity: Activity,
        from: String, permission: String, myPermission: String,
        requestCode: Int
    ) {
        var btn_ok = ""
        var title = ""
        var message = ""
        if (from.contains("Never")) {
            btn_ok = "SETTINGS"
            title = "Permission Required"
            message =
                "You have denied the required permission $permission for this action. To use CTE app, please open settings, go to permissions and allow them."
        } else {
            btn_ok = "OK"
            when (from) {
                "storage" -> {
                    title = "Storage Access Needed"
                    message =
                        """
                Our application allows you to upload your in-stock cars to our classifieds portals. You can also share car details with other dealers or inspect your cars using our app. Images are a very important part of any car report, car overview or car listing. In order for you to append images to your listing, evaluation report or to share images with other dealers, we need access to your storage so that you can select images to create a custom report.
                In addition, we allow you to select a specific sequence of images and to edit certain parts of the image, for which storage access is also needed. In addition, for vehicles that you have won in auction, you can upload documents pertaining to your auction from your gallery. You can also select photos for your social profile on our social module.
                """.trimIndent()
                }

                "storage_auctions" -> {
                    title = "Storage Access Needed"
                    message =
                        "To facilitate the auction process, for vehicles that you have won in auction, you can upload documents pertaining to your auction from your gallery."
                }

                "storage_bajaj" -> {
                    title = "Storage Access Needed"
                    message = "We require storage access so that you can upload images ."
                }

                "storage_share" -> {
                    title = "Storage Access Needed"
                    message = "We need access to your storage, to be able to share images."
                }

                "storage_ctg" -> {
                    title = "Storage Access Needed"
                    message =
                        "We require storage access so that we can process your CarTrade Guarantee Request"
                }

                "storage_hdfc" -> {
                    title = "Storage Access Needed"
                    message =
                        "We require storage access so that we can process your CarTrade Finance Request"
                }

                "storage_interaction" -> {
                    title = "Storage Access Needed"
                    message =
                        "We require storage access so that you can upload images against your listings."
                }

                "callPhone" -> {
                    title = "Phone Call Access Needed"
                    message = "You can now call your leads directly from the CTE app."
                }

                "camera" -> {
                    title = "Camera Access Needed"
                    //   message = "We require camera access so that you can upload images against your listings, evaluations and auctions.";
                    message =
                        """
                    Our application allows you to upload your in-stock cars to our classifieds portals. You can also share car details with other dealers or inspect your cars using our app. Images are a very important part of any car report, car overview or car listing. In order for you to capture live and current images to your listing, evaluation report or to share images with other dealers, the app will access images so that you can capture live images to create a custom report. In addition, images are captured in a specific sequence and using specific dimensions to create an optimal report which will help create a standardized review.
                    In addition, for vehicles that you have won in auction, you can capture documents pertaining to your auction from your camera. You can also select photos for your social profile on our social module.
                    """.trimIndent()
                }

                "camera_auctions" -> {
                    title = "Camera Access Needed"
                    message =
                        "To facilitate the auction process, for vehicles that you have won in auction, you can capture documents pertaining to your auction from your camera."
                }

                "camera_ins" -> {
                    title = "Camera Access Needed"
                    message =
                        "We require camera acccess so that we can upload images relevant to your insurance applications."
                }

                "camera_hdfc" -> {
                    title = "Camera Access Needed"
                    message =
                        "We require camera acccess so that we can upload images relevant to your finance applications."
                }

                "camera_interaction" -> {
                    title = "Camera Access Needed"
                    message =
                        "We require camera access so that you can upload images against your listings."
                }

                "location" -> {
                    title = "Location Access Needed"
                    message = "Location access needed to show cars near to you."
                }

                "notification" -> {
                    title = "Notifications Access Needed"
                    message =
                        "Notification access needed to quick notify the cars added in to your listings."
                }

                "report_location" -> {
                    title = "Location Access Needed"
                    message = "Location access needed to report location."
                }

                "Write_Contacts" -> {
                    title = "Write Contact Access Needed"
                    message =
                        "To share vehicle images from the CTE app to a buyer, seller, or dealer via WhatsApp, you need to save their phone number in your phone's contacts. This will enable you to share images directly and easily from the CTE app.\n" +
                                "\n" +
                                "\n" +
                                "We will require you to provide us WRITE CONTACTS permission to auto save that selected buyer/seller/dealer phone number first in to your phone book contact list. \n" +
                                "\n" +
                                "The following modules in CTE app we required this permission\n" +
                                "\n" +
                                "- Buy Leads \n" +
                                "- Lead Marketplace \n" +
                                "- Evaluations \n" +
                                "- Inventory \n" +
                                "- Social Share \n" +
                                "\n" + "The information that we request will be retained on your device and is not collected/shared with third -party companies   or used for any other purpose beyond what is described."
                }

                "contacts" -> {
                    title = "Read Contact Access Needed"
                    message =
                        "To share vehicle images from the CTE app to a buyer, seller, or dealer via WhatsApp, you need to save their phone number in your phone's contacts. This will enable you to share images directly and easily from the CTE app.. \n" +
                                "\n" +
                                "We will require you to provide us READ CONTACTS permission to check whether that selected buyer/seller/dealer contact is saved in your phonebook contacts list or not,  if not saved then we ask to save that contact first before sharing images from CTE app\n" +
                                "\n" +
                                "The following modules in CTE app we required this permission\n" +
                                "\n" +
                                "- Buy Leads \n" +
                                "- Lead Marketplace \n" +
                                "- Evaluations \n" +
                                "- Inventory \n" +
                                "- Social Share \n" +
                                "\n" + "The information that we request will be retained on your device and is not collected/shared with third -party companies   or used for any other purpose beyond what is described."
                }
            }
        }
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                if (from.contains("Never")) {
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", activity.getPackageName(), null)
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    activity.startActivity(intent)
                } else {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf<String>(myPermission),
                        requestCode
                    )
                }
                isPermissionPopup = true
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
        isPermissionPopup = false
    }


}
