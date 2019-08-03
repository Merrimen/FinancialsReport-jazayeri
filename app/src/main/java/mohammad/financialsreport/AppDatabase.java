package mohammad.financialsreport;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;
import java.util.List;

import mohammad.financialsreport.dao.EmployeeEntityDAO;
import mohammad.financialsreport.dao.PaymentEntiryDAO;
import mohammad.financialsreport.dao.ProjectEntityDAO;
import mohammad.financialsreport.models.Converters;
import mohammad.financialsreport.models.EmployeeEntity;
import mohammad.financialsreport.models.PaymentEntity;
import mohammad.financialsreport.models.ProjectEntity;
import mohammad.financialsreport.utilities.AppExecutors;


@Database(entities = {EmployeeEntity.class, PaymentEntity.class, ProjectEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";
    //static Context context;
    //static AppExecutors executors = AppExecutors.getInstance();

    public abstract EmployeeEntityDAO getEmployeeEntityDAO();

    public abstract PaymentEntiryDAO getAllPaymentEntityDAO();

    public abstract ProjectEntityDAO getAllProjectEntityDAO();

    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Financial_Reports")
                    .fallbackToDestructiveMigration()
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            AppExecutors.getInstance().diskIO().execute(() -> addData());
                        }

                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                        }
                    }).build();
        }

        return appDatabase;
    }


    private static void addData() {

        Log.d(TAG, "addData: add data called");
        //these data are for adding employees
        EmployeeEntityDAO employeeEntityDAO = appDatabase.getEmployeeEntityDAO();
        //employee1
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setUsername("mohammad");
        employeeEntity1.setPassword("abc");
        employeeEntity1.setAccount_number("54832315");
        employeeEntity1.setCard_number("6104337836269025");
        employeeEntity1.setBank_name("tejarat");
        employeeEntity1.setUserid(1);
        employeeEntityDAO.insertEmployeeEntity(employeeEntity1);

        //employee2
        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setUsername("jazayeri");
        employeeEntity2.setPassword("def");
        employeeEntity2.setAccount_number("7455258");
        employeeEntity2.setCard_number("35156415613215");
        employeeEntity2.setBank_name("mellat");
        employeeEntity2.setUserid(2);
        employeeEntityDAO.insertEmployeeEntity(employeeEntity2);

        //employee3
        EmployeeEntity employeeEntity3 = new EmployeeEntity();
        employeeEntity3.setUsername("ahmad");
        employeeEntity3.setPassword("ghi");
        employeeEntity3.setAccount_number("879812");
        employeeEntity3.setCard_number("214856121556825");
        employeeEntity3.setBank_name("meli");
        employeeEntity3.setUserid(3);
        employeeEntityDAO.insertEmployeeEntity(employeeEntity3);

        //employee4
        EmployeeEntity employeeEntity4 = new EmployeeEntity();
        employeeEntity4.setUsername("asghar");
        employeeEntity4.setPassword("jkl");
        employeeEntity4.setAccount_number("952562158");
        employeeEntity4.setCard_number("91684848484");
        employeeEntity4.setBank_name("sadertat");
        employeeEntity4.setUserid(4);
        employeeEntityDAO.insertEmployeeEntity(employeeEntity4);

        List<EmployeeEntity> employeeEntities = employeeEntityDAO.getAllEmployeeEntitys();
        updateem(employeeEntities);

        //these data are for payments
        PaymentEntiryDAO paymentEntiryDAO = appDatabase.getAllPaymentEntityDAO();
        //payment for employee1
        PaymentEntity paymentEntity1 = new PaymentEntity();
        paymentEntity1.setDeposited(9568560);
        paymentEntity1.setEmployee_id(1);
        paymentEntity1.setProject_id(1);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity1);

        //payment for employee2
        PaymentEntity paymentEntity2 = new PaymentEntity();
        paymentEntity2.setDeposited(2654865);
        paymentEntity2.setEmployee_id(2);
        paymentEntity2.setProject_id(2);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity2);

        //payment for employee3
        PaymentEntity paymentEntity3 = new PaymentEntity();
        paymentEntity3.setDeposited(6584845);
        paymentEntity3.setEmployee_id(3);
        paymentEntity3.setProject_id(3);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity3);

        //payment for employee4
        PaymentEntity paymentEntity4 = new PaymentEntity();
        paymentEntity4.setDeposited(7568468);
        paymentEntity4.setEmployee_id(4);
        paymentEntity4.setProject_id(4);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity4);

        //payment2 for employee1
        PaymentEntity paymentEntity5 = new PaymentEntity();
        paymentEntity5.setDeposited(1000000);
        paymentEntity5.setEmployee_id(1);
        paymentEntity5.setProject_id(1);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity5);

        //payment3 for employee1
        PaymentEntity paymentEntity6 = new PaymentEntity();
        paymentEntity6.setDeposited(9000000);
        paymentEntity6.setEmployee_id(1);
        paymentEntity6.setProject_id(1);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity6);

        //payment2 for employee2
        PaymentEntity paymentEntity7 = new PaymentEntity();
        paymentEntity7.setDeposited(8000000);
        paymentEntity7.setEmployee_id(2);
        paymentEntity7.setProject_id(2);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity7);

        //payment3 for employee2
        PaymentEntity paymentEntity8 = new PaymentEntity();
        paymentEntity8.setDeposited(7000000);
        paymentEntity8.setEmployee_id(2);
        paymentEntity8.setProject_id(2);
        paymentEntiryDAO.insertPaymentEntity(paymentEntity8);

        List<PaymentEntity> salaryEntities = paymentEntiryDAO.getAllPaymentEntitys();
//        executors.mainThread().execute(() -> updatesa(salaryEntities));
        updatepay(salaryEntities);

        //these data for project
        ProjectEntityDAO projectEntityDAO = appDatabase.getAllProjectEntityDAO();

        //project1 for employee1
        ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setTotalSalary(9821548);
        projectEntity1.setProject_Name("پروژه اول");
        projectEntity1.setProject_id(1);
        projectEntity1.setEmployee_id(1);
        projectEntity1.setFunction(5000000);
        projectEntity1.setReward(1000000);
        projectEntity1.setTax(500000);
        projectEntity1.setPenalty(400000);
        projectEntityDAO.insertProjectEntity(projectEntity1);

        //project1 for employee2
        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity2.setTotalSalary(9848932);
        projectEntity2.setProject_Name("پروژه دوم");
        projectEntity2.setEmployee_id(2);
        projectEntity2.setProject_id(2);
        projectEntity2.setFunction(6000000);
        projectEntity2.setReward(900000);
        projectEntity2.setTax(200000);
        projectEntity2.setPenalty(100000);
        projectEntityDAO.insertProjectEntity(projectEntity2);

//project1 for employee3
        ProjectEntity projectEntity3 = new ProjectEntity();
        projectEntity3.setTotalSalary(7125225);
        projectEntity3.setProject_Name("پروژه سوم");
        projectEntity3.setEmployee_id(3);
        projectEntity3.setProject_id(3);
        projectEntity3.setFunction(4900000);
        projectEntity3.setReward(650000);
        projectEntity3.setTax(329000);
        projectEntity3.setPenalty(124000);
        projectEntityDAO.insertProjectEntity(projectEntity3);

        //project1 for employee4
        ProjectEntity projectEntity4 = new ProjectEntity();
        projectEntity4.setTotalSalary(6321548);
        projectEntity4.setProject_Name("پروژه چهارم");
        projectEntity4.setEmployee_id(4);
        projectEntity4.setProject_id(4);
        projectEntity4.setFunction(7000000);
        projectEntity4.setReward(800000);
        projectEntity4.setTax(500000);
        projectEntity4.setPenalty(150000);

        projectEntityDAO.insertProjectEntity(projectEntity4);

        List<ProjectEntity> projectEntitys = projectEntityDAO.getAllprojectEntity();
        updatepro(projectEntitys);
    }

    static void updateem(List<EmployeeEntity> employeeEntities) {
        System.out.println(employeeEntities);
    }

    static void updatepay(List<PaymentEntity> paymentEntities) {
        System.out.println(paymentEntities);
    }

    static void updatepro(List<ProjectEntity> projectEntities) {
        System.out.println(projectEntities);
    }


}
