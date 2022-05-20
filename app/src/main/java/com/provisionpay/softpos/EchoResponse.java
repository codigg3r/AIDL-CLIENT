package com.provisionpay.softpos;

import android.os.Parcel;
import android.os.Parcelable;

public class EchoResponse implements Parcelable {
    private String versionName;
    private String versionCode;
    private String dtId;
    private String userHash;
    private String isTerminalReady;

    public EchoResponse(String versionName, String versionCode, String dtId, String userHash, String isTerminalReady) {
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.dtId = dtId;
        this.userHash = userHash;
        this.isTerminalReady = isTerminalReady;
    }

    protected EchoResponse(Parcel in) {
        versionName = in.readString();
        versionCode = in.readString();
        dtId = in.readString();
        userHash = in.readString();
        isTerminalReady = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(versionName);
        dest.writeString(versionCode);
        dest.writeString(dtId);
        dest.writeString(userHash);
        dest.writeString(isTerminalReady);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EchoResponse> CREATOR = new Creator<EchoResponse>() {
        @Override
        public EchoResponse createFromParcel(Parcel in) {
            return new EchoResponse(in);
        }

        @Override
        public EchoResponse[] newArray(int size) {
            return new EchoResponse[size];
        }
    };

    @Override
    public String toString() {
        return "EchoResponse{" +
                "versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", dtId='" + dtId + '\'' +
                ", userHash='" + userHash + '\'' +
                ", isTerminalReady='" + isTerminalReady + '\'' +
                '}';
    }
}