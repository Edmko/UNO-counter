package ua.edmko.unocounter.domain.interactor

import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Inject

class ObservePlayers @Inject constructor(private val playersRepository: PlayersRepository): SubjectInteractor<Unit, List<Player>>(){

    override fun createObservable(params: Unit): Flow<List<Player>> {
        return playersRepository.observePlayers()
    }
}