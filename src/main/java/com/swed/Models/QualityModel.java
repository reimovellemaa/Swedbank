package com.swed.Models;

public class QualityModel {

		private int measure_amt;
		private String measure_date;
		private String qualityMetricTypeName;
		private String validationQualityRuleComment;
		private String service_main_group_name;
		private String quality_metric_categ;
		private String qualityMetricTypeComment;
		private String country;
		private int fact_row;
		private int fact_col;
		
		
	public void setFactCol(int value){
			
			this.fact_col=value;
		}
		
		public int getFactCol(){
			
			return this.fact_col;
		}
		
		
		
		
		public void setFactRow(int value){
			
			this.fact_row=value;
		}
		
		public int getFactRow(){
			
			return this.fact_row;
		}
		
		 public void setCountry(String value){
				
				this.country=value;
			}
					
			public String getCountry(){
						
				return this.country;
			}
		
		public void setQualityMetricCateg(String value){
			
			this.quality_metric_categ=value;
		}
		
		public String getQualityMetricCateg(){
			
			return this.quality_metric_categ;
		}
		
		
		public void setServiceMainGroupName(String value){
			
			this.service_main_group_name=value;
		}
		
		public String getServiceMainGroupName(){
			
			return this. service_main_group_name;
		}
		
		public void setDate(String value){
			
			this.measure_date=value;
		}
		
		public String getDate(){
			
			return this. measure_date;
		}
		
		public void setMeasureAmt(int value){
			
			this.measure_amt=value;
		}
		public int getMeasureAmt(){
			
			return this.measure_amt;
		}
		
	    public void setValidationRuleComment(String value){
			
			 this.validationQualityRuleComment=value;
		}
		
		public String getValidationRuleComment(){
			
			return this.validationQualityRuleComment;
		}
		public void setQualityMetricType(String value){
			this.qualityMetricTypeName=value;
		}
		public String getQualityMetricType(){
			return this.qualityMetricTypeName;
		}
		public void setQualityMetricTypeComment(String value){
			this.qualityMetricTypeComment=value;
		}
		public String getQualityMetricTypeComment(){
			return this.qualityMetricTypeComment;
		}

}
