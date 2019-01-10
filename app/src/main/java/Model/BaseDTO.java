package Model;

public class BaseDTO
{
    public int Id;
    public boolean IsDeleted ;
    public int CreatedUserId ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
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

    public int ModifiedUserId ;

}

