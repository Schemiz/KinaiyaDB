public class REGIST_USERS{

        private final String Emp_Username;
        private final String Emp_Password;
   

        
        public REGIST_USERS (String Emp_Username, String Emp_Password){

            this.Emp_Username = Emp_Username;
            this.Emp_Password = Emp_Password;
          
        }


        public String getEmp_Username() {
            return Emp_Username;
        }
        
        public String getEmp_Password() {
            return Emp_Password;
        }

      
}
