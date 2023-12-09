package com.example.datastorageapp
import ContactListScreen
import com.example.datastorageapp.viewmodel.ContactViewModel
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.datastorageapp.ui.theme.DataStorageAppTheme
class MainActivity : ComponentActivity() {
    private lateinit var contactViewModel: ContactViewModel
    private val permissionRequestCode = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
            setContent {
                DataStorageAppTheme {
                    ContactListScreen(contactViewModel = contactViewModel)
                }
            }
        if (checkPermissions()) {
        } else {
            requestPermissions()
        }
    }
    private fun checkPermissions(): Boolean {
        val readContactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS )
        val writeContactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
        return readContactsPermission == PackageManager.PERMISSION_GRANTED && writeContactsPermission == PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), permissionRequestCode)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                contactViewModel.importContactsFromDevice()
            }
        }
    }
}
fun makePhoneCall(number: String, context: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
    }
    context.startActivity(intent)
}
fun sendMessage(number: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("smsto:$number")
    }
    context.startActivity(intent)
}