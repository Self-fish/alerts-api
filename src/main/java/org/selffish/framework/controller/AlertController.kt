package org.selffish.framework.controller

import org.selffish.adapters.datamodels.AlertDataModel
import org.selffish.domain.entities.Alert
import org.selffish.domain.usecases.AddAlertUseCase
import org.selffish.domain.usecases.DeleteAlertUseCase
import org.selffish.domain.usecases.GetAlertUseCase
import org.selffish.domain.usecases.UpdateAlertUseCase
import org.selffish.framework.exceptions.AlertNotFoundException
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("alerts")
class AlertController(private val addAlert: AddAlertUseCase, private val getAlert: GetAlertUseCase,
    private val deleteAlert: DeleteAlertUseCase, private val updateAlert: UpdateAlertUseCase) {

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody alertDataModel: AlertDataModel): Alert {
        val alert = Alert(null, null, null, alertDataModel.title,
            alertDataModel.text, null, alertDataModel.repeatRate)
        return addAlert.create(alert)
    }

    @RequestMapping(method= [RequestMethod.GET])
    fun getAll() =  getAlert.getAllAlerts()

    @RequestMapping(method= [RequestMethod.GET], value = ["/{id}"])
    fun getById(@PathVariable id: String) =  getAlert.getById(id)


    @RequestMapping(method= [RequestMethod.DELETE], value = ["/{id}"])
    fun deleteById(@PathVariable id: String) =  deleteAlert.deleteById(id)

    @RequestMapping(method = [RequestMethod.PUT], value = ["/{id}"])
    fun update(@RequestBody alert: Alert, @PathVariable id: String): Alert {
        val alert = updateAlert.updateAlert(alert, id)
        alert?.let {
            return alert
        }
        throw AlertNotFoundException()
    }

}