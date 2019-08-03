package mohammad.financialsreport.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "projects", primaryKeys = {"project_id", "project_Name"})
@TypeConverters(Converters.class)
public class ProjectEntity implements Serializable {

    //@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "project_id")
    private int project_id;
    @NonNull
    @ColumnInfo(name = "project_Name")
    private String project_Name;

    @ColumnInfo(name = "employee_id")
    private int employee_id;

    @ColumnInfo(name = "totalSalary")
    private int totalSalary;

    @ColumnInfo(name = "dateStart")
    private Date dateStart;

    @ColumnInfo(name = "dateEnd")
    private Date dateEnd;

    @ColumnInfo(name = "function")
    private long function;

    @ColumnInfo(name = "reward")
    private long reward;

    @ColumnInfo(name = "tax")
    private long tax;
    @ColumnInfo(name = "penalty")
    private long penalty;

    public ProjectEntity(int project_id, @NonNull String project_Name, int totalSalary, Date dateStart, Date dateEnd,
                         int employee_id, long function, long reward, long tax, long penalty) {
        this.project_id = project_id;
        this.project_Name = project_Name;
        this.totalSalary = totalSalary;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.employee_id = employee_id;
        this.function = function;
        this.reward = reward;
        this.tax = tax;
        this.penalty = penalty;
    }

    @Ignore
    public ProjectEntity() {
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getProject_Name() {
        return project_Name;
    }

    public void setProject_Name(String project_Name) {
        this.project_Name = project_Name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public long getFunction() {
        return function;
    }

    public void setFunction(long function) {
        this.function = function;
    }

    public long getReward() {
        return reward;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public long getTax() {
        return tax;
    }

    public void setTax(long tax) {
        this.tax = tax;
    }

    public long getPenalty() {
        return penalty;
    }

    public void setPenalty(long penalty) {
        this.penalty = penalty;
    }
}


