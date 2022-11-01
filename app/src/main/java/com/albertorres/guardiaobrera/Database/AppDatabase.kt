package com.albertorres.guardiaobrera.Database

import android.content.Context
import androidx.room.*
import com.albertorres.guardiaobrera.POJO.PlanGuardiaObrera



@Database(entities = [PlanGuardiaObrera::class],version = 1)
@TypeConverters(CalendarConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun Dao(): Dao

    companion object{

       private var INSTANCE: AppDatabase? = null

        fun getInstance(contex:Context):AppDatabase{
            if (INSTANCE==null){
                INSTANCE= Room.databaseBuilder(contex.applicationContext,AppDatabase::class.java,"Guardia_Database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

            }

            return INSTANCE!!

        }






    }


}