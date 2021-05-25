package ua.edmko.unocounter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ua.edmko.unocounter.R

@Composable
fun EditDialog(textType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), title: String, onClick: (String) -> Unit = {}, ) {
    Dialog(onDismissRequest = {}) {
        var text by remember { mutableStateOf("") }
        Column(
            Modifier
                .background(color = Color.DarkGray, shape = RoundedCornerShape(15.dp))
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp
            )

            GameEditText(value = text, textType = textType, { text = it }, {onClick.invoke(text) })

            Text(
                text = stringResource(R.string.accept),
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 18.dp)
                    .align(Alignment.End)
                    .clickable(onClick = { onClick.invoke(text) }),
            )
        }

    }
}

@Composable
fun GameEditText(value: String, textType: KeyboardOptions, onValueChanged: (String) -> Unit = {}, onImeAction: () -> Unit) {
    val focusRequester = FocusRequester()
    BasicTextField(
        modifier = Modifier
            .padding(top = 32.dp)
            .height(50.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChanged,
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.White, fontSize = 24.sp),
        keyboardOptions = textType,
        cursorBrush = SolidColor(Color.White),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction.invoke()
            }
        )
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(15.dp))
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            innerTextField()
        }
    }
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}