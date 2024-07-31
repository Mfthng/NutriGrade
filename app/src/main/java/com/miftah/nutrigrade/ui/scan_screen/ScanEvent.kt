package com.miftah.nutrigrade.ui.scan_screen

import android.content.Context
import android.net.Uri
import com.miftah.nutrigrade.domain.Scanned

sealed class ScanEvent {
    data class ScanToCLod(val data: Uri, val context : Context) : ScanEvent()
    data class EditText(val data: String) : ScanEvent()
}