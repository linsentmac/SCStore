package com.lenovo.smartShop.bean;

import java.util.List;

/**
 * Created by linsen3 on 2017/9/6.
 */

public class AppDetailInfoBean {
    /**
     * success : true
     * code : null
     * msg : null
     * data : {"appInfo":{"packageName":"com.nba.qiangwangzhiwang","highQualityTag":0,"noAd":0,"hasGameGift":0,"versioncode":"6","risky":0,"averageStar":3.6,"network_identity":"单机","authorProNum":522,"lStateText":null,"paymentDesc":"无额外收费","description":"2012年又一款射击过关类的游戏大作,精美的画面,简单的操作,耐玩的游戏关卡,让您爱不释手.干掉一切阻挡我的敌人,没有人能阻挡我的枪,因为我是枪王之王.","isPrivilege":0,"outUrl":"leapp://ptn/specialcategory.do?apptypeid=2385&code=2385&name=%E5%B0%84%E5%87%BB%E6%B8%B8%E6%88%8F&tagid=-1","points":0,"iconAddr":"http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/1195-2016-03-01041948-1456820388866.png?isCompress=true&width=75&height=75&quantity=0.8","publishDate":1456827305000,"hasActivity":0,"gradeCount":54,"crack":0,"downloadCount":"152万次安装","commentsNum":0,"btnColor":"","targetUrl":"","size":8480966,"discount":"1","price":"0","safeCertification":1,"bigCategory":1,"hState":"0","snapList":[{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/7891-2016-03-01041948-1456820388205.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/4018-2016-03-01041948-1456820388691.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null}],"realDownCount":1515315,"overflowPrice":null,"hasStrategy":0,"smsSupport":null,"chinesize":0,"hasSubscribe":0,"apkSize":8867289,"hasGameCard":1,"fSnapList":[{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/7891-2016-03-01041948-1456820388205.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/4018-2016-03-01041948-1456820388691.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null}],"sharepoints":"0","apkmd5":"61465A4728BB949645C8703C4C80C8A3","appTypeCode":"yx_ycyx","typeInfoId":2385,"version":"1.0.5","tipContent":"","develperId":"211921","hasInnerPay":0,"vState":null,"hasAd":1,"vcNum":1,"fState":null,"name":"枪王之王","compatible":"1","privilege_url":"","developerName":"apps","unDownloadable":0,"compatibleDesc":"兼容本机","advertiseDesc":"有广告","definition":"","oState":null,"credibleFlag":0,"minSdk":"5","dangerousDesc":"","shortDesc":"一款射击过关类游戏","lState":"0","dangerous":0,"lcaid":20279961,"typeName":"射击游戏","updateDesc":"","tipTitle":"","operatorDesc":"","buttonCaption":"","audited":1,"ispay":"0","commonDesc":"","appDownloadAdr":"http://ams.lenovomm.com/ams/3.0/appdownaddress4thirdparty.do?l=zh-CN&pn=com.nba.qiangwangzhiwang&vc=6&wr=0&dp=0&ty=2&ept=0&forceFreeDownFlag=0"}}
     */

    private boolean success;
    private Object code;
    private Object msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appInfo : {"packageName":"com.nba.qiangwangzhiwang","highQualityTag":0,"noAd":0,"hasGameGift":0,"versioncode":"6","risky":0,"averageStar":3.6,"network_identity":"单机","authorProNum":522,"lStateText":null,"paymentDesc":"无额外收费","description":"2012年又一款射击过关类的游戏大作,精美的画面,简单的操作,耐玩的游戏关卡,让您爱不释手.干掉一切阻挡我的敌人,没有人能阻挡我的枪,因为我是枪王之王.","isPrivilege":0,"outUrl":"leapp://ptn/specialcategory.do?apptypeid=2385&code=2385&name=%E5%B0%84%E5%87%BB%E6%B8%B8%E6%88%8F&tagid=-1","points":0,"iconAddr":"http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/1195-2016-03-01041948-1456820388866.png?isCompress=true&width=75&height=75&quantity=0.8","publishDate":1456827305000,"hasActivity":0,"gradeCount":54,"crack":0,"downloadCount":"152万次安装","commentsNum":0,"btnColor":"","targetUrl":"","size":8480966,"discount":"1","price":"0","safeCertification":1,"bigCategory":1,"hState":"0","snapList":[{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/7891-2016-03-01041948-1456820388205.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/4018-2016-03-01041948-1456820388691.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null}],"realDownCount":1515315,"overflowPrice":null,"hasStrategy":0,"smsSupport":null,"chinesize":0,"hasSubscribe":0,"apkSize":8867289,"hasGameCard":1,"fSnapList":[{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/7891-2016-03-01041948-1456820388205.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/4018-2016-03-01041948-1456820388691.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null}],"sharepoints":"0","apkmd5":"61465A4728BB949645C8703C4C80C8A3","appTypeCode":"yx_ycyx","typeInfoId":2385,"version":"1.0.5","tipContent":"","develperId":"211921","hasInnerPay":0,"vState":null,"hasAd":1,"vcNum":1,"fState":null,"name":"枪王之王","compatible":"1","privilege_url":"","developerName":"apps","unDownloadable":0,"compatibleDesc":"兼容本机","advertiseDesc":"有广告","definition":"","oState":null,"credibleFlag":0,"minSdk":"5","dangerousDesc":"","shortDesc":"一款射击过关类游戏","lState":"0","dangerous":0,"lcaid":20279961,"typeName":"射击游戏","updateDesc":"","tipTitle":"","operatorDesc":"","buttonCaption":"","audited":1,"ispay":"0","commonDesc":"","appDownloadAdr":"http://ams.lenovomm.com/ams/3.0/appdownaddress4thirdparty.do?l=zh-CN&pn=com.nba.qiangwangzhiwang&vc=6&wr=0&dp=0&ty=2&ept=0&forceFreeDownFlag=0"}
         */

        private AppInfoBean appInfo;

        public AppInfoBean getAppInfo() {
            return appInfo;
        }

        public void setAppInfo(AppInfoBean appInfo) {
            this.appInfo = appInfo;
        }

        public static class AppInfoBean {
            /**
             * packageName : com.nba.qiangwangzhiwang
             * highQualityTag : 0
             * noAd : 0
             * hasGameGift : 0
             * versioncode : 6
             * risky : 0
             * averageStar : 3.6
             * network_identity : 单机
             * authorProNum : 522
             * lStateText : null
             * paymentDesc : 无额外收费
             * description : 2012年又一款射击过关类的游戏大作,精美的画面,简单的操作,耐玩的游戏关卡,让您爱不释手.干掉一切阻挡我的敌人,没有人能阻挡我的枪,因为我是枪王之王.
             * isPrivilege : 0
             * outUrl : leapp://ptn/specialcategory.do?apptypeid=2385&code=2385&name=%E5%B0%84%E5%87%BB%E6%B8%B8%E6%88%8F&tagid=-1
             * points : 0
             * iconAddr : http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/1195-2016-03-01041948-1456820388866.png?isCompress=true&width=75&height=75&quantity=0.8
             * publishDate : 1456827305000
             * hasActivity : 0
             * gradeCount : 54
             * crack : 0
             * downloadCount : 152万次安装
             * commentsNum : 0
             * btnColor :
             * targetUrl :
             * size : 8480966
             * discount : 1
             * price : 0
             * safeCertification : 1
             * bigCategory : 1
             * hState : 0
             * snapList : [{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/7891-2016-03-01041948-1456820388205.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/4018-2016-03-01041948-1456820388691.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true","DEVICE_RESOLUTION":null}]
             * realDownCount : 1515315
             * overflowPrice : null
             * hasStrategy : 0
             * smsSupport : null
             * chinesize : 0
             * hasSubscribe : 0
             * apkSize : 8867289
             * hasGameCard : 1
             * fSnapList : [{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/7891-2016-03-01041948-1456820388205.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null},{"APPIMG_PATH":"http://img.lenovomm.com/s3/img/app/app-img-lestore/4018-2016-03-01041948-1456820388691.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true","DEVICE_RESOLUTION":null}]
             * sharepoints : 0
             * apkmd5 : 61465A4728BB949645C8703C4C80C8A3
             * appTypeCode : yx_ycyx
             * typeInfoId : 2385
             * version : 1.0.5
             * tipContent :
             * develperId : 211921
             * hasInnerPay : 0
             * vState : null
             * hasAd : 1
             * vcNum : 1
             * fState : null
             * name : 枪王之王
             * compatible : 1
             * privilege_url :
             * developerName : apps
             * unDownloadable : 0
             * compatibleDesc : 兼容本机
             * advertiseDesc : 有广告
             * definition :
             * oState : null
             * credibleFlag : 0
             * minSdk : 5
             * dangerousDesc :
             * shortDesc : 一款射击过关类游戏
             * lState : 0
             * dangerous : 0
             * lcaid : 20279961
             * typeName : 射击游戏
             * updateDesc :
             * tipTitle :
             * operatorDesc :
             * buttonCaption :
             * audited : 1
             * ispay : 0
             * commonDesc :
             * appDownloadAdr : http://ams.lenovomm.com/ams/3.0/appdownaddress4thirdparty.do?l=zh-CN&pn=com.nba.qiangwangzhiwang&vc=6&wr=0&dp=0&ty=2&ept=0&forceFreeDownFlag=0
             */

            private String packageName;
            private int highQualityTag;
            private int noAd;
            private int hasGameGift;
            private String versioncode;
            private int risky;
            private double averageStar;
            private String network_identity;
            private int authorProNum;
            private Object lStateText;
            private String paymentDesc;
            private String description;
            private int isPrivilege;
            private String outUrl;
            private int points;
            private String iconAddr;
            private long publishDate;
            private int hasActivity;
            private int gradeCount;
            private int crack;
            private String downloadCount;
            private int commentsNum;
            private String btnColor;
            private String targetUrl;
            private int size;
            private String discount;
            private String price;
            private int safeCertification;
            private int bigCategory;
            private String hState;
            private int realDownCount;
            private Object overflowPrice;
            private int hasStrategy;
            private Object smsSupport;
            private int chinesize;
            private int hasSubscribe;
            private int apkSize;
            private int hasGameCard;
            private String sharepoints;
            private String apkmd5;
            private String appTypeCode;
            private int typeInfoId;
            private String version;
            private String tipContent;
            private String develperId;
            private int hasInnerPay;
            private Object vState;
            private int hasAd;
            private int vcNum;
            private Object fState;
            private String name;
            private String compatible;
            private String privilege_url;
            private String developerName;
            private int unDownloadable;
            private String compatibleDesc;
            private String advertiseDesc;
            private String definition;
            private Object oState;
            private int credibleFlag;
            private String minSdk;
            private String dangerousDesc;
            private String shortDesc;
            private String lState;
            private int dangerous;
            private int lcaid;
            private String typeName;
            private String updateDesc;
            private String tipTitle;
            private String operatorDesc;
            private String buttonCaption;
            private int audited;
            private String ispay;
            private String commonDesc;
            private String appDownloadAdr;
            private List<SnapListBean> snapList;
            private List<FSnapListBean> fSnapList;

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

            public int getNoAd() {
                return noAd;
            }

            public void setNoAd(int noAd) {
                this.noAd = noAd;
            }

            public int getHasGameGift() {
                return hasGameGift;
            }

            public void setHasGameGift(int hasGameGift) {
                this.hasGameGift = hasGameGift;
            }

            public String getVersioncode() {
                return versioncode;
            }

            public void setVersioncode(String versioncode) {
                this.versioncode = versioncode;
            }

            public int getRisky() {
                return risky;
            }

            public void setRisky(int risky) {
                this.risky = risky;
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

            public int getAuthorProNum() {
                return authorProNum;
            }

            public void setAuthorProNum(int authorProNum) {
                this.authorProNum = authorProNum;
            }

            public Object getLStateText() {
                return lStateText;
            }

            public void setLStateText(Object lStateText) {
                this.lStateText = lStateText;
            }

            public String getPaymentDesc() {
                return paymentDesc;
            }

            public void setPaymentDesc(String paymentDesc) {
                this.paymentDesc = paymentDesc;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
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

            public int getGradeCount() {
                return gradeCount;
            }

            public void setGradeCount(int gradeCount) {
                this.gradeCount = gradeCount;
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

            public int getCommentsNum() {
                return commentsNum;
            }

            public void setCommentsNum(int commentsNum) {
                this.commentsNum = commentsNum;
            }

            public String getBtnColor() {
                return btnColor;
            }

            public void setBtnColor(String btnColor) {
                this.btnColor = btnColor;
            }

            public String getTargetUrl() {
                return targetUrl;
            }

            public void setTargetUrl(String targetUrl) {
                this.targetUrl = targetUrl;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getSafeCertification() {
                return safeCertification;
            }

            public void setSafeCertification(int safeCertification) {
                this.safeCertification = safeCertification;
            }

            public int getBigCategory() {
                return bigCategory;
            }

            public void setBigCategory(int bigCategory) {
                this.bigCategory = bigCategory;
            }

            public String getHState() {
                return hState;
            }

            public void setHState(String hState) {
                this.hState = hState;
            }

            public int getRealDownCount() {
                return realDownCount;
            }

            public void setRealDownCount(int realDownCount) {
                this.realDownCount = realDownCount;
            }

            public Object getOverflowPrice() {
                return overflowPrice;
            }

            public void setOverflowPrice(Object overflowPrice) {
                this.overflowPrice = overflowPrice;
            }

            public int getHasStrategy() {
                return hasStrategy;
            }

            public void setHasStrategy(int hasStrategy) {
                this.hasStrategy = hasStrategy;
            }

            public Object getSmsSupport() {
                return smsSupport;
            }

            public void setSmsSupport(Object smsSupport) {
                this.smsSupport = smsSupport;
            }

            public int getChinesize() {
                return chinesize;
            }

            public void setChinesize(int chinesize) {
                this.chinesize = chinesize;
            }

            public int getHasSubscribe() {
                return hasSubscribe;
            }

            public void setHasSubscribe(int hasSubscribe) {
                this.hasSubscribe = hasSubscribe;
            }

            public int getApkSize() {
                return apkSize;
            }

            public void setApkSize(int apkSize) {
                this.apkSize = apkSize;
            }

            public int getHasGameCard() {
                return hasGameCard;
            }

            public void setHasGameCard(int hasGameCard) {
                this.hasGameCard = hasGameCard;
            }

            public String getSharepoints() {
                return sharepoints;
            }

            public void setSharepoints(String sharepoints) {
                this.sharepoints = sharepoints;
            }

            public String getApkmd5() {
                return apkmd5;
            }

            public void setApkmd5(String apkmd5) {
                this.apkmd5 = apkmd5;
            }

            public String getAppTypeCode() {
                return appTypeCode;
            }

            public void setAppTypeCode(String appTypeCode) {
                this.appTypeCode = appTypeCode;
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

            public String getTipContent() {
                return tipContent;
            }

            public void setTipContent(String tipContent) {
                this.tipContent = tipContent;
            }

            public String getDevelperId() {
                return develperId;
            }

            public void setDevelperId(String develperId) {
                this.develperId = develperId;
            }

            public int getHasInnerPay() {
                return hasInnerPay;
            }

            public void setHasInnerPay(int hasInnerPay) {
                this.hasInnerPay = hasInnerPay;
            }

            public Object getVState() {
                return vState;
            }

            public void setVState(Object vState) {
                this.vState = vState;
            }

            public int getHasAd() {
                return hasAd;
            }

            public void setHasAd(int hasAd) {
                this.hasAd = hasAd;
            }

            public int getVcNum() {
                return vcNum;
            }

            public void setVcNum(int vcNum) {
                this.vcNum = vcNum;
            }

            public Object getFState() {
                return fState;
            }

            public void setFState(Object fState) {
                this.fState = fState;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCompatible() {
                return compatible;
            }

            public void setCompatible(String compatible) {
                this.compatible = compatible;
            }

            public String getPrivilege_url() {
                return privilege_url;
            }

            public void setPrivilege_url(String privilege_url) {
                this.privilege_url = privilege_url;
            }

            public String getDeveloperName() {
                return developerName;
            }

            public void setDeveloperName(String developerName) {
                this.developerName = developerName;
            }

            public int getUnDownloadable() {
                return unDownloadable;
            }

            public void setUnDownloadable(int unDownloadable) {
                this.unDownloadable = unDownloadable;
            }

            public String getCompatibleDesc() {
                return compatibleDesc;
            }

            public void setCompatibleDesc(String compatibleDesc) {
                this.compatibleDesc = compatibleDesc;
            }

            public String getAdvertiseDesc() {
                return advertiseDesc;
            }

            public void setAdvertiseDesc(String advertiseDesc) {
                this.advertiseDesc = advertiseDesc;
            }

            public String getDefinition() {
                return definition;
            }

            public void setDefinition(String definition) {
                this.definition = definition;
            }

            public Object getOState() {
                return oState;
            }

            public void setOState(Object oState) {
                this.oState = oState;
            }

            public int getCredibleFlag() {
                return credibleFlag;
            }

            public void setCredibleFlag(int credibleFlag) {
                this.credibleFlag = credibleFlag;
            }

            public String getMinSdk() {
                return minSdk;
            }

            public void setMinSdk(String minSdk) {
                this.minSdk = minSdk;
            }

            public String getDangerousDesc() {
                return dangerousDesc;
            }

            public void setDangerousDesc(String dangerousDesc) {
                this.dangerousDesc = dangerousDesc;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public String getLState() {
                return lState;
            }

            public void setLState(String lState) {
                this.lState = lState;
            }

            public int getDangerous() {
                return dangerous;
            }

            public void setDangerous(int dangerous) {
                this.dangerous = dangerous;
            }

            public int getLcaid() {
                return lcaid;
            }

            public void setLcaid(int lcaid) {
                this.lcaid = lcaid;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getUpdateDesc() {
                return updateDesc;
            }

            public void setUpdateDesc(String updateDesc) {
                this.updateDesc = updateDesc;
            }

            public String getTipTitle() {
                return tipTitle;
            }

            public void setTipTitle(String tipTitle) {
                this.tipTitle = tipTitle;
            }

            public String getOperatorDesc() {
                return operatorDesc;
            }

            public void setOperatorDesc(String operatorDesc) {
                this.operatorDesc = operatorDesc;
            }

            public String getButtonCaption() {
                return buttonCaption;
            }

            public void setButtonCaption(String buttonCaption) {
                this.buttonCaption = buttonCaption;
            }

            public int getAudited() {
                return audited;
            }

            public void setAudited(int audited) {
                this.audited = audited;
            }

            public String getIspay() {
                return ispay;
            }

            public void setIspay(String ispay) {
                this.ispay = ispay;
            }

            public String getCommonDesc() {
                return commonDesc;
            }

            public void setCommonDesc(String commonDesc) {
                this.commonDesc = commonDesc;
            }

            public String getAppDownloadAdr() {
                return appDownloadAdr;
            }

            public void setAppDownloadAdr(String appDownloadAdr) {
                this.appDownloadAdr = appDownloadAdr;
            }

            public List<SnapListBean> getSnapList() {
                return snapList;
            }

            public void setSnapList(List<SnapListBean> snapList) {
                this.snapList = snapList;
            }

            public List<FSnapListBean> getFSnapList() {
                return fSnapList;
            }

            public void setFSnapList(List<FSnapListBean> fSnapList) {
                this.fSnapList = fSnapList;
            }

            public static class SnapListBean {
                /**
                 * APPIMG_PATH : http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=163&height=271&quantity=0.4&rotate=true
                 * DEVICE_RESOLUTION : null
                 */

                private String APPIMG_PATH;
                private Object DEVICE_RESOLUTION;

                public String getAPPIMG_PATH() {
                    return APPIMG_PATH;
                }

                public void setAPPIMG_PATH(String APPIMG_PATH) {
                    this.APPIMG_PATH = APPIMG_PATH;
                }

                public Object getDEVICE_RESOLUTION() {
                    return DEVICE_RESOLUTION;
                }

                public void setDEVICE_RESOLUTION(Object DEVICE_RESOLUTION) {
                    this.DEVICE_RESOLUTION = DEVICE_RESOLUTION;
                }
            }

            public static class FSnapListBean {
                /**
                 * APPIMG_PATH : http://img.lenovomm.com/s3/img/app/app-img-lestore/1194-2016-03-01041948-1456820388013.jpg?isCompress=true&width=480&height=800&quantity=0.8&rotate=true
                 * DEVICE_RESOLUTION : null
                 */

                private String APPIMG_PATH;
                private Object DEVICE_RESOLUTION;

                public String getAPPIMG_PATH() {
                    return APPIMG_PATH;
                }

                public void setAPPIMG_PATH(String APPIMG_PATH) {
                    this.APPIMG_PATH = APPIMG_PATH;
                }

                public Object getDEVICE_RESOLUTION() {
                    return DEVICE_RESOLUTION;
                }

                public void setDEVICE_RESOLUTION(Object DEVICE_RESOLUTION) {
                    this.DEVICE_RESOLUTION = DEVICE_RESOLUTION;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "AppDetailInfoBean{" +
                "success=" + success +
                ", code=" + code +
                ", msg=" + msg +
                ", data=" + data +
                '}';
    }
}
