package com.mytest.ch7a;

public class CultureVo {
	private String magage_Num;
	private String name_Kor;
	
	public CultureVo(String num,String name){
		this.magage_Num=num;
		this.name_Kor=name;
	}

	public String getMagage_Num() {
		return magage_Num;
	}

	public void setMagage_Num(String magage_Num) {
		this.magage_Num = magage_Num;
	}

	public String getName_Kor() {
		return name_Kor;
	}

	public void setName_Kor(String name_Kor) {
		this.name_Kor = name_Kor;
	}
	
	

}
