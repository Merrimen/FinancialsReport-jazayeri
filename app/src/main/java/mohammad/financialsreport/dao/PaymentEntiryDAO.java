package mohammad.financialsreport.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mohammad.financialsreport.models.PaymentEntity;

@Dao
public interface PaymentEntiryDAO {

    @Query("SELECT * FROM payments")
    List<PaymentEntity> getAllPaymentEntitys();

    @Query("Select * from payments where employee_id = :id")
    PaymentEntity getUseById(int id);

    @Query("SELECT * FROM payments WHERE employee_id =:employee_id")
    PaymentEntity findPaymentforEmployee(final int employee_id);

    @Insert
    void insertPaymentEntity(PaymentEntity... payments);

    @Update
    void updatePaymentEntity(PaymentEntity... payments);

    @Delete
    void deletePaymentEntity(PaymentEntity payments);

}
