package org.selffish.domain.usecases

import org.selffish.domain.contracts.AlertsRepository
import org.springframework.stereotype.Service

@Service
class DeleteAlertUseCase(private val alertsRepository: AlertsRepository) {

    fun deleteById(id: String) = alertsRepository.deleteById(id)
}