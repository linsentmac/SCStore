package com.lenovo.smartShop.bean;

import java.util.List;

/**
 * Created by linsen3 on 2017/9/6.
 */

public class AppDownLoadBean {

    /**
     * data : {"app_versioncode":"2951","MD5":"FCEC62DD590747C5AD54CB014C96A1F0","isSmart":0,"app_version":"3.4.1","app_versionname":"2951","downurls":[{"appMD5":"FCEC62DD590747C5AD54CB014C96A1F0","downurl":"https://apk1s.lenovomm.cn/a6bfd08e1c8819a7856d7a1ab1d89e3f/59ae56ac/dlserver/fileman/s3/apk/app/app-apk-lestore/4471-2016-06-07035814-1465286294088.apk?v=5&order=1&uuid=8090a62e4679425b9c8b14359b5b977d&cMD5=false&sorder=0&group=","appSize":55208060,"path":null,"downurls":[]}],"lState":0,"icon_addr":"http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/0581-2016-05-24035120-1464076280609.png","app_name":"Keep","signatureMd5":"98B91A0E409BAA15A2992B829B024A42","clientPackageName":null,"dar":1,"price":"0","vState":0,"s":"J19fo/unvFszQKQLgL0Oql8K0QPYYyb78I9mlo1aBNRJg71Zq1LJSMr8PZY38VXvNS/Tv8OdlcSJA68MNyvYcQ==","app_size":55208060,"hState":0,"app_package_name":"com.gotokeep.keep","fState":0,"developerName":"北京卡路里科技有限公司","downurl":"http://apk.lenovomm.com/201709051547/92cbe7a5526ca3717649235b46c48d04/dlserver/fileman/s3/apk/app/app-apk-lestore/4471-2016-06-07035814-1465286294088.apk?v=5&order=0&uuid=c820c1bf6e6d46f088c1b1c3ece1032a&cMD5=false&sorder=1&group=","isComputeMD5":false}
     * success : true
     */

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * app_versioncode : 2951
         * MD5 : FCEC62DD590747C5AD54CB014C96A1F0
         * isSmart : 0
         * app_version : 3.4.1
         * app_versionname : 2951
         * downurls : [{"appMD5":"FCEC62DD590747C5AD54CB014C96A1F0","downurl":"https://apk1s.lenovomm.cn/a6bfd08e1c8819a7856d7a1ab1d89e3f/59ae56ac/dlserver/fileman/s3/apk/app/app-apk-lestore/4471-2016-06-07035814-1465286294088.apk?v=5&order=1&uuid=8090a62e4679425b9c8b14359b5b977d&cMD5=false&sorder=0&group=","appSize":55208060,"path":null,"downurls":[]}]
         * lState : 0
         * icon_addr : http://img.lenovomm.com/s3/img/icon/app/app-img-lestore/0581-2016-05-24035120-1464076280609.png
         * app_name : Keep
         * signatureMd5 : 98B91A0E409BAA15A2992B829B024A42
         * clientPackageName : null
         * dar : 1
         * price : 0
         * vState : 0
         * s : J19fo/unvFszQKQLgL0Oql8K0QPYYyb78I9mlo1aBNRJg71Zq1LJSMr8PZY38VXvNS/Tv8OdlcSJA68MNyvYcQ==
         * app_size : 55208060
         * hState : 0
         * app_package_name : com.gotokeep.keep
         * fState : 0
         * developerName : 北京卡路里科技有限公司
         * downurl : http://apk.lenovomm.com/201709051547/92cbe7a5526ca3717649235b46c48d04/dlserver/fileman/s3/apk/app/app-apk-lestore/4471-2016-06-07035814-1465286294088.apk?v=5&order=0&uuid=c820c1bf6e6d46f088c1b1c3ece1032a&cMD5=false&sorder=1&group=
         * isComputeMD5 : false
         */

        private String app_versioncode;
        private String MD5;
        private int isSmart;
        private String app_version;
        private String app_versionname;
        private int lState;
        private String icon_addr;
        private String app_name;
        private String signatureMd5;
        private Object clientPackageName;
        private int dar;
        private String price;
        private int vState;
        private String s;
        private int app_size;
        private int hState;
        private String app_package_name;
        private int fState;
        private String developerName;
        private String downurl;
        private boolean isComputeMD5;
        private List<DownurlsBean> downurls;

        public String getApp_versioncode() {
            return app_versioncode;
        }

        public void setApp_versioncode(String app_versioncode) {
            this.app_versioncode = app_versioncode;
        }

        public String getMD5() {
            return MD5;
        }

        public void setMD5(String MD5) {
            this.MD5 = MD5;
        }

        public int getIsSmart() {
            return isSmart;
        }

        public void setIsSmart(int isSmart) {
            this.isSmart = isSmart;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getApp_versionname() {
            return app_versionname;
        }

        public void setApp_versionname(String app_versionname) {
            this.app_versionname = app_versionname;
        }

        public int getLState() {
            return lState;
        }

        public void setLState(int lState) {
            this.lState = lState;
        }

        public String getIcon_addr() {
            return icon_addr;
        }

        public void setIcon_addr(String icon_addr) {
            this.icon_addr = icon_addr;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getSignatureMd5() {
            return signatureMd5;
        }

        public void setSignatureMd5(String signatureMd5) {
            this.signatureMd5 = signatureMd5;
        }

        public Object getClientPackageName() {
            return clientPackageName;
        }

        public void setClientPackageName(Object clientPackageName) {
            this.clientPackageName = clientPackageName;
        }

        public int getDar() {
            return dar;
        }

        public void setDar(int dar) {
            this.dar = dar;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getVState() {
            return vState;
        }

        public void setVState(int vState) {
            this.vState = vState;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public int getApp_size() {
            return app_size;
        }

        public void setApp_size(int app_size) {
            this.app_size = app_size;
        }

        public int getHState() {
            return hState;
        }

        public void setHState(int hState) {
            this.hState = hState;
        }

        public String getApp_package_name() {
            return app_package_name;
        }

        public void setApp_package_name(String app_package_name) {
            this.app_package_name = app_package_name;
        }

        public int getFState() {
            return fState;
        }

        public void setFState(int fState) {
            this.fState = fState;
        }

        public String getDeveloperName() {
            return developerName;
        }

        public void setDeveloperName(String developerName) {
            this.developerName = developerName;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }

        public boolean isIsComputeMD5() {
            return isComputeMD5;
        }

        public void setIsComputeMD5(boolean isComputeMD5) {
            this.isComputeMD5 = isComputeMD5;
        }

        public List<DownurlsBean> getDownurls() {
            return downurls;
        }

        public void setDownurls(List<DownurlsBean> downurls) {
            this.downurls = downurls;
        }

        public static class DownurlsBean {
            /**
             * appMD5 : FCEC62DD590747C5AD54CB014C96A1F0
             * downurl : https://apk1s.lenovomm.cn/a6bfd08e1c8819a7856d7a1ab1d89e3f/59ae56ac/dlserver/fileman/s3/apk/app/app-apk-lestore/4471-2016-06-07035814-1465286294088.apk?v=5&order=1&uuid=8090a62e4679425b9c8b14359b5b977d&cMD5=false&sorder=0&group=
             * appSize : 55208060
             * path : null
             * downurls : []
             */

            private String appMD5;
            private String downurl;
            private int appSize;
            private Object path;
            private List<?> downurls;

            public String getAppMD5() {
                return appMD5;
            }

            public void setAppMD5(String appMD5) {
                this.appMD5 = appMD5;
            }

            public String getDownurl() {
                return downurl;
            }

            public void setDownurl(String downurl) {
                this.downurl = downurl;
            }

            public int getAppSize() {
                return appSize;
            }

            public void setAppSize(int appSize) {
                this.appSize = appSize;
            }

            public Object getPath() {
                return path;
            }

            public void setPath(Object path) {
                this.path = path;
            }

            public List<?> getDownurls() {
                return downurls;
            }

            public void setDownurls(List<?> downurls) {
                this.downurls = downurls;
            }

            @Override
            public String toString() {
                return "DownurlsBean{" +
                        "appMD5='" + appMD5 + '\'' +
                        ", downurl='" + downurl + '\'' +
                        ", appSize=" + appSize +
                        ", path=" + path +
                        ", downurls=" + downurls +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "app_versioncode='" + app_versioncode + '\'' +
                    ", MD5='" + MD5 + '\'' +
                    ", isSmart=" + isSmart +
                    ", app_version='" + app_version + '\'' +
                    ", app_versionname='" + app_versionname + '\'' +
                    ", lState=" + lState +
                    ", icon_addr='" + icon_addr + '\'' +
                    ", app_name='" + app_name + '\'' +
                    ", signatureMd5='" + signatureMd5 + '\'' +
                    ", clientPackageName=" + clientPackageName +
                    ", dar=" + dar +
                    ", price='" + price + '\'' +
                    ", vState=" + vState +
                    ", s='" + s + '\'' +
                    ", app_size=" + app_size +
                    ", hState=" + hState +
                    ", app_package_name='" + app_package_name + '\'' +
                    ", fState=" + fState +
                    ", developerName='" + developerName + '\'' +
                    ", downurl='" + downurl + '\'' +
                    ", isComputeMD5=" + isComputeMD5 +
                    ", downurls=" + downurls.toString() +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppDownLoadBean{" +
                "data=" + data.toString() +
                ", success=" + success +
                '}';
    }
}
