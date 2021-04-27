package ua.edmko.unocounter.ui.gameSetting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ua.edmko.unocounter.R
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.ui.theme.baseDimension

@Composable
fun GameSettingScreen(viewModel: GameSettingViewModel) {
    val state by viewModel.viewStates().collectAsState()
    UNOcounterTheme {

        //dialog
        if (state?.dialogShows == true) EditDialog { text -> viewModel.obtainEvent(ChangeGoal(text.toInt())) }

        Box(Modifier.fillMaxSize()) {
            Column(Modifier
                    .padding(0.dp, 40.dp, 0.dp, 0.dp)
                    .fillMaxSize()
            ) {
                val goal =  state?.goal.toString()
                TextFieldWithDivider(goal) { viewModel.obtainEvent(OnGoalClickEvent) }
                TextFieldWithDivider(stringResource(R.string.type))
                Text(
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    text = stringResource(R.string.players),
                    modifier = Modifier.padding(baseDimension, 32.dp, baseDimension, 18.dp)
                )
                Divider(color = Color.White)
                Box(Modifier.weight(1f)) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(baseDimension, 0.dp, baseDimension, 0.dp),
                        contentPadding = PaddingValues(bottom = 110.dp)
                    ) {
                        itemsIndexed(viewModel.players) { index, player ->
                            val color = when (index % 4) {
                                0 -> Color.Yellow
                                1 -> Color.Red
                                2 -> Color.Blue
                                else -> Color.Green
                            }
                            PlayerItem(player = player, color)
                        }
                    }
                }
            }
            GameButton(
                text = stringResource(R.string.start_game),
                modifier = Modifier
                    .padding(baseDimension, 0.dp, baseDimension, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun EditDialog(onClick: (String) -> Unit) {
    Dialog(onDismissRequest = {}) {
        var text by remember { mutableStateOf("") }
        Column(
            Modifier
                .background(color = Color.DarkGray, shape = RoundedCornerShape(15.dp))
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.insert_goal),
                color = Color.White,
                fontSize = 24.sp
            )

            GameEditText(value = text) { text = it }

            Text(
                text = "Accept",
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
fun GameEditText(value: String, onValueChanged: (String) -> Unit) {
    BasicTextField(
        modifier = Modifier
            .padding(top = 32.dp)
            .height(50.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChanged,
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.White, fontSize = 24.sp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        cursorBrush = SolidColor(Color.White)
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
}

@Preview
@Composable
fun PlayerItem(player: String = "John Smith", color: Color = Color.Red) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Rounded.Person,
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(0.dp, 5.dp, 10.dp, 5.dp)
                .size(40.dp),
            tint = color
        )
        Text(
            text = player,
            style = MaterialTheme.typography.body1,
            color = color
        )
    }
}

@Preview(widthDp = 300)
@Composable
fun GameButton(text: String = "Start game", modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        colors = buttonColors(backgroundColor = Color.Red),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text,
            color = Color.Black,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black
        )
    }
}

@Preview
@Composable
fun TextFieldWithDivider(initText: String = "Goal", click: (() -> Unit)? = null) {
    Column(Modifier.clickable {
        click?.invoke()
    }) {
        Text(
            style = MaterialTheme.typography.h5,
            color = Color.White,
            text = initText,
            modifier = Modifier.padding(baseDimension)
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(baseDimension, 10.dp, baseDimension, 0.dp), color = Color.White
        )
    }

}

