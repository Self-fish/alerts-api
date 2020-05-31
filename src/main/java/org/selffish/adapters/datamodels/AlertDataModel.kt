package org.selffish.adapters.datamodels

import org.selffish.domain.entities.RepeatRate

data class AlertDataModel(val title: String, val text: String,
    val repeatRate: RepeatRate)
