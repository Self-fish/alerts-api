package org.selffish.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Alert(@Id val id: String?, var creationDate: Long?, val title: String, val text: String,
                 val executionHistory: MutableList<Long>?, val repeatRate: RepeatRate)

enum class RepeatRate {
    DAILY, WEEKLY, MONTHLY
}