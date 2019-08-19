package com.ph;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RepoDetailsBean {

	private StringProperty repoName=new SimpleStringProperty();
	private StringProperty repoBranchName=new SimpleStringProperty();
	private StringProperty repoPersonalBranchName=new SimpleStringProperty();
	private SimpleBooleanProperty isSelected=new SimpleBooleanProperty();
	
/*	public RepoDetailsBean(String repoString,String repoBranch,String personalBranch,boolean isSelected) {
		this.repoName=new SimpleStringProperty(repoString);
		this.repoBranchName=new SimpleStringProperty(repoBranch);
		this.repoPersonalBranchName=new SimpleStringProperty(personalBranch);
		this.isSelected=new SimpleBooleanProperty(isSelected);
	}*/
	
	public String getRepoName() {
		return repoName.get();
	}
	public void setRepoName(String repoName) {
		this.repoName.set(repoName);
	}
	public String getRepoBranchName() {
		return repoBranchName.get();
	}
	public void setRepoBranchName(String repoBranchName) {
		this.repoBranchName.set(repoBranchName);
	}
	public String getRepoPersonalBranchName() {
		return repoPersonalBranchName.get();
	}
	public void setRepoPersonalBranchName(String repoPersonalBranchName) {
		this.repoPersonalBranchName.set(repoPersonalBranchName);
	}
	public boolean getIsSelected() {
		return isSelected.get();
	}
	public void setIsSelected(boolean isSelected) {
		this.isSelected.set(isSelected);
	}
	public BooleanProperty getSelected(){
		return isSelected;
	}
}
