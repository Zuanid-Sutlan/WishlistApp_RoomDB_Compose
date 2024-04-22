package com.example.wishlistapp_roomdb_compose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.example.wishlistapp_roomdb_compose.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ThemeSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    width: Dp,
    height: Dp,
    cornerRadius: Dp,
    borderColor: Color,
    checkedTrackColor: Color,
    uncheckedTrackColor: Color,
    checkedIcon: Int, // Icons.Filled,
    uncheckedIcon: Int, // Icons.Filled,
    onCheckedChange: (Boolean) -> Unit
) {
//    val context = LocalContext.current
//    val isDarkTheme = isSystemInDarkTheme()         //  MaterialTheme.colors.isDark

//    val switchState = remember { mutableStateOf(isDarkTheme) }

    val painter = if (isChecked) checkedIcon else uncheckedIcon

    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .border(
                border = BorderStroke(1.dp, borderColor),
                shape = RoundedCornerShape(cornerRadius)
            )
            .background(
                color = if (isChecked) uncheckedTrackColor else checkedTrackColor,
                shape = RoundedCornerShape(25.dp)
            )
            .clip(RoundedCornerShape(25.dp))
            .clickable {
                val changed = !isChecked
                onCheckedChange(changed)
            },

        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (isChecked) Arrangement.Start else Arrangement.End
        ) {

            Icon(
                painter = painterResource(id = painter),
                contentDescription = null,
                tint = Color.Unspecified
            )

        }


    }


    /*Canvas(
        modifier = modifier
            .clickable {
                switchState.value = !switchState.value
                // Change the theme based on the switch state
                if (switchState.value) {
                    // Light theme

//                    context.setTheme(R.style.Theme_App_Light)
                } else {
                    // Dark theme
//                    context.setTheme(R.style.Theme_App_Dark)
                }
            }
            .scale(scale = scale)
    ) {
        val width = size.width
        val height = size.height

        val trackColor = if (switchState.value) checkedTrackColor else uncheckedTrackColor
        val thumbX = if (switchState.value) width - height else 0f

        // Draw track
        drawRoundRect(
            color = trackColor,
            topLeft = Offset.Zero,
            size = Size(width, height),
            cornerRadius = CornerRadius(height / 2),
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw thumb
        drawCircle(
            color = thumbColor,
            radius = height / 2,
            center = Offset(thumbX + height / 2, height / 2)
        )

        // Draw icons
        val icon = if (switchState.value) checkedIcon else uncheckedIcon
        drawIntoCanvas {
//            icon.paint(it, color = trackColor)


            Image(
                painter = painterResource(icon),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)                       // clip to the circle shape
//                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
        }
    }*/
}
