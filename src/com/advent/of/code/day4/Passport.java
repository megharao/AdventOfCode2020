package com.advent.of.code.day4;

public class Passport {
    private int byr;
    private int iyr;
    private int eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private int cid;

    public boolean isValid() {
        int height =0;
        if(this.hgt != null && (this.hgt.endsWith("cm") || this.hgt.endsWith("in"))) {
            height = Integer.parseInt(this.hgt.substring(0, this.hgt.length() - 2));
        }
        return (this.byr >= 1920 && this.byr <= 2002)
                && (this.iyr >= 2010 && this.iyr <= 2020)
                && (this.eyr >=2020 && this.eyr <=2030)
                && (this.hgt != null && ((this.hgt.endsWith("cm") && height >= 150 && height <= 193) || (this.hgt.endsWith("in") && height >= 59 && height <= 76)))
                && (this.hcl != null && this.hcl.startsWith("#") && this.hcl.length() == 7 && this.hcl.matches("#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]"))
                && (this.ecl != null && (this.ecl.equals("amb") || this.ecl.equals("blu") || this.ecl.equals("brn") ||
                this.ecl.equals("gry")|| this.ecl.equals("grn") || this.ecl.equals("hzl") || this.ecl.equals("oth")))
                && (this.pid != null && this.pid.length() == 9 );
    }

    public int getByr() {
        return byr;
    }

    public void setByr(int byr) {
        this.byr = byr;
    }

    public int getIyr() {
        return iyr;
    }

    public void setIyr(int iyr) {
        this.iyr = iyr;
    }

    public int getEyr() {
        return eyr;
    }

    public void setEyr(int eyr) {
        this.eyr = eyr;
    }

    public String getHgt() {
        return hgt;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    public String getHcl() {
        return hcl;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    public String getEcl() {
        return ecl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "byr=" + byr +
                ", iyr=" + iyr +
                ", eyr=" + eyr +
                ", hgt='" + hgt + '\'' +
                ", hcl='" + hcl + '\'' +
                ", ecl='" + ecl + '\'' +
                ", pid=" + pid +
                ", cid=" + cid +
                '}';
    }
}
