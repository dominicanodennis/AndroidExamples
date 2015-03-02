package de.nikolaipreugschat.adapters;

import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableScanresult implements Parcelable {
	
	ScanResult scanResult;
	
	public ParcelableScanresult(ScanResult scanResult) {
		this.scanResult = scanResult;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(scanResult);
	}
	
	public static final Parcelable.Creator<ParcelableScanresult> CREATOR = new Parcelable.Creator<ParcelableScanresult>() {

		@Override
		public ParcelableScanresult createFromParcel(Parcel source) {
			return new ParcelableScanresult(source);
		}

		@Override
		public ParcelableScanresult[] newArray(int size) {
			return new ParcelableScanresult[size];
		}
		
	};
	
	private ParcelableScanresult(Parcel in) {
		scanResult = (ScanResult) in.readValue(ClassLoader.getSystemClassLoader());
	}
	
	public ScanResult getResult() {
		return scanResult;
	}

}
