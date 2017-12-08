package com.lenovo.smartShop.bean;

import java.util.List;

/**
 * Created by linsen3 on 2017/9/5.
 */

public class AppListBean {
    /**
     * data : {"allcount":2,"datalist":[{"developerId":174711,"packageName":"com.gotokeep.keep","highQualityTag":1,"hasGameGift":0,"noAd":0,"appid":16248922,"versioncode":"2951","averageStar":4.3,"network_identity":"","typeInfoId":2461,"version":"3.4.1","hasInnerPay":0,"paymentDesc":"无额外收费","vState":0,"hasAd":0,"isPrivilege":0,"outUrl":"leapp://ptn/specialcategory.do?apptypeid=2461&code=2461&name=%E8%BF%90%E5%8A%A8.%E5%81%A5%E8%BA%AB&tagid=-1","compatible":"","fState":0,"name":"Keep","developerName":"北京卡路里科技有限公司","apptype":"生活","compatibleDesc":"","iconAddr":"http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/0581-2016-05-24035120-1464076280609.png?isCompress=true&width=75&height=75&quantity=0.8","publishDate":1465289783000,"hasActivity":0,"advertiseDesc":"无广告","oState":0,"definition":null,"credibleFlag":0,"crack":0,"downloadCount":"373万次安装","lState":0,"shortDesc":"健身、跑步、骑行、计步","discount":1,"size":51839233,"typeName":"运动.健身","safeCertification":1,"price":0,"bigCategory":2,"hState":0,"hasStrategy":0,"chinesize":0,"ispay":0},{"developerId":106298,"packageName":"com.sythealth.fitness","highQualityTag":0,"hasGameGift":0,"noAd":0,"appid":12446944,"versioncode":"78","averageStar":4.5,"network_identity":"","typeInfoId":2463,"version":"5.4.4","hasInnerPay":0,"paymentDesc":"无额外收费","vState":0,"hasAd":0,"isPrivilege":0,"outUrl":"leapp://ptn/specialcategory.do?apptypeid=2463&code=2463&name=%E5%8C%BB%E7%96%97.%E5%81%A5%E5%BA%B7&tagid=-1","compatible":"","fState":0,"name":"轻加","developerName":"珠海三益堂科技有限公司","apptype":"生活","compatibleDesc":"","iconAddr":"http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/4787-2016-04-29111739-1461899859970.jpg?isCompress=true&width=75&height=75&quantity=0.8","publishDate":1479280846000,"hasActivity":0,"advertiseDesc":"无广告","oState":0,"definition":null,"credibleFlag":0,"crack":0,"downloadCount":"79万次安装","lState":0,"shortDesc":"轻加 ── 移动减肥专家  适合您的运动饮食方案都在这里","discount":1,"size":28476690,"typeName":"医疗.健康","safeCertification":1,"price":0,"bigCategory":2,"hState":0,"hasStrategy":0,"chinesize":0,"ispay":0}],"c":20,"endpage":1,"code":"22680","datatype":"applist","si":1}
     * code : null
     * msg : null
     * success : true
     */

    private DataBean data;
    private Object code;
    private Object msg;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * allcount : 2
         * datalist : [{"developerId":174711,"packageName":"com.gotokeep.keep","highQualityTag":1,"hasGameGift":0,"noAd":0,"appid":16248922,"versioncode":"2951","averageStar":4.3,"network_identity":"","typeInfoId":2461,"version":"3.4.1","hasInnerPay":0,"paymentDesc":"无额外收费","vState":0,"hasAd":0,"isPrivilege":0,"outUrl":"leapp://ptn/specialcategory.do?apptypeid=2461&code=2461&name=%E8%BF%90%E5%8A%A8.%E5%81%A5%E8%BA%AB&tagid=-1","compatible":"","fState":0,"name":"Keep","developerName":"北京卡路里科技有限公司","apptype":"生活","compatibleDesc":"","iconAddr":"http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/0581-2016-05-24035120-1464076280609.png?isCompress=true&width=75&height=75&quantity=0.8","publishDate":1465289783000,"hasActivity":0,"advertiseDesc":"无广告","oState":0,"definition":null,"credibleFlag":0,"crack":0,"downloadCount":"373万次安装","lState":0,"shortDesc":"健身、跑步、骑行、计步","discount":1,"size":51839233,"typeName":"运动.健身","safeCertification":1,"price":0,"bigCategory":2,"hState":0,"hasStrategy":0,"chinesize":0,"ispay":0},{"developerId":106298,"packageName":"com.sythealth.fitness","highQualityTag":0,"hasGameGift":0,"noAd":0,"appid":12446944,"versioncode":"78","averageStar":4.5,"network_identity":"","typeInfoId":2463,"version":"5.4.4","hasInnerPay":0,"paymentDesc":"无额外收费","vState":0,"hasAd":0,"isPrivilege":0,"outUrl":"leapp://ptn/specialcategory.do?apptypeid=2463&code=2463&name=%E5%8C%BB%E7%96%97.%E5%81%A5%E5%BA%B7&tagid=-1","compatible":"","fState":0,"name":"轻加","developerName":"珠海三益堂科技有限公司","apptype":"生活","compatibleDesc":"","iconAddr":"http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/4787-2016-04-29111739-1461899859970.jpg?isCompress=true&width=75&height=75&quantity=0.8","publishDate":1479280846000,"hasActivity":0,"advertiseDesc":"无广告","oState":0,"definition":null,"credibleFlag":0,"crack":0,"downloadCount":"79万次安装","lState":0,"shortDesc":"轻加 ── 移动减肥专家  适合您的运动饮食方案都在这里","discount":1,"size":28476690,"typeName":"医疗.健康","safeCertification":1,"price":0,"bigCategory":2,"hState":0,"hasStrategy":0,"chinesize":0,"ispay":0}]
         * c : 20
         * endpage : 1
         * code : 22680
         * datatype : applist
         * si : 1
         */

        private int allcount;
        private int c;
        private int endpage;
        private String code;
        private String datatype;
        private int si;
        private List<DatalistBean> datalist;

        public int getAllcount() {
            return allcount;
        }

        public void setAllcount(int allcount) {
            this.allcount = allcount;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public int getEndpage() {
            return endpage;
        }

        public void setEndpage(int endpage) {
            this.endpage = endpage;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDatatype() {
            return datatype;
        }

        public void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        public int getSi() {
            return si;
        }

        public void setSi(int si) {
            this.si = si;
        }

        public List<DatalistBean> getDatalist() {
            return datalist;
        }

        public void setDatalist(List<DatalistBean> datalist) {
            this.datalist = datalist;
        }

        public static class DatalistBean {
            /**
             * developerId : 174711
             * packageName : com.gotokeep.keep
             * highQualityTag : 1
             * hasGameGift : 0
             * noAd : 0
             * appid : 16248922
             * versioncode : 2951
             * averageStar : 4.3
             * network_identity :
             * typeInfoId : 2461
             * version : 3.4.1
             * hasInnerPay : 0
             * paymentDesc : 无额外收费
             * vState : 0
             * hasAd : 0
             * isPrivilege : 0
             * outUrl : leapp://ptn/specialcategory.do?apptypeid=2461&code=2461&name=%E8%BF%90%E5%8A%A8.%E5%81%A5%E8%BA%AB&tagid=-1
             * compatible :
             * fState : 0
             * name : Keep
             * developerName : 北京卡路里科技有限公司
             * apptype : 生活
             * compatibleDesc :
             * iconAddr : http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/0581-2016-05-24035120-1464076280609.png?isCompress=true&width=75&height=75&quantity=0.8
             * publishDate : 1465289783000
             * hasActivity : 0
             * advertiseDesc : 无广告
             * oState : 0
             * definition : null
             * credibleFlag : 0
             * crack : 0
             * downloadCount : 373万次安装
             * lState : 0
             * shortDesc : 健身、跑步、骑行、计步
             * discount : 1
             * size : 51839233
             * typeName : 运动.健身
             * safeCertification : 1
             * price : 0
             * bigCategory : 2
             * hState : 0
             * hasStrategy : 0
             * chinesize : 0
             * ispay : 0
             */

            private int developerId;
            private String packageName;
            private int highQualityTag;
            private int hasGameGift;
            private int noAd;
            private int appid;
            private String versioncode;
            private double averageStar;
            private String network_identity;
            private int typeInfoId;
            private String version;
            private int hasInnerPay;
            private String paymentDesc;
            private int vState;
            private int hasAd;
            private int isPrivilege;
            private String outUrl;
            private String compatible;
            private int fState;
            private String name;
            private String developerName;
            private String apptype;
            private String compatibleDesc;
            private String iconAddr;
            private long publishDate;
            private int hasActivity;
            private String advertiseDesc;
            private int oState;
            private Object definition;
            private int credibleFlag;
            private int crack;
            private String downloadCount;
            private int lState;
            private String shortDesc;
            private int discount;
            private int size;
            private String typeName;
            private int safeCertification;
            private int price;
            private int bigCategory;
            private int hState;
            private int hasStrategy;
            private int chinesize;
            private int ispay;

            public int getDeveloperId() {
                return developerId;
            }

            public void setDeveloperId(int developerId) {
                this.developerId = developerId;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public int getHighQualityTag() {
                return highQualityTag;
            }

            public void setHighQualityTag(int highQualityTag) {
                this.highQualityTag = highQualityTag;
            }

            public int getHasGameGift() {
                return hasGameGift;
            }

            public void setHasGameGift(int hasGameGift) {
                this.hasGameGift = hasGameGift;
            }

            public int getNoAd() {
                return noAd;
            }

            public void setNoAd(int noAd) {
                this.noAd = noAd;
            }

            public int getAppid() {
                return appid;
            }

            public void setAppid(int appid) {
                this.appid = appid;
            }

            public String getVersioncode() {
                return versioncode;
            }

            public void setVersioncode(String versioncode) {
                this.versioncode = versioncode;
            }

            public double getAverageStar() {
                return averageStar;
            }

            public void setAverageStar(double averageStar) {
                this.averageStar = averageStar;
            }

            public String getNetwork_identity() {
                return network_identity;
            }

            public void setNetwork_identity(String network_identity) {
                this.network_identity = network_identity;
            }

            public int getTypeInfoId() {
                return typeInfoId;
            }

            public void setTypeInfoId(int typeInfoId) {
                this.typeInfoId = typeInfoId;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public int getHasInnerPay() {
                return hasInnerPay;
            }

            public void setHasInnerPay(int hasInnerPay) {
                this.hasInnerPay = hasInnerPay;
            }

            public String getPaymentDesc() {
                return paymentDesc;
            }

            public void setPaymentDesc(String paymentDesc) {
                this.paymentDesc = paymentDesc;
            }

            public int getVState() {
                return vState;
            }

            public void setVState(int vState) {
                this.vState = vState;
            }

            public int getHasAd() {
                return hasAd;
            }

            public void setHasAd(int hasAd) {
                this.hasAd = hasAd;
            }

            public int getIsPrivilege() {
                return isPrivilege;
            }

            public void setIsPrivilege(int isPrivilege) {
                this.isPrivilege = isPrivilege;
            }

            public String getOutUrl() {
                return outUrl;
            }

            public void setOutUrl(String outUrl) {
                this.outUrl = outUrl;
            }

            public String getCompatible() {
                return compatible;
            }

            public void setCompatible(String compatible) {
                this.compatible = compatible;
            }

            public int getFState() {
                return fState;
            }

            public void setFState(int fState) {
                this.fState = fState;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDeveloperName() {
                return developerName;
            }

            public void setDeveloperName(String developerName) {
                this.developerName = developerName;
            }

            public String getApptype() {
                return apptype;
            }

            public void setApptype(String apptype) {
                this.apptype = apptype;
            }

            public String getCompatibleDesc() {
                return compatibleDesc;
            }

            public void setCompatibleDesc(String compatibleDesc) {
                this.compatibleDesc = compatibleDesc;
            }

            public String getIconAddr() {
                return iconAddr;
            }

            public void setIconAddr(String iconAddr) {
                this.iconAddr = iconAddr;
            }

            public long getPublishDate() {
                return publishDate;
            }

            public void setPublishDate(long publishDate) {
                this.publishDate = publishDate;
            }

            public int getHasActivity() {
                return hasActivity;
            }

            public void setHasActivity(int hasActivity) {
                this.hasActivity = hasActivity;
            }

            public String getAdvertiseDesc() {
                return advertiseDesc;
            }

            public void setAdvertiseDesc(String advertiseDesc) {
                this.advertiseDesc = advertiseDesc;
            }

            public int getOState() {
                return oState;
            }

            public void setOState(int oState) {
                this.oState = oState;
            }

            public Object getDefinition() {
                return definition;
            }

            public void setDefinition(Object definition) {
                this.definition = definition;
            }

            public int getCredibleFlag() {
                return credibleFlag;
            }

            public void setCredibleFlag(int credibleFlag) {
                this.credibleFlag = credibleFlag;
            }

            public int getCrack() {
                return crack;
            }

            public void setCrack(int crack) {
                this.crack = crack;
            }

            public String getDownloadCount() {
                return downloadCount;
            }

            public void setDownloadCount(String downloadCount) {
                this.downloadCount = downloadCount;
            }

            public int getLState() {
                return lState;
            }

            public void setLState(int lState) {
                this.lState = lState;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public int getSafeCertification() {
                return safeCertification;
            }

            public void setSafeCertification(int safeCertification) {
                this.safeCertification = safeCertification;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getBigCategory() {
                return bigCategory;
            }

            public void setBigCategory(int bigCategory) {
                this.bigCategory = bigCategory;
            }

            public int getHState() {
                return hState;
            }

            public void setHState(int hState) {
                this.hState = hState;
            }

            public int getHasStrategy() {
                return hasStrategy;
            }

            public void setHasStrategy(int hasStrategy) {
                this.hasStrategy = hasStrategy;
            }

            public int getChinesize() {
                return chinesize;
            }

            public void setChinesize(int chinesize) {
                this.chinesize = chinesize;
            }

            public int getIspay() {
                return ispay;
            }

            public void setIspay(int ispay) {
                this.ispay = ispay;
            }
        }
    }

    @Override
    public String toString() {
        return "AppListBean{" +
                "data=" + data +
                ", code=" + code +
                ", msg=" + msg +
                ", success=" + success +
                '}';
    }
}
