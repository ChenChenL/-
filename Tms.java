package com.cookie.c2;
import java.util.*;
public class Tms
{
	//��ʦ���飬�����ʦ��Ϣ
	private Teacher teas[]=new Teacher[3];
	//�����ʦ����
	private int index=0;
	public static void main(String[] args){
	    Tms tms=new Tms();
        tms.mune();
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("�����빦�ܱ�ţ�");
		    String option=scanner.nextLine();
		    switch(option){
			    case"1":
					System.out.println("���������н�ʦ��Ϣ:");
					Teacher[] teas=tms.queryAll();
				    for(Teacher tea:teas){
					    System.out.println(tea);
					}
					System.out.println("�ܼ�"+teas.length+"��");
					break;
				case"2":
					while(true){
					    System.out.println("�������ʦ��Ϣ��id#major#name#salary��������break������һ��Ŀ¼:");
						String teaStr=scanner.nextLine();
						if(teaStr.equals("break")){
						    break;
						}
						String[] teaArr=teaStr.split("#");
						long id=Long.parseLong(teaArr[0]);
						String major=teaArr[1];
						String name=teaArr[2];
						int salary=Integer.parseInt(teaArr[3]);
						Teacher tea=new Teacher(id,major,name,salary);
						//�жϸ��û�id�Ƿ��ѱ�ռ��
						Teacher dbtea=tms.queryById(id);
						if(dbtea!=null){
						    System.out.println("��id�ѱ�ռ�ã�");
							continue;
						}
						tms.add(tea);
				    }
					break;
				case"3":
					while(true){
				        System.out.println("��������Ҫɾ���Ľ�ʦid������break������һ��Ŀ¼:");
						String id=scanner.nextLine();
						if(id.equals("break")){
						    break;
						}
						tms.deleteById(Long.parseLong(id));
						System.out.println("ɾ���ɹ�����ʦ����Ϊ"+tms.index);
				    }
					break;
				case"4":
					while(true){
				        System.out.println("��������Ҫ��ѯ�Ľ�ʦid������break������һ��Ŀ¼:");
				        String id=scanner.nextLine();
					    if(id.equals("break")){
					        break;
					        }
					Teacher tea=tms.queryById(Long.parseLong(id));
					System.out.println("��������Ҫ���ҵ���Ϣ:");
					System.out.println(tea!=null?tea:"Not Found!");
				    }
					break;
					case"5":
					while(true){
				        System.out.println("��������Ҫ�޸ĵĽ�ʦid������break������һ��Ŀ¼:");
				        String id=scanner.nextLine();
					    if(id.equals("break")){
					        break;
					        }
					Teacher tea=tms.queryById(Long.parseLong(id));
					if(tea==null){
					    System.out.println("�ý�ʦ�����ڣ�");
						continue;
					    }
						System.out.println("ԭ������ϢΪ:"+tea);
						System.out.println("��������Ҫ�޸ĵĽ�ʦ��Ϣ��major#name#salary��");
						String teaStr=scanner.nextLine();
						String[] teaArr=teaStr.split("#");
						String major=teaArr[0];
						String name=teaArr[1];
						int salary=Integer.parseInt(teaArr[2]);
						Teacher newTea=new Teacher(Long.parseLong(id),major,name,salary);
						tms.update(newTea);
						System.out.println("�޸ĳɹ�");
					    }
					break;
				case"help":
					tms.mune();
					break;
				case"exit":
					System.out.println("Bye Bye��");
				    System.exit(0);
				default:
					System.out.println("�����������������!");
			}
		}
	}
	public void mune(){
		System.out.println("***��ʦ��Ϣ����ϵͳ***");
		System.out.println("*1���鿴���н�ʦ��Ϣ*");
		System.out.println("*2����ӽ�ʦ��Ϣ*");
		System.out.println("*3��ɾ����ʦ��Ϣ*");
		System.out.println("*4����ѯ��ʦ��Ϣ*");
		System.out.println("*5���޸Ľ�ʦ��Ϣ*");
		System.out.println("*help���������˵�*");
		System.out.println("*exit���˳�*");
		System.out.println("**********************");
	}
	//�鿴���н�ʦ��Ϣ
	public Teacher[] queryAll(){
		Teacher[] demo=new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
	    return demo;
	}
	//��ӽ�ʦ��Ϣ
	public void add(Teacher tea){
	    if(index>teas.length){
		    Teacher[] demo=new Teacher[teas.length+3];
			System.arraycopy(teas,0,demo,0,teas.length);
			teas=demo;
		}
		teas[index++]=tea;
	}
	//ɾ����ʦ��Ϣ
	public void deleteById(long id){
	    int teaIndex=queryIndexById(id);
		if(teaIndex!=-1){
		    for(int i=teaIndex;i<index-1;i++){
			    teas[i]=teas[i+1];
			}
			teas[--index]=null;
		}
	}
	//��ѯ��ʦ��Ϣ
	public Teacher queryById(long id){
		int teaIndex=queryIndexById(id);
	    return teaIndex==-1?null:teas[teaIndex];
	}
	public int queryIndexById(long id){
	    int teaIndex=-1;
		for(int i=0;i<index;i++){
		    if(teas[i].getId()==id){
			    teaIndex=i;
			}
		}
		return teaIndex;
	}
	public void update(Teacher tea){
	    for(int i=0;i<index;i++){
		    if(tea.getId()==teas[i].getId()){
			    teas[i].setMajor(tea.getMajor());
				teas[i].setName(tea.getName());
				teas[i].setSalary(tea.getSalary());
			}
		}
	}
}