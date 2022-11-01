package com.albertorres.guardiaobrera.Database

import androidx.room.*
import androidx.room.Dao
import com.albertorres.guardiaobrera.POJO.PlanGuardiaObrera


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertarPlanGuardiaObrera( guardiaObrera: PlanGuardiaObrera)

    @Update
    fun UpdatePlanGuardiaObrera(guardiaObrera: PlanGuardiaObrera)

    @Delete
    fun DeletePlanGuardiaObrera(guardiaObrera: PlanGuardiaObrera)

    @Query("SELECT * FROM PlanGuardiaObrera")
    fun getPlanes():List<PlanGuardiaObrera>


}