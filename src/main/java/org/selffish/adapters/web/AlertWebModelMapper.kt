package org.selffish.adapters.web

import org.selffish.domain.entities.Alert
import org.springframework.stereotype.Service

@Service
class AlertWebModelMapper {

    fun createAlert(alertWebModel: AlertWebModel) =
        Alert(null, System.currentTimeMillis(), alertWebModel.title,
            alertWebModel.text, mutableListOf(), alertWebModel.starts, alertWebModel.repeatRate)

}