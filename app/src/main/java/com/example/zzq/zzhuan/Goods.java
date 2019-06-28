package com.example.zzq.zzhuan;

public class Goods {
    private long gid;//商品标识
    private String gname;//商品名称
    private long uid;//用户标识
    private String Time;//发布时间
    private String detail;//详述
    private double price;//价格
    private String Image;//图片地址
    private int Scannum;//浏览人数

    public Goods(){}

    public long getGid() {
        return gid;
    }

    public void setGid(long gid
    ) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getScannum() {
        return Scannum;
    }

    public void setScannum(int scannum) {
        Scannum = scannum;
    }
}
