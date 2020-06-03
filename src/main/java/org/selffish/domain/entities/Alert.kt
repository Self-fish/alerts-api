package org.selffish.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Alert(@Id val id: String?, var creationDate: Long?, val title: String, val text: String,
                 val executionHistory: MutableList<Long>?, val starts: StartingMoment?, val repeatRate: RepeatRate)

data class StartingMoment(val day: DayOfWeek, val hour: Int,
                          val minute: Int)

enum class DayOfWeek {
    MON, TUE, WED, THU, FRI, SAT, SAN
}


enum class RepeatRate {
    DAILY, WEEKLY, BIWEEKLY, MONTHLY, NO_REPEAT
}