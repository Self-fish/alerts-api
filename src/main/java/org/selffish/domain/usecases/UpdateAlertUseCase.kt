package org.selffish.domain.usecases

import org.selffish.domain.contracts.AlertsRepository
import org.selffish.domain.entities.Alert
import org.springframework.stereotype.Service

@Service
class UpdateAlertUseCase(private val alertsRepository: AlertsRepository) {

    fun updateAlert(alert: Alert, id: String) : Alert? {
        if(alertsRepository.exist(id)) {
            return alertsRepository.updateAlert(alert)
        }
        return null
    }
}