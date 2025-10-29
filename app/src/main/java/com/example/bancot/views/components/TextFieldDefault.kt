package com.example.bancot.views.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTextFieldDefault(
    modifier: Modifier = Modifier,
    textLabel: String,
    icon: ImageVector? = null,
    colorTextWithFocus: Color? = null,
    colorWithFocus: Color? = null,
    colorTextWithoutFocus: Color? = null,
    colorWithoutFocus: Color? = null,
    text: String,
    maxLine: Int,
    singleLine: Boolean,
    keyBoardType: KeyboardType? = null,
    isError: Boolean,
    supportingText: @Composable (() -> Unit)? = null,
    textChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        maxLines = maxLine,
        singleLine = singleLine,
        leadingIcon = {
            icon?.let {
                Icon(imageVector = icon, contentDescription = icon.name)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType ?: KeyboardType.Text),
        value = text,
        onValueChange = {
            textChanged(it)
        },
        label = {
            Text(
                modifier = Modifier
                    .padding(horizontal = (-15).dp),
                text = textLabel,
                color = colorTextWithoutFocus ?: Color.Black
            )
        },
        colors = (
                TextFieldDefaults.colors(
                    focusedContainerColor = colorWithFocus ?: Color.White,
                    focusedTextColor = colorTextWithFocus ?: Color.White,
                    unfocusedTextColor = colorTextWithoutFocus ?: Color.Black,
                    unfocusedContainerColor = colorWithoutFocus ?: Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
                ),
        isError = isError,
        supportingText = supportingText
    )
}

@Preview
@Composable
private fun TextFieldPadraoPreview() {
    var text by remember { mutableStateOf("") }

    OutlinedTextFieldDefault(
        Modifier,
        "Nome",
//        icon =  Icons.Filled.Css,
        text = text,
        maxLine = 1,
        singleLine = false,
        isError = false,
        textChanged = { newText ->
            text = newText
        }
    )
}