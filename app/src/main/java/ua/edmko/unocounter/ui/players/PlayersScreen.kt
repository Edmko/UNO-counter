package ua.edmko.unocounter.ui.players

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsPadding
import ua.edmko.unocounter.R
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Player.Companion.getPlayersStub
import ua.edmko.unocounter.ui.components.EditDialog
import ua.edmko.unocounter.ui.components.Toolbar
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.ui.theme.baseDimension

@Composable
fun PlayersScreen(viewModel: PlayersViewModel) {
    val state by viewModel.viewStates().collectAsState()
    UNOcounterTheme {
        Scaffold(
            topBar = { Toolbar(title = "Players") { viewModel.obtainEvent(NavigateBack) } },
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) { paddings ->
            Box(
                Modifier
                    .padding(paddings)
                    .background(Color.Black)
                    .fillMaxSize()

            ) {
                if (state?.isDialogShows == true) EditDialog(
                    title = stringResource(R.string.insert_name),
                    dismiss = { viewModel.obtainEvent(DismissDialog) }) { text ->
                    viewModel.obtainEvent(
                        CreatePlayer(text)
                    )
                }
                LazyColumn() {
                    state?.players?.let {
                        items(it) { player ->
//                        SwipeToDismiss(state = false, background = {
//
//                        }) {
//
//                        }
                            PlayerItem(player) { event -> viewModel.obtainEvent(event) }
                        }
                    }

                }

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 50.dp, end = baseDimension),
                    backgroundColor = Color.Red,
                    onClick = { viewModel.obtainEvent(AddPlayerButton) }) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center),
                        tint = Color.Black
                    )

                }
            }
        }
    }
}

@Composable
fun PlayerItem(player: Player, event: (PlayersEvent) -> Unit) {
    val textColor = if (player.isSelected) MaterialTheme.colors.secondary else Color.White
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(50.dp)
    ) {
        Checkbox(
            checked = player.isSelected,
            onCheckedChange = { isChecked ->
                event.invoke(UpdatePlayersSelection(player.copy(isSelected = isChecked)))
            },
            modifier = Modifier
                .padding(start = 18.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = player.name,
            color = textColor,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(
                    Alignment.CenterStart
                )
                .padding(start = 54.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_bin),
            "Delete player",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 54.dp)
                .clickable {
                    event.invoke(DeletePlayerEvent(player))
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "Edit player",
            Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 18.dp)
        )
        Divider(
            Modifier
                .height(1.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = Color.White
        )
    }
}


@Preview(backgroundColor = 0x000000, showBackground = true)
@Composable
fun PlayerItemPreview(name: String = "John Simons") {
    PlayerItem(player = getPlayersStub().first(), {})
}