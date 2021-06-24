package com.sondershop.sondershopapp.detalleVenta.dbDirecciones;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sondershop.sondershopapp.detalleVenta.db.User;
import com.sondershop.sondershopapp.detalleVenta.db.UserDaoInterface;

@Database(entities = {UserDirecciones.class}, version  = 1)
public abstract class AppDatabaseDirecciones extends RoomDatabase {

    public abstract UserDaoInterfaceDirecciones userDaoInterfdirecciones();

    private static AppDatabaseDirecciones INSTANCE;

    public static AppDatabaseDirecciones getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseDirecciones.class, "DB_NAME2")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
