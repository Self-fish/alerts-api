package org.selffish.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.DayOfWeek

@Document
data class Alert(@Id val id: String?, val creationDate: Long, val title: String, val text: String,
                 val executionHistory: MutableList<Long>, val starts: StartingMoment, val repeatRate: RepeatRate)

data class StartingMoment(val day: DayOfWeek, val hour: Int,
                          val minute: Int)

enum class RepeatRate {
    DAILY, WEEKLY, BIWEEKLY, MONTHLY, NO_REPEAT
}