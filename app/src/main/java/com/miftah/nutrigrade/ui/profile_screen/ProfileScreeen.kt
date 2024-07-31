package com.miftah.nutrigrade.ui.profile_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.lang.String
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun ProfileScreen(
    modifier: Modifier
) {
    val message = remember {
        mutableStateOf("panduan")
    }
    val phoneNumber = remember {
        mutableStateOf("+6285156247518")
    }
    val context = LocalContext.current
    Column(
        Modifier.padding(16.dp)
    ) {

        Button(
            onClick = {
                // on below line we are starting activity
                // to send the sms from whatsapp.
                context.startActivity(
                    // on below line we are opening the intent.
                    Intent(
                        // on below line we are calling
                        // uri to parse the data
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            // on below line we are passing uri,
                            // message and whats app phone number.
                            String.format(
                                "https://api.whatsapp.com/send?phone=%s&text=%s",
                                phoneNumber.value,
                                message.value,
                            )
                        )
                    )
                )

            },
            // on below line adding
            // a modifier for our button.
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // on below line adding a text for our button.
            Text(text = "Gunakan Nutrigrade di whatsapp")

        }
    }

}