package com.search;
public class TopItem {
 
   /** Property business_id */
   String business_id;
 
   String[] topItems=null;
/** Property topItem_1 */
   String topItem_1=null;
 
   /** Property topItem_1_Count */
   int topItem_1_Count=0;
 
   /** Property topItem_2 */
   String topItem_2=null;
 
   /** Property topItem_2_Count */
   int topItem_2_Count=0;
 
   /** Property topItem_3 */
   String topItem_3=null;
 
   /** Property topItem_3_Count */
   int topItem_3_Count=0;
 
   /** Property topItem_4 */
   String topItem_4=null;
 
   /** Property topItem_4_Count */
   int topItem_4_Count=0;
 
   /** Property topItem_5 */
   String topItem_5=null;
 
   /** Property topItem_5_Count */
   int topItem_5_Count=0;
 
   /** Property business_name */
   String business_name;
 
   /**
    * Constructor
    */
   public TopItem(String business_id,String[] topItems) {
	   this.business_id=business_id;
	   this.business_name=business_name;
	   this.topItems=topItems;
	   
   }

   public String[] getTopItems() {
	return topItems;
   }

   public void setTopItems(String[] topItems) {
	this.topItems = topItems;
   }
   
   /**
    * Gets the business_id
    */
   public String getBusiness_id() {
      return this.business_id;
   }
 
   /**
    * Sets the business_id
    */
   public void setBusiness_id(String value) {
      this.business_id = value;
   }
 
   /**
    * Gets the topItem_1
    */
   public String getTopItem_1() {
      return this.topItem_1;
   }
 
   /**
    * Sets the topItem_1
    */
   public void setTopItem_1(String value) {
      this.topItem_1 = value;
   }
 
   /**
    * Gets the topItem_1_Count
    */
   public int getTopItem_1_Count() {
      return this.topItem_1_Count;
   }
 
   /**
    * Sets the topItem_1_Count
    */
   public void setTopItem_1_Count(int value) {
      this.topItem_1_Count = value;
   }
 
   /**
    * Gets the topItem_2
    */
   public String getTopItem_2() {
      return this.topItem_2;
   }
 
   /**
    * Sets the topItem_2
    */
   public void setTopItem_2(String value) {
      this.topItem_2 = value;
   }
 
   /**
    * Gets the topItem_2_Count
    */
   public int getTopItem_2_Count() {
      return this.topItem_2_Count;
   }
 
   /**
    * Sets the topItem_2_Count
    */
   public void setTopItem_2_Count(int value) {
      this.topItem_2_Count = value;
   }
 
   /**
    * Gets the topItem_3
    */
   public String getTopItem_3() {
      return this.topItem_3;
   }
 
   /**
    * Sets the topItem_3
    */
   public void setTopItem_3(String value) {
      this.topItem_3 = value;
   }
 
   /**
    * Gets the topItem_3_Count
    */
   public int getTopItem_3_Count() {
      return this.topItem_3_Count;
   }
 
   /**
    * Sets the topItem_3_Count
    */
   public void setTopItem_3_Count(int value) {
      this.topItem_3_Count = value;
   }
 
   /**
    * Gets the topItem_4
    */
   public String getTopItem_4() {
      return this.topItem_4;
   }
 
   /**
    * Sets the topItem_4
    */
   public void setTopItem_4(String value) {
      this.topItem_4 = value;
   }
 
   /**
    * Gets the topItem_4_Count
    */
   public int getTopItem_4_Count() {
      return this.topItem_4_Count;
   }
 
   /**
    * Sets the topItem_4_Count
    */
   public void setTopItem_4_Count(int value) {
      this.topItem_4_Count = value;
   }
 
   /**
    * Gets the topItem_5
    */
   public String getTopItem_5() {
      return this.topItem_5;
   }
 
   /**
    * Sets the topItem_5
    */
   public void setTopItem_5(String value) {
      this.topItem_5 = value;
   }
 
   /**
    * Gets the topItem_5_Count
    */
   public int getTopItem_5_Count() {
      return this.topItem_5_Count;
   }
 
   /**
    * Sets the topItem_5_Count
    */
   public void setTopItem_5_Count(int value) {
      this.topItem_5_Count = value;
   }
 
   /**
    * Gets the business_name
    */
   public String getBusiness_name() {
      return this.business_name;
   }
 
   /**
    * Sets the business_name
    */
   public void setBusiness_name(String value) {
      this.business_name = value;
   }
}