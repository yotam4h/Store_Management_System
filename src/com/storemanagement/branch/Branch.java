package com.storemanagement.branch;

public class Branch
{
    private int branchId;
    private String branchName;
    private String branchAddress;
    private String branchPhone;

    public Branch(int branchId, String branchName, String branchAddress, String branchPhone)
    {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchPhone = branchPhone;
    }

    // Overloaded constructor without branchId (for new branches)
    public Branch(String branchName, String branchAddress, String branchPhone)
    {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchPhone = branchPhone;
    }

    public int getBranchId()
    {
        return branchId;
    }

    public void setBranchId(int branchId)
    {
        this.branchId = branchId;
    }

    public String getBranchName()
    {
        return branchName;
    }

    public void setBranchName(String branchName)
    {
        this.branchName = branchName;
    }

    public String getBranchAddress()
    {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress)
    {
        this.branchAddress = branchAddress;
    }

    public String getBranchPhone()
    {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone)
    {
        this.branchPhone = branchPhone;
    }


    @Override
    public String toString()
    {
        return "Branch{" +
                "branchId=" + branchId +
                ", branchName=" + branchName +
                ", branchAddress=" + branchAddress +
                ", branchPhone=" + branchPhone +
                '}';
    }
}
