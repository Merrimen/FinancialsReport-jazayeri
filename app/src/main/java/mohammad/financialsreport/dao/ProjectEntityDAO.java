package mohammad.financialsreport.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import mohammad.financialsreport.models.PaymentEntity;
import mohammad.financialsreport.models.ProjectEntity;

@Dao
public interface ProjectEntityDAO {

    @Query("SELECT * FROM projects")
    List<ProjectEntity> getAllprojectEntity();

    @Query("SELECT * FROM projects WHERE employee_id =:project_id ")
    ProjectEntity getUseById(int project_id);

    @Query("SELECT * FROM projects WHERE employee_id =:employee_id")
    ProjectEntity findProjectforEmployee(final int employee_id);

    @Query("select  * from projects where dateStart between :from AND :to")
    List<ProjectEntity> findProjectsBetweenDates(Date from, Date to);

    @Insert
    void insertProjectEntity(ProjectEntity... projects);

    @Update
    void updateProjectEntity(ProjectEntity... projects);

    @Delete
    void deleteProjectEntity(ProjectEntity... projects);
}
