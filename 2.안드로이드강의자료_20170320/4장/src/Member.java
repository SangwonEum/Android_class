package com.ch4a.ex2;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable{
	private String name;
	private String address;
	private int age;
	
	
	public Member(){
		
	}
	public Member(Parcel src){
		this.name=src.readString();
		this.address=src.readString();
		this.age=src.readInt();
	}
	
	public static final Parcelable.Creator CREATOR=new Parcelable.Creator() {
		public Member createFromParcel(Parcel in){
			return new Member(in);
		}

		@Override
		public Member[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Member[size];
		}
	};
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.name);
		dest.writeString(this.address);
		dest.writeInt(this.age);
		
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
