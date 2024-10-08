package ua.edmko.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.edmko.core.ui.R
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.horizontalPadding

@Composable
fun DialogApp(
    title: String,
    testTag: String = "DialogApp",
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(onDismiss) {
        Column(
            Modifier
                .background(color = AppTheme.colors.surface, shape = AppTheme.shapes.large)
                .padding(15.dp)
                .fillMaxWidth()
                .testTag(testTag),
        ) {
            Text(
                text = title,
                color = AppTheme.colors.onSurface,
                style = AppTheme.typography.h5,
            )
            content()
        }
    }
}

@Composable
fun EditDialog(
    textType: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done,
    ),
    title: String,
    testTag: String = "EditDialog",
    onDismiss: () -> Unit,
    onClick: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    DialogApp(title = title, testTag = testTag, onDismiss = onDismiss) {
        GameEditText(
            modifier = Modifier
                .padding(top = 32.dp)
                .height(50.dp)
                .fillMaxWidth(),
            value = text,
            textType = textType,
            { text = it },
            { onClick(text) },
        )

        Text(
            text = stringResource(R.string.accept),
            color = AppTheme.colors.onSurface,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.End)
                .clickable(onClick = { onClick(text) }),
        )
    }
}

@Composable
fun ConfirmationDialog(
    title: String,
    dismiss: () -> Unit,
    accept: () -> Unit,
) {
    DialogApp(title = title, onDismiss = dismiss) {
        Row(
            modifier = Modifier
                .padding(horizontalPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GameButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                onClick = accept,
                isEnabled = true,
                text = stringResource(R.string.accept),
                fontSize = 24.sp,
            )

            Spacer(Modifier.width(20.dp))

            GameButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                onClick = dismiss,
                isEnabled = true,
                text = stringResource(R.string.cancel_button_title),
                fontSize = 24.sp,
            )
        }
    }
}

@Composable
fun GameEditText(
    modifier: Modifier = Modifier,
    value: String,
    textType: KeyboardOptions,
    onValueChanged: (String) -> Unit = {},
    onImeAction: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val coroutine = rememberCoroutineScope()
    BasicTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChanged,
        maxLines = 1,
        textStyle = TextStyle(color = Color.White, fontSize = 24.sp),
        keyboardOptions = textType,
        cursorBrush = SolidColor(Color.White),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(15.dp))
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart,
        ) {
            innerTextField()
        }
    }

    SideEffect {
        coroutine.launch {
            delay(100L)
            focusRequester.requestFocus()
        }
    }
}

@Preview
@Composable
fun AppDialog() {
    AppTheme {
        DialogApp(
            title = "Title",
            onDismiss = { /** Empty */ },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(AppTheme.colors.primary),
                )
            },
        )
    }
}

@Preview
@Composable
fun ConfirmationDialogPreview() {
    AppTheme {
        ConfirmationDialog(
            title = "Title",
            dismiss = { /** Empty */ },
            accept = { /** Empty */ },
        )
    }
}

@Preview
@Composable
fun EditDialogPreview() {
    AppTheme {
        EditDialog(
            title = "Title",
            onDismiss = { /** Empty */ },
        )
    }
}
