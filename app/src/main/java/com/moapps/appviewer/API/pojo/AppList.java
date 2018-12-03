package com.moapps.appviewer.API.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppList {

    @SerializedName("skip")
    public int skip ;
    @SerializedName("take")
    public int take;
    @SerializedName("osType")
    public int osType;
    @SerializedName("userToken")
    public String userToken;


    @SerializedName("data")
    public List<AppData> data = null;

    public class AppData {

        @SerializedName("applicationToken")
        public String applicationToken;
        @SerializedName("isPayment")
        public Boolean isPayment;
        @SerializedName("applicationStatus")
        public Boolean applicationStatus;
        @SerializedName("applicationName")
        public String applicationName;
        @SerializedName("endOfPayment")
        public String endOfPayment;
        @SerializedName("applicationIcoUrl")
        public String applicationIcoUrl;
        @SerializedName("applicationUrl")
        public String applicationUrl;

        public String getApplicationToken() {
            return applicationToken;
        }
        public Boolean getPayment() {
            return isPayment;
        }
        public Boolean getApplicationStatus() {
            return applicationStatus;
        }
        public String getApplicationName() {
            return applicationName;
        }
        public String getEndOfPayment() {
            return endOfPayment;
        }
        public String getApplicationIcoUrl() {
            return applicationIcoUrl;
        }
        public String getApplicationUrl() {
            return applicationUrl;
        }

    }

    public AppList(int skip, int take, int osType, String userToken) {
        this.skip = skip;
        this.take = take;
        this.osType = osType;
        this.userToken = userToken;
    }


}