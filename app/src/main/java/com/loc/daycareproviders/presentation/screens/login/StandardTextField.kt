package com.loc.daycareproviders.presentation.screens.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.R
import com.loc.daycareproviders.ui.Dimens.ICON_SIZE
import com.loc.daycareproviders.ui.theme.Blue
import com.loc.daycareproviders.ui.theme.WhitePinkLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    @DrawableRes trailingIcon: Int? = null,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onNext: (() -> Unit)? = null,
    onGo: (() -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier.focusRequester(focusRequester),
        value = text,
        onValueChange = {
            onValueChanged(it)
        },
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            focusedLeadingIconColor = Blue.copy(alpha = 0.8f),
            unfocusedLeadingIconColor = Blue.copy(alpha = 0.8f),
            focusedLabelColor = Color.Black.copy(alpha = 0.8f),
            unfocusedLabelColor = Color.Black.copy(alpha = 0.8f),
            containerColor = WhitePinkLight,
            focusedTrailingIconColor = Color.Black.copy(alpha = 0.9f),
            unfocusedTrailingIconColor = Color.Black.copy(alpha = 0.9f)
        ),
        trailingIcon = {
            trailingIcon?.let {
                IconButton(
                    onClick = { onTrailingIconClick?.invoke() },
                    content = {
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            null,
                            modifier = Modifier.size(ICON_SIZE)
                        )
                    }
                )
            }
        },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onNext = {
                onNext?.invoke()
            },
            onGo = {
                onGo?.invoke()
            }
        )
    )
}