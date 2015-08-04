package com.model;

import java.util.Date;

public class BorrowInfo {

		private int id;//Id和store中的count联系
		private String ISBN;
		private String studentId;
		private Boolean returnFlag;
		private Date borrowDate;
		private Date returnDate;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getISBN() {
			return ISBN;
		}
		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}
		public String getStudentId() {
			return studentId;
		}
		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}
		public Boolean getReturnFlag() {
			return returnFlag;
		}
		public void setReturnFlag(Boolean returnFlag) {
			this.returnFlag = returnFlag;
		}
		public Date getBorrowDate() {
			return borrowDate;
		}
		public void setBorrowDate(Date borrowDate) {
			this.borrowDate = borrowDate;
		}
		public Date getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}
		
}
