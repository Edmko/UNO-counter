package ua.edmko.unocounter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ua.edmko.unocounter.R
import ua.edmko.unocounter.domain.entities.GameType
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.ui.theme.baseDp


@Composable
fun DialogApp(
    title: String,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(onDismiss) {

        Column(
            Modifier
                .background(color = Color.DarkGray, shape = RoundedCornerShape(15.dp))
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = MaterialTheme.colors.onSurface,
                fontSize = 24.sp
            )
            content()
        }
    }
}

@Composable
fun EditDialog(
    textType: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    ),
    title: String,
    onDismiss: () -> Unit,
    onClick: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    DialogApp(title = title, onDismiss = onDismiss) {
        GameEditText(
            modifier = Modifier
                .padding(top = 32.dp)
                .height(50.dp)
                .fillMaxWidth(),
            value = text,
            textType = textType,
            { text = it },
            { onClick(text) }
        )

        Text(
            text = stringResource(R.string.accept),
            color = MaterialTheme.colors.onSurface,
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
                .padding(baseDp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
                    .height(50.dp),
                onClick = accept,
                isEnabled = true,
                text = "Accept",
                fontSize = 24.sp
            )

            GameButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
                    .height(50.dp),
                onClick = dismiss,
                isEnabled = true,
                text = "Cancel",
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun OptionsDialog(
    title: String,
    type: GameType,
    dismiss: (GameType) -> Unit
) {
    var selected by remember { mutableStateOf(type) }
    DialogApp(title = title, onDismiss = { dismiss(selected) }) {


        Spacer(modifier = Modifier.size(15.dp))
        Row(Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { selected = GameType.CLASSIC }) {
            RadioButton(selected = selected == GameType.CLASSIC, onClick = {
                selected = GameType.CLASSIC
            })
            Text(text = GameType.CLASSIC.title, modifier = Modifier.padding(start = 10.dp), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.size(15.dp))
        Row(
            Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { selected = GameType.COLLECTIVE }) {
            RadioButton(
                selected = selected == GameType.COLLECTIVE,
                onClick = { selected = GameType.COLLECTIVE })
            Text(text = GameType.COLLECTIVE.title, modifier = Modifier.padding(start = 10.dp), fontSize = 18.sp)
        }
        Text(
            text = stringResource(R.string.accept),
            color = MaterialTheme.colors.onSurface,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.End)
                .clickable(onClick = { dismiss(selected) }),
        )
    }
}

@Composable
fun GameEditText(
    modifier: Modifier = Modifier,
    value: String,
    textType: KeyboardOptions,
    onValueChanged: (String) -> Unit = {},
    onImeAction: () -> Unit
) {
    val focusRequester = FocusRequester()
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