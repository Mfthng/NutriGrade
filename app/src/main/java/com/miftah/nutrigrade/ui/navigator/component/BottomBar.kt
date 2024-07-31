package com.miftah.nutrigrade.ui.navigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.miftah.nutrigrade.ui.theme.GreenPrimary
import com.miftah.nutrigrade.ui.theme.NutriGradeTheme

data class BottomBarItemData(
    val drawableId: ImageVector,
    val selected: Boolean = false,
    val onClick: () -> Unit = {}
)

@Composable
fun BottomBar(
    barHeight: Dp = 60.dp,
    fabColor: Color = Color(0xFF7980FF),
    fabSize: Dp = 64.dp,
    fabIconSize: Dp = 32.dp,
    cardTopCornerSize: Dp = 24.dp,
    cardElevation: Dp = 8.dp,
    buttons: List<BottomBarItemData> = listOf(
        BottomBarItemData(drawableId = Icons.Default.Home),
        BottomBarItemData(drawableId = Icons.Default.Home),
    ),
    fabOnClick: () -> Unit = {}
) {
    require(buttons.size == 2) { "BottomBar must have exactly 4 buttons" }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight + fabSize / 2)
            .padding(bottom = 35.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
            shape = RoundedCornerShape(
                topStart = cardTopCornerSize,
                topEnd = cardTopCornerSize,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            )
            {
                BottomBarItem(buttons[0])
                Spacer(modifier = Modifier.size(fabSize))
                BottomBarItem(buttons[1])
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .size(fabSize)
                .align(Alignment.TopCenter),
            onClick = { fabOnClick() },
            shape = CircleShape,
            containerColor = GreenPrimary,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(defaultElevation = 0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(fabIconSize)
            )
        }
    }
}


@Composable
fun BottomBarItem(
    itemData: BottomBarItemData,
    selectedColor: Color = Color(0xFF7980FF),
    nonSelectedColor: Color = Color(0xFF464D61).copy(alpha = 0.7f),
    iconSize: Dp = 24.dp
) {
    IconButton(onClick = { itemData.onClick() }) {
        Icon(
            imageVector = itemData.drawableId,
            contentDescription = null,
            tint = if (itemData.selected) selectedColor else nonSelectedColor,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    NutriGradeTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBar()
        }
    }
}