package com.sondershop.sondershopapp.detalleVenta.dbDirecciones;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sondershop.sondershopapp.detalleVenta.db.User;

import java.util.List;

@Dao
public interface UserDaoInterfaceDirecciones {

    @Query("SELECT * FROM userdirecciones")
    List<UserDirecciones> getAllDirecciones();

    @Insert
    void insertDirecciones(UserDirecciones... usersdir);

    @Delete
    void delete(UserDirecciones userdir);
}
