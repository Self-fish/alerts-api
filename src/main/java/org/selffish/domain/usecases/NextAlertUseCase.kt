package org.selffish.domain.usecases

import org.selffish.domain.contracts.AlertsRepository
import org.selffish.domain.entities.Alert
import org.selffish.domain.entities.StartingMoment
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class NextAlertUseCase(private val alertsRepository: AlertsRepository) {

    companion object {
        const val TIME_ZONE = "Europe/Madrid"
    }

    fun getNextAlert(): Alert? {
        val orderedAlerts = orderAlerts(alertsRepository.getAll())
        for(alert in orderedAlerts) {
            if(isNearlyAlert(alert) && !wasNearlySent(alert)) {
                return alert
            }
        }
        return null
    }

    private fun orderAlerts(allAlerts: List<Alert>): List<Alert> {
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

    private fun wasNearlySent(alert: Alert): Boolean {
        if(alert.executionHistory.isNotEmpty()) {
            val lastExecution: Long = alert.executionHistory.last()
            return (lastExecution + 120000) > System.currentTimeMillis()
        }
        return false
    }

    private fun isNearlyAlert(alert: Alert): Boolean =
        alert.starts.day == ZonedDateTime.now(ZoneId.of(TIME_ZONE)).dayOfWeek &&
                alert.starts.hour == ZonedDateTime.now(ZoneId.of(TIME_ZONE)).hour &&
                alert.starts.minute > ZonedDateTime.now(ZoneId.of(TIME_ZONE)).minute &&
                alert.starts.minute < ZonedDateTime.now(ZoneId.of(TIME_ZONE)).minute + 2

}