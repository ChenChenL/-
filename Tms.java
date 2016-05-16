package com.cookie.c2;
import java.util.*;
public class Tms
{
	//教师数组，保存教师信息
	private Teacher teas[]=new Teacher[3];
	//保存教师人数
	private int index=0;
	public static void main(String[] args){
	    Tms tms=new Tms();
        tms.mune();
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("请输入功能编号：");
		    String option=scanner.nextLine();
		    switch(option){
			    case"1":
					System.out.println("以下是所有教师信息:");
					Teacher[] teas=tms.queryAll();
				    for(Teacher tea:teas){
					    System.out.println(tea);
					}
					System.out.println("总计"+teas.length+"人");
					break;
				case"2":
					while(true){
					    System.out.println("请输入教师信息【id#major#name#salary】或输入break返回上一级目录:");
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
						//判断该用户id是否已被占用
						Teacher dbtea=tms.queryById(id);
						if(dbtea!=null){
						    System.out.println("该id已被占用！");
							continue;
						}
						tms.add(tea);
				    }
					break;
				case"3":
					while(true){
				        System.out.println("请输入你要删除的教师id或输入break返回上一级目录:");
						String id=scanner.nextLine();
						if(id.equals("break")){
						    break;
						}
						tms.deleteById(Long.parseLong(id));
						System.out.println("删除成功！教师个数为"+tms.index);
				    }
					break;
				case"4":
					while(true){
				        System.out.println("请输入你要查询的教师id或输入break返回上一级目录:");
				        String id=scanner.nextLine();
					    if(id.equals("break")){
					        break;
					        }
					Teacher tea=tms.queryById(Long.parseLong(id));
					System.out.println("以下是你要查找的信息:");
					System.out.println(tea!=null?tea:"Not Found!");
				    }
					break;
					case"5":
					while(true){
				        System.out.println("请输入你要修改的教师id或输入break返回上一级目录:");
				        String id=scanner.nextLine();
					    if(id.equals("break")){
					        break;
					        }
					Teacher tea=tms.queryById(Long.parseLong(id));
					if(tea==null){
					    System.out.println("该教师不存在！");
						continue;
					    }
						System.out.println("原来的信息为:"+tea);
						System.out.println("请输入你要修改的教师信息【major#name#salary】");
						String teaStr=scanner.nextLine();
						String[] teaArr=teaStr.split("#");
						String major=teaArr[0];
						String name=teaArr[1];
						int salary=Integer.parseInt(teaArr[2]);
						Teacher newTea=new Teacher(Long.parseLong(id),major,name,salary);
						tms.update(newTea);
						System.out.println("修改成功");
					    }
					break;
				case"help":
					tms.mune();
					break;
				case"exit":
					System.out.println("Bye Bye！");
				    System.exit(0);
				default:
					System.out.println("输入出错，请重新输入!");
			}
		}
	}
	public void mune(){
		System.out.println("***教师信息管理系统***");
		System.out.println("*1，查看所有教师信息*");
		System.out.println("*2，添加教师信息*");
		System.out.println("*3，删除教师信息*");
		System.out.println("*4，查询教师信息*");
		System.out.println("*5，修改教师信息*");
		System.out.println("*help，返回主菜单*");
		System.out.println("*exit，退出*");
		System.out.println("**********************");
	}
	//查看所有教师信息
	public Teacher[] queryAll(){
		Teacher[] demo=new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
	    return demo;
	}
	//添加教师信息
	public void add(Teacher tea){
	    if(index>teas.length){
		    Teacher[] demo=new Teacher[teas.length+3];
			System.arraycopy(teas,0,demo,0,teas.length);
			teas=demo;
		}
		teas[index++]=tea;
	}
	//删除教师信息
	public void deleteById(long id){
	    int teaIndex=queryIndexById(id);
		if(teaIndex!=-1){
		    for(int i=teaIndex;i<index-1;i++){
			    teas[i]=teas[i+1];
			}
			teas[--index]=null;
		}
	}
	//查询教师信息
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