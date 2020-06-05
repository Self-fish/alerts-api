package org.selffish.framework.controller

import org.selffish.adapters.datamodels.AlertWebModel
import org.selffish.domain.entities.Alert
import org.selffish.domain.usecases.*
import org.selffish.framework.exceptions.AlertNotFoundException
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("alerts")
class AlertController(private val addAlert: AddAlertUseCase,
                      private val getAlert: GetAlertUseCase,
                      private val deleteAlert: DeleteAlertUseCase,
                      private val updateAlert: UpdateAlertUseCase,
                      private val executeAlertUseCase: ExecuteAlertUseCase) {

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody alertWebModel: AlertWebModel): Alert {
        val alert = Alert(null, null, alertWebModel.title,
            alertWebModel.text, null, alertWebModel.starts, alertWebModel.repeatRate)
        return addAlert.create(alert)
    }

    @RequestMapping(method= [RequestMethod.GET])
    fun getAll() =  getAlert.getAllAlerts()

    @RequestMapping(method= [RequestMethod.GET], value = ["/{id}"])
    fun getById(@PathVariable id: String) : Alert {
        val alert = getAlert.getById(id)
        if(alert.isPresent) {
            return alert.get()
        }
        throw AlertNotFoundException()
    }

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

    @RequestMapping(method= [RequestMethod.POST], value = ["/{id}/execute"])
    fun execute(@PathVariable id: String) : Alert {
        val alert = executeAlertUseCase.executeAlert(id)
        if(alert != null) {
            return alert
        }
        throw AlertNotFoundException()
    }



}