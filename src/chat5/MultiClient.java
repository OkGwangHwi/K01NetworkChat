package chat5;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStreamReader;

public class MultiClient {

	public static void main(String[] args) {

		System.out.print("이름을 입력하세요:");
		Scanner scanner = new Scanner(System.in);
		String s_name = scanner.nextLine();
		
		//Sender가 기능을 가져가므로 여기서는 필요없음
//		PrintWriter out = null;
		//Receiver가 기능을 가져가므로 여기서는 필요없음
//		BufferedReader in = null;
		
		try {
			//localhost대신 127.0.0.1로 접속해도 무관하다.
			//별도의 매개변수가 없으면 접속IP는 localhost로 고정됨
			String ServerIP = "localhost";
			//클라이언트 실행시 매개변수가 있는경우 아이피로 설정함
			if(args.length > 0) {
				ServerIP = args[0];
			}
			//IP주소와 포트를 기반으로 소켓객체를 생성하여 서버에 접속함
			Socket socket = new Socket(ServerIP,9999);
			//서버와 연결되면 콘솔에 메세지 출력
			System.out.println("서버와 연결되었습니다.");
			
			//서버에서 보내는 Echo메세지를 클라이언트에 출력하기 위한 쓰레드 생성
			Thread receiver = new Receiver(socket);
			receiver.start();
			
			//클라이언트의 메세지를 서버로 전송해주는 쓰레드 생성
			Thread sender = new Sender(socket,s_name);
			sender.start();
		}
		catch(Exception e) {
			System.out.println("예외발생[MultiClient]"+e);
		}
		
	}

}
