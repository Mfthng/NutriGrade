package com.miftah.nutrigrade.ui.scan_screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.graphics.Paint.Align
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.miftah.nutrigrade.R
import com.miftah.nutrigrade.domain.Scanned
import com.miftah.nutrigrade.ui.navgraph.Route
import com.miftah.nutrigrade.ui.navgraph.navigateWithBundle
import com.miftah.nutrigrade.ui.theme.NutriGradeTheme
import com.miftah.nutrigrade.utils.Constanta.SCANNED_DATA
import com.miftah.nutrigrade.utils.UiState
import com.miftah.nutrigrade.utils.saveBitmapToFile
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(
    modifier: Modifier = Modifier,
    state: ScanState = ScanState(),
    onEvent: (ScanEvent) -> Unit,
    navigate: (Scanned) -> Unit
) {

    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
            )
        }
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()


    var uri by remember { mutableStateOf<Uri?>(null) }
    val bitmap by remember {
        derivedStateOf {
            if (uri != null) {
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
                if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri!!)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, uri!!)
                    ImageDecoder.decodeBitmap(source)
                }
            } else {
                null
            }
        }
    }
    val launcherGallery = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent(),
        onResult = {
            uri = it
        }
    )
    var lensFacing by remember {
        mutableIntStateOf(CameraSelector.LENS_FACING_BACK)
    }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()
    LaunchedEffect(lensFacing) {
        controller.setCameraSelector(cameraSelector)
    }
    CameraX(
        controller = controller, modifier = Modifier
            .fillMaxSize()
    )
    if (uri != null) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    value = state.title, onValueChange = {
                    onEvent(ScanEvent.EditText(it))
                })
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(ScanEvent.ScanToCLod(uri!!, context))
                    }) {
                    Text(text = "Send")
                }
            }
        ) {
            bitmap?.let {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = it.asImageBitmap(),
                    contentDescription = null
                )
            }
        }
    } else {
        Box(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    onClick = {
                        launcherGallery.launch("image/*")
                    }) {
                    Icon(painter = painterResource(id = R.drawable.ic_rotation ),  contentDescription = null,tint = Color.White,modifier = Modifier.size(24.dp))                }
                IconButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    onClick = {
                        takePhoto(controller, context) {
                            uri = saveBitmapToFile(context, it)
                        }
                    }) {
                    Icon(painter = painterResource(id = R.drawable.ic_camera ), contentDescription = null,tint = Color.White,modifier = Modifier.size(24.dp))                }
                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    onClick = {
                        takePhoto(controller, context) {
                            lensFacing =
                                if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT else CameraSelector.LENS_FACING_BACK
                        }
                    }) {
                    Icon(painter = painterResource(id = R.drawable.ic_rotation ),  contentDescription = null,tint = Color.White,modifier = Modifier.size(24.dp))                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        state.imageState?.collectAsState(initial = null)?.value.let { data ->
            when (data) {
                is UiState.Error -> {
                    Toast.makeText(context, "ERR", Toast.LENGTH_SHORT).show()
                }

                UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(64.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is UiState.Success -> {
                    Toast.makeText(context, "SCC", Toast.LENGTH_SHORT).show()
                    navigate(data.data)
                }

                null -> {

                }
            }
        }
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Bitmap) -> Unit
) {
    if (!hasPermission(context)) {
        return
    }
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                    postScale(1f, 1f)
                }
                val rotatedBitMap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )
                onPhotoTaken(rotatedBitMap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.d("Camera ", "Could't take photo", exception)
            }
        }
    )
}

fun hasPermission(context: Context): Boolean {
    return CameraPermission.CAMERAX_PERMISSION.all {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}

object CameraPermission {
    val CAMERAX_PERMISSION = arrayOf(Manifest.permission.CAMERA)
}

@Preview
@Composable
private fun ScanScreenPreview() {
    NutriGradeTheme {
        ScanScreen(
            navigate = {},
            onEvent = {}
        )
    }
}

/*
* Box(modifier = modifier.fillMaxSize()) {
        if (uri == null) {
            CameraX(
                controller = controller, modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    onClick = {
                        launcherGallery.launch("image/*")
                    }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
                IconButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    onClick = {
                        takePhoto(controller, context) {
                            uri = saveBitmapToFile(context, it)
                        }
                    }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    onClick = {
                        takePhoto(controller, context) {
                            lensFacing =
                                if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT else CameraSelector.LENS_FACING_BACK
                        }
                    }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
            }
        } else {
            bitmap?.let {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = it.asImageBitmap(),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Button(onClick = {
                    onEvent(ScanEvent.ScanToCLod(uri!!, context))
                }) {
                    Text(text = "Send")
                }
            }
        }
        state.imageState?.collectAsState(initial = null)?.value.let { data ->
            when (data) {
                is UiState.Error -> {
                    Toast.makeText(context, "ERR", Toast.LENGTH_SHORT).show()
                }

                UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(64.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is UiState.Success -> {
                    Toast.makeText(context, "SCC", Toast.LENGTH_SHORT).show()
                    /*Button(
                        modifier = Modifier,
                        onClick = {
                            navigate(data.data)
                        }
                    ) {
                        Text(text = "To Detail")
                    }*/
//                    navigate(data.data)
                }

                null -> {

                }
            }
        }
    }
* */