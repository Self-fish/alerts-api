package org.selffish.adapters.datamodels

import org.selffish.domain.entities.RepeatRate
import org.selffish.domain.entities.StartingMoment

data class AlertWebModel(val title: String, val text: String, val starts: StartingMoment,
                         val repeatRate: RepeatRate)


