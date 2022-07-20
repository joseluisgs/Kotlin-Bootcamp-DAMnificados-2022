package services.tenistas

import models.Tenista
import services.base.Storage

/**
 * Servicio de persistencia de tenistas JSON
 */
interface StorageTenistasJsonService : Storage<Tenista>