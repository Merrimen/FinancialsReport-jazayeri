package mohammad.financialsreport.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


import mohammad.financialsreport.models.EmployeeEntity;

@Dao
public interface EmployeeEntityDAO {
    @Query("SELECT * FROM employees WHERE userid = :userid")
       EmployeeEntity getUserById(int userid);

    @Query("SELECT * from employees")
    List<EmployeeEntity> getAllEmployeeEntitys();

    @Insert
    void insertEmployeeEntity(EmployeeEntity... employees);

    @Update
    void updateEmployeeEntity (EmployeeEntity... employees);

    @Delete
    void deleteEmployeeEntity (EmployeeEntity employee);

    @Query("DELETE FROM employees")
    void deleteAllEmployeeEntitys();

    @Query("SELECT * FROM employees where username =:username ")
    EmployeeEntity getByUsername(String username);
}
