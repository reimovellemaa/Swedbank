package com.swed;
import java.sql.Date;

public class CompletenessModel {
		private int measure_amt;
		private String run_sttd;
		private String qualityMetricTypeName;
		
		public void setDate(String value){
			
			this.run_sttd=value;
		}
		
		public String getDate(){
			
			return this.run_sttd;
		}
		
		public void setMeasureAmt(int value){
			
			this.measure_amt=value;
		}
		public int getMeasureAmt(){
			
			return this.measure_amt;
		}
		public void setQualityMetricType(String value){
			this.qualityMetricTypeName=value;
		}
		public String getQualityMetricType(){
			return this.qualityMetricTypeName;
		}
}
