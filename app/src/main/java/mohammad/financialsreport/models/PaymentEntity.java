package mohammad.financialsreport.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "payments")
//        ,foreignKeys = @ForeignKey(entity = EmployeeEntity.class, parentColumns = "userid",
//        childColumns = "employee_id",
//        onDelete = CASCADE,onUpdate = CASCADE)
//)
public class PaymentEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "employee_id")
    int employee_id;

    @ColumnInfo(name = "project_id")
    private int project_id;

    @ColumnInfo(name = "deposited")
    private long deposited;

    @ColumnInfo(name = "date")
    private Date date;

    public PaymentEntity(int id, int employee_id, int project_id, long deposited, Date date) {
        this.id = id;
        this.employee_id = employee_id;
        this.project_id = project_id;
        this.deposited = deposited;
        this.date = date;
    }

    @Ignore
    public PaymentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public long getDeposited() {
        return deposited;
    }

    public void setDeposited(long deposited) {
        this.deposited = deposited;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
