public class Users {

        private final String Emp_ID;
        private final String Emp_Name;
        private final String Emp_Username;
        private final String Emp_Password;
        private final String Emp_Position;
   

        
        public Users (String Emp_ID,String Emp_Name, String Emp_Username, String Emp_Password, String Emp_Position ){
            this.Emp_ID = Emp_ID;
            this.Emp_Name = Emp_Name;
            this.Emp_Username = Emp_Username;
            this.Emp_Password = Emp_Password;
            this.Emp_Position = Emp_Position;
        }

        public String getEmp_ID() {
            return Emp_ID;
        }

        public String getEmp_Name() {
            return Emp_Name;
        }

        public String getEmp_Username() {
            return Emp_Username;
        }
        
        public String getEmp_Password() {
            return Emp_Password;
        }

        public String getEmp_Position() {
            return Emp_Position;
        }

}
