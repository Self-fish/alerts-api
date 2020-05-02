package org.selffish.domain.usecases

import org.selffish.domain.contracts.AlertsRepository
import org.selffish.domain.entities.Alert
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetAlertUseCase(private val alertsRepository: AlertsRepository) {

    fun getAllAlerts(): MutableList<Alert> = alertsRepository.getAll()
    fun getById(id: String): Optional<Alert> = alertsRepository.getById(id)

}