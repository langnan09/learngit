package information;

import java.io.Serializable;

public class account implements Serializable{
		   private String username; 
		   private String sex;
		   private String  [] friend;
		   private String friends;
		   private String Ip;
		   //private int port;去掉这个方法了 
		   private String id;
		   private String password;
		   private String birthday;
		   private String question;
		   private String answer;
		   private int status;//1为在线，0为不在线
		   public void setfriends(String friends) {  
		        this.friends=friends;
		   } 
		   public String getfriends() {  
		       return friends;  
		   } 
		   public String getid() {  
		       return id;  
		   }  
		   public void setid(String id) {  
		       this.id = id;  
		   }  
		   public void setpassword(String password){
			   this.password=password;
		   }
		   public String getpassword() {
			return password;
		   }
		    public String getbirthday() {  
		        return birthday;  
		    }  
		    public void setbirthday(String birthday) {  
		        this.birthday = birthday;  
		    }  
		   public String getSex() {  
		       return sex;  
		   }  
		   public void setSex(String sex) {  
		       this.sex = sex;  
		   }  
		   public String getusername() {  
		       return username;  
		   }  
		   public void setusername(String username) {  
		       this.username = username;  
		   }  
		   public String [] getfriend() {  
		        return friend;  
		    }  
		    public void setfriend(String [] friend) {  
		        this.friend = friend;  
		    }  
		    public void setquestion(String question) {  
		        this.question = question;  
		    }  
		    public String getquestion() {  
		        return question;  
		    }
		    public void setanswer(String answer) {  
		        this.answer = answer;  
		    }  
		    public String getanswer() {  
		        return answer;  
		    }public void setIp(String Ip) {  
		        this.Ip = Ip;  
		    }  
		    public String getIp() {  
		        return Ip;  
		    }
		    public void setstatus(int status){
		    	this.status=status;
		    	
		    }
		    public int getstatus(){
		    	return status;
		    }
		    public account (String id)
			{
				setid(id);
			
			}
			public account (String id,String password,String friends)
			{	
				setfriends(friends);
				setid(id);
				setpassword(password);
				
			}
			public account (String id,String password,String username,String sex,String birthday,String question,String answer)
			{
				setid(id);
				setpassword(password);
				setusername(username);
				setSex(sex);
				setbirthday(birthday);
				setquestion(question);
				setanswer(answer);
			}
			public account (String [] friend)
			{   setfriend(friend);
				
			}
			public account (String id,String answer,String password,String question)
			{   setquestion(question);
				setid(id);
				setanswer(answer);
				setpassword(password);
			}
			
}