package com.albertorres.guardiaobrera.POJO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
  data class PlanGuardiaObrera(@PrimaryKey val id:Int,
                               @ColumnInfo val listaObreros:String,
                               @ColumnInfo val fecha: Calendar,
                               @ColumnInfo val lugar: String)