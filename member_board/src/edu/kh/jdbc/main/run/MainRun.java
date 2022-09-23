package edu.kh.jdbc.main.run;

import edu.kh.jdbc.main.view.MainView;

public class MainRun {
	public static void main(String[] args) {
		new MainView().mainMenu();
		
		//System.exit(0); //System 종료 //JVM을 종료시킨다 //내부적으로 존재(컴파일러가 자동 추가)
	}
}
