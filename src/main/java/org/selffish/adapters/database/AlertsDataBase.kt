package org.selffish.adapters.database

import org.selffish.domain.entities.Alert
import org.springframework.data.mongodb.repository.MongoRepository

interface AlertsDataBase: MongoRepository<Alert, String>