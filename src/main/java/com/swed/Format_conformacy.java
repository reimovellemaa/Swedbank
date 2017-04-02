package com.swed;

public class Format_conformacy {
	 public Format_conformacy() {}
	 private int kpi=0;
	 private String serviceName="";
	 private String validationRuleComment="";

     public int getKPI() {
         return this.kpi;
     }
     public void setKPI(int value) {
         this.kpi=value;
     }
     public String getServiceName(){
    	 return this.serviceName;
     }
     public void setServiceName(String value){
    	 this.serviceName=value;
     }
     public String getValidationRule(){
    	 return this.validationRuleComment;
     }
     public void setValidationRule(String value){
    	 this.validationRuleComment=value;
     }
	
	
}
