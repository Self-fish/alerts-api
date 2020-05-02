package org.selffish.adapters.repositories

import org.selffish.adapters.database.AlertsDataBase
import org.selffish.domain.contracts.AlertsRepository
import org.selffish.domain.entities.Alert
import org.springframework.stereotype.Component
import java.util.*

@Component
class AlertsRepositoryImpl(private val alertsDataBase: AlertsDataBase): AlertsRepository {

    override fun insert(alert: Alert): Alert = alertsDataBase.insert(alert)
    override fun getAll(): MutableList<Alert> = alertsDataBase.findAll()
    override fun getById(id: String): Optional<Alert> = alertsDataBase.findById(id)
    override fun deleteById(id: String) = alertsDataBase.deleteById(id)
    override fun exist(id: String): Boolean = alertsDataBase.existsById(id)
    override fun updateAlert(alert: Alert?): Alert = alertsDataBase.save(alert)!!

}