package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;


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
		if(so.contains("Windows")) {
			String command = "ipconfig";
			String ip = callProcess(command);
			String vet[] = ip.split(" ");
		
			String result="";
			for (int i = 0; i < vet.length; i++) {
				if(vet[i].equals("Adaptador") || vet[i].equals("WindowsAdaptador")) {
					for (int j = i+1; j < vet.length; j++) {
						if(vet[j].equals("IPv4.")) {
							result += "Adaptador ";
							for (int j2 = i+1; j2 < vet.length; j2++) {
								if(!vet[j2].contains(":")) {
									result+=vet[j2]+" ";
								}else {
									result+=vet[j2].replace(":", "\n");
									break;
								}
							}
						}else if(vet[j].equals("Adaptador")) break;
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
			String vet[] = ip.split(" ");
			String result=vet[0].replace(":", "")+"\n";
			for (int i = 0; i < vet.length; i++) {
				if(vet[i].contains("inet") && !vet[i].contains("inet6")) {
					result+=vet[i+1]+"\n\n";
				}else if(vet[i].contains("collisions")) {
					result+=vet[i+1].substring(1).replace(":", "")+"\n";
				}
			}
			
			return result;
		}
	}
	
	public String ping(String so) {
		if(so.contains("Windows")) {
			String command = "ping -4 -n 10 www.google.com.br";
			String ping = callProcess(command);
			String vet[] = ping.split(" ");
			String result = "Ping: www.google.com.br\nMedia = " + vet[vet.length-1] + "\n";
			
			return result;
		}else {
			String command = "ping -4 -c 10 www.google.com.br";
			String ping = callProcess(command);
			String vet[] = ping.split(" ");
			
			double media=0;
			double count=0;
			for (int i = 0; i < vet.length; i++) {
				if(vet[i].contains("tempo=")) {
					media+=Double.parseDouble(vet[i].substring(6));
					count++;
				}
			}
			media/=count;
			String result = "Ping: www.google.com.br\nMedia = "+media+"ms\n";
			return result;
		}
	}
	
}
