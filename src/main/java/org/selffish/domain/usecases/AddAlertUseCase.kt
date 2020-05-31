package org.selffish.domain.usecases

import org.selffish.domain.contracts.AlertsRepository
import org.selffish.domain.entities.Alert
import org.springframework.stereotype.Service

@Service
class AddAlertUseCase(private val alertsRepository: AlertsRepository) {

    fun create(alert: Alert) : Alert {
        alert.creationDate = System.currentTimeMillis()
        return alertsRepository.insert(alert)
    }

}