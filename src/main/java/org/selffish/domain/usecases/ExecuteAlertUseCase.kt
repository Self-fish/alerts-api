package org.selffish.domain.usecases

import org.selffish.domain.contracts.AlertsRepository
import org.selffish.domain.entities.Alert
import org.springframework.stereotype.Service

@Service
class ExecuteAlertUseCase(private val alertsRepository: AlertsRepository) {

    fun executeAlert(id: String) : Alert? {
        val alert = alertsRepository.getById(id)
        alert.let {
            if(alert.isPresent) {
                alert.get().executionHistory?.add(System.currentTimeMillis())
                return alertsRepository.updateAlert(alert.get())
            } else {
                return null
            }

        }
    }

}