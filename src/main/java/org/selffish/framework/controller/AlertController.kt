package org.selffish.framework.controller

import org.selffish.adapters.web.AlertWebModel
import org.selffish.adapters.web.AlertWebModelMapper
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
                      private val executeAlertUseCase: ExecuteAlertUseCase,
                      private val alertWebModelMapper: AlertWebModelMapper,
                      private val nextAlert: NextAlertUseCase) {

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody alertWebModel: AlertWebModel): Alert {
        return addAlert.create(alertWebModelMapper.createAlert(alertWebModel))
    }

    @RequestMapping(method= [RequestMethod.GET])
    fun getAll() =  getAlert.getAllAlerts()

    @RequestMapping(method= [RequestMethod.GET], value = ["/next"])
    fun getNext() =  nextAlert.getNextAlert()

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