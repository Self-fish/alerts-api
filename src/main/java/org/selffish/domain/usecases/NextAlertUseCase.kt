package org.selffish.domain.usecases

import org.selffish.domain.contracts.AlertsRepository
import org.selffish.domain.entities.Alert
import org.selffish.domain.entities.StartingMoment
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NextAlertUseCase(private val alertsRepository: AlertsRepository) {

    fun getNextAlert(): Alert {
        val orderedAlerts = orderAlerts(alertsRepository.getAll())
        return orderedAlerts.first()
    }

    fun orderAlerts(allAlerts: List<Alert>): List<Alert> {
        val alertsComparator = Comparator<Alert> { a, b ->
            val today = StartingMoment(
                LocalDateTime.now().dayOfWeek, LocalDateTime.now().hour,
                LocalDateTime.now().minute)


            when {
                a.starts == today -> -1
                b.starts == today -> 1
                a.starts < today && b.starts >= today -> 1
                b.starts < today && a.starts >= today -> -1
                a > b -> 1
                a < b -> -1
                else -> 0
            }
        }

        return allAlerts.sortedWith(alertsComparator)
    }
}