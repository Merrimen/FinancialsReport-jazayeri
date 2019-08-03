package mohammad.financialsreport.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "employees")
public class EmployeeEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid")
    private int userid;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "account_number")
    private String account_number;

    @ColumnInfo(name = "card_number")
    private String card_number;

    @ColumnInfo(name = "bank_name")
    String bank_name;


    public EmployeeEntity(int userid, String username, String password, String account_number, String card_number, String bank_name) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.account_number = account_number;
        this.card_number = card_number;
        this.bank_name = bank_name;
    }

    public EmployeeEntity() {
    }



    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
