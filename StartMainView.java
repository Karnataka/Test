package com.ph;

import java.util.function.Predicate;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;


public class StartMainView extends Application{
	TableView<RepoDetailsBean> tableView;
	ObservableList<RepoDetailsBean> detailsBeans=FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		TabPane tabPane=new TabPane();
		tabPane.prefHeight(900);
		tabPane.prefWidth(500.0);
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab tab=new Tab("WorkSpace SetUp");

		AnchorPane anchorPane=new AnchorPane();
		anchorPane.minHeight(0);
		anchorPane.minWidth(0);
		anchorPane.prefHeight(180);
		anchorPane.prefWidth(500);
		Pane pane1=new Pane();
		pane1.setLayoutX(18);
		pane1.setLayoutY(11);
		pane1.prefHeight(97);
		pane1.prefWidth(700);
		Label l1=new Label("Select Release");
		l1.setLayoutX(14.0); 
		l1.setLayoutY(25.0); 
		l1.prefHeight(31.0); 
		l1.prefWidth(99.0);
		ComboBox<String> releaseCombo=new ComboBox<String>();
		releaseCombo.setLayoutX(190);
		releaseCombo.setLayoutY(25);
		releaseCombo.prefHeight(40);
		releaseCombo.prefWidth(150);
		ObservableList<String> releaselist=FXCollections.observableArrayList("20190812","20190916","20191014","20191111","20191214");
		releaseCombo.setItems(releaselist);
		releaseCombo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println(releaseCombo.getSelectionModel().getSelectedItem());
				String selectedRelease=releaseCombo.getSelectionModel().getSelectedItem();
				//	ObservableList<RepoDetailsBean> detailsBeans=FXCollections.observableArrayList();
				detailsBeans.removeAll(detailsBeans);
				for (int i = 0; i < 20; i++) {
					RepoDetailsBean bean=new RepoDetailsBean();
					bean.setRepoName("Auto_"+i);
					bean.setRepoBranchName("Auto."+selectedRelease.substring(0, 4)+"."+selectedRelease.substring(4, 6)+".01.00_"+i);
					bean.setRepoPersonalBranchName("phubc_Auto."+selectedRelease.substring(0, 4)+"."+selectedRelease.substring(4, 6)+".01.00_"+i);
					bean.setIsSelected(true);
					detailsBeans.add(bean);
				}
				tableView.setItems(detailsBeans);
			}
		});
		pane1.getChildren().addAll(l1,releaseCombo);

		Pane pane2=new Pane();
		pane2.setLayoutX(18);
		pane2.setLayoutY(108);
		pane2.prefHeight(52);
		pane2.prefWidth(700);
		Label l2=new Label("Apply Filter");
		l2.setLayoutX(14.0); 
		l2.setLayoutY(11); 
		l2.prefHeight(31.0); 
		l2.prefWidth(99.0);
		ObservableList<String> filList=FXCollections.observableArrayList("No filter defined");
		ComboBox<String> filterCombo=new ComboBox<String>();
		filterCombo.setItems(filList);
		filterCombo.setLayoutX(190);
		filterCombo.setLayoutY(6);
		filterCombo.prefHeight(40);
		filterCombo.prefWidth(150);
		TextField filterText=new TextField();
		filterText.setLayoutX(440);
		filterText.setLayoutY(6);
		filterText.prefHeight(50);
		filterText.prefWidth(300);
		filterText.setPromptText("Enter RepoName To Filter..");
		FilteredList<RepoDetailsBean> filteredList=new FilteredList<RepoDetailsBean>(detailsBeans, e-> true);
		filterText.setOnKeyReleased(e->{
			filterText.textProperty().addListener( (ovalue,oldvalue,newvalue)->{
				filteredList.setPredicate((Predicate<? super RepoDetailsBean>) repo-> {
				if(newvalue==null ||newvalue.isEmpty()){
					return true;
				}
				if(repo.getRepoName().toLowerCase().contains(newvalue.toLowerCase())){
					return true;
				}else if(repo.getRepoBranchName().toLowerCase().contains(newvalue.toLowerCase())){
					return true;
				}
				return false;
				});
			});
			tableView.setItems(null);
			tableView.setItems(filteredList);
		});
		pane2.getChildren().addAll(l2,filterCombo,filterText);

		Pane pane3=new Pane();
		pane3.setLayoutX(18.0); 
		pane3.setLayoutY(160.0); 
		pane3.prefHeight(100.0); 
		pane3.prefWidth(685.0);
		pane3.getChildren().add(getTableView());
		Pane pane4=new Pane();
		pane4.setLayoutX(30.0); 
		pane4.setLayoutY(450.0); 
		pane4.prefHeight(62.0);
		pane4.prefWidth(671.0);
		Button startImportClone=new Button("Lets Start");
		startImportClone.setLayoutX(520.0);
		startImportClone.setLayoutY(3.0);
		startImportClone.setMnemonicParsing(false); 
		startImportClone.prefHeight(60.0); 
		startImportClone.prefWidth(203.0);
		pane4.getChildren().add(startImportClone);
		anchorPane.getChildren().addAll(pane1,pane2,pane3,pane4);
		tab.setContent(anchorPane);
		tabPane.getTabs().add(tab);
		Background  bg=new Background(new BackgroundFill(Paint.valueOf("lightgreen"), null, null));
		tabPane.setBackground(bg);
		Scene s = new Scene(tabPane);
		primaryStage.setScene(s);
		primaryStage.show();   
	}
	public TableView<RepoDetailsBean> getTableView(){
		tableView=new TableView<RepoDetailsBean>();
		tableView.setLayoutX(7.0);
		tableView.setLayoutY(9.0);
		//  tableView.prefHeight(50.0);
		//  tableView.prefWidth(600.0);
		tableView.setPrefSize(590, 270);
		TableColumn<RepoDetailsBean,Boolean> importColumn=new TableColumn<RepoDetailsBean, Boolean>();
		importColumn.setPrefWidth(75);
		importColumn.setText("import");
		//CheckBox importCheck=new CheckBox();

		//importColumn.setGraphic(importCheck);
		importColumn.setCellValueFactory(new PropertyValueFactory<RepoDetailsBean, Boolean>("isSelected"));
		importColumn.setCellFactory(new Callback<TableColumn<RepoDetailsBean, Boolean>, TableCell<RepoDetailsBean, Boolean>>() {
			public TableCell<RepoDetailsBean, Boolean> call(TableColumn<RepoDetailsBean, Boolean> p) {
				final TableCell<RepoDetailsBean, Boolean> cell = new TableCell<RepoDetailsBean, Boolean>() {
					@Override
					public void updateItem(final Boolean item, boolean empty) {
						if (item == null)
							return;
						super.updateItem(item, empty);
						if (!isEmpty()) {
							final RepoDetailsBean repo = getTableView().getItems().get(getIndex());
							CheckBox checkBox = new CheckBox();
							checkBox.selectedProperty().bindBidirectional(repo.getSelected());
							// checkBox.setOnAction(event);
							setGraphic(checkBox);
						}
					}
				};
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		//"Repo" column
		TableColumn<RepoDetailsBean,String> repoColumn=new TableColumn<RepoDetailsBean, String>();
		repoColumn.setPrefWidth(90);
		repoColumn.setText("Repo Name");
		repoColumn.setEditable(true);
		repoColumn.setCellFactory(TextFieldTableCell.<RepoDetailsBean>forTableColumn());
		repoColumn.setCellValueFactory(new PropertyValueFactory<RepoDetailsBean, String>("repoName"));
		//"Release Branch Name" column
		TableColumn<RepoDetailsBean, String> releaseBranchColumn=new TableColumn<RepoDetailsBean, String>();
		releaseBranchColumn.setPrefWidth(198);
		releaseBranchColumn.setText("Release Branch Name");
		releaseBranchColumn.setEditable(true);
		releaseBranchColumn.setCellFactory(TextFieldTableCell.<RepoDetailsBean>forTableColumn());
		releaseBranchColumn.setCellValueFactory(new PropertyValueFactory<RepoDetailsBean, String>("repoBranchName"));

		TableColumn<RepoDetailsBean, String> personalBranchColumn=new TableColumn<RepoDetailsBean, String>();
		personalBranchColumn.setPrefWidth(230);
		personalBranchColumn.setText("Personal Branch Name");
		personalBranchColumn.setEditable(true);
		personalBranchColumn.setCellFactory(TextFieldTableCell.<RepoDetailsBean>forTableColumn());
		personalBranchColumn.setCellValueFactory(new PropertyValueFactory<RepoDetailsBean, String>("repoPersonalBranchName"));

		tableView.getColumns().addAll(importColumn,repoColumn,releaseBranchColumn,personalBranchColumn);
		//ObservableList<RepoDetailsBean> detailsBeans=FXCollections.observableArrayList();
		detailsBeans.removeAll(detailsBeans);
		for (int i = 0; i < 10; i++) {
			RepoDetailsBean bean=new RepoDetailsBean();
			bean.setRepoName("Auto_"+i);
			bean.setRepoBranchName("Auto.2019.09.00_"+i);
			bean.setRepoPersonalBranchName("phubc_Auto.2019.09.00_"+i);
			bean.setIsSelected(true);
			detailsBeans.add(bean);
		}

		tableView.setItems(detailsBeans);
		tableView.setEditable(true);
		return tableView;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
