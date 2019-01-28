package Model;

import java.sql.Time;

public class Base
{
    private boolean IsDeleted ;
    private Time CreatedDate ;
    private Time ModifiedDate ;
    private int CreatedUserId ;
    private int ModifiedUserId ;

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public Time getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Time createdDate) {
        CreatedDate = createdDate;
    }

    public Time getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Time modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public int getCreatedUserId() {
        return CreatedUserId;
    }

    public void setCreatedUserId(int createdUserId) {
        CreatedUserId = createdUserId;
    }

    public int getModifiedUserId() {
        return ModifiedUserId;
    }

    public void setModifiedUserId(int modifiedUserId) {
        ModifiedUserId = modifiedUserId;
    }
}
