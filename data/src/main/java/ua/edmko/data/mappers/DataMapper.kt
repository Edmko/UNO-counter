package ua.edmko.data.mappers

import ua.edmko.data.local.entities.DaoModel
import ua.edmko.domain.entities.Entity

internal interface DataMapper<Local : DaoModel, Domain : Entity> {

    fun map(domain: Domain): Local

    fun map(local: Local): Domain
}
