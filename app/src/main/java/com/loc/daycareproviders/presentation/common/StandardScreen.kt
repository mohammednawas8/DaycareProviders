package com.loc.daycareproviders.presentation.common

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.loc.daycareproviders.util.UIComponent
import java.util.Queue

@Composable
fun StandardScreen(
    queue: Queue<UIComponent>,
    removeUiComponent: () -> Unit,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    if (queue.isNotEmpty()) {
        val uiComponent = queue.peek()
        when (uiComponent) {
            is UIComponent.Toast -> {
                Toast.makeText(context, uiComponent.message, Toast.LENGTH_SHORT).show()
                removeUiComponent()
            }

            is UIComponent.Dialog -> {

            }

            is UIComponent.None -> {

            }
            else -> Unit
        }
    }
    Box {
        content()
    }


}