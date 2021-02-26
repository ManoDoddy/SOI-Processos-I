package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RedesController {
	
	public String so() {
		return System.getProperty("os.name");
	}
	
	public String callProcess(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			InputStream fluxo = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			String result = "";
			
			while(linha!=null) {
				result+=linha;
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String ip(String so) {
		if(so.equals("Windows 10")) {
			String command = "ipconfig";
			String ip = callProcess(command);
			System.out.println(ip);
			String vet[] = ip.split(" ");
			
			String result="";
			for (int i = 0; i < vet.length; i++) {
				if(vet[i].equals("Adaptador") || vet[i].equals("WindowsAdaptador")) {
					result+="Adaptador ";
					for (int j = i+1; j < vet.length; j++) {
						if(!vet[j].contains(":")) {
							result+=vet[j]+" ";
						}else {
							result+=vet[j].replace(":", "\n");
							break;
						}
					}
				}else if(vet[i].equals("IPv4.")) {
					for (int j = i+1; j < vet.length; j++) {
						if(vet[j].length()>1) {
							result+= "IPv4: "+vet[j]+"\n\n";
							break;
						}
					}
				}
			}
			return result;
			
		}else {
			String command = "ifconfig";
			String ip = callProcess(command);
			
			return null;
		}
	}
	
	public String ping(String so) {
		if(so.equals("Windows 10")) {
			String command = "ping -4 -n 10 www.google.com.br";
			String ping = callProcess(command);
			String vet[] = ping.split(" ");
			
			String result="";
			for (int i = 0; i < vet.length; i++) {
				if(vet[i].equals("M‚dia")) {
					result = "Ping: www.google.com.br\nMédia = " + vet[i+2] + "\n";
				}
			}
			
			return result;
		}else {
			String command = "ping -4 -c 10 www.google.com.br";
			String ping = callProcess(command);
			
			return null;
		}
	}
	
}
