package org.selffish.domain.contracts

import org.selffish.domain.entities.Alert
import java.util.*

interface AlertsRepository {

    fun insert(alert: Alert) : Alert
    fun getAll() : MutableList<Alert>
    fun getById(id: String): Optional<Alert>
    fun deleteById(id: String)
    fun exist(id: String) : Boolean
    fun updateAlert(alert: Alert?) : Alert

}