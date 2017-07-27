package com.ch1.ex3;

import android.os.Parcel;
import android.os.Parcelable;

public class ImgInfo implements Parcelable{
	private String title;
	private String imageSrc;
	
	public ImgInfo(){
		
	}
	public ImgInfo(Parcel src){
		this.title=src.readString();
		this.imageSrc=src.readString();
	}
	
	public static final Parcelable.Creator CREATOR=new Parcelable.Creator() {
		public ImgInfo createFromParcel(Parcel in){
			return new ImgInfo(in);
		}

		@Override
		public ImgInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ImgInfo[size];
		}
	};
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.title);
		dest.writeString(this.imageSrc);
		
	}
	
	
	

}
