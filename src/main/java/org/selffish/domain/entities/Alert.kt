package org.selffish.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.DayOfWeek

@Document
data class Alert(@Id val id: String?, val creationDate: Long, val title: String, val text: String,
                 val executionHistory: MutableList<Long>, val starts: StartingMoment, val repeatRate: RepeatRate) {

    operator fun compareTo(alert: Alert): Int {
        return starts.compareTo(alert.starts)
    }

}

data class StartingMoment(val day: DayOfWeek, val hour: Int,
                          val minute: Int) {

    operator fun compareTo(alertDate: StartingMoment): Int {
        return when {
            day < alertDate.day -> -1
            day > alertDate.day ->  1
            else -> return when {
                hour < alertDate.hour -> -1
                hour > alertDate.hour -> 1
                else -> return when {
                    minute < alertDate.minute -> -1
                    minute > alertDate.minute -> 1
                    else -> 0
                }
            }
        }
    }


}

enum class RepeatRate {
    DAILY, WEEKLY, BIWEEKLY, MONTHLY, NO_REPEAT
}